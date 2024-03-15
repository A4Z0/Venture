package org.a4z0.venture.vertex;

import org.joml.Vector2f;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.List;

/**
* ...
*/

public class Vertex {

    protected final List<Float> STREAM_POSITIONS;
    protected final List<Float> STREAM_UVs;
    protected final List<Float> STREAM_NORMALS;

    /**
    * Construct a {@link Vertex}.
    */

    public Vertex() {
        this.STREAM_POSITIONS = new ArrayList<>();
        this.STREAM_UVs = new ArrayList<>();
        this.STREAM_NORMALS = new ArrayList<>();
    }

    /**
    * Construct a {@link Vertex}.
    *
    * @param POSITIONS ...
    * @param UVs ...
    * @param NORMALS ...
    */

    public Vertex(Vector3f[] POSITIONS, Vector2f[] UVs, Vector3f[] NORMALS) {
        this();

        this.vertex(POSITIONS, UVs, NORMALS);
    }

    /**
    * Construct a {@link Vertex}.
    *
    * @param POSITIONS ...
    * @param UVs ...
    * @param NORMALS ...
    */

    public Vertex(float[] POSITIONS, float[] UVs, float[] NORMALS) {
        this();

        this.vertex(POSITIONS, UVs, NORMALS);
    }

    /**
    * ...
    *
    * @param POSITIONS ...
    * @param UVs ...
    * @param NORMALS ...
    *
    * @return ...
    */

    public Vertex vertex(Vector3f[] POSITIONS, Vector2f[] UVs, Vector3f[] NORMALS) {
        for(Vector3f POSITION : POSITIONS) {
            this.STREAM_POSITIONS.add(POSITION.x);
            this.STREAM_POSITIONS.add(POSITION.y);
            this.STREAM_POSITIONS.add(POSITION.z);
        }

        for(Vector2f UV : UVs) {
            this.STREAM_UVs.add(UV.x);
            this.STREAM_UVs.add(UV.y);
        }

        for(Vector3f NORMAL : NORMALS) {
            this.STREAM_NORMALS.add(NORMAL.x);
            this.STREAM_NORMALS.add(NORMAL.y);
            this.STREAM_NORMALS.add(NORMAL.z);
        }

        return this;
    }

    /**
    * ...
    *
    * @param POSITIONS ...
    * @param UVs ...
    * @param NORMALS ...
    *
    * @return ...
    */

    public Vertex vertex(float[] POSITIONS, float[] UVs, float[] NORMALS) {
        for(int i = 0; i < POSITIONS.length; i += 3) {
            this.STREAM_POSITIONS.add(POSITIONS[i]);
            this.STREAM_POSITIONS.add(POSITIONS[i + 1]);
            this.STREAM_POSITIONS.add(POSITIONS[i + 2]);
        }

        for(int i = 0; i < UVs.length; i += 2) {
            this.STREAM_UVs.add(UVs[i]);
            this.STREAM_UVs.add(UVs[i + 1]);
        }

        for(int i = 0; i < NORMALS.length; i += 3) {
            this.STREAM_NORMALS.add(NORMALS[i]);
            this.STREAM_NORMALS.add(NORMALS[i + 1]);
            this.STREAM_NORMALS.add(NORMALS[i + 2]);
        }

        return this;
    }

    /**
    * @return ...
    */

    public float[] getPositions() {
        float[] FLOAT_ARRAY = new float[this.STREAM_POSITIONS.size()];

        for(int i = 0; i < this.STREAM_POSITIONS.size(); i++)
            FLOAT_ARRAY[i] = this.STREAM_POSITIONS.get(i);

        return FLOAT_ARRAY;
    }

    /**
    * @return ...
    */

    public float[] getUVs() {
        float[] FLOAT_ARRAY = new float[this.STREAM_UVs.size()];

        for(int i = 0; i < this.STREAM_UVs.size(); i++)
            FLOAT_ARRAY[i] = this.STREAM_UVs.get(i);

        return FLOAT_ARRAY;
    }

    /**
    * @return ...
    */

    public float[] getNormals() {
        float[] FLOAT_ARRAY = new float[this.STREAM_NORMALS.size()];

        for(int i = 0; i < this.STREAM_NORMALS.size(); i++)
            FLOAT_ARRAY[i] = this.STREAM_NORMALS.get(i);

        return FLOAT_ARRAY;
    }
}