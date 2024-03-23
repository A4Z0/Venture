package org.a4z0.venture.gl.vertex;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL15.*;

/**
* ...
*/

public class VertexBuffer {

    protected final VertexArrayObject VAO = new VertexArrayObject();
    protected final List<VertexBufferObject> VBOs = new ArrayList<>();

    /**
    * Construct a {@link VertexBuffer}.
    */

    protected VertexBuffer() {

    }

    /**
    * ...
    *
    * @param VBA ...
    * @param glBufferArray ...
    *
    * @return ...
    */

    public VertexBuffer add(VertexBufferAttribute VBA, byte[] glBufferArray) {
        this.VAO.bind();

        VertexBufferObject VBO = new VertexBufferObject();
        this.VBOs.add(VBO);

        VBO.bind();
        VBO.buffer(glBufferArray, GL_DYNAMIC_DRAW);

        this.VAO.attribute(VBA.getIndex(), VBA.getSize(), GL_BYTE, false, 0, 0);

        VBO.unbind();
        VAO.unbind();

        return this;
    }

    /**
    * ...
    *
    * @param VBA ...
    * @param glBufferArray ...
    *
    * @return ...
    */

    public VertexBuffer add(VertexBufferAttribute VBA, float[] glBufferArray) {
        this.VAO.bind();

        VertexBufferObject VBO = new VertexBufferObject();
        this.VBOs.add(VBO);

        VBO.bind();
        VBO.buffer(glBufferArray, GL_DYNAMIC_DRAW);

        this.VAO.attribute(VBA.getIndex(), VBA.getSize(), GL_FLOAT, false, 0, 0);

        VBO.unbind();
        VAO.unbind();

        return this;
    }

    /**
    * @return the {@link VertexArrayObject VAO} of this {@link VertexBuffer}.
    */

    public VertexArrayObject getVAO() {
        return this.VAO;
    }

    /**
    * Deletes {@link VertexArrayObject VAO} and {@link VertexBufferObject VBOs} from this {@link VertexBuffer}.
    */

    public void delete() {
        this.VAO.delete();

        for(VertexBufferObject VBO : this.VBOs)
            VBO.delete();
    }

    /**
    * ...
    *
    * @param Vertex ...
    *
    * @return ...
    */

    public static VertexBuffer create(Vertex Vertex) {
        return new VertexBuffer()
            .add(new VertexBufferAttribute(0, 3), Vertex.getPositions())
            .add(new VertexBufferAttribute(1, 2), Vertex.getUVs())
            .add(new VertexBufferAttribute(2, 3), Vertex.getNormals())
            .add(new VertexBufferAttribute(3, 4), Vertex.getAOs());
    }
}