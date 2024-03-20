package org.a4z0.venture.box;

import org.joml.Math;

/**
* ...
*/

public class AABB {

    protected final float X1, Y1, Z1;
    protected final float X2, Y2, Z2;

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
        this.X1 = Math.min(X1, (X2 < 1 ? X2 - 1 : X2 + 1));
        this.Y1 = Math.min(Y1, (Y2 < 1 ? Y2 - 1 : Y2 + 1));
        this.Z1 = Math.min(Z1, (Z2 < 1 ? Z2 - 1 : Z2 + 1));
        this.X2 = Math.max(X1, (X2 < 1 ? X2 - 1 : X2 + 1));
        this.Y2 = Math.max(Y1, (Y2 < 1 ? Y2 - 1 : Y2 + 1));
        this.Z2 = Math.max(Z1, (Z2 < 1 ? Z2 - 1 : Z2 + 1));
    }

    public float getLowerX() {
        return this.X1;
    }

    /**
     * @return ...
     */

    public float getLowerY() {
        return this.Y1;
    }

    /**
    * @return ...
    */

    public float getLowerZ() {
        return this.Z1;
    }

    /**
    * @return ...
    */

    public float getUpperX() {
        return this.X2;
    }

    /**
    * @return ...
    */

    public float getUpperY() {
        return this.Y2;
    }

    /**
    * @return ...
    */

    public float getUpperZ() {
        return this.Z2;
    }
}