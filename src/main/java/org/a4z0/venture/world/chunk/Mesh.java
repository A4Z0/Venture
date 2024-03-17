package org.a4z0.venture.world.chunk;

import org.a4z0.venture.world.block.Block;
import org.a4z0.venture.world.block.CraftMagicBlocks;
import org.a4z0.venture.world.block.LightSource;
import org.a4z0.venture.world.Direction;
import org.a4z0.venture.light.Light;
import org.a4z0.venture.vertex.Vertex;
import org.a4z0.venture.world.Position;
import org.a4z0.venture.texture.Textures;

import java.util.ArrayList;
import java.util.List;

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

    public static float[] AO = {
        1f, 1f, 1f, 1f,
        1f, 1f, 1f, 1f,
        1f, 1f, 1f, 1f,
        1f, 1f, 1f, 1f,
        1f, 1f, 1f, 1f,
        1f, 1f, 1f, 1f,
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

        List<Light> Lights = new ArrayList<>();

        for(Block BLOCK : Blocks) {
            if(BLOCK instanceof LightSource)
                Lights.add(((LightSource) BLOCK).getLight());
        }

        for(Block BLOCK : Blocks)
            if(BLOCK != null)
                for(Direction BLOCK_FACE : Direction.values())
                    if(CraftMagicBlocks.getBlockAt(BLOCK.getPosition(), BLOCK_FACE) == null)
                       generate(Stream, BLOCK, BLOCK_FACE, Lights.toArray(Lights.toArray(new Light[0])));

        return Stream;
    }

    /**
    * ...
    *
    * @param Block ...
    * @param Face ...
    */

    public static void generate(Vertex Vertex, Block Block, Direction Face, Light[] Lights) {
        generate(Vertex, Block.getPosition(), Block.getMaterial().getID(Face), Face, Lights);
    }

    /**
    * ...
    *
    * @param Position ...
    * @param Face ...
    */

    public static void generate(Vertex Vertex, Position Position, int ID, Direction Face, Light[] Lights) {
        generate(Vertex, Position.getX(), Position.getY(), Position.getZ(), ID, Face, Lights);
    }

    /**
    * ...
    *
    * @param X ...
    * @param Y ...
    * @param Z ...
    * @param Face ...
    */

    public static void generate(Vertex Vertex, int X, int Y, int Z, int ID, Direction Face, Light[] Lights) {
        switch (Face) {
            case NORTH: {
                Vertex.vertex(adjustPosition(NORTH, X, Y, Z), adjustUV(UV, ID), NORMAL, adjustAO(X, Y, Z, Lights, AO));

                break;
            }
            case SOUTH: {
                Vertex.vertex(adjustPosition(SOUTH, X, Y, Z), adjustUV(UV, ID), NORMAL, adjustAO(X, Y, Z, Lights, AO));

                break;
            }
            case EAST: {
                Vertex.vertex(adjustPosition(EAST, X, Y, Z), adjustUV(UV, ID), NORMAL, adjustAO(X, Y, Z, Lights, AO));

                break;
            }
            case WEST: {
                Vertex.vertex(adjustPosition(WEST, X, Y, Z), adjustUV(UV, ID), NORMAL, adjustAO(X, Y, Z, Lights, AO));

                break;
            }
            case TOP: {
                Vertex.vertex(adjustPosition(TOP, X, Y, Z), adjustUV(UV, ID), NORMAL, adjustAO(X, Y, Z, Lights, AO));

                break;
            }
            case BOTTOM: {
                Vertex.vertex(adjustPosition(BOTTOM, X, Y, Z), adjustUV(UV, ID), NORMAL, adjustAO(X, Y, Z, Lights, AO));

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

    /**
    * ...
    *
    * @param X ...
    * @param Y ...
    * @param Z ...
    * @param Lights ...
    * @param AO ...
    *
    * @return ...
    */

    public static float[] adjustAO(int X, int Y, int Z, Light[] Lights, float[] AO) {
        if(true)
            return AO;

        float[] ADJUSTED_AO = AO.clone();

        for (Light light : Lights) {
            float Distance = light.getPosition().distance(X, Y, Z);

            if (Distance > light.getLevel())
                continue;

            // Calculando a atenuação da luz com base na distância
            float attenuation = (light.getLevel() - Distance) / light.getLevel();
            float intensityAttenuation = attenuation * attenuation; // Atenuação da intensidade
            float colorAttenuation = attenuation * attenuation; // Atenuação da cor

            for(int i = 0; i < AO.length; i += 4) {
                ADJUSTED_AO[i] += (light.getRed() * colorAttenuation);
                ADJUSTED_AO[i + 1] += (light.getGreen() * colorAttenuation);
                ADJUSTED_AO[i + 2] += (light.getBlue() * colorAttenuation);

                ADJUSTED_AO[i] = Math.min(ADJUSTED_AO[i], 1.0f);
                ADJUSTED_AO[i + 1] = Math.min(ADJUSTED_AO[i + 1], 1.0f);
                ADJUSTED_AO[i + 2] = Math.min(ADJUSTED_AO[i + 2], 1.0f);

                // Adicionar a intensidade da luz ao valor de oclusão ambiental
                ADJUSTED_AO[i + 3] += intensityAttenuation;

                // Garantir que o valor de oclusão ambiental não ultrapasse 1.0f
                ADJUSTED_AO[i + 3] = Math.min(ADJUSTED_AO[i + 3], 1.0f);
            }
        }

        return ADJUSTED_AO;
    }
}