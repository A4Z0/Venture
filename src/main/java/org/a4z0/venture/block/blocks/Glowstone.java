package org.a4z0.venture.block.blocks;

import org.a4z0.venture.block.Material;
import org.a4z0.venture.light.Light;
import org.joml.Vector3f;

/**
* ...
*/

public class Glowstone extends SimpleBlock {

    /**
    * Construct a {@link Glowstone}.
    *
    * @param X ...
    * @param Y ...
    * @param Z ...
    */

    public Glowstone(int X, int Y, int Z) {
        super(X, Y, Z, Material.GLOWSTONE);
    }

    public Light getLight() {
        return new Light(BLOCK_POSITION, new Vector3f(1, 1, 1), 15);
    }
}