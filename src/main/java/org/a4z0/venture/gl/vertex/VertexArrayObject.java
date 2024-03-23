package org.a4z0.venture.gl.vertex;

import static org.lwjgl.opengl.GL30.*;

/**
* ...
*/

public class VertexArrayObject {

    protected int glVAO;

    /**
    * Construct a {@link VertexArrayObject}.
    */

    public VertexArrayObject() {
        this.glVAO = glGenVertexArrays();
    }

    /**
    * @return the {@link VertexArrayObject VAO} ID.
    */

    public int getID() {
        return this.glVAO;
    }

    /**
    * Binds this {@link VertexArrayObject VAO} to the current context.
    */

    public void bind() {
        glBindVertexArray(this.glVAO);
    }

    /**
    * Unbinds this {@link VertexArrayObject VAO} from the current context.
    */

    public void unbind() {
        glBindVertexArray(0);
    }

    /**
    * Deletes this {@link VertexArrayObject VAO}.
    */

    public void delete() {
        glDeleteVertexArrays(this.glVAO);
    }

    /**
    * ...
    *
    * @param glIndex ...
    * @param glSize ...
    * @param glType ...
    * @param glNormalized ...
    * @param glStride ...
    * @param glPointer ...
    */

    public void attribute(int glIndex, int glSize, int glType, boolean glNormalized, int glStride, int glPointer) {
        glVertexAttribPointer(glIndex, glSize, glType, glNormalized, glStride, glPointer);
    }
}