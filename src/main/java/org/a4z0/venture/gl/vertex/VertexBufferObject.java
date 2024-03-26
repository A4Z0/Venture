package org.a4z0.venture.gl.vertex;

import org.lwjgl.BufferUtils;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import static org.lwjgl.opengl.GL30.*;

/**
* ...
*/

public class VertexBufferObject {

    protected final int glVBO;

    /**
    * Construct a {@link VertexBufferObject}.
    */

    public VertexBufferObject() {
        this.glVBO = glGenBuffers();
    }

    /**
    * @return the {@link VertexBufferObject VBO} ID.
    */

    public int getID() {
        return this.glVBO;
    }

    /**
    * Binds this {@link VertexBufferObject VBO} to the current context.
    */

    public void bind() {
        glBindBuffer(GL_ARRAY_BUFFER, this.glVBO);
    }

    /**
    * Unbinds this {@link VertexBufferObject VBO} from the current context.
    */

    public void unbind() {
        glBindBuffer(GL_ARRAY_BUFFER, 0);
    }

    /**
    * Deletes this {@link VertexBufferObject VBO}.
    */

    public void delete() {
        glDeleteBuffers(this.glVBO);
    }

    /**
    * ...
    *
    * @param glBuffer ...
    * @param glUsage ...
    */

    public void buffer(ByteBuffer glBuffer, int glUsage) {
        glBufferData(GL_ARRAY_BUFFER, glBuffer, glUsage);
    }

    /**
    * ...
    *
    * @param glBuffer ...
    * @param glUsage ...
    */

    public void buffer(byte[] glBuffer, int glUsage) {
        glBufferData(GL_ARRAY_BUFFER, ByteBuffer.wrap(glBuffer), glUsage);
    }

    /**
    * ...
    *
    * @param glBuffer ...
    * @param glUsage ...
    */

    public void buffer(ShortBuffer glBuffer, int glUsage) {
        glBufferData(GL_ARRAY_BUFFER, glBuffer, glUsage);
    }

    /**
    * ...
    *
    * @param glBuffer ...
    * @param glUsage ...
    */

    public void buffer(short[] glBuffer, int glUsage) {
        glBufferData(GL_ARRAY_BUFFER, glBuffer, glUsage);
    }

    /**
    * ...
    *
    * @param glBuffer ...
    * @param glUsage ...
    */

    public void buffer(FloatBuffer glBuffer, int glUsage) {
        glBufferData(GL_ARRAY_BUFFER, glBuffer, glUsage);
    }

    /**
    * ...
    *
    * @param glBuffer ...
    * @param glUsage ...
    */

    public void buffer(float[] glBuffer, int glUsage) {
        glBufferData(GL_ARRAY_BUFFER, glBuffer, glUsage);
    }
}