package com.filipswiszcz.entity;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import com.filipswiszcz.instance.Instance;

/**
 * Implement Viewable, Tickable, Schedulable, Snapshotable, EventHandler<EntityEvent>, Taggable, 
 * PermissionHandler, HoverEventSource<ShowEntity>, Sound.Emitter
 */
public class Entity {

    private static final int velocityUpdateInterval = 1;

    //private static final Int2ObjectSyncMap<Entity> entityById = Int2ObjectSyncMap.hashmap();
    private static final Map<UUID, Entity> entityByUUID = new ConcurrentHashMap<>();
    private static final AtomicInteger lastEntityId = new AtomicInteger();

    //private final CachedPacket destroyPacketCache = new CachedPacket(() -> new DestroyEntitiesPacket(getEntityId()));

    protected Instance instance;

    private final int id;

    public Entity(final UUID uuid) {
        this.id = generateId();
    }

    public static int generateId() {
        return lastEntityId.incrementAndGet();
    }
    
}
