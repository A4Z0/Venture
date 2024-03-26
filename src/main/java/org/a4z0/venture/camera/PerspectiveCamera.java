package org.a4z0.venture.camera;

import org.a4z0.venture.Venture;
import org.a4z0.venture.position.Position;
import org.joml.*;
import org.joml.Math;

/**
* ...
*/

public class PerspectiveCamera implements Camera {

    protected final Position position;

    /**
    * Construct a {@link PerspectiveCamera}
    */

    public PerspectiveCamera() {
        this(0, 0, 0, DEFAULT_YAW, DEFAULT_PITCH);
    }

    /**
    * Construct a {@link PerspectiveCamera}.
    *
    * @param x ...
    * @param y ...
    * @param z ...
    * @param yaw ...
    * @param pitch ...
    */

    public PerspectiveCamera(float x, float y, float z, float yaw, float pitch) {
        this(new Position(x, y, z, yaw, pitch));
    }

    /**
    * Construct a {@link PerspectiveCamera}.
    *
    * @param position ...
    */

    public PerspectiveCamera(Position position) {
        this.position = position;
    }

    @Override
    public Position getPosition() {
        return this.position;
    }

    @Override
    public Matrix4f getProjection() {
        return new Matrix4f().identity().perspective(
            Math.toRadians(DEFAULT_FOV),
            ((float) Venture.WINDOW.getWidth() / (float) Venture.WINDOW.getHeight()),
            DEFAULT_NEAR_PLANE,
            DEFAULT_FAR_PLANE
        );
    }

    @Override
    public Matrix4f getView() {
        return new Matrix4f().identity().lookAt(
            0, 0, 0,
            (0 + this.position.getDirection().x),
            (0 + this.position.getDirection().y),
            (0 + this.position.getDirection().z),
            0, 1, 0
        );
    }
}