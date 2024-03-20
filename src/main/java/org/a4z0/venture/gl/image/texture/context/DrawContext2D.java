package org.a4z0.venture.gl.image.texture.context;

import java.nio.ByteBuffer;

/**
* ...
*/

public abstract class DrawContext2D {

    protected final int glWidth, glHeight;
    protected final int glFormat;
    protected final int glType;

    protected final Object glPixels;

    /**
    * Construct a {@link DrawContext2D}.
    *
    * @param glWidth ...
    * @param glHeight ...
    * @param glFormat ...
    * @param glType ...
    * @param glPixels ...
    */

    protected DrawContext2D(
        int glWidth,
        int glHeight,
        int glFormat,
        int glType,
        int glPixels
    ) {
        this.glWidth = glWidth;
        this.glHeight = glHeight;
        this.glFormat = glFormat;
        this.glType = glType;
        this.glPixels = glPixels;
    }

    /**
    * Construct a {@link DrawContext2D}.
    *
    * @param glWidth ...
    * @param glHeight ...
    * @param glFormat ...
    * @param glType ...
    * @param glPixels ...
    */

    protected DrawContext2D(
        int glWidth,
        int glHeight,
        int glFormat,
        int glType,
        ByteBuffer glPixels
    ) {
        this.glWidth = glWidth;
        this.glHeight = glHeight;
        this.glFormat = glFormat;
        this.glType = glType;
        this.glPixels = glPixels;
    }

    /**
    * ...
    */

    public abstract void draw();
}