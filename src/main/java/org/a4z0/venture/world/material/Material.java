package org.a4z0.venture.world.material;

import org.a4z0.venture.util.BlockVertex;
import org.a4z0.venture.util.Utils;
import org.a4z0.venture.gl.image.atlas.Address;
import org.a4z0.venture.gl.vertex.Vertex;
import org.a4z0.venture.gl.image.texture.Textures;
import org.a4z0.venture.world.position.Direction;

import java.util.Map;

/**
* ...
*/

public enum Material {
    DIRT(Textures.DIRT),
    BEDROCK(Textures.BEDROCK),
    GLOWSTONE(Textures.GLOWSTONE),
    OAK_LOG(Textures.OAK_LOG, Textures.OAK_LOG, Textures.OAK_LOG, Textures.OAK_LOG, Textures.OAK_LOG_CRUMB, Textures.OAK_LOG_CRUMB);

    private final Map<Direction, Address> TEXTURE_BY_SIDE;

    /**
    * Construct a {@link Material}.
    *
    * @param ID ...
    */

    Material(Address ID) {
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
        Address NORTH,
        Address SOUTH,
        Address EAST,
        Address WEST,
        Address TOP,
        Address BOTTOM
    ) {
        this.TEXTURE_BY_SIDE = Map.of(Direction.NORTH, NORTH, Direction.SOUTH, SOUTH, Direction.EAST, EAST, Direction.WEST, WEST, Direction.TOP, TOP, Direction.BOTTOM, BOTTOM);
    }

    /**
    * @param FACE ...
    *
    * @return ...
    */

    public Address getID(Direction FACE) {
        return TEXTURE_BY_SIDE.get(FACE);
    }

    /**
    * ...
    *
    * @param Vertex ...
    * @param X ...
    * @param Y ...
    * @param Z ...
    */

    public void vertex(Vertex Vertex, int X, int Y, int Z) {
        for(Direction Dir : Direction.values())
            if(Utils.getAt(X, Y, Z, Dir) == null)
                BlockVertex.stream(Vertex, Dir, X, Y, Z, this.getID(Dir));
    }
}