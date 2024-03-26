package org.a4z0.venture.world.block.blocks;

import org.a4z0.venture.position.block.BlockPosition;

/**
* ...
*/

public class Dirt extends BaseBlock {

    /**
    * Construct a {@link Dirt}.
    */

    public Dirt(int X, int Y, int Z) {
        super((byte) 1, new BlockPosition(X, Y, Z));
    }
}
