package org.a4z0.venture.world;

import org.a4z0.venture.world.chunk.Chunk;
import org.a4z0.venture.world.enviroment.Sky;
import org.a4z0.venture.world.material.Material;

/**
* ...
*/

public class Overworld implements World {

    private final Sky WORLD_SKY;
    private final Chunk[] CHUNK_ARRAY = new Chunk[1];

    /**
    * Construct a {@link Overworld}.
    */

    public Overworld() {
        this.WORLD_SKY = Sky.DEFAULT_SKY;
        this.CHUNK_ARRAY[0] = new Chunk(this, 0, 0);
    }

    @Override
    public Sky getSky() {
        return this.WORLD_SKY;
    }

    @Override
    public Chunk[] getChunks() {
        return CHUNK_ARRAY;
    }

    @Override
    public Chunk getChunkAt(int x, int z) {
        return this.CHUNK_ARRAY[0];
    }

    @Override
    public void setBlock(int x, int y, int z, Material material) {
        this.getChunkAt(x, z).setBlock(x, y, z, material);
    }

    @Override
    public Material getBlock(int x, int y, int z) {
        return this.getChunkAt(x, z).getBlock(x, y, z);
    }
}