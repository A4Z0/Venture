package org.a4z0.venture.vertex;

import java.util.*;

/**
* A {@link Vertex} represented by Positions, UVs, Normals and AOs.
*
* <br>
* <br>
*
* Each part of the Vertex is represented by its index.
*
* <ul name="Parts">
*   <li>0: Positions</li>
*   <li>1: UVs</li>
*   <li>2: Normals</li>
*   <li>3: AOs</li>
* </ul>
*/

public class Vertex {

    protected final List<List<Float>> parts;

    /**
    * Construct a {@link Vertex}.
    */

    public Vertex() {
        this.parts = List.of(
            new ArrayList<>(),
            new ArrayList<>(),
            new ArrayList<>(),
            new ArrayList<>(),
            new ArrayList<>(),
            new ArrayList<>(),
            new ArrayList<>(),
            new ArrayList<>()
        );
    }

    /**
    * @return the length of positions.
    */

    public int getLength() {
        return (Vertex.secure(0, this).size() / 3);
    }

    /**
    * Adds Positions to this {@link Vertex} and returns itself.
    *
    * @param positions Positions that will be added.
    *
    * @return this {@link Vertex}.
    */

    public Vertex vertex(float[] positions) {
        return this.vertex(positions, new float[2 * (positions.length)]);
    }

    /**
    * Adds Positions and UVs to this {@link Vertex} and returns itself.
    *
    * @param positions Positions that will be added.
    * @param uvs UVs that will be added.
    *
    * @return this {@link Vertex}.
    */

    public Vertex vertex(float[] positions, float[] uvs) {
        return this.vertex(positions, uvs, new float[3 * (positions.length / 3)]);
    }

    /**
    * Adds Positions, UVs and Normals to this {@link Vertex} and returns itself.
    *
    * @param positions Positions that will be added.
    * @param uvs UVs that will be added.
    * @param normals Normals that will be added.
    *
    * @return this {@link Vertex}.
    */

    public Vertex vertex(float[] positions, float[] uvs, float[] normals) {
        return this.vertex(positions, uvs, normals, new float[4 * (positions.length / 3)]);
    }

    /**
    * Adds Positions, UVs, Normals and AOs to this {@link Vertex} and returns itself.
    *
    * @param positions Positions that will be added.
    * @param uvs UVs that will be added.
    * @param normals Normals that will be added.
    * @param aos AOs that will be added.
    *
    * @return this {@link Vertex}.
    */

    public Vertex vertex(float[] positions, float[] uvs, float[] normals, float[] aos) {
        for(int i = 0; i < (positions.length / 3); i++) {
            Vertex.add(i, 3, Vertex.secure(0, this), positions);
            Vertex.add(i, 2, Vertex.secure(1, this), uvs);
            Vertex.add(i, 3, Vertex.secure(2, this), normals);
            Vertex.add(i, 4, Vertex.secure(3, this), aos);
            Vertex.add(i, 3, Vertex.secure(0, true, this), new float[positions.length]);
            Vertex.add(i, 2, Vertex.secure(1, true, this), new float[uvs.length]);
            Vertex.add(i, 3, Vertex.secure(2, true, this), new float[normals.length]);
            Vertex.add(i, 4, Vertex.secure(3, true, this), new float[aos.length]);
        } return this;
    }

    public Vertex offset(int index, int where, float[] array) {
        return this.offset(index, this.getLength(), where, array);
    }

    /**
    * Offsets a specific {@link Vertex} part.
    *
    * @param index Index of the {@link Vertex} where the offset will start.
    * @param until Index of the {@link Vertex} where the offset will end.
    *
    * <br>
    *
    * @param where Part of the {@link Vertex} represented by an <a href=#Parts>Index</a> will undergo the offset.
    * A value that isn't represented in the <a href=#Parts>Index</a> will result in no offset being applied.
    *
    * <br>
    *
    * @param array An Array containing the offset values.
    *
    * @return this {@link Vertex}.
    */

    public Vertex offset(int index, int until, int where, float[] array) {
        for(int i = index; i < until; i++) {
            Vertex.set(i, switch (where) {
                case 0, 2 -> 3;
                case 1 -> 2;
                default -> 4;
            }, Vertex.secure(where, true, this), array);
        } return this;
    }

    public Vertex shift(int index, int where, float[] array) {
        return this.shift(index, this.getLength(), where, array);
    }

    /**
    * Shifts a specific {@link Vertex} part.
    *
    * @param index Index of the {@link Vertex} where the shift will start.
    * @param until Index of the {@link Vertex} where the shift will end.
    *
    * <br>
    *
    * @param where Part of the {@link Vertex} represented by an <a href=#Parts>Index</a> will undergo the shift.
    * A value that isn't represented in the <a href=#Parts>Index</a> will result in no shift being applied.
    *
    * <br>
    *
    * @param array An Array containing the shift values.
    *
    * @return this {@link Vertex}.
    */

    public Vertex shift(int index, int until, int where, float[] array) {
        for(int i = index; i < until; i++) {
            Vertex.mix(i, switch (where) {
                case 0, 2 -> 3;
                case 1 -> 2;
                default -> 4;
            }, Vertex.secure(where, this), array);
        } return this;
    }

    /**
    * @return the Positions of this {@link Vertex}.
    */

    public float[] getPositions() {
        return Vertex.parse(0, this);
    }

    /**
    * @return the UVs of this {@link Vertex}.
    */

    public float[] getUVs() {
        return Vertex.parse(1, this);
    }

    /**
    * @return the Normals of this {@link Vertex}.
    */

    public float[] getNormals() {
        return Vertex.parse(2, this);
    }

    /**
    * @return the AOS of this {@link Vertex}.
    */

    public float[] getAOs() {
        return Vertex.parse(3, this);
    }

    /**
    * ...
    *
    * @param Index ...
    * @param Offset ...
    * @param FLOAT_LIST ...
    * @param FLOAT_ARRAY ...
    */

    protected static void mix(int Index, int Offset, List<Float> FLOAT_LIST, float[] FLOAT_ARRAY) {
        for(int i = 0; i < Offset; i++)
            FLOAT_LIST.set((Index * Offset) + i, FLOAT_LIST.get((Index * Offset) + i) + FLOAT_ARRAY[i * Offset]);
    }

    /**
    * ...
    *
    * @param Index ...
    * @param Offset ...
    * @param FLOAT_LIST ...
    * @param FLOAT_ARRAY ...
    */

    protected static void set(int Index, int Offset, List<Float> FLOAT_LIST, float[] FLOAT_ARRAY) {
        for(int i = 0; i < Offset; i++)
            FLOAT_LIST.set((Index * Offset) + i, FLOAT_ARRAY[i * Offset]);
    }

    /**
    * ...
    *
    * @param Index ...
    * @param Offset ...
    * @param FLOAT_LIST ...
    * @param FLOAT_ARRAY ...
    */

    protected static void add(int Index, int Offset, List<Float> FLOAT_LIST, float[] FLOAT_ARRAY) {
        for(int i = 0; i < Offset; i++)
            FLOAT_LIST.add(FLOAT_ARRAY[(Index * Offset) + i]);
    }

    /**
    * ...
    *
    * @param index ...
    * @param Vertex ...
    *
    * @return ...
    */

    protected static float[] parse(int index, Vertex Vertex) {
        float[] FLOAT_ARRAY = new float[secure(index, Vertex).size()];

        for(int i = 0; i < FLOAT_ARRAY.length; i++)
            FLOAT_ARRAY[i] = (secure(index, Vertex).get(i) + secure(index, true, Vertex).get(i));

        return FLOAT_ARRAY;
    }

    /**
    * ...
    *
    * @param index ...
    * @param vertex ...
    *
    * @return ...
    */

    protected static List<Float> secure(int index, Vertex vertex) {
        return secure(index, false, vertex);
    }

    /**
    * ...
    *
    * @param index ...
    * @param isOffset ...
    * @param vertex ...
    *
    * @return ...
    */

    protected static List<Float> secure(int index, boolean isOffset, Vertex vertex) {
        return !isOffset ? vertex.parts.get(index) : vertex.parts.get(index + 4);
    }
}