package com.filipswiszcz.utils;

import com.filipswiszcz.navigation.Point;
import com.filipswiszcz.world.Chunk;

public final class ChunkUtils {

    public static int getChunkCoordinate(double xz) {
        return getChunkCoordinate((int) Math.floor(xz));
    }

    public static int getChunkCoordinate(int xz) {
        return xz >> 4;
    }

    /**
     * Gets the chunk id of chunk coordinates.
     * <p>
     * Used when you want to store a chunk somewhere without using a reference
     *  to the whole object. (as this leads to memory leaks)
     * 
     * @param chunkX the chunk X
     * @param chunkZ the chunk Z
     * @return a number storing chunk X and chunk Z
     */
    public static long getChunkId(int chunkX, int chunkZ) {
        return (((long) chunkX) << 32 | (chunkZ & 0xffffffffL));
    }

    public static long getChunkId(Chunk chunk) {
        return getChunkId(chunk.getX(), chunk.getZ());
    }

    public static long getChunkId(Point point) {
        return getChunkId(point.getChunkX(), point.getChunkZ());
    }
    
}
