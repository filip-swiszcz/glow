package com.filipswiszcz.world;

import java.util.UUID;

/**
 * A chunk is a part of an {@link World}, limited by the size of 16x320x16 and subdivided in 16 sections of 16 blocks height.
 * Should contain all the blocks located at those positions and manage their tick updates also.
 * <p>
 * It is a good practice to avoid storing references of this object, because it could lead to a huge memory leak.
 * Store the chunk coordinates instead.
 */
public abstract class Chunk {

    public static final int chunkSizeX = 16;
    public static final int chunkSizeZ = 16;
    public static final int chunkSectionSize = 16;

    private final UUID uuid;

    protected World world;
    protected final int x, z;
    //protected final int min, max;

    private final boolean generate;
    private boolean readOnly;

    protected volatile boolean loaded = true;
    //private final ChunkView viewers;

    //protected PathfinderColumn...

    //private final TagHandler handler = TagHandler.newHandler();

    public Chunk(final World world, final int x, final int z, final boolean generate) {
        this.uuid = UUID.randomUUID();
        this.world = world;
        this.x = x;
        this.z = z;
        //this.min = 
        //this.max = 
        this.generate = generate;
    }

    //public abstract void setBlock(final int x, final int y, final int z, final Block block);

    /**
     * Executes a chunk tick.
     * <p>
     * Should be used to update all the blocks in the chunk.
     * <p>
     * WARNING: this method doesn't necessary have to be thread-safe, use with caution.
     * 
     * @param time of the update in miliseconds
     */
    public abstract void tick(final long time);
    
}
