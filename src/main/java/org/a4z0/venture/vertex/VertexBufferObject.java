package org.a4z0.venture.vertex;

import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL30.*;

/**
* ...
*/

public class VertexBufferObject {

    protected final int VBO_ID;

    /**
    * Construct a {@link VertexBufferObject}.
    */

    public VertexBufferObject() {
        this.VBO_ID = glGenBuffers();
    }

    /**
    * @return ...
    */

    public int getID() {
        return this.VBO_ID;
    }

    /**
    * ...
    */

    public void bind() {
        glBindBuffer(GL_ARRAY_BUFFER, this.VBO_ID);
    }

    /**
    * ...
    */

    public void unbind() {
        glBindBuffer(GL_ARRAY_BUFFER, 0);
    }

    /**
    * ...
    *
    * @param data ...
    * @param usage ...
    */

    public void addData(float[] data, int usage) {
        FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
        buffer.put(data);
        buffer.flip();

        this.addData(buffer, usage);
    }

    /**
    * ...
    *
    * @param buffer ...
    * @param usage ...
    */

    public void addData(FloatBuffer buffer, int usage) {
        glBufferData(GL_ARRAY_BUFFER, buffer, usage);
    }

    /**
    * ...
    *
    * @param data ...
    * @param usage ...
    */

    public void addData(int target, int[] data, int usage) {
        IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
        buffer.put(data);
        buffer.flip();

        this.addData(target, buffer, usage);
    }

    /**
    * ...
    *
    * @param buffer ...
    * @param usage ...
    */

    public void addData(int target, IntBuffer buffer, int usage) {
        glBufferData(target, buffer, usage);
    }

    /**
    * ...
    */

    public void delete() {
        glDeleteVertexArrays(this.VBO_ID);
    }
}