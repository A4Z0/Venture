package org.a4z0.venture.world;

import jdk.jfr.Experimental;
import org.a4z0.venture.world.block.Block;
import org.a4z0.venture.world.position.Position;
import org.a4z0.venture.world.position.block.BlockPosition;
import org.joml.Vector3f;

/**
* ...
*/

@Experimental
public class SimpleBlockMap {

    public static final int VOXEL_MAP_X = (2 * 8) + 1;
    public static final int VOXEL_MAP_Y = (64 + 320) + 1;
    public static final int VOXEL_MAP_Z = (2 * 8) + 1;

    protected final byte[][][] voxels;

    public boolean hasUpdated;

    /**
    * Construct a {@link SimpleBlockMap}.
    */

    public SimpleBlockMap() {
        this.voxels = new byte[VOXEL_MAP_X][VOXEL_MAP_Y][VOXEL_MAP_Z];
    }

    /**
    * @return ...
    */

    public byte[][][] getVoxels() {
        return this.voxels;
    }

    /**
    * ...
    *
    * @param X ...
    * @param Y ...
    * @param Z ...
    * @param Block ...
    */

    public void setVoxel(int X, int Y, int Z, Block Block) {
        if(X < 0 || X >= VOXEL_MAP_X)
            return;

        if(Y < 0 || Y >= VOXEL_MAP_Y)
            return;

        if(Z < 0 || Z >= VOXEL_MAP_Z)
            return;

        this.hasUpdated = true;

        this.voxels[X][Y][Z] = Block.getID();
    }

    /**
    * ...
    *
    * @param X ...
    * @param Y ...
    * @param Z ...
    *
    * @return ...
    */

    public byte getVoxel(int X, int Y, int Z) {
        if(X < 0 || X >= VOXEL_MAP_X)
            return 0;

        if(Y < 0 || Y >= VOXEL_MAP_Y)
            return 0;

        if(Z < 0 || Z >= VOXEL_MAP_Z)
            return 0;

        return this.voxels[X][Y][Z];
    }

    /**
    * ..
    *
    * @param Position ...
    *
    * @return ...
    */

    public BlockPosition[] getVoxel(Position Position, int Range) {
        Vector3f Dir = Position.getDirection();

        for(int i = 0; i <= Range; i++) {
            int X = Math.round((Position.getX() + Dir.x * i));
            int Y = Math.round((Position.getY() + Dir.y * i));
            int Z = Math.round((Position.getZ() + Dir.z * i));
            int X2 = Math.round((Position.getX() + Dir.x * (i - 1)));
            int Y2 = Math.round((Position.getY() + Dir.y * (i - 1)));
            int Z2 = Math.round((Position.getZ() + Dir.z * (i - 1)));

            if(this.getVoxel(X, Y, Z) != 0)
                return new BlockPosition[]{
                    new BlockPosition(X, Y, Z),
                    new BlockPosition(X2, Y2, Z2),
                };
        }

        return null;
    }
}