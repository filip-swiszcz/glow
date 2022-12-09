package com.filipswiszcz.world;

public enum Difficulty {

    EASY((byte) 0), NORMAL((byte) 1), HARD((byte) 2);

    private final byte id;

    Difficulty(final byte id) {
        this.id = id;
    }

    public byte getId() {
        return id;
    }
    
}
