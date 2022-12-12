package com.filipswiszcz.instance;

import java.time.Duration;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public abstract class Instance {

    private boolean registered;

    //private final DimensionType dimensionType;
    //private final WorldBorder border;

    private long age;

    private long time;
    private int rate = 1;
    private Duration update = Duration.ofSeconds(1);
    private long lastUpdate;

    private long lastTickAge = System.currentTimeMillis();

    //private EntityTracker tracker = new iEntityTrakcer();
    
    //private ChunkCache blockRetiever = new ChunkCache(this, null, null);

    protected UUID uuid;

    //private final TagHandler tag = TagHandler.newHandler();
    //private final Scheduler scheduler = Scheduler.newScheduler();
    //private final EventNode<InstanceEvent> event;

    //private ExplosionSupplier explosionSupplier;

    //private InstancePathFinder pathFinder = new InstancePathFinder(this);

    //private final Pointers pointers;

    /**
     * Creates a new instance.
     * 
     * @param uuid          the {@link UUID} of the instance
     * @param dimensionType the {@link DimensionType} of the instance
     */
    public Instance(final UUID uuid) {
        this.uuid = uuid;
    }
    
}
