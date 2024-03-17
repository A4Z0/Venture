package org.a4z0.venture.vertex;

/**
* ...
*/

public class VertexBufferAttribute {

    protected final VertexBufferObject A;
    protected final int B;
    protected final int C;

    /**
    * Construct a {@link VertexBufferAttribute}.
    *
    * @param VAO ...
    * @param ATTRIBUTE_INDEX ...
    * @param ATTRIBUTE_LENGTH ...
    */

    public VertexBufferAttribute(
        VertexBufferObject VAO,
        int ATTRIBUTE_INDEX,
        int ATTRIBUTE_LENGTH
    ) {
        this.A = VAO;
        this.B = ATTRIBUTE_INDEX;
        this.C = ATTRIBUTE_LENGTH;
    }

    /**
    * @return ...
    */

    public VertexBufferObject getVBO() {
        return this.A;
    }

    /**
    * @return ...
    */

    public int getIndex() {
        return this.B;
    }

    /**
    * @return ...
    */

    public int getSize() {
        return this.C;
    }
}