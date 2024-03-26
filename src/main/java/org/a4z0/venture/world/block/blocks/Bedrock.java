package org.a4z0.venture.world.block.blocks;

import org.a4z0.venture.position.block.BlockPosition;

/**
* ...
*/

public class Bedrock extends BaseBlock {

    /**
    * Construct a {@link Bedrock}.
    */

    public Bedrock(int X, int Y, int Z) {
        super((byte) 2, new BlockPosition(X, Y, Z));
    }
}
