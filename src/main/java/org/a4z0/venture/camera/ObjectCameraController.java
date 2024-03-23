package org.a4z0.venture.camera;

import org.a4z0.venture.gl.input.Input;
import org.joml.Vector3f;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_LEFT_SHIFT;

/**
* ...
*/

public class ObjectCameraController {

    protected final Camera Camera;
    protected final org.a4z0.venture.world.position.Position Position;

    private final float SENSITIVITY = 0.05f;
    private final float HORIZONTAL_SPEED = 0.05f;
    private final float VERTICAL_SPEED = 0.05f;

    private float CALCULATED_HORIZONTAL_SPEED = HORIZONTAL_SPEED;
    private float CALCULATED_VERTICAL_SPEED = VERTICAL_SPEED;

    private float LAST_MOUSE_X = 0f;
    private float LAST_MOUSE_Y = 0f;

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
        Vector3f Direction = this.Camera.getPosition().getDirection();

        Vector3f HorizontalDirection = new Vector3f(Direction.x, 0, Direction.z).normalize();

        if(Input.isKeyDown(GLFW_KEY_LEFT_CONTROL)) {
            CALCULATED_HORIZONTAL_SPEED = HORIZONTAL_SPEED + 0.5f;
            CALCULATED_VERTICAL_SPEED = VERTICAL_SPEED + 0.5f;
        } else {
            CALCULATED_HORIZONTAL_SPEED = HORIZONTAL_SPEED;
            CALCULATED_VERTICAL_SPEED = VERTICAL_SPEED;
        }

        if(Input.isKeyDown(GLFW_KEY_W))
            this.Position.add(HorizontalDirection.x * CALCULATED_VERTICAL_SPEED, 0, HorizontalDirection.z * CALCULATED_VERTICAL_SPEED);
        if(Input.isKeyDown(GLFW_KEY_A))
            this.Position.add(HorizontalDirection.z * CALCULATED_HORIZONTAL_SPEED, 0, -HorizontalDirection.x * CALCULATED_HORIZONTAL_SPEED);
        if(Input.isKeyDown(GLFW_KEY_S))
            this.Position.add(-HorizontalDirection.x * CALCULATED_VERTICAL_SPEED, 0, -HorizontalDirection.z * CALCULATED_VERTICAL_SPEED);
        if(Input.isKeyDown(GLFW_KEY_D))
            this.Position.add(-HorizontalDirection.z * CALCULATED_HORIZONTAL_SPEED, 0, HorizontalDirection.x * CALCULATED_HORIZONTAL_SPEED);

        if(Input.isKeyDown(GLFW_KEY_SPACE))
            this.Position.add(0, CALCULATED_VERTICAL_SPEED, 0);
        if(Input.isKeyDown(GLFW_KEY_LEFT_SHIFT))
            this.Position.add(0, -CALCULATED_VERTICAL_SPEED, 0);
    }

    /**
    * ...
    *
    * @param X ...
    * @param Y ...
    */

    public void onMouse(float X, float Y) {
        this.Position.setYaw(this.Position.getYaw() + ((LAST_MOUSE_X - X) * -SENSITIVITY));
        this.Position.setPitch(this.Position.getPitch() - ((LAST_MOUSE_Y - Y) * SENSITIVITY));

        this.LAST_MOUSE_X = X;
        this.LAST_MOUSE_Y = Y;
    }
}