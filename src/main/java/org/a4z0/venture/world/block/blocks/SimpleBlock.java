package org.a4z0.venture.world.block.blocks;

import org.a4z0.venture.world.block.Block;
import org.a4z0.venture.world.block.mat.Material;
import org.a4z0.venture.world.Position;

/**
* ...
*/

public abstract class SimpleBlock implements Block {

    protected final Position BLOCK_POSITION;
    protected final Material BLOCK_MATERIAL;

    /**
    * Construct a {@link SimpleBlock}.
    *
    * @param X ...
    * @param Y ...
    * @param Z ...
    * @param BLOCK_MATERIAL ...
    */

    protected SimpleBlock(int X, int Y, int Z, Material BLOCK_MATERIAL) {
        this.BLOCK_POSITION = new Position(X, Y, Z);
        this.BLOCK_MATERIAL = BLOCK_MATERIAL;
    }

    @Override
    public Position getPosition() {
        return this.BLOCK_POSITION;
    }

    @Override
    public Material getMaterial() {
        return this.BLOCK_MATERIAL;
    }
}