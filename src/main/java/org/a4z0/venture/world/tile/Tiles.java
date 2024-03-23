package org.a4z0.venture.world.tile;

import org.a4z0.venture.gl.image.texture.Textures;

/**
* ...
*/

public class Tiles {

    public static final Tile[] REGISTERED_TILES = new Tile[255];

    public static int i = 1;

    /**
    * ...
    *
    * @param i ...
    *
    * @return ...
    */

    public static Tile get(int i) {
        return REGISTERED_TILES[i];
    }

    /**
    * ...
    *
    * @param t ...
    */

    public static void register(Tile t) {
        REGISTERED_TILES[i++] = t;
    }

    /**
    * ...
    */

    public static void init() {
        register(new Tile(Textures.DIRT));
        register(new Tile(Textures.BEDROCK));
    }
}