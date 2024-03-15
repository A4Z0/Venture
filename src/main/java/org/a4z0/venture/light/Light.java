package org.a4z0.venture.light;

import org.a4z0.venture.position.Position;
import org.joml.Vector3f;

/**
* ...
*/

public class Light {

    protected final Position Position;
    protected final Vector3f Color;
    protected final float Intensity;

    /**
    * Construct a {@link Light}.
    *
    * @param x ...
    * @param y ...
    * @param z ...
    * @param r ...
    * @param g ...
    * @param b ...
    * @param i ...
    */

    public Light(int x, int y, int z, float r, float g, float b, float i) {
        this(new Position(x, y, z), new Vector3f(r, g, b), i);
    }

    /**
    * Construct a {@link Light}.
    *
    * @param Position ...
    * @param Color ...
    * @param Intensity ...
    */

    public Light(Position Position, Vector3f Color, float Intensity) {
        this.Position = Position;
        this.Color = Color;
        this.Intensity = Intensity;
    }

    /**
    * @return ...
    */

    public Position getPosition() {
        return this.Position;
    }

    /**
    * @return ...
    */

    public Vector3f getColor() {
        return this.Color;
    }

    /**
     * @return ...
     */

    public float getIntensity() {
        return this.Intensity;
    }
}