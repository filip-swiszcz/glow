package com.filipswiszcz.entity;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import com.filipswiszcz.world.World;

/**
 * A class that can be an object, a player, an animal or a monster.
 * <p>
 * In case of creating your own living entity, extend {@link Human} or {@link Creature}.
 */
public class Entity {

    private static final int velocityUpdateInterval = 1;

    //private static final Int2ObjectSyncMap<Entity> entityById = Int2ObjectSyncMap.hashmap();
    private static final Map<UUID, Entity> entityByUUID = new ConcurrentHashMap<>();
    private static final AtomicInteger lastEntityId = new AtomicInteger();

    //private final CachedPacket destroyPacketCache = new CachedPacket(() -> new DestroyEntitiesPacket(getEntityId()));

    protected World world;

    private final int id;

    public Entity(final UUID uuid) {
        this.id = generateId();
    }

    public static int generateId() {
        return lastEntityId.incrementAndGet();
    }
    
}
