package org.a4z0.venture.world;

import org.a4z0.venture.world.block.Block;
import org.a4z0.venture.world.chunk.Chunk;

/**
* ...
*/

public interface World {

    int CHUNK_MAX_X = 1;
    int CHUNK_MAX_Z = 1;

    int CHUNK_MIN_X = -1;
    int CHUNK_MIN_Z = -1;

    int CHUNK_X_SQRT = (int) Math.sqrt(Chunk.CHUNK_SIZE_X);
    int CHUNK_Z_SQRT = (int) Math.sqrt(Chunk.CHUNK_SIZE_Z);

    int CHUNK_ARRAY_X = (CHUNK_MAX_X - CHUNK_MIN_X) + 1;
    int CHUNK_ARRAY_Z = (CHUNK_MAX_Z - CHUNK_MIN_Z) + 1;

    int CHUNK_ARRAY_LENGTH = CHUNK_ARRAY_X * CHUNK_ARRAY_Z;

    /**
    * @return ...
    */

    Chunk[] getChunks();

    /**
    * Obtains a {@link Chunk} at the given coordinates.
    *
    * @param x X-Axis.
    * @param z Y-Axis.
    *
    * @return a {@link Chunk}.
    */

    Chunk getChunkAt(int x, int z);

    /**
    * Sets a {@link Block} in this {@link World}.
    *
    * @param block {@link Block} that will be set.
    */

    void setBlock(Block block);

    /**
    * Obtains a {@link Block} from this {@link World}.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    *
    * @return a {@link Block}.
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