package com.filipswiszcz.navigation;

import java.util.function.DoubleUnaryOperator;

import com.filipswiszcz.utils.ChunkUtils;

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

    default int getChunkX(){
        return ChunkUtils.getChunkCoordinate(getX());
    }

    default int getChunkSection(){
        return ChunkUtils.getChunkCoordinate(getY());
    }

    default int getChunkZ(){
        return ChunkUtils.getChunkCoordinate(getZ());
    }

    /**
     * Creates a point with a modified X coordinate based on its value.
     *
     * @param operator the operator providing the current X coordinate and returning the new
     * @return a new point
     */
    Point withX(DoubleUnaryOperator operator);

    /**
     * Creates a point with the specified X coordinate.
     * 
     * @param the new X coordinate
     * @return a new point
     */
    Point withX(double x);

    /**
     * Creates a point with a modified Y coordinate based on its value.
     * 
     * @param operator the operator providing the current Y coordinate and returning the new
     * @return a new point
     */
    Point withY(DoubleUnaryOperator operator);

    /**
     * Creates a point with the specified Y coordinate.
     * 
     * @param the new Y coordinate
     * @return a new point
     */
    Point withY(double y);

    /**
     * Creates a point with a new modified Z coordinate based on its value.
     * 
     * @param operator the operator providing the current Z coordinate and returning the new
     * @return a new point
     */
    Point withZ(DoubleUnaryOperator operator);

    /**
     * Creates a point with the specified Z coordinate.
     * 
     * @param the new Z point.
     * @return a new point.
     */
    Point withZ(double z);

    Point add(double x, double y, double z);

    Point add(Point point);

    Point add(double value);

    Point sub(double x, double y, double z);

    Point sub(Point point);

    Point sub(double value);

    Point mul(double x, double y, double z);

    Point mul(Point point);

    Point mul(double value);

    Point div(double x, double y, double z);

    Point div(Point point);

    Point div(double value);

    
    
}
