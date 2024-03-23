package org.a4z0.venture;

import org.a4z0.venture.render.CrossRenderer;
import org.a4z0.venture.render.OutlineRenderer;
import org.a4z0.venture.render.BlockRenderer;
import org.a4z0.venture.world.SimpleBlockMap;
import org.a4z0.venture.world.SimpleBlockMesh;
import org.a4z0.venture.world.block.blocks.Air;
import org.a4z0.venture.world.block.blocks.Bedrock;
import org.a4z0.venture.world.block.blocks.Dirt;
import org.a4z0.venture.camera.Camera;
import org.a4z0.venture.camera.FreeCamera;
import org.a4z0.venture.camera.ObjectCameraController;
import org.a4z0.venture.gl.image.texture.Textures;
import org.a4z0.venture.gl.input.Input;
import org.a4z0.venture.gl.shader.Shaders;
import org.a4z0.venture.gl.vertex.Vertex;
import org.a4z0.venture.gl.window.Window;
import org.a4z0.venture.world.position.Position;
import org.a4z0.venture.world.position.block.BlockPosition;
import org.a4z0.venture.world.tile.Tiles;
import org.joml.Matrix4f;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;

public final class Venture {

    public static Window WINDOW;

    public static Camera CAMERA;
    public static ObjectCameraController CAMERA_CONTROLLER;

    public static CrossRenderer CROSS_RENDERER;
    public static BlockRenderer BLOCK_RENDERER;
    public static OutlineRenderer OUTLINE_RENDERER;

    /**
    * ...
    *
    * @param args ...
    */

    public static void main(String[] args) {
        if(!glfwInit())
            throw new IllegalStateException("Couldn't initialize GLFW!");

        boolean isFullscreen = false;

        // Window
        WINDOW = new Window();

        glfwWindowHint(GLFW_SAMPLES, 16);

        // Input
        glfwSetInputMode(WINDOW.getID(), GLFW_CURSOR, GLFW_CURSOR_DISABLED);
        glfwSetCursorPosCallback(WINDOW.getID(), (window, x, y) -> {
            CAMERA_CONTROLLER.onMouse((float) x, (float) y);
        });

        // Loaders
        Textures.init();
        Shaders.init();
        Tiles.init();

        // Renderers
        CROSS_RENDERER = new CrossRenderer();
        BLOCK_RENDERER = new BlockRenderer();
        OUTLINE_RENDERER = new OutlineRenderer();

        // Camera
        CAMERA = new FreeCamera();
        CAMERA_CONTROLLER = new ObjectCameraController(CAMERA);

        // Map
        SimpleBlockMap Map = new SimpleBlockMap();

        for(int X = 0; X < 16; X++) {
            for(int Y = 0; Y < 64; Y++) {
                for(int Z = 0; Z < 16; Z++) {
                    Map.setVoxel(X, Y, Z, new Dirt());
                }
            }
        }

        Vertex Stream = SimpleBlockMesh.mesh(Map);

        // Default
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glClearColor(0f, 0f, 0f, 1.0f);
        glEnable(GL_DEPTH_TEST);
        glLineWidth(1f);

        // Loop
        while(!WINDOW.isClosing()) {
            glViewport(0, 0, WINDOW.getWidth(), WINDOW.getHeight());
            glClear(GL_COLOR_BUFFER_BIT  | GL_DEPTH_BUFFER_BIT);
            glClearColor(0.6f, 0.8f, 1f, 1);

            CAMERA_CONTROLLER.onKeyboard();

            if(Input.isKeyPressed(GLFW_KEY_F12)) {
                WINDOW.setFullScreen(isFullscreen = !isFullscreen);
            }

            if(Input.isKeyPressed(GLFW_KEY_ESCAPE)) {
                glfwTerminate();
                System.exit(0);
            }

            Matrix4f View = CAMERA.getView();
            Matrix4f Projection = CAMERA.getProjection();

            Shaders.BLOCK_SHADER_PROGRAM.bind();
            Shaders.BLOCK_SHADER_PROGRAM.setUniform("camera_projection", Projection);
            Shaders.BLOCK_SHADER_PROGRAM.setUniform("camera_view", View);
            Shaders.BLOCK_SHADER_PROGRAM.unbind();

            Shaders.OUTLINE_SHADER_PROGRAM.bind();
            Shaders.OUTLINE_SHADER_PROGRAM.setUniform("camera_projection", Projection);
            Shaders.OUTLINE_SHADER_PROGRAM.setUniform("camera_view", View);
            Shaders.OUTLINE_SHADER_PROGRAM.unbind();

            BlockPosition[] After = Map.getVoxel(CAMERA.getPosition(), 5);

            if(After != null) {
                if(Input.isButtonPressed(GLFW_MOUSE_BUTTON_LEFT)) {
                    Map.setVoxel(After[0].getX(), After[0].getY(), After[0].getZ(), new Air());
                }

                if(Input.isButtonPressed(GLFW_MOUSE_BUTTON_RIGHT)) {
                    Map.setVoxel(After[1].getX(), After[1].getY(), After[1].getZ(), new Bedrock());
                }
            }

            if(Map.hasUpdated) {
                Map.hasUpdated = false;
                Stream = SimpleBlockMesh.mesh(Map);
            }

            BLOCK_RENDERER.render(0, 0, 0, Stream);

            BlockPosition[] Before = Map.getVoxel(CAMERA.getPosition(), 5);

            if(Before != null)
                OUTLINE_RENDERER.render(Before[0], 0f, 0f, 0f, 1f, 1f);

            CROSS_RENDERER.render();

            WINDOW.update();
        }

        glfwTerminate();
        System.exit(0);
    }
}