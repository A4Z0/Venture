package org.a4z0.venture.world.position;

/**
* ...
*/

public class BlockPosition {
    
    protected int X, Y, Z;

    /**
    * Construct a {@link BlockPosition}.
    */

    public BlockPosition() {
        this(0, 0, 0);
    }

    /**
    * Construct a {@link BlockPosition}.
    *
    * @param X ...
    * @param Y ...
    * @param Z ...
    */

    public BlockPosition(final int X, final int Y, final int Z) {
        this.X = X;
        this.Y = Y;
        this.Z = Z;
    }

    /**
    * @return ...
    */

    public int getX() {
        return this.X;
    }

    /**
    * @return ...
    */

    public int getY() {
        return this.Y;
    }

    /**
    * @return ...
    */

    public int getZ() {
        return this.Z;
    }

    /**
    * ...
    *
    * @param X ...
    * @param Y ...
    * @param Z ...
    *
    * @return ...
    */

    public int distance(int X, int Y, int Z) {
        return (int) (Math.pow((this.X - X), 2) + Math.pow((this.Y - Y), 2) + Math.pow((this.Z - Z), 2));
    }
}