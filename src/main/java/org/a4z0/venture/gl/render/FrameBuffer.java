package org.a4z0.venture.gl.render;

import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL30.GL_FRAMEBUFFER;

/**
* ...
*/

public class FrameBuffer {

    protected final int frameBufferId;

    /**
    * Construct a {@link FrameBuffer}.
    */

    public FrameBuffer() {
        this.frameBufferId = glGenFramebuffers();
    }

    /**
    * @return ...
    */

    public int getID() {
        return this.frameBufferId;
    }

    /**
    * ...
    */

    public void bind() {
        glBindFramebuffer(GL_FRAMEBUFFER, this.frameBufferId);
        glDrawBuffer(GL_COLOR_ATTACHMENT0);
    }

    /**
    * ...
    */

    public void unbind() {
        glBindFramebuffer(GL_FRAMEBUFFER, 0);
    }

    /**
    * ...
    */

    public void delete() {
        glDeleteFramebuffers(this.frameBufferId);
    }
}