package org.a4z0.venture.gl.vertex;

/**
* ...
*/

public class VertexBufferAttribute {

    protected final int Index;
    protected final int Size;

    /**
    * Construct a {@link VertexBufferAttribute}.
    *
    * @param Index Index that the {@link VertexBufferObject VBO} will be set.
    * @param Size ...
    */

    public VertexBufferAttribute(int Index, int Size) {
        this.Index = Index;
        this.Size = Size;
    }

    /**
    * @return the Index.
    */

    public int getIndex() {
        return this.Index;
    }

    /**
    * @return the Size.
    */

    public int getSize() {
        return this.Size;
    }
}