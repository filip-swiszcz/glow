package com.filipswiszcz.world;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

import com.filipswiszcz.Viewable;
import com.filipswiszcz.entity.Entity;
import com.filipswiszcz.navigation.Point;
import com.filipswiszcz.navigation.Vector;

import space.vectrix.flare.fastutil.Int2ObjectSyncMap;
import space.vectrix.flare.fastutil.Long2ObjectSyncMap;

import static com.filipswiszcz.world.Chunk.chunkSizeX;
import static com.filipswiszcz.world.Chunk.chunkSizeZ;

final class TrackerImpl implements Tracker {

    static final AtomicInteger counter = new AtomicInteger();

    final Entry<Entity>[] entries = Tracker.Target.targets.stream()
        .map((Function<Target<?>, Entry>) Entry::new).toArray(Entry[]::new);
    private final Int2ObjectSyncMap<Point> entityPositions = Int2ObjectSyncMap.hashmap();

    @Override
    public <T extends Entity> void register(Entity entity, Point point, Target<T> target, Update<T> update) {
        final Point previousPoint = entityPositions.putIfAbsent(entity.getId(), point);
        if(previousPoint != null) return;
        // need to add more functions about chunk
    }

    @Override
    public <T extends Entity> void unregister(Entity entity, Target<T> target, Update<T> update) {
        final Point point = entityPositions.remove(entity.getId());
    }

    @Override
    public <T extends Entity> void move(Entity entity, Point point, Target<T> target, Update<T> update) {
        Point previousPoint = entityPositions.put(entity.getId(), point);
        // need to add more functions about chunk
    }
    // add functions here

    static final class Entry<T extends Entity> {

        private final Tracker.Target<T> target;
        private final Set<T> entities = ConcurrentHashMap.newKeySet();
        private final Set<T> entitiesView = Collections.unmodifiableSet(entities);
        final Long2ObjectSyncMap<List<T>> entitiesChunk = Long2ObjectSyncMap.hashmap();

        Entry(Target<T> target) {
            this.target = target;
        }

        List<T> entitiesChunk(long id) {
            return entitiesChunk.computeIfAbsent(id, i -> (List<T>) new CopyOnWriteArrayList());
        }

        void addToChunk(long id, T entity) {
            this.entitiesChunk(id).add(entity);
        }

        void removeFromChunk(long id, T entity) {
            List<T> entities = this.entitiesChunk.get(id);
            if (entities != null) entities.remove(entity);
        }

    }

    private final class ChunkView implements Viewable {

        private ChunkViewKey key;
        private final int chunkX, chunkZ;
        private final Point point;
        // set with players
        private int lastReferenceCount;

        private ChunkView(ChunkViewKey key) {
            this.key = key;
            this.chunkX = key.chunkX;
            this.chunkZ = key.chunkZ;
            this.point = new Vector(chunkSizeX * chunkX, 0, chunkSizeZ * chunkZ);
        }

        // add more functions

    }

    record ChunkViewKey(List<SharedWorld> sharedWords, int chunkX, int chunkZ) {

        @Override
        public boolean equals(Object object) {
            if (this == object) return true;
            if (!(object instanceof ChunkViewKey key)) return false;
            return sharedWords == key.sharedWords && 
                chunkX == key.chunkX && chunkZ == key.chunkZ;
        }

    }
    
}
