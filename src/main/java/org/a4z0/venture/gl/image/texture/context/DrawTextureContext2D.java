package org.a4z0.venture.gl.image.texture.context;

import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL11.glDrawPixels;
import static org.lwjgl.opengl.GL11C.*;

/**
* ...
*/

public class DrawTextureContext2D extends DrawContext2D {

    protected final int glInternalFormat;

    /**
    * Construct a {@link DrawTextureContext2D}.
    *
    * @param glWidth ...
    * @param glHeight ...
    * @param glPixels ...
    */

    public DrawTextureContext2D(
        int glWidth,
        int glHeight,
        int glPixels
    ) {
        this(GL_RGBA, glWidth, glHeight, GL_RGBA, GL_UNSIGNED_BYTE, glPixels);
    }

    /**
    * Construct a {@link DrawTextureContext2D}.
    *
    * @param glInternalFormat ...
    * @param glWidth ...
    * @param glHeight ...
    * @param glFormat ...
    * @param glType ...
    * @param glPixels ...
    */

    protected DrawTextureContext2D(
        int glInternalFormat,
        int glWidth,
        int glHeight,
        int glFormat,
        int glType,
        int glPixels
    ) {
        super(glWidth, glHeight, glFormat, glType, glPixels);

        this.glInternalFormat = glInternalFormat;
    }

    /**
    * Construct a {@link DrawTextureContext2D}.
    *
    * @param glWidth ...
    * @param glHeight ...
    * @param glPixels ...
    */

    public DrawTextureContext2D(
        int glWidth,
        int glHeight,
        ByteBuffer glPixels
    ) {
        this(GL_RGBA, glWidth, glHeight, GL_RGBA, GL_UNSIGNED_BYTE, glPixels);
    }

    /**
    * Construct a {@link DrawTextureContext2D}.
    *
    * @param glInternalFormat ...
    * @param glWidth ...
    * @param glHeight ...
    * @param glFormat ...
    * @param glType ...
    * @param glPixels ...
    */

    protected DrawTextureContext2D(
        int glInternalFormat,
        int glWidth,
        int glHeight,
        int glFormat,
        int glType,
        ByteBuffer glPixels
    ) {
        super(glWidth, glHeight, glFormat, glType, glPixels);

        this.glInternalFormat = glInternalFormat;
    }

    @Override
    public void draw() {
        if(this.glPixels instanceof Integer)
            glTexImage2D(GL_TEXTURE_2D, 0, this.glInternalFormat, this.glWidth, this.glHeight, 0, this.glFormat, this.glType, (int) this.glPixels);
        else if(this.glPixels instanceof ByteBuffer)
            glTexImage2D(GL_TEXTURE_2D, 0, this.glInternalFormat, this.glWidth, this.glHeight, 0, this.glFormat, this.glType, (ByteBuffer) this.glPixels);
    }
}