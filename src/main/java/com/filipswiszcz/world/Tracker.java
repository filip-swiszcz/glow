package com.filipswiszcz.world;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

import com.filipswiszcz.Viewable;
import com.filipswiszcz.entity.Entity;
import com.filipswiszcz.navigation.Point;

/**
 * Defines {@link Entity entities} are tracked within an {@link World world}.
 * <p>
 * Implemetations are expected to be thread-safe.
 */
public sealed interface Tracker permits TrackerImpl {

    static Tracker newTracker() {
        return new TrackerImpl();
    }

    /**
     * Register an entity to be tracked.
     */
    <T extends Entity> void register(Entity entity, Point point, Target<T> target, Update<T> update);

    /**
     * Unregister an entity tracked.
     */
    <T extends Entity> void unregister(Entity entity, Target<T> target, Update<T> update);

    /**
     * Called every time an entity move, you may want to verify if the new
     * position is in a different chunk.
     */
    <T extends Entity> void move(Entity entity, Point point, Target<T> target, Update<T> update);

    <T extends Entity> Collection<T> chunkEntities(int chunkX, int chunkZ, Target<T> target);

    default <T extends Entity> Collection<T> chunkEntities(Point point, Target<T> target) {
        return chunkEntities(point.getChunkX(), point.getChunkZ(), target);
    }

    /**
     * Gets the entities within a chunk range.
     */
    <T extends Entity> void nearbyEntitiesByChunkRange(Point point, int chunkRange, Target<T> target, Consumer<T> query);

    /**
     * Gets the entities within a range.
     */
    <T extends Entity> void nearbyEntities(Point point, double range, Target<T> target, Consumer<T> query);

    /**
     * Gets all the entities tracked by this class.
     */
    <T extends Entity> Set<T> entities(Target<T> target);

    default Set<Entity> entities() {
        return entities(Target.enitites);
    }

    Viewable viewable(List<SharedWorld> sharedWorlds, int chunkX, int chunkZ);

    default Viewable viewable(int chunkX, int chunkZ) {
        return viewable(List.of(), chunkX, chunkZ);
    }

    /**
     * Represents the type of entity you want to retrieve.
     * 
     * @param <E> the entity type
     */
    interface Target<E extends Entity> {

        Target<Entity> enitites = create(Entity.class);
        // more to add (etc. players)

        List<Tracker.Target<? extends Entity>> targets = List.of(Tracker.Target.enitites);
        // when added above, add here also

        Class<E> getType();

        int getId();

        private static <T extends Entity> Tracker.Target<T> create(Class<T> type) {

            final int id = TrackerImpl.counter.getAndIncrement();

            return new Target<T>() {

                @Override
                public Class<T> getType() {
                    return type;
                }

                @Override
                public int getId() {
                    return id;
                }
                
            };

        }

    }

    /**
     * Callback to know the newly visible entities and those to remove.
     */
    interface Update<E extends Entity> {

        void add(Entity entity);

        void remove(Entity entity);

        default void referenceUpdate(Point point, Tracker tracker) {
            // empty
        }

    }
    
}
