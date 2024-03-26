package org.a4z0.venture.gl.vertex;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL15.*;

// FIXME: This class needs an Fix. But its working for now.

@Deprecated
public class VertexBuffer {

    protected final VertexArrayObject VAO = new VertexArrayObject();
    protected final List<VertexBufferObject> VBOs = new ArrayList<>();

    /**
    * Construct a {@link VertexBuffer}.
    */

    public VertexBuffer() {

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

    public VertexBuffer prepare(float[] glBufferArray) {
        {
            this.VAO.bind();

            VertexBufferObject VBO = new VertexBufferObject();
            this.VBOs.add(VBO);

            VBO.bind();
            VBO.buffer(glBufferArray, GL_DYNAMIC_DRAW);

            this.VAO.attribute(0, 3, GL_FLOAT, true, VertexStream.VERTEX_SIZE_ELEMENTS * Float.BYTES, 0);
            this.VAO.attribute(1, 2, GL_FLOAT, true, VertexStream.VERTEX_SIZE_ELEMENTS * Float.BYTES, (3) * Float.BYTES);
            this.VAO.attribute(2, 3, GL_FLOAT, true, VertexStream.VERTEX_SIZE_ELEMENTS * Float.BYTES, (3 + 2) * Float.BYTES);
            this.VAO.attribute(3, 1, GL_FLOAT, true, VertexStream.VERTEX_SIZE_ELEMENTS * Float.BYTES, (3 + 2 + 3) * Float.BYTES);

            VBO.unbind();
            VAO.unbind();
        }
        /*{
            this.VAO.bind();

            VertexBufferObject VBO = new VertexBufferObject();
            this.VBOs.add(VBO);

            VBO.bind();
            VBO.buffer(glBufferArray, GL_DYNAMIC_DRAW);

            this.VAO.attribute(1, 2, GL_FLOAT, false, Vertices.VERTEX_SIZE_ELEMENTS * Float.BYTES,  * Float.BYTES);

            VBO.unbind();
            VAO.unbind();
        }*/

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
    * @param Vertices ...
    *
    * @return ...
    */

    public static VertexBuffer create(VertexStream Vertices) {
        return new VertexBuffer().prepare(Vertices.elements());
    }
}