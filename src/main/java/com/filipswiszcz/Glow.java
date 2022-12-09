package com.filipswiszcz;

import java.util.logging.Logger;

import com.filipswiszcz.world.Difficulty;

public final class Glow {

    private final Logger logger = Logger.getLogger("Glow");

    private final String version = "1.0-SNAPSHOT";
    private final Difficulty difficulty = Difficulty.NORMAL;

    public Glow() {

    }

    public Logger getLogger() {
        return logger;
    }

    public String getVersion() {
        return version;
    }

    public Difficulty geDifficulty() {
        return difficulty;
    }

}
