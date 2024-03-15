package org.a4z0.venture.chunk;

import org.a4z0.venture.block.Block;
import org.a4z0.venture.block.CraftMagicBlocks;
import org.a4z0.venture.block.Face;
import org.a4z0.venture.position.Position;
import org.a4z0.venture.texture.Textures;
import org.a4z0.venture.vertex.Vertex;

/**
* ...
*/

public class Mesh {

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

    /**
    * @param chunk ...
    *
    * @return ...
    */

    public static Vertex transform(Chunk chunk) {
        return transform(chunk.getBlocks());
    }

    /**
    * @param layer ...
    *
    * @return ...
    */

    public static Vertex transform(Layer layer) {
        return transform(layer.getBlocks());
    }

    /**
    * @return ...
    */

    public static Vertex transform(Block[] Blocks) {
        Vertex Stream = new Vertex();

        for(Block BLOCK : Blocks)
            if(BLOCK != null)
                for(Face BLOCK_FACE : Face.values())
                    if(CraftMagicBlocks.getBlockAt(BLOCK.getPosition(), BLOCK_FACE) == null)
                       generate(Stream, BLOCK, BLOCK_FACE);

        return Stream;
    }

    /**
    * ...
    *
    * @param Block ...
    * @param Face ...
    */

    public static void generate(Vertex Vertex, Block Block, Face Face) {
        generate(Vertex, Block.getPosition(), Block.getMaterial().getID(Face), Face);
    }

    /**
    * ...
    *
    * @param Position ...
    * @param Face ...
    */

    public static void generate(Vertex Vertex, Position Position, int ID, Face Face) {
        generate(Vertex, Position.getX(), Position.getY(), Position.getZ(), ID, Face);
    }

    /**
    * ...
    *
    * @param X ...
    * @param Y ...
    * @param Z ...
    * @param Face ...
    */

    public static void generate(Vertex Vertex, int X, int Y, int Z, int ID, Face Face) {
        switch (Face) {
            case NORTH: {
                Vertex.vertex(adjustPosition(NORTH, X, Y, Z), adjustUV(UV, ID), NORMAL);

                break;
            }
            case SOUTH: {
                Vertex.vertex(adjustPosition(SOUTH, X, Y, Z), adjustUV(UV, ID), NORMAL);

                break;
            }
            case EAST: {
                Vertex.vertex(adjustPosition(EAST, X, Y, Z), adjustUV(UV, ID), NORMAL);

                break;
            }
            case WEST: {
                Vertex.vertex(adjustPosition(WEST, X, Y, Z), adjustUV(UV, ID), NORMAL);

                break;
            }
            case TOP: {
                Vertex.vertex(adjustPosition(TOP, X, Y, Z), adjustUV(UV, ID), NORMAL);

                break;
            }
            case BOTTOM: {
                Vertex.vertex(adjustPosition(BOTTOM, X, Y, Z), adjustUV(UV, ID), NORMAL);

                break;
            }
        }
    }

    /**
    * ...
    *
    * @param Positions ...
    * @param X ...
    * @param Y ...
    * @param Z ...
    *
    * @return ...
    */

    public static float[] adjustPosition(float[] Positions, int X, int Y, int Z) {
        float[] ADJUSTED_POSITION = new float[Positions.length];

        for(int i = 0; i < Positions.length; i += 3) {
            ADJUSTED_POSITION[i] = (Positions[i] + X);
            ADJUSTED_POSITION[i + 1] = (Positions[i + 1] + Y);
            ADJUSTED_POSITION[i + 2] = (Positions[i + 2] + Z);
        }

        return ADJUSTED_POSITION;
    }

    /**
    * ...
    *
    * @param uv ...
    * @param index ...
    *
    * @return ...
    */

    public static float[] adjustUV(float[] uv, int index) {
        return adjustUV(index, Textures.TEXTURE_ARRAY.getWidth(), 16, Textures.TEXTURE_ARRAY.getLength(), uv);
    }

    /**
    * ...
    *
    * @param TEXTURE_INDEX ...
    * @param TEXTURE_ARRAY_SIZE ...
    * @param TEXTURE_SIZE ...
    * @param TEXTURE_ARRAY_LENGTH ...
    * @param UV ...
    *
    * @return ...
    */

    public static float[] adjustUV(int TEXTURE_INDEX, int TEXTURE_ARRAY_SIZE, int TEXTURE_SIZE, int TEXTURE_ARRAY_LENGTH, float[] UV) {
        float[] ADJUSTED_UV = new float[UV.length];

        for(int i = 0; i < ADJUSTED_UV.length; i += 2) {
            int xPos = (TEXTURE_INDEX % (TEXTURE_ARRAY_LENGTH / (TEXTURE_ARRAY_SIZE / TEXTURE_SIZE))) * TEXTURE_SIZE;
            int yPos = (TEXTURE_INDEX / (TEXTURE_ARRAY_LENGTH / (TEXTURE_ARRAY_SIZE / TEXTURE_SIZE))) * TEXTURE_SIZE;

            ADJUSTED_UV[i] = (UV[i] * TEXTURE_SIZE + xPos) / TEXTURE_ARRAY_SIZE;
            ADJUSTED_UV[i + 1] = (UV[i + 1] * TEXTURE_SIZE + yPos) / TEXTURE_ARRAY_SIZE;
        }

        return ADJUSTED_UV;
    }
}