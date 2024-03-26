package org.a4z0.venture.world;

import org.a4z0.venture.world.block.Block;
import org.a4z0.venture.world.chunk.Chunk;
import org.a4z0.venture.world.chunk.EmptyChunk;
import org.a4z0.venture.world.chunk.LoadedChunk;

import java.util.Arrays;

/**
* ...
*/

public class Overworld implements World {

    protected final long Seed;

    protected final Chunk[] Chunks = new Chunk[CHUNK_ARRAY_LENGTH];

    /**
    * Construct a {@link Overworld}.
    *
    * @param Seed ...
    */

    public Overworld(long Seed) {
        this.Seed = Seed;

        // We create individual chunks by an offset of 16 of each axis.
        for(int x = CHUNK_MIN_X; x <= CHUNK_MAX_X; x++) {
            for(int z = CHUNK_MIN_Z; z <= CHUNK_MAX_Z; z++) {
                int AX = x + CHUNK_MAX_X;
                int AZ = z + CHUNK_MAX_Z;

                int Index = AX + CHUNK_ARRAY_X * AZ;

                this.Chunks[Index] = new LoadedChunk(this, x * 16, z * 16);
            }
        }
    }

    @Override
    public Chunk[] getChunks() {
        return this.Chunks;
    }

    @Override
    public Chunk getChunkAt(int x, int z) {
        int BX = (x >> CHUNK_X_SQRT) + CHUNK_MAX_X;
        int BZ = (z >> CHUNK_Z_SQRT) + CHUNK_MAX_Z;

        int Index = BX + CHUNK_ARRAY_X * BZ;

        if(Index < 0 || Index >= this.Chunks.length)
            return new EmptyChunk(this, Integer.MIN_VALUE, Integer.MIN_VALUE);

        return this.Chunks[Index];
    }

    @Override
    public void setBlock(Block block) {
        this.getChunkAt(block.getPosition().getX(), block.getPosition().getZ()).setBlock(block);
    }

    @Override
    public Block getBlock(int x, int y, int z) {
        return this.getChunkAt(x, z).getBlock(x, y, z);
    }

    @Override
    public boolean hasBlockAt(int x, int y, int z) {
        return this.getChunkAt(x, z).hasBlockAt(x, y, z);
    }
}