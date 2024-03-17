package org.a4z0.venture;

import org.a4z0.venture.world.block.blocks.Dirt;
import org.a4z0.venture.world.block.blocks.Glowstone;
import org.a4z0.venture.camera.FirstPersonCamera;
import org.a4z0.venture.input.Input;
import org.a4z0.venture.render.world.block.outline.BlockOutlineRenderer;
import org.a4z0.venture.render.world.chunk.ChunkRenderer;
import org.a4z0.venture.model.geometry.Geometry;
import org.a4z0.venture.shader.Shader;
import org.a4z0.venture.shader.ShaderProgram;
import org.a4z0.venture.shader.Shaders;
import org.a4z0.venture.texture.Textures;
import org.a4z0.venture.window.Window;
import org.a4z0.venture.world.Overworld;
import org.a4z0.venture.world.chunk.Chunk;
import org.joml.Matrix4fc;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL20.GL_FRAGMENT_SHADER;
import static org.lwjgl.opengl.GL20.GL_VERTEX_SHADER;

public final class Venture {

    public static Window WINDOW;

    public static ShaderProgram BLOCK_SHADER_PROGRAM;
    public static Shader BLOCK_VERTEX_SHADER, BLOCK_FRAGMENT_SHADER;

    public static ShaderProgram BLOCK_OUTLINE_SHADER_PROGRAM;
    public static Shader BLOCK_OUTLINE_VERTEX_SHADER, BLOCK_OUTLINE_FRAGMENT_SHADER;

    public static FirstPersonCamera FIRST_PERSON_CAMERA;

    public static ChunkRenderer CHUNK_RENDERER;
    public static BlockOutlineRenderer BLOCK_OUTLINE_RENDERER;

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

        WINDOW.setVsync(false);

        Shaders.init();
        Textures.init();
        Geometry.init();

        glfwSetInputMode(WINDOW.getID(), GLFW_CURSOR, GLFW_CURSOR_DISABLED);

        glfwSetCursorPosCallback(WINDOW.getID(), (window, x, y) -> {
            FIRST_PERSON_CAMERA.handleMouse(x, y);
        });

        FIRST_PERSON_CAMERA = new FirstPersonCamera();
        FIRST_PERSON_CAMERA.setPosition(0, 0, 0);
        FIRST_PERSON_CAMERA.setRotation(0, 0);

        WORLD = new Overworld();

        for(int x = -8; x <= 8; x++) {
            for (int z = -8; z <= 8; z++) {
                WORLD.setBlock(new Dirt(x, 0, z));
            }
        }

        WORLD.setBlock(new Glowstone(0, 0, 0));

        // Block
        BLOCK_SHADER_PROGRAM = new ShaderProgram();
        BLOCK_SHADER_PROGRAM.bind(0, "vertex_position");
        BLOCK_SHADER_PROGRAM.bind(1, "vertex_texture_coordinates");
        BLOCK_SHADER_PROGRAM.bind(2, "vertex_normal");
        BLOCK_SHADER_PROGRAM.bind(3, "vertex_ao");

        BLOCK_VERTEX_SHADER = new Shader(GL_VERTEX_SHADER);
        BLOCK_VERTEX_SHADER.source(Shaders.BLOCK_VERTEX_SHADER);
        BLOCK_VERTEX_SHADER.compile();

        BLOCK_FRAGMENT_SHADER = new Shader(GL_FRAGMENT_SHADER);
        BLOCK_FRAGMENT_SHADER.source(Shaders.BLOCK_FRAGMENT_SHADER);
        BLOCK_FRAGMENT_SHADER.compile();

        BLOCK_SHADER_PROGRAM.add(BLOCK_VERTEX_SHADER);
        BLOCK_SHADER_PROGRAM.add(BLOCK_FRAGMENT_SHADER);

        BLOCK_SHADER_PROGRAM.link();

        // Outline
        BLOCK_OUTLINE_SHADER_PROGRAM = new ShaderProgram();
        BLOCK_OUTLINE_SHADER_PROGRAM.bind(0, "vertex_position");

        BLOCK_OUTLINE_VERTEX_SHADER = new Shader(GL_VERTEX_SHADER);
        BLOCK_OUTLINE_VERTEX_SHADER.source(Shaders.BLOCK_OUTLINE_VERTEX_SHADER);
        BLOCK_OUTLINE_VERTEX_SHADER.compile();

        BLOCK_OUTLINE_FRAGMENT_SHADER = new Shader(GL_FRAGMENT_SHADER);
        BLOCK_OUTLINE_FRAGMENT_SHADER.source(Shaders.BLOCK_OUTLINE_FRAGMENT_SHADER);
        BLOCK_OUTLINE_FRAGMENT_SHADER.compile();

        BLOCK_OUTLINE_SHADER_PROGRAM.add(BLOCK_OUTLINE_VERTEX_SHADER);
        BLOCK_OUTLINE_SHADER_PROGRAM.add(BLOCK_OUTLINE_FRAGMENT_SHADER);

        BLOCK_OUTLINE_SHADER_PROGRAM.link();

        CHUNK_RENDERER = new ChunkRenderer(BLOCK_SHADER_PROGRAM);
        BLOCK_OUTLINE_RENDERER = new BlockOutlineRenderer(BLOCK_OUTLINE_SHADER_PROGRAM);

        boolean Fullscreen = false;

        glfwWindowHint(GLFW_SAMPLES, 16);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glClearColor(0f, 0f, 0f, 1.0f);
        glEnable(GL_DEPTH_TEST);
        glDepthFunc(GL_LEQUAL);
        glLineWidth(1f);

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

            FIRST_PERSON_CAMERA.updateProjection();
            FIRST_PERSON_CAMERA.updateView();

            Matrix4fc view = FIRST_PERSON_CAMERA.getView();
            Matrix4fc projection = FIRST_PERSON_CAMERA.getProjection();

            BLOCK_SHADER_PROGRAM.use();

            BLOCK_SHADER_PROGRAM.setUniform("camera_projection", projection);
            BLOCK_SHADER_PROGRAM.setUniform("camera_view", view);

            BLOCK_SHADER_PROGRAM.unbind();

            BLOCK_OUTLINE_SHADER_PROGRAM.use();

            BLOCK_OUTLINE_SHADER_PROGRAM.setUniform("camera_projection", projection);
            BLOCK_OUTLINE_SHADER_PROGRAM.setUniform("camera_view", view);

            BLOCK_OUTLINE_SHADER_PROGRAM.unbind();

            for(Chunk CHUNK : WORLD.getChunks()) {
                CHUNK.render(CHUNK_RENDERER);
            }

            BLOCK_OUTLINE_RENDERER.render(0, 0, 0, 1f);

            WINDOW.update();
        }

        BLOCK_SHADER_PROGRAM.delete();
        WINDOW.delete();
    }
}