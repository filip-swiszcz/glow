package com.filipswiszcz.world;

import java.time.Duration;
import java.util.UUID;

/**
 * A class that represents world from Minecraft, you can add an entity in it by using {@link Entity#setWorld(World)}.
 * <p>
 * A world has entities and chunks, each world contains it's own entity list, but the chunk
 * implementation has to be defined, see {@link WorldContainer}.
 * <p>
 * WARNING: When making your own implementation, registering the instance manually is required
 * with {@link WorldManager#registerWorld(World)} and you need also to signal the {@link ThreadDispatcher} of every partition/element change.
 */
public abstract class World {
    
    private boolean registered;

    //private final DimensionType dimensionType dimensionType;
    //private final WorldBorder border;

    private long age;

    private long time;
    private int rate = 1;
    private Duration update = Duration.ofSeconds(1);
    private long lastUpdate;

    private long lastTickAge = System.currentTimeMillis();

    //private EntityTracker tracker = new iEntityTracker();

    //private ChunkCache blockRetiever = new ChunkCache(this, null, null);

    protected UUID uuid;

    //private final TagHandler tag = TagHandler.newHandler();
    //private final Scheduler scheduler = Scheduler.newScheduler();
    //private final EventNode<InstanceEvent> event;

    //private ExplosionSupplier explosionSupplier;

    //private InstancePathFinder pathFinder = new InstancePathFinder(this);

    //private final Pointers pointers;

    /**
     * Creates a new world.
     * 
     * @param uuid          the {@link UUID} of the world
     * @param dimensionType the {@link DimensionType} of the world
     */
    public World(final UUID uuid) {
        this.uuid = uuid;
    }

}
