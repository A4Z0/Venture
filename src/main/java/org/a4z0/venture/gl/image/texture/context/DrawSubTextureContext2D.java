package org.a4z0.venture.gl.image.texture.context;

import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11C.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11C.GL_UNSIGNED_BYTE;

/**
* ...
*/

public class DrawSubTextureContext2D extends DrawContext2D {

    protected final int glOffsetWidth;
    protected final int glOffsetHeight;

    /**
    * Construct a {@link DrawSubTextureContext2D}.
    *
    * @param glOffsetWidth ...
    * @param glOffsetHeight ...
    * @param glWidth ...
    * @param glHeight ...
    * @param glPixels ...
    */

    public DrawSubTextureContext2D(
        int glOffsetWidth,
        int glOffsetHeight,
        int glWidth,
        int glHeight,
        int glPixels
    ) {
        this(glOffsetWidth, glOffsetHeight, glWidth, glHeight, GL_RGBA, GL_UNSIGNED_BYTE, glPixels);
    }

    /**
    * Construct a {@link DrawSubTextureContext2D}.
    *
    * @param glOffsetWidth ...
    * @param glOffsetHeight ...
    * @param glWidth ...
    * @param glHeight ...
    * @param glFormat ...
    * @param glPixels ...
    */

    protected DrawSubTextureContext2D(
        int glOffsetWidth,
        int glOffsetHeight,
        int glWidth,
        int glHeight,
        int glFormat,
        int glPixels
    ) {
        this(glOffsetWidth, glOffsetHeight, glWidth, glHeight, glFormat, GL_UNSIGNED_BYTE, glPixels);
    }

    /**
    * Construct a {@link DrawSubTextureContext2D}.
    *
    * @param glOffsetWidth ...
    * @param glOffsetHeight ...
    * @param glWidth ...
    * @param glHeight ...
    * @param glFormat ...
    * @param glType ...
    * @param glPixels ...
    */

    protected DrawSubTextureContext2D(
        int glOffsetWidth,
        int glOffsetHeight,
        int glWidth,
        int glHeight,
        int glFormat,
        int glType,
        int glPixels
    ) {
        super(glWidth, glHeight, glFormat, glType, glPixels);

        this.glOffsetWidth = glOffsetWidth;
        this.glOffsetHeight = glOffsetHeight;
    }

    /**
    * Construct a {@link DrawSubTextureContext2D}.
    *
    * @param glOffsetWidth ...
    * @param glOffsetHeight ...
    * @param glWidth ...
    * @param glHeight ...
    * @param glPixels ...
    */

    public DrawSubTextureContext2D(
        int glOffsetWidth,
        int glOffsetHeight,
        int glWidth,
        int glHeight,
        ByteBuffer glPixels
    ) {
        this(glOffsetWidth, glOffsetHeight, glWidth, glHeight, GL_RGBA, GL_UNSIGNED_BYTE, glPixels);
    }

    /**
    * Construct a {@link DrawSubTextureContext2D}.
    *
    * @param glOffsetWidth ...
    * @param glOffsetHeight ...
    * @param glWidth ...
    * @param glHeight ...
    * @param glFormat ...
    * @param glPixels ...
    */

    protected DrawSubTextureContext2D(
        int glOffsetWidth,
        int glOffsetHeight,
        int glWidth,
        int glHeight,
        int glFormat,
        ByteBuffer glPixels
    ) {
        this(glOffsetWidth, glOffsetHeight, glWidth, glHeight, glFormat, GL_UNSIGNED_BYTE, glPixels);
    }

    /**
    * Construct a {@link DrawSubTextureContext2D}.
    *
    * @param glOffsetWidth ...
    * @param glOffsetHeight ...
    * @param glWidth ...
    * @param glHeight ...
    * @param glFormat ...
    * @param glType ...
    * @param glPixels ...
    */

    protected DrawSubTextureContext2D(
        int glOffsetWidth,
        int glOffsetHeight,
        int glWidth,
        int glHeight,
        int glFormat,
        int glType,
        ByteBuffer glPixels
    ) {
        super(glWidth, glHeight, glFormat, glType, glPixels);

        this.glOffsetWidth = glOffsetWidth;
        this.glOffsetHeight = glOffsetHeight;
    }

    @Override
    public void draw() {
        glRasterPos2i(this.glOffsetWidth, this.glOffsetHeight);

        if(this.glPixels instanceof Integer)
            glTexSubImage2D(GL_TEXTURE_2D, 0, this.glOffsetWidth, this.glOffsetHeight, this.glWidth, this.glHeight, this.glFormat, this.glType, (int) this.glPixels);
        else if(this.glPixels instanceof ByteBuffer)
            glTexSubImage2D(GL_TEXTURE_2D, 0, this.glOffsetWidth, this.glOffsetHeight, this.glWidth, this.glHeight, this.glFormat, this.glType, (ByteBuffer) this.glPixels);
    }
}