package org.a4z0.venture.chunk;

import org.a4z0.venture.block.Block;

import java.util.Iterator;

/**
* Iterates over the {@link Block Blocks} of a Chunk.
*/

public class ChunkIterator implements Iterator<Block> {

    protected int CURRENT_INDEX = 0;
    protected Chunk CHUNK;

    /**
    * Construct a {@link ChunkIterator}.
    *
    * @param CHUNK ...
    */

    protected ChunkIterator(Chunk CHUNK) {
        this.CHUNK = CHUNK;
    }

    @Override
    public boolean hasNext() {
        return this.CURRENT_INDEX < this.CHUNK.BLOCK_ARRAY.getLength();
    }

    @Override
    public Block next() {
        if(!hasNext())
            throw new ArrayIndexOutOfBoundsException("Couldn't find any more blocks in this Chunk.");

        return BlockRegister.getBlock(this.CHUNK.BLOCK_ARRAY.get(this.CURRENT_INDEX++));
    }
}