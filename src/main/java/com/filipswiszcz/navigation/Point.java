package com.filipswiszcz.navigation;

/**
 * Represents a 3D point.
 */
public sealed interface Point permits Position, Vector {

    /**
     * Gets the x coordinate.
     * 
     * @return the x coordinate
     */
    double getX();

    /**
     * Gets the y coordinate.
     * 
     * @return the y coordinate
     */
    double getY();

    /**
     * Gets the z coordinate.
     * 
     * @return the z coordinate
     */
    double getZ();

    /**
     * Gets the floored x coordinate.
     * 
     * @return the block x coordinate
     */
    default int getBlockX() {
        return (int) Math.floor(this.getX());
    }

    /**
     * Gets the floored y coordinate.
     * 
     * @return the block y coordinate
     */
    default int getBlockY() {
        return (int) Math.floor(this.getY());
    }

    /**
     * Gets the floored z coordinate.
     * 
     * @return the block z coordinate
     */
    default int getBlockZ() {
        return (int) Math.floor(this.getZ());
    }
    
}
