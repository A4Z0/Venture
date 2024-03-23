package org.a4z0.venture.box;

import org.a4z0.venture.world.position.block.BlockPosition;
import org.a4z0.venture.world.position.Position;
import org.joml.Math;

/**
* ...
*/

public class AABB {

    protected final float x1, y1, z1;
    protected final float x2, y2, z2;

    /**
    * Construct a {@link AABB}.
    *
    * @param x1 ...
    * @param y1 ...
    * @param z1 ...
    * @param x2 ...
    * @param y2 ...
    * @param z2 ...
    */

    public AABB(
        float x1, float y1, float z1,
        float x2, float y2, float z2
    ) {
        this.x1 = Math.min(x1, x2);
        this.y1 = Math.min(y1, y2);
        this.z1 = Math.min(z1, z2);
        this.x2 = Math.max(x1, x2);
        this.y2 = Math.max(y1, y2);
        this.z2 = Math.max(z1, z2);
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

    /**
    * ....
    *
    * @param position ...
    *
    * @return ...
    */

    public boolean intersects(Position position) {
        return this.intersects(position.getX(), position.getY(), position.getZ());
    }

    /**
    * ...
    *
    * @param blockPosition ...
    *
    * @return ...
    */

    public boolean intersects(BlockPosition blockPosition) {
        return this.intersects(blockPosition.getX(), blockPosition.getY(), blockPosition.getZ());
    }

    /**
    * ...
    *
    * @param other ...
    *
    * @return ...
    */

    public boolean intersects(AABB other) {
        return this.x1 <= other.x2 && this.x2 >= other.x1 && this.y1 <= other.y2 && this.y2 >= other.y1 && this.z1 <= other.z2 && this.z2 >= other.z1;
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

    public boolean intersects(float x, float y, float z) {
        return x >= this.x1 && x <= this.x2 && y >= this.y1 && y <= this.y2 && z >= this.z1 && z <= this.z2;
    }
}