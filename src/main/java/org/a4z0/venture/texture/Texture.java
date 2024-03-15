package org.a4z0.venture.texture;

import org.lwjgl.stb.STBImage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.file.Path;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11C.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11C.GL_UNSIGNED_BYTE;
import static org.lwjgl.opengl.GL11C.glTexImage2D;
import static org.lwjgl.opengl.GL11C.glTexParameteri;

/**
* ...
*/

public class Texture implements Image {

    protected final int TEXTURE_ID;

    protected final int TEXTURE_WIDTH;
    protected final int TEXTURE_HEIGHT;

    /**
    * Construct a {@link Texture}.
    *
    * @param WIDTH ...
    * @param HEIGHT ...
    */

    public Texture(int WIDTH, int HEIGHT) {
        this.TEXTURE_ID = glGenTextures();
        this.TEXTURE_WIDTH = WIDTH;
        this.TEXTURE_HEIGHT = HEIGHT;
    }

    @Override
    public int getID() {
        return this.TEXTURE_ID;
    }

    @Override
    public int getWidth() {
        return this.TEXTURE_WIDTH;
    }

    @Override
    public int getHeight() {
        return this.TEXTURE_HEIGHT;
    }

    /**
    * ...
    */

    public void bind() {
        glBindTexture(GL_TEXTURE_2D, this.TEXTURE_ID);
    }

    /**
    * ...
    *
    * @param p_name ...
    * @param p_param ...
    */

    public void setParam(int p_name, int p_param) {
        glTexParameteri(GL_TEXTURE_2D, p_name, p_param);
    }

    /**
    * ...
    *
    * @param X ...
    * @param Y ...
    * @param PATH_URL ...
    */

    public void setData(int X, int Y, String PATH_URL) {
        this.setData(X, Y, Path.of(PATH_URL));
    }

    /***
    * ...
    *
    * @param X ..
    * @param Y ...
    * @param PATH_URL ...
    */

    public void setData(int X, int Y, Path PATH_URL) {
        try {
            this.setData(X, Y, Texture.class.getClassLoader().getResourceAsStream(PATH_URL.toString()));
        } catch (NullPointerException e) {
            throw new RuntimeException(e);
        }
    }

    /**
    * ...
    *
    * @param X ...
    * @param Y ...
    * @param TEXTURE_INPUT_STREAM ...
    */

    public void setData(int X, int Y, InputStream TEXTURE_INPUT_STREAM) {
        ByteBuffer IMAGE_DATA = Image.getData(TEXTURE_INPUT_STREAM);

        this.setData(GL_RGBA, X, Y, GL_RGBA, IMAGE_DATA);
        STBImage.stbi_image_free(IMAGE_DATA);
    }

    /**
    * ...
    *
    * @param X ...
    * @param Y ...
    * @param INTERNAL_FORMAT ...
    * @param FORMAT ...
    * @param i ...
    */

    public void setData(int INTERNAL_FORMAT, int X, int Y, int FORMAT, int i) {
        glTexImage2D(GL_TEXTURE_2D, 0, INTERNAL_FORMAT, X, Y, 0, FORMAT, GL_UNSIGNED_BYTE, i);
    }

    /**
    * ...
    *
    * @param X ...
    * @param Y ...
    * @param INTERNAL_FORMAT ...
    * @param FORMAT ...
    * @param BYTE_BUFFER ...
    */

    public void setData(int INTERNAL_FORMAT, int X, int Y, int FORMAT, ByteBuffer BYTE_BUFFER) {
        glTexImage2D(GL_TEXTURE_2D, 0, INTERNAL_FORMAT, X, Y, 0, FORMAT, GL_UNSIGNED_BYTE, BYTE_BUFFER);
    }

    /**
    * ...
    *
    * @param OFFSET_X ...
    * @param OFFSET_Y ...
    * @param X ...
    * @param Y ...
    * @param PATH_URL ...
    */

    public void uploadData(int OFFSET_X, int OFFSET_Y, int X, int Y, String PATH_URL) {
        this.uploadData(OFFSET_X, OFFSET_Y, X, Y, Path.of(PATH_URL));
    }

    /**
    * ...
    *
    * @param OFFSET_X ...
    * @param OFFSET_Y ...
    * @param X ...
    * @param Y ...
    * @param PATH_URL ...
    */

    public void uploadData(int OFFSET_X, int OFFSET_Y, int X, int Y, Path PATH_URL) {
        try {
            this.uploadData(OFFSET_X, OFFSET_Y, X, Y, new FileInputStream(PATH_URL.toFile()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
    * ...
    *
    * @param OFFSET_X ...
    * @param OFFSET_Y ...
    * @param X ...
    * @param Y ...
    * @param TEXTURE_INPUT_STREAM ...
    */

    public void uploadData(int OFFSET_X, int OFFSET_Y, int X, int Y, InputStream TEXTURE_INPUT_STREAM) {
        ByteBuffer IMAGE_DATA = Image.getData(TEXTURE_INPUT_STREAM);

        this.updateData(OFFSET_X, OFFSET_Y, X, Y, GL_RGBA, IMAGE_DATA);
        STBImage.stbi_image_free(IMAGE_DATA);
    }

    /**
    * ...
    *
    * @param X ...
    * @param Y ...
    * @param OFFSET_X ...
    * @param OFFSET_Y ...
    * @param FORMAT ...
    * @param i ...
    */

    public void updateData(int OFFSET_X, int OFFSET_Y, int X, int Y, int FORMAT, int i) {
        glTexSubImage2D(GL_TEXTURE_2D, 0, OFFSET_X, OFFSET_Y, X, Y, FORMAT, GL_UNSIGNED_BYTE, i);
    }

    /**
    * ...
    *
    * @param X ...
    * @param Y ...
    * @param OFFSET_X ...
    * @param OFFSET_Y ...
    * @param FORMAT ...
    * @param BYTE_BUFFER ...
    */

    public void updateData(int OFFSET_X, int OFFSET_Y, int X, int Y, int FORMAT, ByteBuffer BYTE_BUFFER) {
        glTexSubImage2D(GL_TEXTURE_2D, 0, OFFSET_X, OFFSET_Y, X, Y, FORMAT, GL_UNSIGNED_BYTE, BYTE_BUFFER);
    }

    /**
    * ...
    */

    public void delete() {
        glDeleteTextures(this.TEXTURE_ID);
    }
}