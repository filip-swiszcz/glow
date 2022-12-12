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
    
}
