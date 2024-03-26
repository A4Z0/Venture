package org.a4z0.venture.world.block.blocks;

import org.a4z0.venture.position.block.BlockPosition;
import org.a4z0.venture.world.block.Block;

/**
* ...
*/

public abstract class BaseBlock implements Block {

    protected final byte ID;
    protected final BlockPosition Position;

    /**
    * Construct a {@link BaseBlock}.
    *
    * @param ID ...
    * @param Position ...
    */

    public BaseBlock(byte ID, BlockPosition Position) {
        this.ID = ID;
        this.Position = Position;
    }

    @Override
    public byte getID() {
        return this.ID;
    }

    @Override
    public BlockPosition getPosition() {
        return this.Position;
    }
}