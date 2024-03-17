package org.a4z0.venture.world;

import org.joml.Vector3i;

/**
* ...
*/

public class Position {

    protected final Vector3i i;

    /**
    * Construct a {@link Position}.
    */

    public Position() {
        this(0, 0, 0);
    }

    /**
    * Construct a {@link Position}.
    *
    * @param x ...
    * @param y ...
    * @param z ...
    */

    public Position(int x, int y, int z) {
        this.i = new Vector3i(x, y, z);
    }

    /**
    * @return ...
    */

    public int getX() {
        return this.i.x;
    }

    /**
    * @return ...
    */

    public int getY() {
        return this.i.y;
    }

    /**
    * @return ...
    */

    public int getZ() {
        return this.i.z;
    }



    public float distance(Position other) {
        return this.distance(other.getX(), other.getY(), other.getZ());
    }

    public float distance(int X, int Y, int Z) {
        int dx = this.getX() - X;
        int dy = this.getY() - Y;
        int dz = this.getZ() - Z;
        return (float) Math.sqrt(dx * dx + dy * dy + dz * dz);
    }
}