package org.a4z0.venture.gl.image.texture;

import org.a4z0.venture.gl.image.Image;
import org.a4z0.venture.gl.image.texture.context.DrawContext2D;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11C.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11C.glTexParameteri;

/**
* ...
*/

public class Texture implements Image {

    protected final int glTextureID;

    protected final int glTextureWidth;
    protected final int glTextureHeight;

    /**
    * Construct a {@link Texture}.
    *
    * @param glTextureWidth ...
    * @param glTextureHeight ...
    */

    public Texture(int glTextureWidth, int glTextureHeight) {
        this.glTextureID = glGenTextures();
        this.glTextureWidth = glTextureWidth;
        this.glTextureHeight = glTextureHeight;
    }

    @Override
    public int getID() {
        return this.glTextureID;
    }

    @Override
    public int getWidth() {
        return this.glTextureWidth;
    }

    @Override
    public int getHeight() {
        return this.glTextureHeight;
    }

    /**
    * Binds this {@link Texture} to the current context.
    */

    public void bind() {
        glBindTexture(GL_TEXTURE_2D, this.glTextureID);
    }

    /**
    * Sets a Param to the current {@link Texture} context.
    *
    * @param glParamTarget Target of the param to be set.
    * @param glParam Param to be set.
    */

    public void param(int glParamTarget, int glParam) {
        glTexParameteri(GL_TEXTURE_2D, glParamTarget, glParam);
    }

    /**
    * Draws using a {@link DrawContext2D} in the current {@link Texture} context.
    *
    * @param drawContext2D {@link DrawContext2D} that will be used to draw.
    */

    public void draw(DrawContext2D drawContext2D) {
        drawContext2D.draw();
    }

    /**
    * Unbinds this {@link Texture} from the current context.
    */

    public void unbind() {
        glBindTexture(GL_TEXTURE_2D, 0);
    }

    /**
    * Deletes this {@link Texture}.
    */

    public void delete() {
        glDeleteTextures(this.glTextureID);
    }
}