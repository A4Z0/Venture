package org.a4z0.venture.world;

import jdk.jfr.Experimental;
import org.a4z0.venture.gl.vertex.Vertex;;
import org.a4z0.venture.world.tile.Tile;
import org.a4z0.venture.world.tile.Tiles;
import org.a4z0.venture.world.position.Direction;

/**
* ...
*/

@Experimental
public class SimpleBlockMesh {

    /**
    * @return ...
    */

    public static Vertex mesh(SimpleBlockMap VoxelMap) {
        Vertex Vertex = new Vertex();

        for(int X = 0; X < 17; X++) {
            for(int Y = 0; Y < 385; Y++) {
                for(int Z = 0; Z < 17; Z++) {
                    byte ID = VoxelMap.getVoxel(X, Y, Z);

                    if(ID == 0)
                        continue;

                    Tile Tile = Tiles.get(ID);

                    for(Direction Direction : Direction.values()) {
                        if(isFaceVisible(VoxelMap, Direction, X, Y, Z)) {
                            SimpleBlockVertex.stream(Vertex, Direction, Tile, X, Y, Z);
                        }
                    }
                }
            }
        }

        return Vertex;
    }

    /**
    * ...
    *
    * @param VoxelMap ...
    * @param Direction ...
    * @param X ...
    * @param Y ...
    * @param Z ...
    *
    * @return ...
    */

    protected static boolean isFaceVisible(SimpleBlockMap VoxelMap, Direction Direction, int X, int Y, int Z) {
        return switch (Direction) {
            case NORTH ->
                VoxelMap.getVoxel(X , Y, Z - 1) == 0;
            case SOUTH ->
                VoxelMap.getVoxel(X, Y, Z + 1) == 0;
            case EAST ->
                VoxelMap.getVoxel(X + 1, Y, Z) == 0;
            case WEST ->
                VoxelMap.getVoxel(X - 1, Y, Z) == 0;
            case TOP ->
                VoxelMap.getVoxel(X, Y + 1, Z) == 0;
            case BOTTOM ->
                VoxelMap.getVoxel(X, Y - 1, Z) == 0;
        };
    }
}