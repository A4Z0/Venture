package org.a4z0.venture.vertex;

/**
* ...
*/

@Deprecated
public class VertexBufferAttribute {

    protected final Type A;
    protected final int B;
    protected final int C;

    /**
    * Construct a {@link VertexBufferAttribute}.
    *
    * @param ATTRIBUTE_TYPE ...
    * @param ATTRIBUTE_INDEX ...
    * @param ATTRIBUTE_LENGTH ...           
    */

    public VertexBufferAttribute(
        Type ATTRIBUTE_TYPE,
        int ATTRIBUTE_INDEX,
        int ATTRIBUTE_LENGTH
    ) {
        this.A = ATTRIBUTE_TYPE;
        this.B = ATTRIBUTE_INDEX;
        this.C = ATTRIBUTE_LENGTH;
    }

    /**
    * @return ...
    */

    public Type getType() {
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

    /**
    * ...
    */

    public enum Type {
        VERTEX,
        UV,
        NORMAL
    }
}