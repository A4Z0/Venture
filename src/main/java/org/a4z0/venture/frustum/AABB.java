package org.a4z0.venture.frustum;

import org.a4z0.venture.chunk.Chunk;
import org.joml.Vector3f;

/**
* ...
*/

public class AABB {

    protected final Vector3f Min;
    protected final Vector3f Max;

    /**
    * Construct a {@link AABB}.
    *
    * @param X1 ...
    * @param Y1 ...
    * @param Z1 ...
    * @param X2 ...
    * @param Y2 ...
    * @param Z2 ...
    */

    public AABB(
        float X1, float Y1, float Z1,
        float X2, float Y2, float Z2
    ) {
        this(new Vector3f(X1, Y1, Z1), new Vector3f(X2, Y2, Z2));
    }

    /**
    * Construct a {@link AABB}.
    *
    * @param Min ...
    * @param Max ...
    */

    public AABB(Vector3f Min, Vector3f Max) {
        this.Min = Min;
        this.Max = Max;
    }

    public Vector3f getMax() {
        return Max;
    }

    public Vector3f getMin() {
        return Min;
    }

    /**
    * @param other ...
    *
    * @return true if it intersects, false otherwise.
    */

    public boolean intersects(AABB other) {
        return !(this.Max.x < other.Min.x
            || this.Min.x > other.Max.x
            || this.Max.y < other.Min.y
            || this.Min.y > other.Max.y
            || this.Max.z < other.Min.z
            || this.Min.z > other.Max.z);
    }

    /**
    * @return ...
    */

    public Vector3f[] getCorners() {
        return new Vector3f[]{
            new Vector3f(Min.x, Min.y, Min.z),
            new Vector3f(Max.x, Min.y, Min.z),
            new Vector3f(Min.x, Max.y, Min.z),
            new Vector3f(Max.x, Max.y, Min.z),
            new Vector3f(Min.x, Min.y, Max.z),
            new Vector3f(Max.x, Min.y, Max.z),
            new Vector3f(Min.x, Max.y, Max.z),
            new Vector3f(Max.x, Max.y, Max.z)
        };
    }

    /**
    * Constructs an {@link AABB} based on a {@link Chunk}.
    *
    * @param chunk {@link Chunk} that will be incorporated into {@link AABB}.
    *
    * @return a new {@link AABB}.
    */

    public static AABB incorporate(Chunk chunk) {
        return new AABB(
            new Vector3f((chunk.getX() + Chunk.CHUNK_SIZE_X), (Chunk.CHUNK_SIZE_Y), (chunk.getZ() + Chunk.CHUNK_SIZE_Z)),
            new Vector3f(-(chunk.getX() + Chunk.CHUNK_SIZE_X), -(Chunk.CHUNK_SIZE_Y), -(chunk.getZ() + Chunk.CHUNK_SIZE_Z))
        );
    }
}