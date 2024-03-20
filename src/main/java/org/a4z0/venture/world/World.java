package org.a4z0.venture.world;

import org.a4z0.venture.world.chunk.Chunk;
import org.a4z0.venture.world.enviroment.Sky;

/**
* ...
*/

public interface World extends TileMap {

    /**
    * @return the {@link Sky} of this {@link World}.
    */

    Sky getSky();

    /**
    * @return an Array of {@link Chunk Chunks} that have been loaded.
    */

    Chunk[] getChunks();

    /**
    * Returns a {@link Chunk} from this {@link World}.
    *
    * @param x X-Axis.
    * @param z Y-Axis.
    *
    * @return a {@link Chunk}.
    */

    Chunk getChunkAt(int x, int z);
}