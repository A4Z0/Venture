package org.a4z0.venture.world;

import org.joml.Vector3f;
import org.joml.Vector3fc;

/**
* ...
*/

public final class Sky {

    public static final Sky DEFAULT_SKY = new Sky(
        new Vector3f(1.0f, 0.8f, 0.8f).mul(1.2f),
        new Vector3f(1.0f, 0.8f, 0.8f).mul(0.2f),
        new Vector3f(1f, -1f, 1f).normalize()
    );

    private final Vector3f directionalDiffuseColor;
    private final Vector3f directionalAmbientColor;
    private final Vector3f directionalDirection;

    /**
    * Construct a {@link Sky}.
    *
    * @param directionalDiffuseColor ...
    * @param directionalAmbientColor ...
    * @param directionalDirection ...
    */

    public Sky(Vector3f directionalDiffuseColor, Vector3f directionalAmbientColor, Vector3f directionalDirection) {
        this.directionalDiffuseColor = directionalDiffuseColor;
        this.directionalAmbientColor = directionalAmbientColor;
        this.directionalDirection = directionalDirection;
    }

    /**
    * @return ...
    */

    public Vector3fc getDirectionalAmbientColor() {
        return directionalAmbientColor;
    }

    /**
    * @return ...
    */

    public Vector3fc getDirectionalDiffuseColor() {
        return directionalDiffuseColor;
    }

    /**
    * @return ...
    */

    public Vector3fc getDirectionalDirection() {
        return directionalDirection;
    }
}