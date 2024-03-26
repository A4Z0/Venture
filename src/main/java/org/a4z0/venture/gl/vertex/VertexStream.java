package org.a4z0.venture.gl.vertex;

import jdk.jfr.Experimental;

import java.util.Arrays;

/**
* ...
*/

@Experimental
public class VertexStream {

    public static final int VERTEX_SIZE_ELEMENTS = 3 + 2 + 3 + 1;

    protected int length = 0;
    protected int vertices = 0;

    protected float[] elements;

    /**
    * Construct a {@link VertexStream}.
    */

    public VertexStream() {
        this(VERTEX_SIZE_ELEMENTS);
    }

    /**
    * Construct a {@link VertexStream}.
    *
    * @param i Initial length of this {@link VertexStream}.
    */

    public VertexStream(int i) {
        this.elements = new float[i];
    }

    /**
    * @return the amount of Vertices.
    */

    public int vertices() {
        return this.vertices;
    }

    /**
    * @return the elements of this {@link VertexStream}.
    */

    public float[] elements() {
        return Arrays.copyOf(this.elements, this.length);
    }

    /**
    * Adds a Vertex.
    *
    * @param x ...
    * @param y ...
    * @param z ...
    * @param uX ...
    * @param uY ...
    * @param nX ...
    * @param nY ...
    * @param nZ ...
    * @param ao ...
    *
    * @return this {@link VertexStream}.
    */

    public VertexStream vertex(float x, float y, float z, float uX, float uY, float nX, float nY, float nZ, float ao) {
        // If the Array can't hold anymore, increase it!
        if(this.length + VERTEX_SIZE_ELEMENTS > this.elements.length)
            this.elements = Arrays.copyOf(this.elements, this.elements.length * 2 + VERTEX_SIZE_ELEMENTS);

        this.elements[length++] = x;
        this.elements[length++] = y;
        this.elements[length++] = z;
        this.elements[length++] = uX;
        this.elements[length++] = uY;
        this.elements[length++] = nX;
        this.elements[length++] = nY;
        this.elements[length++] = nZ;
        this.elements[length++] = ao;

        this.vertices++;

        return this;
    }
}