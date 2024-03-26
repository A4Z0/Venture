package org.a4z0.venture.camera.frustum;

/**
* ...
*/

public class Plane {

    public float x;
    public float y;
    public float z;

    public float distance;

    /**
    * Construct a {@link Plane}.
    *
    * @param x ...
    * @param y ...
    * @param z ...
    * @param distance ...
    */

    public Plane(final float x, final float y, final float z, final float distance) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.distance = distance;
    }

    /**
    * @return the X-Axis.
    */

    public float getX() {
        return this.x;
    }

    /**
    * @return the Y-Axis.
    */

    public float getY() {
        return this.y;
    }

    /**
    * @return the Z-Axis.
    */

    public float getZ() {
        return this.z;
    }

    /**
    * @return the Distance.
    */

    public float getDistance() {
        return this.distance;
    }

    /**
    * ...
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    *
    * @return ...
    */

    public Plane set(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.distance = w;

        return this;
    }

    /**
    * Normalize the plane.
    */

    public Plane normalize() {
        float length = (float) Math.sqrt(x * x + y * y + z * z);
        x /= length;
        y /= length;
        z /= length;
        distance /= length;
        return this;
    }

    /**
    * ...
    *
    * @param x ...
    * @param y ...
    * @param z ...
    *
    * @return ...
    */

    public float distance(float x, float y, float z) {
        return this.x * x + this.y * y + this.z * z + this.distance;
    }
}