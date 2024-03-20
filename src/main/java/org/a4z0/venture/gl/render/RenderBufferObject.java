package org.a4z0.venture.gl.render;

import static org.lwjgl.opengl.GL30.*;

/**
* ...
*/

public class RenderBufferObject {

    protected final int renderBufferObjectId;

    /**
    * Construct a {@link RenderBufferObject}.
    */

    public RenderBufferObject() {
        this.renderBufferObjectId = glGenRenderbuffers();
    }

    /**
    * @return ...
    */

    public int getID() {
        return this.renderBufferObjectId;
    }

    /**
    * ...
    */

    public void bind() {
        glBindRenderbuffer(GL_RENDERBUFFER, this.renderBufferObjectId);
    }

    /**
    * ...
    */

    public void unbind() {
        glBindRenderbuffer(GL_RENDERBUFFER, 0);
    }

    /**
    * ...
    */

    public void delete() {
        glDeleteRenderbuffers(this.renderBufferObjectId);
    }
}