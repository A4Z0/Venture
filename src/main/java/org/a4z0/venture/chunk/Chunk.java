package org.a4z0.venture.chunk;

import org.a4z0.venture.Array3d;
import org.a4z0.venture.block.Block;
import org.a4z0.venture.frustum.AABB;
import org.a4z0.venture.frustum.Frustum;
import org.a4z0.venture.model.old.Model;
import org.a4z0.venture.vertex.BlockRenderer;
import org.a4z0.venture.world.World;

/**
* ...
*/

public class Chunk implements Iterable<Block> {

    public static final int CHUNK_SIZE_X = 16 + 1;
    public static final int CHUNK_SIZE_Y = 64 + 1;
    public static final int CHUNK_SIZE_Z = 16 + 1;
    public static final int CHUNK_TOTAL_SIZE  = CHUNK_SIZE_X * CHUNK_SIZE_Y * CHUNK_SIZE_Z;

    protected final World WORLD;
    protected final int X, Z;
    public final Array3d<Layer> Layers;

    public final Array3d<Integer> BLOCK_ARRAY;


    public Frustum FRUSTUM;
    public AABB axisAlignedBoudingBox;

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
        this.BLOCK_ARRAY = new Array3d<>(
            Integer.class,
            CHUNK_SIZE_X,
            0,
            -CHUNK_SIZE_X,
            -this.X,
            CHUNK_SIZE_Y,
            0,
            -CHUNK_SIZE_Y,
            0,
            CHUNK_SIZE_Z,
            0,
            -CHUNK_SIZE_Z,
            -this.Z
        );

        this.Layers = new Array3d<>(
            Layer.class,
            1,
            0,
            0,
            0,
            CHUNK_SIZE_Y,
            0,
            -CHUNK_SIZE_Y,
            0,
            1,
            0,
            0,
           0
        );

        for(int y = -CHUNK_SIZE_Y; y < CHUNK_SIZE_Y; y++)
            this.Layers.set(0, y, 0, new Layer(this, y));

        this.axisAlignedBoudingBox = new AABB(1, 1, 1, 2, 2, 2);
        this.FRUSTUM = new Frustum();
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

    /**
    * @return ...
    */

    public Layer[] getLayers() {
        return this.Layers.ARRAY;
    }

    /**
    * @return ...
    */

    public Layer getLayerAt(int Y) {
        return this.Layers.get(0, Y, 0);
    }

    /**
    * @return ...
    */

    public Block[] getBlocks() {
        Block[] BLOCK_ARRAY = new Block[(2 * CHUNK_SIZE_X) * (2 * CHUNK_SIZE_Y) * (2 * CHUNK_SIZE_Z)];

        int i = 0;

        for(Block BLOCK : this)
            BLOCK_ARRAY[i++] = BLOCK;

        return BLOCK_ARRAY;
    }

    /**
    * ...
    *
    * @param BLOCK ...
    */

    public void setBlock(Block BLOCK) {
        BLOCK_ARRAY.set(BLOCK.getPosition().getX(), BLOCK.getPosition().getY(), BLOCK.getPosition().getZ(), BlockRegister.setBlock(BLOCK));
    }

    /**
    * @param X ...
    * @param Y ...
    * @param Z ...
    *
    * @return ...
    */

    public Block getBlock(int X, int Y, int Z) {
        return BlockRegister.getBlock(BLOCK_ARRAY.get(X, Y, Z));
    }

    /**
    * ...
    *
    * @param renderer ...
    */

    public void render(BlockRenderer renderer) {
        /*this.FRUSTUM.updateFrustum(Venture.FIRST_PERSON_CAMERA);

        if(!FRUSTUM.intersects(this.axisAlignedBoudingBox))
            return;*/

        renderer.render(this.getX(), 0, this.getZ(), new Model(Mesh.transform(this)));
        //renderer.render(new AABB(1, 1, 1, 2, 2, 2));
    }

    /**
    * @return a {@link ChunkIterator Chunk Iterator}.
    */

    @Override
    public ChunkIterator iterator() {
        return new ChunkIterator(this);
    }
}