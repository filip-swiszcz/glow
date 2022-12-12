package com.filipswiszcz.navigation;

/**
 * 
 * @param x
 * @param y
 * @param z
 */
public record Position(double x, double y, double z, float yaw, float pitch) implements Point {

    public static final Position ZERO = new Position(0, 0, 0);

    public Position(double x, double y, double z) {
        this(x, y, z, 0, 0);
    }

    public Position(Point point, float yaw, float pitch) {
        this(point.getX(), point.getY(), point.getZ(), yaw, pitch);
    }

    public Position(Point point) {
        this(point, 0, 0);
    }
    
}
