package org.a4z0.venture.camera;

import org.a4z0.venture.gl.input.Input;
import org.joml.Vector3d;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_LEFT_SHIFT;

/**
* ...
*/

public class ObjectCameraController {

    protected final Camera Camera;
    protected final org.a4z0.venture.world.position.Position Position;

    private final double SENSITIVITY = 0.05f;
    private final double HORIZONTAL_SPEED = 0.05f;
    private final double VERTICAL_SPEED = 0.05f;

    private double LAST_MOUSE_X = 0f;
    private double LAST_MOUSE_Y = 0f;

    /**
    * Construct a {@link ObjectCameraController}.
    *
    * @param Camera ...
    */

    public ObjectCameraController(Camera Camera) {
        this.Camera = Camera;
        this.Position = Camera.getPosition();
    }

    /**
    * ...
    */

    public void onKeyboard() {
        Vector3d Direction = this.Camera.getPosition().getDirection();

        Vector3d HorizontalDirection = new Vector3d(Direction.x, 0, Direction.z).normalize();

        if(Input.isKeyDown(GLFW_KEY_W))
            this.Position.add(HorizontalDirection.x * VERTICAL_SPEED, 0, HorizontalDirection.z * VERTICAL_SPEED);
        if(Input.isKeyDown(GLFW_KEY_A))
            this.Position.add(HorizontalDirection.z * HORIZONTAL_SPEED, 0, -HorizontalDirection.x * HORIZONTAL_SPEED);
        if(Input.isKeyDown(GLFW_KEY_S))
            this.Position.add(-HorizontalDirection.x * VERTICAL_SPEED, 0, -HorizontalDirection.z * VERTICAL_SPEED);
        if(Input.isKeyDown(GLFW_KEY_D))
            this.Position.add(-HorizontalDirection.z * HORIZONTAL_SPEED, 0, HorizontalDirection.x * HORIZONTAL_SPEED);

        if(Input.isKeyDown(GLFW_KEY_SPACE))
            this.Position.add(0, VERTICAL_SPEED, 0);
        if(Input.isKeyDown(GLFW_KEY_LEFT_SHIFT))
            this.Position.add(0, -VERTICAL_SPEED, 0);
    }

    /**
    * ...
    *
    * @param X ...
    * @param Y ...
    */

    public void onMouse(double X, double Y) {
        this.Position.setYaw(this.Position.getYaw() + ((LAST_MOUSE_X - X) * -SENSITIVITY));
        this.Position.setPitch(this.Position.getPitch() - ((LAST_MOUSE_Y - Y) * SENSITIVITY));

        this.LAST_MOUSE_X = X;
        this.LAST_MOUSE_Y = Y;
    }
}