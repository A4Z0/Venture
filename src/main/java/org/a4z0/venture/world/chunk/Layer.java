package org.a4z0.venture.world.chunk;

import org.a4z0.venture.world.block.Block;
import org.a4z0.venture.render.VertexRenderer;

/**
* ...
*/

public class Layer implements Iterable<Block> {

    public static final int LAYER_SIZE_X = Chunk.CHUNK_SIZE_X;
    public static final int LAYER_SIZE_Z = Chunk.CHUNK_SIZE_Z;
    public static final int LAYER_TOTAL_SIZE = LAYER_SIZE_X * LAYER_SIZE_Z;

    private final Chunk CHUNK;
    private final int Y;

    /**
    * Construct a {@link Layer}.
    *
    * @param CHUNK ...
    * @param Y ...
    */

    public Layer(Chunk CHUNK, int Y) {
        this.CHUNK = CHUNK;
        this.Y = Y;
    }

    /**
    * @return ...
    */

    public int getY() {
        return this.Y;
    }

    /**
    * @return ...
    */

    public Chunk getChunk() {
        return this.CHUNK;
    }

    /**
    * @return ...
    */

    public Block[] getBlocks() {
        Block[] BLOCK_ARRAY = new Block[(2 * LAYER_SIZE_X) * (2 * LAYER_SIZE_Z)];

        int i = 0;

        for(int x = -(LAYER_SIZE_X + this.CHUNK.getX()); x < (LAYER_SIZE_X + this.CHUNK.getX()); x++)
            for(int z = -(LAYER_SIZE_Z + this.CHUNK.getZ()); z < (LAYER_SIZE_Z + this.CHUNK.getZ()); z++)
                BLOCK_ARRAY[i++] = this.getBlock(x, z);

        return BLOCK_ARRAY;
    }

    /**
    * ...
    *
    * @param BLOCK ...
    */

    public void setBlock(Block BLOCK) {
        this.CHUNK.setBlock(BLOCK);
    }

    /**
    * @param X ...
    * @param Z ...
    *
    * @return ...
    */

    public Block getBlock(int X, int Z) {
        return this.CHUNK.getBlock(X, this.Y, Z);
    }

    /**
    * ...
    *
    * @param renderer ...
    */

    public void render(VertexRenderer renderer) {
        /*Vertex Vertex = Mesh.transform(this);

        if(!CraftMagicBlocks.frustum(Venture.FIRST_PERSON_CAMERA, Vertex.getPositions()))
            return;

        renderer.render(this.getChunk().getX(), 0, this.getChunk().getZ(), new Model(Vertex));*/
    }

    /**
    * @return a {@link LayerIterator Layer Iterator}.
    */

    @Override
    public LayerIterator iterator() {
        return new LayerIterator(this);
    }
}