package com.filipswiszcz.navigation;

public record Vector(double x, double y, double z) implements Point {

    public static final Vector zero = new Vector(0, 0, 0);
    public static final Vector one = new Vector(1, 1, 1);

    public static final double epsilon = 0.000001;

    public Vector(double x, double z){
        this(x, 0, z);
    }

    public Vector(double value) {
        this(value, value, value);
    }
    
}
