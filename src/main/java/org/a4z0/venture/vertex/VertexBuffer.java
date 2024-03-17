package org.a4z0.venture.vertex;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL15.*;

/**
* ...
*/

public class VertexBuffer {

    protected final VertexArrayObject VAO;
    protected final List<VertexBufferObject> VBOs;

    /**
    * Construct a {@link VertexBuffer}.
    */

    public VertexBuffer() {
        this.VAO = new VertexArrayObject();
        this.VBOs = new ArrayList<>();
    }

    /**
    * ...
    *
    * @param Index ...
    * @param Size ...
    * @param FLOAT_ARRAY ...
    *
    * @return ...
    */

    public VertexBuffer add(int Index, int Size, float[] FLOAT_ARRAY) {
        return this.add(new VertexBufferAttribute(new VertexBufferObject(), Index, Size), FLOAT_ARRAY);
    }

    /**
    * ...
    *
    * @param VBA ...
    * @param FLOAT_ARRAY ...
    *
    * @return ...
    */

    public VertexBuffer add(VertexBufferAttribute VBA, float[] FLOAT_ARRAY) {
        this.VAO.bind();

        VBA.getVBO().bind();
        VBA.getVBO().addData(FLOAT_ARRAY, GL_DYNAMIC_DRAW);

        this.VAO.attr(VBA.getIndex(), VBA.getSize(), 0, 0);

        VBA.getVBO().unbind();
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
    * Create a {@link VertexBuffer} from a {@link Vertex}.
    *
    * @param Vertex {@link Vertex} that will instantiate the {@link VertexBuffer}.
    *
    * @return a new {@link VertexBuffer}.
    */

    public static VertexBuffer create(Vertex Vertex) {
        return new VertexBuffer()
            .add(0, 3, Vertex.getPositions())
            .add(1, 2, Vertex.getUVs())
            .add(2, 3, Vertex.getNormals())
            .add(3, 4, Vertex.getAOs());
    }
}