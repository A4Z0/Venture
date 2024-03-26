package org.a4z0.venture.render;

import org.joml.Math;

/**
* ...
*/

public class Outline {

    protected final float x1, x2;
    protected final float y1, y2;
    protected final float z1, z2;

    protected final float r;
    protected final float g;
    protected final float b;
    protected final float a;

    /**
    * Construct a {@link Outline}.
    *
    * @param x1 ...
    * @param y1 ...
    * @param z1 ...
    * @param x2 ...
    * @param y2 ...
    * @param z2 ...
    */

    public Outline(
        float x1, float y1, float z1,
        float x2, float y2, float z2,
        float r, float g, float b, float a
    ) {
        this.x1 = Math.min(x1, x2);
        this.y1 = Math.min(y1, y2);
        this.z1 = Math.min(z1, z2);
        this.x2 = Math.max(x1, x2);
        this.y2 = Math.max(y1, y2);
        this.z2 = Math.max(z1, z2);
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
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

    public float getAlpha() {
        return this.a;
    }

    /**
    * @return ...
    */

    public float getLowerX() {
        return this.x1;
    }

    /**
    * @return ...
    */

    public float getLowerY() {
        return this.y1;
    }

    /**
    * @return ...
    */

    public float getLowerZ() {
        return this.z1;
    }

    /**
    * @return ...
    */

    public float getUpperX() {
        return this.x2;
    }

    /**
    * @return ...
    */

    public float getUpperY() {
        return this.y2;
    }

    /**
    * @return ...
    */

    public float getUpperZ() {
        return this.z2;
    }
}