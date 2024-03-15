package org.a4z0.venture.window;

import org.a4z0.venture.input.Input;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import java.nio.IntBuffer;

import static org.lwjgl.glfw.GLFW.*;

/**
* ...
*/

public class Window {

    protected final long WINDOW_ID;

    /**
    * Construct a {@link Window}.
    */

    public Window() {
        this(800, 600, "Hello, World!");
    }

    /**
    * Construct a {@link Window}.
    *
    * @param width ...
    * @param height ...
    * @param title ...
    */

    public Window(int width, int height, String title) {
        this.WINDOW_ID = glfwCreateWindow(width, height, title, 0, 0);

        if(this.WINDOW_ID == 0) {
            glfwTerminate();

            throw new RuntimeException("Unable to create a Window!");
        }

        glfwMakeContextCurrent(this.WINDOW_ID);
        this.setVsync(true);

        GL.createCapabilities();
    }

    /**
    * @return ...
    */

    public long getID() {
        return this.WINDOW_ID;
    }

    /**
    * ...
    */

    public void update() {
        Input.update();

        glfwPollEvents();
        glfwSwapBuffers(this.WINDOW_ID);
    }

    /**
    * ...
    */

    public void delete() {
        glfwDestroyWindow(this.WINDOW_ID);
    }

    /**
    * @return ...
    */

    public boolean isClosing() {
        return glfwWindowShouldClose(this.WINDOW_ID);
    }

    /**
    * ...
    *
    * @param s ...
    */

    public void setTitle(String s) {
        glfwSetWindowTitle(this.WINDOW_ID, s);
    }

    /**
    * ...
    *
    * @param b ...
    */

    public void setVsync(boolean b) {
        glfwSwapInterval(b ? 1 : 0);
    }

    /**
    * @return ...
    */

    public int getWidth() {
        IntBuffer INT_BUFFER = BufferUtils.createIntBuffer(1);
        glfwGetWindowSize(this.WINDOW_ID, INT_BUFFER, null);

        return INT_BUFFER.get();
    }

    /**
    * @return ...
    */

    public int getHeight() {
        IntBuffer INT_BUFFER = BufferUtils.createIntBuffer(1);
        glfwGetWindowSize(this.WINDOW_ID, null, INT_BUFFER);

        return INT_BUFFER.get();
    }

    public void setFullScreen(boolean fullscreen) {
        if (fullscreen) {
            // Obter o monitor principal
            long monitor = glfwGetPrimaryMonitor();

            // Obter as configurações do monitor principal
            GLFWVidMode vidmode = glfwGetVideoMode(monitor);

            // Definir a janela para tela cheia no monitor principal
            glfwSetWindowMonitor(this.WINDOW_ID, monitor, 0, 0, vidmode.width(), vidmode.height(), vidmode.refreshRate());
        } else {
            // Definir a janela para o modo de janela
            // Supondo que você queira definir a janela para um tamanho específico em modo de janela
            int windowedWidth = 800; // Exemplo de largura
            int windowedHeight = 600; // Exemplo de altura

            // Definir a janela para o modo de janela
            glfwSetWindowMonitor(this.WINDOW_ID, 0, 100, 100, windowedWidth, windowedHeight, 0);
        }
    }
}