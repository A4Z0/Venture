package org.a4z0.venture.frustum;

import org.joml.Vector3f;
import org.joml.Vector4f;

/**
* ...
*/

public class Plane {

    protected Vector4f vector4f;

    /**
    * Construct a {@link Plane}.
    *
    * @param x ...
    * @param y ...
    * @param z ...
    * @param w ...
    */

    public Plane(float x, float y, float z, float w) {
        this(new Vector4f(x, y, z, w));
    }

    /**
    * Construct a {@link Plane}.
    *
    * @param vector4f ...
    */

    public Plane(Vector4f vector4f) {
        this.vector4f = vector4f;
    }

    /**
    * ...
    *
    * @param x ...
    * @param y ...
    * @param z ...
    * @param w ...
    */

    public void set(float x, float y, float z, float w) {
        this.set(new Vector4f(x, y, z, w));
    }

    /**
    * ...
    *
    * @param vector4f ...
    */

    public void set(Vector4f vector4f) {
        this.vector4f = vector4f;
    }

    /**
    * @param point ...
    *
    * @return ...
     */

    public float distance(Vector4f point) {
        return (vector4f.x * point.x + vector4f.y * point.y + vector4f.z * point.z + vector4f.w) / (float) Math.sqrt(vector4f.x * vector4f.x + vector4f.y * vector4f.y + vector4f.z * vector4f.z);
    }
}