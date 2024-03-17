package org.a4z0.venture.world.chunk;

import org.a4z0.venture.Array3d;
import org.a4z0.venture.world.block.Block;

/**
* ...
*/

public class BlockRegister {

    public static final Array3d<Block> REGISTERED_BLOCKS = new Array3d<>(Block.class, Chunk.CHUNK_SIZE_X, -Chunk.CHUNK_SIZE_Y, Chunk.CHUNK_SIZE_Y, -Chunk.CHUNK_SIZE_Y, Chunk.CHUNK_SIZE_Z, -Chunk.CHUNK_SIZE_Z);

    /**
     * ...
     *
     * @param BLOCK ...
     */

    public static int setBlock(Block BLOCK) {
        return REGISTERED_BLOCKS.set(BLOCK.getPosition().getX(), BLOCK.getPosition().getY(), BLOCK.getPosition().getZ(), BLOCK);
    }

    public static Block getBlock(int X, int Y, int Z) {
        return REGISTERED_BLOCKS.get(X, Y, Z);
    }

    /**
    * @param ID ...
    *
    * @return ...
    */

    public static Block getBlock(Integer ID) {
        if(ID == null)
            return null;

        return REGISTERED_BLOCKS.get(ID);
    }
}