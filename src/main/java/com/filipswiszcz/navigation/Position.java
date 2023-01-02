package com.filipswiszcz.navigation;

/**
 * Represents a position containing coordinates and a view.
 */
public record Position(double x, double y, double z, float yaw, float pitch) implements Point {

    public static final Position zero = new Position(0, 0, 0);

    public Position {
        fixYaw(yaw);
    }

    public Position(double x, double y, double z) {
        this(x, y, z, 0, 0);
    }

    public Position(Point point, float yaw, float pitch) {
        this(point.getX(), point.getY(), point.getZ(), yaw, pitch);
    }

    public Position(Point point) {
        this(point, 0, 0);
    }

    /**
     * Converts a {@link Point} into a {@link Position}.
     * Will cast if possible, otherwise creates a new object.
     * 
     * @param point the point to convert
     * @return the converted position
     */
    public static Position fromPoint(Point point) {
        if (point instanceof Position position) return position;
        return new Position(point.getX(), point.getY(), point.getZ());
    }

    /**
     * Changes coordinates of the {@link Position}.
     * 
     * @param x the X coordinate
     * @param y the Y coordinate
     * @param z the Z coordinate
     * @return a new position
     */
    public Position withCoordinations(double x, double y, double z) {
        return new Position(x, y, z, this.yaw, this.pitch);
    }

    /**
     * Changes coordinates of the {@link Position} by using a {@link Point}.
     * 
     * @param point the point to use
     * @return a new position
     */
    public Position withCoordinations(Point point) {
        return withCoordinations(point.getX(), point.getY(), point.getZ());
    }

    /**
     * Changes view of the {@link Position}.
     * 
     * @param yaw the yaw to use
     * @param pitch the pitch to use
     * @return same position with a new view
     */
    public Position withView(float yaw, float pitch) {
        return new Position(this.x, this.y, this.z, yaw, pitch);
    }

    /**
     * Changes view of the {@link Position} by using a {@link Position}.
     * 
     * @param position the position to use
     * @return same position with a new view
     */
    public Position withView(Position position) {
        return withView(position.yaw(), position.pitch());
    }

    /**
     * Sets the yaw and pitch to point
     * in the direction of the point.
     */
    public Position withDirection(Point point) {
        final double x = point.getX();
        final double z = point.getZ();
        if (x == 0 & y == 0) {
            //return withPitch()
        }
        final double theta = Math.atan2(-x, z);
        final double xz = Math.sqrt((x * x) + (z * z));
        final double _2pi = 2 * Math.PI;
        return withView((float) Math.toDegrees((theta + _2pi) % _2pi), 
            (float) Math.toDegrees(Math.atan(-point.getY() / xz)));
    }

    private static float fixYaw(float yaw) {
        yaw = yaw % 360;
        if (yaw < -180.0F) {
            yaw += 360.0F;
        } else if (yaw > 180.0F) {
            yaw -= 360.0F;
        }
        return yaw;
    }
    
}
