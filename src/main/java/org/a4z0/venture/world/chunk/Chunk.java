package org.a4z0.venture.world.chunk;

import org.a4z0.venture.world.World;
import org.a4z0.venture.world.block.Block;

/**
* ...
*/

public interface Chunk {

    int CHUNK_SIZE_X = 16;
    int CHUNK_SIZE_Y = 128;
    int CHUNK_SIZE_Z = 16;

    int CHUNK_BLOCK_ARRAY_X = CHUNK_SIZE_X + 1;
    int CHUNK_BLOCK_ARRAY_Y = CHUNK_SIZE_Y + 1;
    int CHUNK_BLOCK_ARRAY_Z = CHUNK_SIZE_Z + 1;

    int CHUNK_BLOCK_ARRAY_LENGTH = CHUNK_BLOCK_ARRAY_X * CHUNK_BLOCK_ARRAY_Y * CHUNK_BLOCK_ARRAY_Z;

    /**
    * @return ...
    */

    int getX();

    /**
    * @return ...
    */

    int getZ();

    /**
    * @return ...
    */

    World getWorld();

    /**
    * ...
    *
    * @param Block ...
    */

    void setBlock(Block Block);

    /**
    * ...
    *
    * @param x ...
    * @param y ...
    * @param z ...
    *
    * @return ...
    */

    Block getBlock(int x, int y, int z);

    /**
    * ...
    *
    * @param x ...
    * @param y ...
    * @param z ...
    *
    * @return ...
    */

    boolean hasBlockAt(int x, int y, int z);
}