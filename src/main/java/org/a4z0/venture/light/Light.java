package org.a4z0.venture.light;

import org.a4z0.venture.world.Position;

/**
* ...
*/

public class Light {

    public static final Light LIGHT_LEVEL_1 = new Light(255, 255, 255, 1);
    public static final Light LIGHT_LEVEL_2 = new Light(255, 255, 255, 2);
    public static final Light LIGHT_LEVEL_3 = new Light(255, 255, 255, 3);
    public static final Light LIGHT_LEVEL_4 = new Light(255, 255, 255, 4);
    public static final Light LIGHT_LEVEL_5 = new Light(255, 255, 255, 5);
    public static final Light LIGHT_LEVEL_6 = new Light(255, 255, 255, 6);
    public static final Light LIGHT_LEVEL_7 = new Light(255, 255, 255, 7);
    public static final Light LIGHT_LEVEL_8 = new Light(255, 255, 255, 8);
    public static final Light LIGHT_LEVEL_9 = new Light(255, 255, 255, 9);
    public static final Light LIGHT_LEVEL_10 = new Light(255, 255, 255, 10);
    public static final Light LIGHT_LEVEL_11 = new Light(255, 255, 255, 11);
    public static final Light LIGHT_LEVEL_12 = new Light(255, 255, 255, 12);
    public static final Light LIGHT_LEVEL_13 = new Light(255, 255, 255, 13);
    public static final Light LIGHT_LEVEL_14 = new Light(255, 255, 255, 14);
    public static final Light LIGHT_LEVEL_15 = new Light(255, 255, 255, 15);

    protected final float r, g, b;
    protected final int level;

    /**
    * Construct a {@link Light}.
    *
    * @param r ...
    * @param g ...
    * @param b ...
    * @param level ...
    */

    protected Light(int r, int g, int b, int level) {
        this(r / 255f, g / 255f, b / 255f, level);
    }

    /**
    * Construct a {@link Light}.
    *
    * @param r ...
    * @param g ...
    * @param b ...
    * @param level ...
    */

    protected Light(float r, float g, float b, int level) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.level = level;
    }

    /**
    * @return ...
    */

    public float getRed() {
        return this.r;
    }

    /**
    * @return ...
    */

    public float getGreen() {
        return this.g;
    }

    /**
    * @return ...
    */

    public float getBlue() {
        return this.b;
    }

    /**
    * @return ...
    */

    public int getLevel() {
        return this.level;
    }

    public Position getPosition() {
        return new Position(0, 8, 0);
    }
}