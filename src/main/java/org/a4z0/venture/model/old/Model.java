package org.a4z0.venture.model.old;

import org.a4z0.venture.vertex.Vertex;
import org.a4z0.venture.vertex.VertexArrayObject;
import org.a4z0.venture.vertex.VertexBufferObject;
import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL30.*;

/**
* ...
*/

public class Model {

    protected final VertexArrayObject VAO;

    protected final VertexBufferObject POSITION_BUFFER;
    protected final VertexBufferObject UV_BUFFER;
    protected final VertexBufferObject NORMAL_BUFFER;
    protected final VertexBufferObject AMBIENT_OCCLUSION;

    protected final float[] POSITION_ARRAY;
    protected final float[] UV_ARRAY;
    protected final float[] NORMAL_ARRAY;

    /**
    * Construct a {@link Model}.
    *
    * @param Vertex ...
    */

    public Model(Vertex Vertex) {
        this(Vertex.getPositions(), Vertex.getUVs(), Vertex.getNormals(), Vertex.getAO());
    }

    /**
    * Construct a {@link Model}.
    *
    * @param POSITION_ARRAY ...
    * @param UV_ARRAY ...
    * @param NORMAL_ARRAY ...
    */

    public Model(float[] POSITION_ARRAY, float[] UV_ARRAY, float[] NORMAL_ARRAY, float[] AO) {
        this.POSITION_ARRAY = POSITION_ARRAY;
        this.UV_ARRAY = UV_ARRAY;
        this.NORMAL_ARRAY = NORMAL_ARRAY;

        this.VAO = new VertexArrayObject();
        this.VAO.bind();

        // Vertex
        this.POSITION_BUFFER = new VertexBufferObject();
        this.POSITION_BUFFER.bind(GL_ARRAY_BUFFER);

        FloatBuffer VERTEX_BUFFER = BufferUtils.createFloatBuffer(POSITION_ARRAY.length);
        VERTEX_BUFFER.put(POSITION_ARRAY);
        VERTEX_BUFFER.flip();

        this.POSITION_BUFFER.addData(GL_ARRAY_BUFFER, VERTEX_BUFFER, GL_DYNAMIC_DRAW);

        glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
        glBindBuffer(GL_ARRAY_BUFFER, 0);

        // Texture
        this.UV_BUFFER = new VertexBufferObject();
        this.UV_BUFFER.bind(GL_ARRAY_BUFFER);

        FloatBuffer TEXTURE_BUFFER = BufferUtils.createFloatBuffer(UV_ARRAY.length);
        TEXTURE_BUFFER.put(UV_ARRAY);
        TEXTURE_BUFFER.flip();

        this.UV_BUFFER.addData(GL_ARRAY_BUFFER, TEXTURE_BUFFER, GL_DYNAMIC_DRAW);

        glVertexAttribPointer(1, 2, GL_FLOAT, false, 0, 0);
        glBindBuffer(GL_ARRAY_BUFFER, 0);

        // Normal
        this.NORMAL_BUFFER = new VertexBufferObject();
        this.NORMAL_BUFFER.bind(GL_ARRAY_BUFFER);

        FloatBuffer NORMAL_BUFFER = BufferUtils.createFloatBuffer(NORMAL_ARRAY.length);
        NORMAL_BUFFER.put(NORMAL_ARRAY);
        NORMAL_BUFFER.flip();

        this.NORMAL_BUFFER.addData(GL_ARRAY_BUFFER, NORMAL_BUFFER, GL_DYNAMIC_DRAW);

        glVertexAttribPointer(2, 3, GL_FLOAT, false, 0, 0);
        glBindBuffer(GL_ARRAY_BUFFER, 0);

        // Ambient Occlusion
        this.AMBIENT_OCCLUSION = new VertexBufferObject();
        this.AMBIENT_OCCLUSION.bind(GL_ARRAY_BUFFER);

        FloatBuffer AMBIENT_OCCLUSION_BUFFER = BufferUtils.createFloatBuffer(AO.length);
        AMBIENT_OCCLUSION_BUFFER.put(AO);
        AMBIENT_OCCLUSION_BUFFER.flip();

        this.AMBIENT_OCCLUSION.addData(GL_ARRAY_BUFFER, AMBIENT_OCCLUSION_BUFFER, GL_DYNAMIC_DRAW);

        glVertexAttribPointer(3, 4, GL_FLOAT, false, 0, 0);
        glBindBuffer(GL_ARRAY_BUFFER, 0);

        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindVertexArray(0);
    }

    /**
    * @return ...
    */

    public VertexArrayObject getVAO() {
        return this.VAO;
    }

    /**
    * @return ...
    */

    public int getLength() {
        return this.POSITION_ARRAY.length;
    }

    /**
    * ...
    */

    public void delete() {
        this.VAO.delete();
        this.POSITION_BUFFER.delete();
        this.UV_BUFFER.delete();
        this.NORMAL_BUFFER.delete();
    }
}