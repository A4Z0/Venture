package org.a4z0.venture.world.chunk;

import org.a4z0.venture.world.TileMap;
import org.a4z0.venture.world.World;
import org.a4z0.venture.world.material.Material;

/**
* ...
*/

public class Chunk implements TileMap {

    public static final int CHUNK_SIZE = 32 + 1;

    protected final World WORLD;
    protected final int X, Z;

    protected final int[] TILE_MAP = new int[CHUNK_SIZE * CHUNK_SIZE * CHUNK_SIZE];

    /**
    * Construct a {@link Chunk}.
    *
    * @param WORLD ...
    * @param X ...
    * @param Z ...
    */

    public Chunk(World WORLD, int X, int Z) {
        this.X = X;
        this.Z = Z;
        this.WORLD = WORLD;
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
        return this.WORLD;
    }

    @Override
    public void setBlock(int X, int Y, int Z, Material Material) {
        int Index = (Y * CHUNK_SIZE * CHUNK_SIZE) + (Z * CHUNK_SIZE) + X;

        if(Index > this.TILE_MAP.length || Index < 0)
            return;

        TILE_MAP[Index] = Material.ordinal();
    }

    @Override
    public Material getBlock(int X, int Y, int Z) {
        int Index = (Y * CHUNK_SIZE * CHUNK_SIZE) + (Z * CHUNK_SIZE) + X;

        if(Index > this.TILE_MAP.length || Index < 0)
            return null;

        int ID = TILE_MAP[Index];

        if(ID == 0 || ID > Material.values().length)
            return null;

        return Material.values()[ID];
    }
}