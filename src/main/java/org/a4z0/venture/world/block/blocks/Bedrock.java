package org.a4z0.venture.world.block.blocks;

import org.a4z0.venture.world.block.mat.Material;

/**
* ...
*/

public class Bedrock extends SimpleBlock {

    /**
    * Construct a {@link Bedrock}.
    *
    * @param X ...
    * @param Y ...
    * @param Z ...
    */

    public Bedrock(int X, int Y, int Z) {
        super(X, Y, Z, Material.BEDROCK);
    }
}