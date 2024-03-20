package org.a4z0.venture.camera;

import org.a4z0.venture.world.position.Position;
import org.joml.Matrix4d;

/**
* ...
*/

public interface Camera {

    double DEFAULT_FAR_PLANE = 1000d;
    double DEFAULT_NEAR_PLANE = 0.1d;

    double DEFAULT_FOV = 80d;

    double DEFAULT_PITCH = 0d;
    double DEFAULT_YAW = -90d;

    /**
    * @return the {@link Camera} Projection {@link Matrix4d Matrix}.
    */

    Matrix4d getProjection();

    /**
    * @return the {@link Camera} View {@link Matrix4d Matrix}.
    */

    Matrix4d getView();

    /**
    * @return the {@link Camera} {@link Position}.
    */

    Position getPosition();
}