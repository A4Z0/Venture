package org.a4z0.venture.world;

import org.a4z0.venture.block.Block;
import org.a4z0.venture.chunk.Chunk;

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
    public Chunk getChunkAt(int X, int Z) {
        return this.CHUNK_ARRAY[0];
    }

    @Override
    public void setBlock(Block BLOCK) {
        this.getChunkAt(BLOCK.getPosition().getX(), BLOCK.getPosition().getZ()).setBlock(BLOCK);
    }

    @Override
    public Block getBlock(int X, int Y, int Z) {
        return this.getChunkAt(X, Z).getBlock(X, Y, Z);
    }
}