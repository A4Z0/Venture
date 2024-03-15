package org.a4z0.venture.position;

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
}