package org.a4z0.venture.world.chunk;

import org.a4z0.venture.world.World;
import org.a4z0.venture.world.block.Block;
import org.a4z0.venture.world.block.blocks.Air;

/**
*
*/

public class EmptyChunk implements Chunk {

    protected final World World;

    protected final int X;
    protected final int Z;

    /**
    * Construct a {@link EmptyChunk}.
    *
    * @param World ...
    * @param X ...
    * @param Z ...
    */

    public EmptyChunk(World World, int X, int Z) {
        this.World = World;
        this.X = X;
        this.Z = Z;
    }

    @Override
    public int getX() {
        return this.X;
    }

    @Override
    public int getZ() {
        return this.Z;
    }

    @Override
    public World getWorld() {
        return this.World;
    }

    @Override
    public void setBlock(Block Block) {

    }

    @Override
    public Block getBlock(int x, int y, int z) {
        return new Air(x, y, z);
    }

    @Override
    public boolean hasBlockAt(int x, int y, int z) {
        return false;
    }
}