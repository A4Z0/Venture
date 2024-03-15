package org.a4z0.venture.camera;

import org.a4z0.venture.Venture;
import org.a4z0.venture.input.Input;
import org.joml.*;
import org.joml.Math;

import static org.lwjgl.glfw.GLFW.*;

/**
* ...
*/

public class FirstPersonCamera implements Camera {

    public static final float DEFAULT_FOV = 80f;
    public static final float DEFAULT_PITCH = 0f;
    public static final float DEFAULT_YAW = -90f;

    private final Vector3f POSITION = new Vector3f(DEFAULT_POSITION);
    private final Vector3f ROTATION = new Vector3f(0, DEFAULT_YAW, DEFAULT_PITCH);

    private final Vector3f position;
    private final Vector3f rotation;

    private float horizontalSpeed = 0.05f;
    private float verticalSpeed = 0.05f;

    private double sensitivity = 0.05f;
    private double lastX = 0f;
    private double lastY = 0f;

    private Matrix4f projection;
    private Matrix4f view;

    /**
    * Construct a {@link FirstPersonCamera}.
    */

    public FirstPersonCamera() {
        this.position = new Vector3f(0, 0, 0);
        this.rotation = new Vector3f(0, 0, 0);
    }

    public void translate(float x, float y, float z) {
        this.position.x += x;
        this.position.y += y;
        this.position.z += z;
    }

    @Override
    public void setPosition(Vector3f position) {

    }

    @Override
    public void setRotation(Vector3f rotation) {

    }

    @Override
    public Matrix4f getProjection() {
        return this.projection;
    }

    @Override
    public Matrix4f getView() {
        return this.view;
    }

    public void updateProjection() {
        this.projection = new Matrix4f()
            .identity()
            .perspective(
            Math.toRadians(DEFAULT_FOV),
            ((float) Venture.WINDOW.getWidth() / (float) Venture.WINDOW.getHeight()),
      DEFAULT_NEAR_PLANE,
       DEFAULT_FAR_PLANE
        );
    }

    public void updateView() {
        Matrix4f matrix = new Matrix4f();

        float pitchRadians = Math.toRadians(this.rotation.x());
        float yawRadians = Math.toRadians(this.rotation.y());

        Vector3f front = new Vector3f();
        Vector3f right = new Vector3f();
        Vector3f up = new Vector3f();

        front.set(
                Math.cos(pitchRadians) * Math.cos(yawRadians),
                Math.sin(pitchRadians),
                Math.cos(pitchRadians) * Math.sin(yawRadians)
        ).normalize();

        right.set(DEFAULT_WORLD_UP).cross(front).normalize();
        up.set(front).cross(right).normalize();

        matrix.identity().lookAt(
                this.position.x, this.position.y, this.position.z,
                this.position.x + front.x(),
                this.position.y + front.y(),
                this.position.z + front.z(),
                up.x(), up.y(), up.z()
        );

        this.view = matrix;
    }

    /**
    * ...
    */

    public void handleKeyboard() {
        float pitchRadians = Math.toRadians(this.rotation.x());
        float yawRadians = Math.toRadians(this.rotation.y());

        Vector3f front = new Vector3f();
        front.set(
                Math.cos(pitchRadians) * Math.cos(yawRadians),
                Math.sin(pitchRadians),
                Math.cos(pitchRadians) * Math.sin(yawRadians)
        ).normalize();

        Vector3f right = new Vector3f();
        right.set(DEFAULT_WORLD_UP).cross(front).normalize();

        if(Input.isKeyDown(GLFW_KEY_W))
            translate(front.x() * verticalSpeed, front.y() * verticalSpeed, front.z() * verticalSpeed);
        if(Input.isKeyDown(GLFW_KEY_A))
            translate(right.x() * horizontalSpeed, right.y() * horizontalSpeed, right.z() * horizontalSpeed);
        if(Input.isKeyDown(GLFW_KEY_S))
            translate(-front.x() * verticalSpeed, -front.y() * verticalSpeed, -front.z() * verticalSpeed);
        if(Input.isKeyDown(GLFW_KEY_D))
            translate(-right.x() * horizontalSpeed, -right.y() * horizontalSpeed, -right.z() * horizontalSpeed);


        if(Input.isKeyDown(GLFW_KEY_SPACE))
            translate(0, verticalSpeed, 0);
        if(Input.isKeyDown(GLFW_KEY_LEFT_SHIFT))
            translate(0, -verticalSpeed, 0);
    }

    public void handleMouse(double mx, double my) {
        double x = lastX - mx;
        double y = lastY - my;

        this.rotation.set(
                this.rotation.x() + (float) (y * sensitivity),
                this.rotation.y() + (float) (x * -sensitivity),
                0
        );

        if(this.rotation.x() >= 90)
            this.rotation.set(89.9f, this.rotation.y(), 0);
        if(this.rotation.x() <= -90)
            this.rotation.set(-89.9f, this.rotation.y(), 0);
        
        lastX = mx;
        lastY = my;
    }
}