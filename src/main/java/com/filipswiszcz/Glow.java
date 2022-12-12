package com.filipswiszcz;

import java.util.logging.Logger;

import com.filipswiszcz.world.Difficulty;

/**
 * Ideas:
 * - player can ask glow a question on chat, which will be answered
 * - npcs dialogues will be shown near them
 * - server responses will be shown above of player's inventory bar
 */
public final class Glow {

    private static final Logger logger = Logger.getLogger("Glow");

    private static final String version = "1.0-SNAPSHOT";
    private static final int ticks = 1000 / 20;
    private static final int chunkViewDistance = 8;
    private static final int entityViewDistance = 5;
    private static final Difficulty difficulty = Difficulty.NORMAL;

    public Glow() {

    }

    public static Logger getLogger() {
        return logger;
    }

    public static String getVersion() {
        return version;
    }

    public static int getTicks() {
        return ticks;
    }

    public static int getChunkViewDistance() {
        return chunkViewDistance;
    }

    public static int getEntityViewDistance() {
        return entityViewDistance;
    }

    public static Difficulty geDifficulty() {
        return difficulty;
    }

}
