package org.a4z0.venture;

import org.a4z0.venture.camera.Camera;
import org.a4z0.venture.camera.FreeCamera;
import org.a4z0.venture.camera.ObjectCameraController;
import org.a4z0.venture.gl.render.renderer.OutlineRenderer;
import org.a4z0.venture.util.Utils;
import org.a4z0.venture.world.Overworld;
import org.a4z0.venture.world.World;
import org.a4z0.venture.gl.render.renderer.CrossRenderer;
import org.a4z0.venture.gl.input.Input;
import org.a4z0.venture.gl.render.renderer.VertexRenderer;
import org.a4z0.venture.gl.shader.Shaders;
import org.a4z0.venture.gl.image.texture.Textures;
import org.a4z0.venture.gl.window.Window;
import org.a4z0.venture.world.material.Material;
import org.a4z0.venture.world.chunk.Mesh;
import org.a4z0.venture.world.position.BlockPosition;
import org.joml.Matrix4d;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;

public final class Venture {

    public static Window WINDOW;

    public static Camera CAMERA;
    public static ObjectCameraController CAMERA_CONTROLLER;

    public static CrossRenderer CROSS_RENDERER;
    public static VertexRenderer VERTEX_RENDERER;
    public static OutlineRenderer OUTLINE_RENDERER;

    public static World WORLD;

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
            CAMERA_CONTROLLER.onMouse(x, y);
        });

        // Loaders
        Textures.init();
        Shaders.init();

        // Renderers
        CROSS_RENDERER = new CrossRenderer(Shaders.CROSS_SHADER_PROGRAM);
        VERTEX_RENDERER = new VertexRenderer(Shaders.BLOCK_SHADER_PROGRAM);
        OUTLINE_RENDERER = new OutlineRenderer(Shaders.OUTLINE_SHADER_PROGRAM);

        // Camera
        CAMERA = new FreeCamera();
        CAMERA_CONTROLLER = new ObjectCameraController(CAMERA);

        // Tile Map
        WORLD = new Overworld();

        for(int x = 0; x < 16; x++) {
            for(int y = 0; y < 1; y++) {
                for (int z = 0; z < 16; z++) {
                    WORLD.setBlock(x, y, z, Material.DIRT);
                }
            }
        }

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
                WINDOW.delete();

                System.exit(0);
            }

            Matrix4d view = CAMERA.getView();
            Matrix4d projection = CAMERA.getProjection();
            BlockPosition blockPosition = Utils.getDirAt(CAMERA.getPosition(), 5);

            Shaders.BLOCK_SHADER_PROGRAM.bind();
            Shaders.BLOCK_SHADER_PROGRAM.setUniform("camera_projection", projection);
            Shaders.BLOCK_SHADER_PROGRAM.setUniform("camera_view", view);
            Shaders.BLOCK_SHADER_PROGRAM.unbind();

            Shaders.OUTLINE_SHADER_PROGRAM.bind();
            Shaders.OUTLINE_SHADER_PROGRAM.setUniform("camera_projection", projection);
            Shaders.OUTLINE_SHADER_PROGRAM.setUniform("camera_view", view);
            Shaders.OUTLINE_SHADER_PROGRAM.unbind();

            VERTEX_RENDERER.render(0, 0, 0, Mesh.create(WORLD.getChunkAt(0, 0)));

            if(blockPosition != null)
                OUTLINE_RENDERER.render(blockPosition, 0, 0, 0, 1f, 1f);

            CROSS_RENDERER.renderAim();

            WINDOW.update();
        }

        // Exit
        WINDOW.delete();

        Shaders.BLOCK_SHADER_PROGRAM.delete();
        Shaders.OUTLINE_SHADER_PROGRAM.delete();
        Shaders.CROSS_SHADER_PROGRAM.delete();
    }
}