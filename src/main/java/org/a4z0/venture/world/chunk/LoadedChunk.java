package org.a4z0.venture.world.chunk;

import org.a4z0.venture.position.block.BlockPosition;
import org.a4z0.venture.world.World;
import org.a4z0.venture.world.block.Block;
import org.a4z0.venture.world.block.blocks.Air;

/**
* ...
*/

public class LoadedChunk implements Chunk {

    protected final World World;

    protected final int X;
    protected final int Z;

    protected final byte[][][] Blocks = new byte[CHUNK_BLOCK_ARRAY_X][CHUNK_BLOCK_ARRAY_Y][CHUNK_BLOCK_ARRAY_Z];

    /**
    * Construct a {@link LoadedChunk}.
    *
    * @param world {@link World} that instantiated this {@link LoadedChunk}.
    * @param x X-Axis that this {@link LoadedChunk} is on.
    * @param z Z-Axis that this {@link LoadedChunk} is on.
    */

    public LoadedChunk(World world, int x, int z) {
        this.World = world;
        this.X = x;
        this.Z = z;
    }

    /**
    * @return ...
    */

    public int getX() {
        return this.X;
    }

    /**
    * @return ...
    */

    public int getZ() {
        return this.Z;
    }

    /**
    * @return ...
    */

    public World getWorld() {
        return this.World;
    }

    /**
    * ...
    *
    * @param Block ...
    */

    public void setBlock(Block Block) {
        int AX = Block.getPosition().getX() - this.getX();
        int AY = Block.getPosition().getY();
        int AZ = Block.getPosition().getZ() - this.getZ();

        if(AX < 0 || AX > CHUNK_SIZE_X)
            return;

        if(AY < 0 || AY > CHUNK_SIZE_Y)
            return;

        if(AZ < 0 || AZ > CHUNK_SIZE_Z)
            return;

        this.Blocks[AX][AY][AZ] = Block.getID();
    }

    /**
    * ...
    *
    * @param x ...
    * @param y ...
    * @param z ...
    *
    * @return ...
    */

    public Block getBlock(int x, int y, int z) {
        int AX = x - this.getX();
        int AZ = z - this.getZ();

        if(AX < 0 || AX > CHUNK_SIZE_X)
            return new Air(x, y, z);

        if(y < 0 || y > CHUNK_SIZE_Y)
            return new Air(x, y, z);

        if(AZ < 0 || AZ > CHUNK_SIZE_Z)
            return new Air(x, y, z);

        return new Block() {

            @Override
            public byte getID() {
                return Blocks[AX][y][AZ];
            }

            @Override
            public BlockPosition getPosition() {
                return new BlockPosition(x, y, z);
            }
        };
    }

    /**
    * ...
    *
    * @param x ...
    * @param y ...
    * @param z ...
    *
    * @return ...
    */

    public boolean hasBlockAt(int x, int y, int z) {
        int AX = x - this.getX();
        int AZ = z - this.getZ();

        if(AX < 0 || AX > CHUNK_SIZE_X)
            return false;

        if(y < 0 || y > CHUNK_SIZE_Y)
            return false;

        if(AZ < 0 || AZ > CHUNK_SIZE_Z)
            return false;

        return this.Blocks[AX][y][AZ] != 0;
    }
}