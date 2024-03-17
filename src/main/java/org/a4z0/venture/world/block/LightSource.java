package org.a4z0.venture.world.block;

import org.a4z0.venture.light.Light;

/**
* ...
*/

public interface LightSource {

    /**
    * @return {@link Light} from this {@link LightSource}.
    */

    Light getLight();
}