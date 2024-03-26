package org.a4z0.venture.position.block;

/**
* Represents the Position of a Block.
*/

public class BlockPosition {
    
    protected final int x, y, z;

    /**
    * Construct a {@link BlockPosition}.
    */

    public BlockPosition() {
        this(0, 0, 0);
    }

    /**
    * Construct a {@link BlockPosition}.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    */

    public BlockPosition(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
    * @return the X-Axis.
    */

    public int getX() {
        return this.x;
    }

    /**
    * @return the Y-Axis.
    */

    public int getY() {
        return this.y;
    }

    /**
    * @return the Z-Axis.
    */

    public int getZ() {
        return this.z;
    }

    /**
    * Calculates the distance between positions.
    *
    * @param other Other {@link BlockPosition}.
    *
    * @return the distance between positions.
    */

    public int distance(BlockPosition other) {
        return this.distance(other.getX(), other.getY(), other.getZ());
    }

    /**
    * Calculates the distance between positions.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    *
    * @return the distance between positions.
    */

    public int distance(int x, int y, int z) {
        return (int) (Math.pow((this.x - x), 2) + Math.pow((this.y - y), 2) + Math.pow((this.z - z), 2));
    }
}