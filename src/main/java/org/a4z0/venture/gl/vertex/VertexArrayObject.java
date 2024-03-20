package org.a4z0.venture.gl.vertex;

import static org.lwjgl.opengl.GL30.*;

/**
* ...
*/

public class VertexArrayObject {

    protected int VAO_ID;

    /**
    * Construct a {@link VertexArrayObject}.
    */

    public VertexArrayObject() {
        this.VAO_ID = glGenVertexArrays();
    }

    /**
    * @return ...
    */

    public int getID() {
        return this.VAO_ID;
    }

    /**
    * ...
    */

    public void bind() {
        glBindVertexArray(this.VAO_ID);
    }

    /**
    * ...
    */

    public void unbind() {
        glBindVertexArray(0);
    }

    /**
    * ...
    *
    * @param size ...
    * @param stride ...
    * @param pointer ...
    */

    public void attr(int index, int size, int stride, int pointer) {
        glVertexAttribPointer(index, size, GL_FLOAT, false, stride, pointer);
    }

    /**
    * ...
    */

    public void delete() {
        glDeleteVertexArrays(this.VAO_ID);
    }
}