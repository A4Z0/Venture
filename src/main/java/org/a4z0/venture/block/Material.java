package org.a4z0.venture.block;

import org.a4z0.venture.texture.Textures;

import java.util.Map;

/**
* ...
*/

public enum Material {
    DIRT(Textures.DIRT),
    BEDROCK(Textures.BEDROCK),
    GLOWSTONE(Textures.GLOWSTONE),
    OAK_LOG(Textures.OAK_LOG, Textures.OAK_LOG, Textures.OAK_LOG, Textures.OAK_LOG, Textures.OAK_LOG_CRUMB, Textures.OAK_LOG_CRUMB);

    private final Map<Face, Integer> TEXTURE_BY_SIDE;

    /**
    * Construct a {@link Material}.
    *
    * @param ID ...
    */

    Material(int ID) {
        this(ID, ID, ID, ID, ID, ID);
    }

    /**
    * Construct a {@link Material}.
    *
    * @param NORTH ...
    * @param SOUTH ...
    * @param EAST ...
    * @param WEST ...
    * @param TOP ...
    * @param BOTTOM ...
    */

    Material(
        int NORTH,
        int SOUTH,
        int EAST,
        int WEST,
        int TOP,
        int BOTTOM
    ) {
        this.TEXTURE_BY_SIDE = Map.of(Face.NORTH, NORTH, Face.SOUTH, SOUTH, Face.EAST, EAST, Face.WEST, WEST, Face.TOP, TOP, Face.BOTTOM, BOTTOM);
    }

    /**
    * @param FACE ...
    *
    * @return ...
    */

    public int getID(Face FACE) {
        return TEXTURE_BY_SIDE.get(FACE);
    }
}