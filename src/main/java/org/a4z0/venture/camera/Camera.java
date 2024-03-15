package org.a4z0.venture.camera;

import org.joml.Matrix4fc;
import org.joml.Vector3f;
import org.joml.Vector3fc;

/**
* ...
*/

public interface Camera {

    float DEFAULT_FAR_PLANE = 1000f;
    float DEFAULT_NEAR_PLANE = 0.1f;
    Vector3fc DEFAULT_POSITION = new Vector3f(0, 0, 0);
    Vector3fc DEFAULT_WORLD_UP = new Vector3f(0, 1, 0);

    /**
    * ...
    *
    * @param x ...
    * @param y ...
    * @param z ...
    */

    default void setPosition(float x, float y, float z) {
        this.setPosition(new Vector3f(x, y, z));
    }

    /**
    * ...
    *
    * @param vector3f ...
    */

    void setPosition(Vector3f vector3f);

    /**
    * ...
    *
    * @param yaw ...
    * @param pitch ...
    */

    default void setRotation(float yaw, float pitch) {
        this.setRotation(new Vector3f(0, pitch, yaw));
    }

    /**
    * ...
    *
    * @param vector3dc ...
    */

    void setRotation(Vector3f vector3dc);

    /**
    * @return ...
    */

    Matrix4fc getProjection();

    /**
    * @return ...
    */

    Matrix4fc getView();
}