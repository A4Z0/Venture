package org.a4z0.venture.world.chunk;

import jdk.jfr.Experimental;
import org.a4z0.venture.gl.vertex.VertexStream;
import org.a4z0.venture.gl.image.atlas.Address;
import org.a4z0.venture.position.Direction;
import org.a4z0.venture.tile.Tile;

/**
* ...
*/

@Experimental
public class Vertices {

    public static float[] UV = {
        0f, 0f,
        0f, 1f,
        1f, 1f,
        1f, 1f,
        1f, 0f,
        0f, 0f
    };

    public static float[] NORMAL = {
        0f, 0f, 0f,
        0f, 0f, 0f,
        0f, 0f, 0f,
        0f, 0f, 0f,
        0f, 0f, 0f,
        0f, 0f, 0f
    };

    public static float[] AO = {
        1f, 1f, 1f, 1f,
        1f, 1f, 1f, 1f,
        1f, 1f, 1f, 1f,
        1f, 1f, 1f, 1f,
        1f, 1f, 1f, 1f,
        1f, 1f, 1f, 1f,
    };

    public static final float[] NORTH = new float[]{
        -0.5f, 0.5f, -0.5f,
        -0.5f, -0.5f, -0.5f,
        0.5f, -0.5f, -0.5f,
        0.5f, -0.5f, -0.5f,
        0.5f, 0.5f, -0.5f,
        -0.5f, 0.5f, -0.5f
    };

    public static final float[] SOUTH = new float[]{
        -0.5f, 0.5f, 0.5f,
        -0.5f, -0.5f, 0.5f,
        0.5f, -0.5f, 0.5f,
        0.5f, -0.5f, 0.5f,
        0.5f, 0.5f, 0.5f,
        -0.5f, 0.5f, 0.5f
    };

    public static final float[] EAST = new float[]{
        0.5f,0.5f,-0.5f,
        0.5f,-0.5f,-0.5f,
        0.5f,-0.5f,0.5f,
        0.5f,-0.5f,0.5f,
        0.5f,0.5f,0.5f,
        0.5f,0.5f,-0.5f
    };

    public static final float[] WEST = new float[]{
        -0.5f, 0.5f, -0.5f,
        -0.5f, -0.5f, -0.5f,
        -0.5f, -0.5f, 0.5f,
        -0.5f, -0.5f, 0.5f,
        -0.5f, 0.5f, 0.5f,
        -0.5f, 0.5f, -0.5f
    };

    public static final float[] TOP = new float[]{
        -0.5f, 0.5f, 0.5f,
        -0.5f, 0.5f, -0.5f,
        0.5f, 0.5f, -0.5f,
        0.5f, 0.5f, -0.5f,
        0.5f, 0.5f, 0.5f,
        -0.5f, 0.5f, 0.5f
    };

    public static final float[] BOTTOM = new float[]{
        -0.5f, -0.5f, 0.5f,
        -0.5f, -0.5f, -0.5f,
        0.5f, -0.5f, -0.5f,
        0.5f, -0.5f, -0.5f,
        0.5f, -0.5f, 0.5f,
        -0.5f, -0.5f, 0.5f
    };

    /**
    * ...
    *
    * @param X ...
    * @param Y ...
    * @param Z ...
    * @param Direction ...
    */

    public static void stream(VertexStream Vertex, Direction Direction, Tile Tile, int X, int Y, int Z) {
        float PX = 0.5f + X;
        float NX = -0.5f + X;
        float PY = 0.5f + Y;
        float NY = -0.5f + Y;
        float PZ = 0.5f + Z;
        float NZ = -0.5f + Z;

        switch (Direction) {
            case NORTH: {
                float[] UV = getUV(Tile.getNorth());

                Vertex.vertex(PX, PY, NZ, UV[0], UV[1], 0, 0, -1, 1);
                Vertex.vertex(PX, NY, NZ, UV[2], UV[3], 0, 0, -1, 1);
                Vertex.vertex(NX, NY, NZ, UV[4], UV[5], 0, 0, -1, 1);
                Vertex.vertex(NX, NY, NZ, UV[6], UV[7], 0, 0, -1, 1);
                Vertex.vertex(NX, PY, NZ, UV[8], UV[9], 0, 0, -1, 1);
                Vertex.vertex(PX, PY, NZ, UV[10], UV[11], 0, 0, -1, 1);

                break;
            }
            case SOUTH: {
                float[] UV = getUV(Tile.getSouth());

                Vertex.vertex(NX, PY, PZ, UV[0], UV[1], 0, 0, -1, 1);
                Vertex.vertex(NX, NY, PZ, UV[2], UV[3], 0, 0, -1, 1);
                Vertex.vertex(PX, NY, PZ, UV[4], UV[5], 0, 0, -1, 1);
                Vertex.vertex(PX, NY, PZ, UV[6], UV[7], 0, 0, -1, 1);
                Vertex.vertex(PX, PY, PZ, UV[8], UV[9], 0, 0, -1, 1);
                Vertex.vertex(NX, PY, PZ, UV[10], UV[11], 0, 0, -1, 1);

                break;
            }
            case EAST: {
                float[] UV = getUV(Tile.getEast());

                Vertex.vertex(PX, PY, PZ, UV[0], UV[1], 0, 0, -1, 1);
                Vertex.vertex(PX, NY, PZ, UV[2], UV[3], 0, 0, -1, 1);
                Vertex.vertex(PX, NY, NZ, UV[4], UV[5], 0, 0, -1, 1);
                Vertex.vertex(PX, NY, NZ, UV[6], UV[7], 0, 0, -1, 1);
                Vertex.vertex(PX, PY, NZ, UV[8], UV[9], 0, 0, -1, 1);
                Vertex.vertex(PX, PY, PZ, UV[10], UV[11], 0, 0, -1, 1);

                break;
            }
            case WEST: {
                float[] UV = getUV(Tile.getWest());

                Vertex.vertex(NX, PY, NZ, UV[0], UV[1], 0, 0, -1, 1);
                Vertex.vertex(NX, NY, NZ, UV[2], UV[3], 0, 0, -1, 1);
                Vertex.vertex(NX, NY, PZ, UV[4], UV[5], 0, 0, -1, 1);
                Vertex.vertex(NX, NY, PZ, UV[6], UV[7], 0, 0, -1, 1);
                Vertex.vertex(NX, PY, PZ, UV[8], UV[9], 0, 0, -1, 1);
                Vertex.vertex(NX, PY, NZ, UV[10], UV[11], 0, 0, -1, 1);

                break;
            }
            case TOP: {
                float[] UV = getUV(Tile.getTop());

                Vertex.vertex(NX, PY, NZ, UV[0], UV[1], 0, 0, -1, 1);
                Vertex.vertex(NX, PY, PZ, UV[2], UV[3], 0, 0, -1, 1);
                Vertex.vertex(PX, PY, PZ, UV[4], UV[5], 0, 0, -1, 1);
                Vertex.vertex(PX, PY, PZ, UV[6], UV[7], 0, 0, -1, 1);
                Vertex.vertex(PX, PY, NZ, UV[8], UV[9], 0, 0, -1, 1);
                Vertex.vertex(NX, PY, NZ, UV[10], UV[11], 0, 0, -1, 1);

                break;
            }
            case BOTTOM: {
                float[] UV = getUV(Tile.getBottom());

                Vertex.vertex(NX, NY, PZ, UV[0], UV[1], 0, 0, -1, 1);
                Vertex.vertex(NX, NY, NZ, UV[2], UV[3], 0, 0, -1, 1);
                Vertex.vertex(PX, NY, NZ, UV[4], UV[5], 0, 0, -1, 1);
                Vertex.vertex(PX, NY, NZ, UV[6], UV[7], 0, 0, -1, 1);
                Vertex.vertex(PX, NY, PZ, UV[8], UV[9], 0, 0, -1, 1);
                Vertex.vertex(NX, NY, PZ, UV[10], UV[11], 0, 0, -1, 1);

                break;
            }
        }

        /*switch (Direction) {
            case NORTH: {
                for(int i = 0; i < NORTH.length / 3; i ++) {
                    Vertex.vertex(NORTH[i * 3] + X, NORTH[i * 3 + 1] + Y, NORTH[i * 3 + 2] + Z, UV[i * 2], UV[i * 2 + 1], 0, 0, 0, 0);
                }

                break;
            }
            case SOUTH: {
                for(int i = 0; i < SOUTH.length / 3; i ++) {
                    Vertex.vertex(SOUTH[i * 3] + X, SOUTH[i * 3 + 1] + Y, SOUTH[i * 3 + 2] + Z, UV[i * 2], UV[i * 2 + 1], 0,  0, 0, 0);
                }

                break;
            }
            case EAST: {
                for(int i = 0; i < EAST.length / 3; i ++) {
                    Vertex.vertex(EAST[i * 3] + X, EAST[i * 3 + 1] + Y, EAST[i * 3 + 2] + Z, UV[i * 2], UV[i * 2 + 1], 0,  0, 0, 0);
                }

                break;
            }
            case WEST: {
                for(int i = 0; i < WEST.length / 3; i ++) {
                    Vertex.vertex(WEST[i * 3] + X, WEST[i * 3 + 1] + Y, WEST[i * 3 + 2] + Z, UV[i * 2], UV[i * 2 + 1], 0,  0, 0, 0);
                }

                break;
            }
            case TOP: {
                for(int i = 0; i < TOP.length / 3; i ++) {
                    Vertex.vertex(TOP[i * 3] + X, TOP[i * 3 + 1] + Y, TOP[i * 3 + 2] + Z, UV[i * 2], UV[i * 2 + 1], 0,  0, 0, 0);
                }

                break;
            }
            case BOTTOM: {
                for(int i = 0; i < BOTTOM.length / 3; i ++) {
                    Vertex.vertex(BOTTOM[i * 3] + X, BOTTOM[i * 3 + 1] + Y, BOTTOM[i * 3 + 2] + Z, UV[i * 2], UV[i * 2 + 1], 0, 0, 0, 0);
                }

                break;
            }
        }*/
    }

    /**
    * ...
    *
    * @param Address ...
    *
    * @return ...
    */

    private static float[] getUV(Address Address) {
        float[] ADJUSTED_UV = new float[]{
            0f, 0f,
            0f, 1f,
            1f, 1f,
            1f, 1f,
            1f, 0f,
            0f, 0f
        };

        for(int i = 0; i < ADJUSTED_UV.length; i += 2) {
            ADJUSTED_UV[i] = (ADJUSTED_UV[i] * Address.getSize() + Address.getX()) / Address.getTileMap().getSize();
            ADJUSTED_UV[i + 1] = (ADJUSTED_UV[i + 1] * Address.getSize() + Address.getY()) / Address.getTileMap().getSize();
        }

        return ADJUSTED_UV;
    }
}