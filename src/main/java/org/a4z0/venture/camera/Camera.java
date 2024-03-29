package org.a4z0.venture.camera;

import org.a4z0.venture.position.Position;
import org.joml.Matrix4f;

/**
* ...
*/

public interface Camera {

    float DEFAULT_FAR_PLANE = 100f;
    float DEFAULT_NEAR_PLANE = 0.001f;

    float DEFAULT_FOV = 90f;

    float DEFAULT_PITCH = 0f;
    float DEFAULT_YAW = -90f;

    /**
    * @return the {@link Camera} {@link Position}.
    */

    Position getPosition();

    /**
    * @return the {@link Camera} Projection {@link Matrix4f Matrix}.
    */

    Matrix4f getProjection();

    /**
    * @return the {@link Camera} View {@link Matrix4f Matrix}.
    */

    Matrix4f getView();
}