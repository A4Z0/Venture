package org.a4z0.venture.world.block;

import org.a4z0.venture.vertex.Vertex;
import org.a4z0.venture.world.Direction;

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
        0f, 0f, 0f, 0f,
        0f, 0f, 0f, 0f,
        0f, 0f, 0f, 0f,
        0f, 0f, 0f, 0f,
        0f, 0f, 0f, 0f,
        0f, 0f, 0f, 0f,
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

    public static void stream(Vertex Vertex, int X, int Y, int Z, Direction Direction) {
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

                Vertex.vertex(NORTH.getPositions(), NORTH.getUVs(), NORTH.getNormals(), NORTH.getAOs());

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

                Vertex.vertex(SOUTH.getPositions(), SOUTH.getUVs(), SOUTH.getNormals(), SOUTH.getAOs());

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

                Vertex.vertex(EAST.getPositions(), EAST.getUVs(), EAST.getNormals(), EAST.getAOs());

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

                Vertex.vertex(WEST.getPositions(), WEST.getUVs(), WEST.getNormals(), WEST.getAOs());

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

                Vertex.vertex(TOP.getPositions(), TOP.getUVs(), TOP.getNormals(), TOP.getAOs());

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

                Vertex.vertex(BOTTOM.getPositions(), BOTTOM.getUVs(), BOTTOM.getNormals(), BOTTOM.getAOs());

                break;
            }
        }
    }
}