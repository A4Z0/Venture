package org.a4z0.venture;

import org.a4z0.venture.camera.frustum.Frustum;
import org.a4z0.venture.gl.vertex.VertexStream;
import org.a4z0.venture.gl.vertex.VertexBuffer;
import org.a4z0.venture.position.Position;
import org.a4z0.venture.position.block.BlockPosition;
import org.a4z0.venture.render.CrossRenderer;
import org.a4z0.venture.render.OutlineRenderer;
import org.a4z0.venture.render.BlockRenderer;
import org.a4z0.venture.camera.Camera;
import org.a4z0.venture.camera.FreeCamera;
import org.a4z0.venture.camera.ObjectCameraController;
import org.a4z0.venture.gl.image.texture.Textures;
import org.a4z0.venture.gl.input.Input;
import org.a4z0.venture.gl.shader.Shaders;
import org.a4z0.venture.gl.window.Window;
import org.a4z0.venture.tile.Tiles;
import org.a4z0.venture.world.Overworld;
import org.a4z0.venture.world.World;
import org.a4z0.venture.world.block.blocks.Air;
import org.a4z0.venture.world.block.blocks.Bedrock;
import org.a4z0.venture.world.block.blocks.Dirt;
import org.a4z0.venture.world.chunk.Chunk;
import org.a4z0.venture.world.chunk.Mesh;
import org.joml.Matrix4f;
import org.joml.Random;
import org.joml.Vector3f;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;

public final class Venture {

    public static Window WINDOW;

    public static Camera CAMERA;
    public static Frustum FRUSTUM;

    public static ObjectCameraController CAMERA_CONTROLLER;

    public static CrossRenderer CROSS_RENDERER;
    public static BlockRenderer BLOCK_RENDERER;
    public static OutlineRenderer OUTLINE_RENDERER;

    public static World WORLD;

    public static boolean DEBUG_CHUNK;
    public static boolean DISABLE_FRUSTUM_UPDATE;

    public static void main(String[] args) {
        if(!glfwInit())
            throw new IllegalStateException("Couldn't initialize GLFW!");

        boolean isFullscreen = false;

        // Window
        WINDOW = new Window();
        WINDOW.setVsync(false);

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
        FRUSTUM = new Frustum();

        CAMERA_CONTROLLER = new ObjectCameraController(CAMERA);

        // World
        WORLD = new Overworld(Random.newSeed());

        for(int x = -32; x <= 32; x++) {
            //for(int y = 0; y <= 16; y++) {
                for(int z = -32; z <= 32; z++) {
                    WORLD.setBlock(new Dirt(x, 0, z));
                }
            //}
        }

        VertexStream Stream;

        // Default
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glClearColor(0f, 0f, 0f, 1.0f);
        glEnable(GL_DEPTH_TEST);
        glClearDepth(1f);
        glDepthFunc(GL_LEQUAL);
        glEnable(GL_CULL_FACE);
        glCullFace(GL_BACK);
        glLineWidth(1f);

        // Loop
        while(!WINDOW.isClosing()) {
            glViewport(0, 0, WINDOW.getWidth(), WINDOW.getHeight());
            glClear(GL_COLOR_BUFFER_BIT  | GL_DEPTH_BUFFER_BIT);
            glClearColor(0.6f, 0.8f, 1f, 1);

            // Camera Keyboard
            CAMERA_CONTROLLER.onKeyboard();

            // Window Fullscreen (F12)
            if(Input.isKeyPressed(GLFW_KEY_F12))
                WINDOW.setFullScreen(isFullscreen = !isFullscreen);

            // Game Exit (ESC+X)
            if(Input.isKeyDown(GLFW_KEY_ESCAPE) && Input.isKeyPressed(GLFW_KEY_X)) {
                glfwTerminate();
                System.exit(0);
            }

            Matrix4f View = CAMERA.getView();
            Matrix4f Projection = CAMERA.getProjection();

            Position Position = CAMERA.getPosition();

            Shaders.BLOCK_SHADER_PROGRAM.bind();
            Shaders.BLOCK_SHADER_PROGRAM.setUniform("camera_projection", Projection);
            Shaders.BLOCK_SHADER_PROGRAM.setUniform("camera_view", View);
            Shaders.BLOCK_SHADER_PROGRAM.unbind();

            Shaders.OUTLINE_SHADER_PROGRAM.bind();
            Shaders.OUTLINE_SHADER_PROGRAM.setUniform("camera_projection", Projection);
            Shaders.OUTLINE_SHADER_PROGRAM.setUniform("camera_view", View);
            Shaders.OUTLINE_SHADER_PROGRAM.unbind();

            Shaders.TEXT_SHADER_PROGRAM.bind();
            Shaders.TEXT_SHADER_PROGRAM.setUniform("camera_projection", Projection);
            Shaders.TEXT_SHADER_PROGRAM.setUniform("camera_view", View);
            Shaders.TEXT_SHADER_PROGRAM.unbind();

            // Disable Frustum Update (CTRL+F)
            if(Input.isKeyDown(GLFW_KEY_LEFT_CONTROL) && Input.isKeyPressed(GLFW_KEY_F))
                DISABLE_FRUSTUM_UPDATE = !DISABLE_FRUSTUM_UPDATE;

            // Frustum Update
            if(!DISABLE_FRUSTUM_UPDATE)
                FRUSTUM.frustrate(Projection, View);

            // World Mesh
            Stream = new VertexStream();

            for(Chunk chunk : WORLD.getChunks())
                Mesh.create(Stream, chunk, FRUSTUM);

            // Scene Rendering
            BLOCK_RENDERER.render(0, 0, 0, VertexBuffer.create(Stream), Stream.vertices());

            // FIXME: Get block by Direction isn't correct and needs an optimization.
            // Block Outline + Block Placing
            Chunk titleChunk = WORLD.getChunkAt(CAMERA.getPosition().getBlockX(), CAMERA.getPosition().getBlockZ());

            WINDOW.setTitle("Chunk - X: " + titleChunk.getX() + ", Z: " + titleChunk.getZ());

            BlockPosition[] Looking = getBlockAt(Position, 5);

            if(Looking != null) {
                BlockPosition A = Looking[0];
                BlockPosition B = Looking[1];

                if(Input.isButtonPressed(GLFW_MOUSE_BUTTON_RIGHT)) {
                    WORLD.setBlock(new Bedrock(B.getX(), B.getY(), B.getZ()));
                }

                if(Input.isButtonPressed(GLFW_MOUSE_BUTTON_LEFT)) {
                    WORLD.setBlock(new Air(A.getX(), A.getY(), A.getZ()));
                }

                OUTLINE_RENDERER.render(OutlineRenderer.BLOCK_BOUNDARIES, A, 0, 0, 0, 1f, 1f);
            }

            // Chunk Debugging (Ctrl+G)
            if(Input.isKeyDown(GLFW_KEY_LEFT_CONTROL) && Input.isKeyPressed(GLFW_KEY_G))
                DEBUG_CHUNK = !DEBUG_CHUNK;

            if(DEBUG_CHUNK)
                OUTLINE_RENDERER.render(WORLD.getChunkAt(Position.getBlockX(), Position.getBlockZ()), 1f, 1f, 0, 1f, 1f);;

            // Sight Rendering
            CROSS_RENDERER.render(4);

            WINDOW.update();
        }

        glfwTerminate();
        System.exit(0);
    }

    public static BlockPosition[] getBlockAt(Position Position, int Range) {
        Vector3f Dir = Position.getDirection();

        for(int i = 0; i <= Range; i++) {
            int X = Math.round((Position.getX() + Dir.x * i));
            int Y = Math.round((Position.getY() + Dir.y * i));
            int Z = Math.round((Position.getZ() + Dir.z * i));
            int X2 = Math.round((Position.getX() + Dir.x * (i - 1)));
            int Y2 = Math.round((Position.getY() + Dir.y * (i - 1)));
            int Z2 = Math.round((Position.getZ() + Dir.z * (i - 1)));

            if(WORLD.getBlock(X, Y, Z).getID() != 0)
                return new BlockPosition[]{
                        new BlockPosition(X, Y, Z),
                        new BlockPosition(X2, Y2, Z2),
                };
        }

        return null;
    }
}