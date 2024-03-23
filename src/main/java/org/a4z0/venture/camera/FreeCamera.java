package org.a4z0.venture.camera;

import org.a4z0.venture.Venture;
import org.a4z0.venture.world.position.Position;
import org.joml.Math;
import org.joml.Matrix4f;

/**
* ...
*/

public class FreeCamera implements Camera {

    protected final Position position;

    /**
    * Construct a {@link FreeCamera}
    */

    public FreeCamera() {
        this(0f, 0f, 0f, DEFAULT_YAW, DEFAULT_PITCH);
    }

    /**
    * Construct a {@link FreeCamera}.
    *
    * @param x ...
    * @param y ...
    * @param z ...
    * @param yaw ...
    * @param pitch ...
    */

    public FreeCamera(float x, float y, float z, float yaw, float pitch) {
        this(new Position(x, y, z, yaw, pitch));
    }

    /**
    * Construct a {@link FreeCamera}.
    *
    * @param position {@link Position} that will be attached to {@link FreeCamera}.
    */

    public FreeCamera(final Position position) {
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
            this.position.getX(), this.position.getY(), this.position.getZ(),
            (this.position.getX() + this.position.getDirection().x),
            (this.position.getY() + this.position.getDirection().y),
            (this.position.getZ() + this.position.getDirection().z),
        0, 1, 0
        );
    }
}