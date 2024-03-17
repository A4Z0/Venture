package org.a4z0.venture.models;

import org.a4z0.venture.Interval;
import org.a4z0.venture.model.Model;
import org.a4z0.venture.vertex.Vertex;

/**
* How to load a ".obj".
*/

public class VertexExample {

    public static final float[] AO = new float[]{
        0f, 0f, 0f, 0f,
        0f, 0f, 0f, 0f,
        0f, 0f, 0f, 0f,
        0f, 0f, 0f, 0f,
        0f, 0f, 0f, 0f,
        0f, 0f, 0f, 0f,
    };

    public static final Model CUBE_MODEL = Model.getFrom("assets/geometry/block.obj");

    public static Vertex VERTEX;

    /**
    * 1. Upload template.
    * 2. Add Cube vertices.
    */

    public static void main(String[] args) {
        Interval.reset();

        VERTEX = new Vertex();

        float[] CUBE_VERTEX_ARRAY = CUBE_MODEL.getVertices();
        float[] CUBE_UV_ARRAY = CUBE_MODEL.getUVs();
        float[] CUBE_NORMAL_ARRAY = CUBE_MODEL.getNormals();

        for(int i = 0; i < (CUBE_VERTEX_ARRAY.length / 6); i += 6)
            VERTEX.vertex(new float[]{
                CUBE_VERTEX_ARRAY[i], CUBE_VERTEX_ARRAY[i + 1], CUBE_VERTEX_ARRAY[i + 2],
                CUBE_VERTEX_ARRAY[i + 1], CUBE_VERTEX_ARRAY[i + 2], CUBE_VERTEX_ARRAY[i + 3],
                CUBE_VERTEX_ARRAY[i + 4], CUBE_VERTEX_ARRAY[i + 5], CUBE_VERTEX_ARRAY[i + 6],
                CUBE_VERTEX_ARRAY[i + 7], CUBE_VERTEX_ARRAY[i + 8], CUBE_VERTEX_ARRAY[i + 9],
                CUBE_VERTEX_ARRAY[i + 10], CUBE_VERTEX_ARRAY[i + 11], CUBE_VERTEX_ARRAY[i + 12],
                CUBE_VERTEX_ARRAY[i + 13], CUBE_VERTEX_ARRAY[i + 14], CUBE_VERTEX_ARRAY[i + 15]
            }, CUBE_UV_ARRAY, CUBE_NORMAL_ARRAY, AO);

        System.out.println("Execution elapsed time -> " + (Interval.stop() / 1000.0) + " seconds!");
    }
}