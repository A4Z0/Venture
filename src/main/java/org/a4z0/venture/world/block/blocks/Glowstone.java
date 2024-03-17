package org.a4z0.venture.world.block.blocks;

import org.a4z0.venture.world.block.LightSource;
import org.a4z0.venture.world.block.mat.Material;
import org.a4z0.venture.light.Light;

/**
* ...
*/

public class Glowstone extends SimpleBlock implements LightSource {

    /**
    * Construct a {@link Glowstone}.
    *
    * @param x ...
    * @param y ...
    * @param z ...
    */

    public Glowstone(int x, int y, int z) {
        super(x, y, z, Material.GLOWSTONE);
    }

    @Override
    public Light getLight() {
        return Light.LIGHT_LEVEL_8;
    }
}