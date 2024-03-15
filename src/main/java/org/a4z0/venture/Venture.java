package org.a4z0.venture;

import org.a4z0.venture.block.blocks.Dirt;
import org.a4z0.venture.block.blocks.Glowstone;
import org.a4z0.venture.camera.FirstPersonCamera;
import org.a4z0.venture.input.Input;
import org.a4z0.venture.shader.Shader;
import org.a4z0.venture.shader.ShaderProgram;
import org.a4z0.venture.shader.Shaders;
import org.a4z0.venture.texture.Textures;
import org.a4z0.venture.vertex.BlockRenderer;
import org.a4z0.venture.window.Window;
import org.a4z0.venture.world.Overworld;
import org.a4z0.venture.chunk.Chunk;
import org.joml.Matrix4fc;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL20.GL_FRAGMENT_SHADER;
import static org.lwjgl.opengl.GL20.GL_VERTEX_SHADER;

public final class Venture {

    public static Window WINDOW;

    public static ShaderProgram SHADER_PROGRAM;
    public static Shader VERTEX_SHADER, FRAGMENT_SHADER;

    public static FirstPersonCamera FIRST_PERSON_CAMERA;

    public static BlockRenderer VERTEX_RENDERER;
    public static Overworld WORLD;

    /**
    * ...
    *
    * @param args ...
    */

    public static void main(String[] args) {
        if(!glfwInit())
            throw new IllegalStateException("Couldn't initialize GLFW!");

        WINDOW = new Window();
        glEnable(GL_DEPTH_TEST);
        glfwWindowHint(GLFW_SAMPLES, 16);
        glfwSetInputMode(WINDOW.getID(), GLFW_CURSOR, GLFW_CURSOR_DISABLED);

        glfwSetCursorPosCallback(WINDOW.getID(), (window, x, y) -> {
            FIRST_PERSON_CAMERA.handleMouse(x, y);
        });

        FIRST_PERSON_CAMERA = new FirstPersonCamera();
        FIRST_PERSON_CAMERA.setPosition(0, 0, 0);
        FIRST_PERSON_CAMERA.setRotation(0, 0);

        Shaders.init();
        Textures.init();

        WORLD = new Overworld();

        for(int x = -16; x <= 16; x++) {
            for (int z = -16; z <= 16; z++) {
                WORLD.setBlock(new Dirt(x, 0, z));
            }
        }

        WORLD.setBlock(new Glowstone(0, 0, 0));

        SHADER_PROGRAM = new ShaderProgram();
        SHADER_PROGRAM.bind(0, "vertex_position");
        SHADER_PROGRAM.bind(1, "vertex_texture_coordinates");
        SHADER_PROGRAM.bind(2, "vertex_normal");
        SHADER_PROGRAM.bind(3, "vertex_ao");

        VERTEX_SHADER = new Shader(GL_VERTEX_SHADER);
        VERTEX_SHADER.source(Shaders.VERTEX_SHADER_V0_2);
        VERTEX_SHADER.compile();

        FRAGMENT_SHADER = new Shader(GL_FRAGMENT_SHADER);
        FRAGMENT_SHADER.source(Shaders.FRAGMENT_SHADER_V0_2);
        FRAGMENT_SHADER.compile();

        SHADER_PROGRAM.add(VERTEX_SHADER);
        SHADER_PROGRAM.add(FRAGMENT_SHADER);

        SHADER_PROGRAM.link();

        VERTEX_RENDERER = new BlockRenderer(SHADER_PROGRAM);

        boolean Fullscreen = false;

        while(!WINDOW.isClosing()) {
            glViewport(0, 0, WINDOW.getWidth(), WINDOW.getHeight());
            glClear(GL_COLOR_BUFFER_BIT  | GL_DEPTH_BUFFER_BIT);
            glClearColor(0.6f, 0.8f, 1f, 1);

            FIRST_PERSON_CAMERA.handleKeyboard();

            if(Input.isKeyPressed(GLFW_KEY_F12)) {
                WINDOW.setFullScreen(Fullscreen = !Fullscreen);
            }

            if(Input.isKeyPressed(GLFW_KEY_ESCAPE)) {
                WINDOW.delete();
                System.exit(0);
            }

            SHADER_PROGRAM.use();

            FIRST_PERSON_CAMERA.updateProjection();
            FIRST_PERSON_CAMERA.updateView();

            Matrix4fc view = FIRST_PERSON_CAMERA.getView();
            Matrix4fc projection = FIRST_PERSON_CAMERA.getProjection();

            SHADER_PROGRAM.setUniform("camera_projection", projection);
            SHADER_PROGRAM.setUniform("camera_view", view);

            for(Chunk CHUNK : WORLD.getChunks()) {
                CHUNK.render(VERTEX_RENDERER);
            }

            WINDOW.update();
        }

        SHADER_PROGRAM.delete();
        WINDOW.delete();
    }
}