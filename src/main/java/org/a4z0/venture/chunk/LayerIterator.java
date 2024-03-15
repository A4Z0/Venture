package org.a4z0.venture.chunk;

import org.a4z0.venture.block.Block;

import java.util.Iterator;

/**
* Iterates over the {@link Block Blocks} of a Layer.
*/

public class LayerIterator implements Iterator<Block> {

    private int CURRENT_INDEX_X = -Layer.LAYER_SIZE_X;
    private int CURRENT_INDEX_Z = -Layer.LAYER_SIZE_Z;

    private final Layer LAYER;

    /**
    * Construct a {@link LayerIterator}.
    *
    * @param LAYER ...
    */

    protected LayerIterator(Layer LAYER) {
        this.LAYER = LAYER;
    }

    @Override
    public boolean hasNext() {
        return this.CURRENT_INDEX_X < Layer.LAYER_SIZE_X && this.CURRENT_INDEX_Z < Layer.LAYER_SIZE_Z;
    }

    @Override
    public Block next() {
        if(!hasNext())
            throw new ArrayIndexOutOfBoundsException("No more Blocks were found in this Layer.");

        Block CURRENT_BLOCK = this.LAYER.getBlock(this.CURRENT_INDEX_X, this.CURRENT_INDEX_Z);

        this.CURRENT_INDEX_Z++;

        if(this.CURRENT_INDEX_Z >= Layer.LAYER_SIZE_Z) {
            this.CURRENT_INDEX_Z = -Layer.LAYER_SIZE_Z;

            CURRENT_INDEX_X++;
        }

        return CURRENT_BLOCK;
    }
}