package com.filipswiszcz.entity;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import com.filipswiszcz.Viewable;
import com.filipswiszcz.navigation.Position;
import com.filipswiszcz.navigation.Vector;
import com.filipswiszcz.world.Chunk;
import com.filipswiszcz.world.World;

/**
 * A class that can be an object, a player, an animal or a monster.
 * <p>
 * In case of creating your own living entity, extend {@link Human} or {@link Creature}.
 */
public class Entity implements Viewable {

    private static final int velocityUpdateInterval = 1;

    //private static final Int2ObjectSyncMap<Entity> entityById = Int2ObjectSyncMap.hashmap();
    private static final Map<UUID, Entity> entityByUUID = new ConcurrentHashMap<>();
    private static final AtomicInteger lastEntityId = new AtomicInteger();

    //private final CachedPacket destroyPacketCache = new CachedPacket(() -> new DestroyEntitiesPacket(getEntityId()));

    protected World world;
    protected Chunk chunk;
    protected Position position;
    protected Position previouPosition;
    protected Position lastSyncedPosition;
    protected boolean ground;

    //private BoundingBox boundingBox;
    //private PhysicsResult lastPhysicsResult = null;

    protected Entity transport;

    protected Vector velocity = Vector.zero;
    protected boolean lastVelocityWasZero = true;
    protected boolean physics = true;

    protected double gravityDragPerTick;
    protected double gravityAcceleration;
    protected int gravityTickCount;

    private final int id;

    // more to add

    public Entity(final UUID uuid) {
        this.id = generateId();
    }

    public static int generateId() {
        return lastEntityId.incrementAndGet();
    }

    public int getId(){
        return id;
    }
    
}
