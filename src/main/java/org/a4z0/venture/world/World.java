package org.a4z0.venture.world;

import org.a4z0.venture.world.block.Block;
import org.a4z0.venture.world.chunk.Chunk;

/**
* ...
*/

public interface World {

    /**
    * @return ...
    */

    Sky getSky();

    /**
    * @return ...
    */

    Chunk[] getChunks();

    /**
    * ...
    *
    * @param X ...
    * @param Z ...
    *
    * @return ...
    */

    Chunk getChunkAt(int X, int Z);

    /**
    * ...
    *
    * @param BLOCK ...
    */

    void setBlock(Block BLOCK);

    /**
    * ...
    *
    * @param X ...
    * @param Y ...
    * @param Z ...
    *
    * @return ...
    */

    Block getBlock(int X, int Y, int Z);
}