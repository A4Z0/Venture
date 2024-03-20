package org.a4z0.venture.util;

import org.a4z0.venture.gl.image.atlas.Address;
import org.a4z0.venture.gl.vertex.Vertex;
import org.a4z0.venture.world.position.Direction;

/**
* ...
*/

public class BlockVertex {

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

    public static final Vertex NORTH = new Vertex().vertex(new float[]{
        -0.5f, 0.5f, -0.5f,
        -0.5f, -0.5f, -0.5f,
        0.5f, -0.5f, -0.5f,
        0.5f, -0.5f, -0.5f,
        0.5f, 0.5f, -0.5f,
        -0.5f, 0.5f, -0.5f
    }, UV, NORMAL, AO);

    public static final Vertex SOUTH = new Vertex().vertex(new float[]{
        -0.5f, 0.5f, 0.5f,
        -0.5f, -0.5f, 0.5f,
        0.5f, -0.5f, 0.5f,
        0.5f, -0.5f, 0.5f,
        0.5f, 0.5f, 0.5f,
        -0.5f, 0.5f, 0.5f
    }, UV, NORMAL, AO);

    public static final Vertex EAST = new Vertex().vertex(new float[]{
        0.5f,0.5f,-0.5f,
        0.5f,-0.5f,-0.5f,
        0.5f,-0.5f,0.5f,
        0.5f,-0.5f,0.5f,
        0.5f,0.5f,0.5f,
        0.5f,0.5f,-0.5f
    }, UV, NORMAL, AO);

    public static final Vertex WEST = new Vertex().vertex(new float[]{
        -0.5f, 0.5f, -0.5f,
        -0.5f, -0.5f, -0.5f,
        -0.5f, -0.5f, 0.5f,
        -0.5f, -0.5f, 0.5f,
        -0.5f, 0.5f, 0.5f,
        -0.5f, 0.5f, -0.5f
    }, UV, NORMAL, AO);

    public static final Vertex TOP = new Vertex().vertex(new float[]{
        -0.5f, 0.5f, 0.5f,
        -0.5f, 0.5f, -0.5f,
        0.5f, 0.5f, -0.5f,
        0.5f, 0.5f, -0.5f,
        0.5f, 0.5f, 0.5f,
        -0.5f, 0.5f, 0.5f
    }, UV, NORMAL, AO);

    public static final Vertex BOTTOM = new Vertex().vertex(new float[]{
        -0.5f, -0.5f, 0.5f,
        -0.5f, -0.5f, -0.5f,
        0.5f, -0.5f, -0.5f,
        0.5f, -0.5f, -0.5f,
        0.5f, -0.5f, 0.5f,
        -0.5f, -0.5f, 0.5f
    }, UV, NORMAL, AO);

    /**
    * ...
    *
    * @param X ...
    * @param Y ...
    * @param Z ...
    * @param Direction ...
    */

    public static void stream(Vertex Vertex, Direction Direction, int X, int Y, int Z, Address Address) {
        switch (Direction) {
            case NORTH: {
                NORTH.offset(0, 0, new float[]{
                    X, Y, Z,
                    X, Y, Z,
                    X, Y, Z,
                    X, Y, Z,
                    X, Y, Z,
                    X, Y, Z
                });

                Vertex.vertex(NORTH.getPositions(), getUV(Address), NORTH.getNormals(), NORTH.getAOs());

                break;
            }
            case SOUTH: {
                SOUTH.offset(0, 0, new float[]{
                    X, Y, Z,
                    X, Y, Z,
                    X, Y, Z,
                    X, Y, Z,
                    X, Y, Z,
                    X, Y, Z
                });

                Vertex.vertex(SOUTH.getPositions(), getUV(Address), SOUTH.getNormals(), SOUTH.getAOs());

                break;
            }
            case EAST: {
                EAST.offset(0, 0, new float[]{
                    X, Y, Z,
                    X, Y, Z,
                    X, Y, Z,
                    X, Y, Z,
                    X, Y, Z,
                    X, Y, Z
                });

                Vertex.vertex(EAST.getPositions(), getUV(Address), EAST.getNormals(), EAST.getAOs());

                break;
            }
            case WEST: {
                WEST.offset(0, 0, new float[]{
                    X, Y, Z,
                    X, Y, Z,
                    X, Y, Z,
                    X, Y, Z,
                    X, Y, Z,
                    X, Y, Z
                });

                Vertex.vertex(WEST.getPositions(), getUV(Address), WEST.getNormals(), WEST.getAOs());

                break;
            }
            case TOP: {
                TOP.offset(0, 0, new float[]{
                    X, Y, Z,
                    X, Y, Z,
                    X, Y, Z,
                    X, Y, Z,
                    X, Y, Z,
                    X, Y, Z
                });

                Vertex.vertex(TOP.getPositions(), getUV(Address), TOP.getNormals(), TOP.getAOs());

                break;
            }
            case BOTTOM: {
                BOTTOM.offset(0, 0, new float[]{
                    X, Y, Z,
                    X, Y, Z,
                    X, Y, Z,
                    X, Y, Z,
                    X, Y, Z,
                    X, Y, Z
                });

                Vertex.vertex(BOTTOM.getPositions(), getUV(Address), BOTTOM.getNormals(), BOTTOM.getAOs());

                break;
            }
        }
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