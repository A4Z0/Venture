package org.a4z0.venture.world.chunk;

import jdk.jfr.Experimental;
import org.a4z0.venture.camera.frustum.Frustum;
import org.a4z0.venture.gl.vertex.VertexStream;
import org.a4z0.venture.tile.Tiles;
import org.a4z0.venture.position.Direction;

/**
* ...
*/

@Experimental
public class Mesh {

    /**
    * ...
    *
    * @return ...
    */

    public static VertexStream create(VertexStream Stream, Chunk chunk, Frustum Frustum) {
        for(int X = 0; X <= Chunk.CHUNK_SIZE_X; X++) {
            for(int Y = 0; Y <= Chunk.CHUNK_SIZE_Y; Y++) {
                for(int Z = 0; Z <= Chunk.CHUNK_SIZE_Z; Z++) {
                    int AX = X + chunk.getX();
                    int AZ = Z + chunk.getZ();

                    byte ID = chunk.getBlock(AX, Y, AZ).getID();

                    if(ID == 0)
                        continue;

                    for(Direction Direction : Direction.values()) {
                        if(!Mesh.hasBlockAt(chunk, Direction, AX, Y, AZ) && Frustum.resides(AX, Y, AZ, 1)) {
                            Vertices.stream(Stream, Direction, Tiles.get(ID), AX, Y, AZ);
                        }
                    }
                }
            }
        }

        return Stream;
    }

    /**
    * ...
    *
    * @param Direction ...
    * @param X ...
    * @param Z ...
    *
    * @return ...
    */

    private static boolean hasBlockAt(Chunk chunk, Direction Direction, int X, int Y, int Z) {
        return switch (Direction) {
            case NORTH ->
                chunk.getWorld().hasBlockAt(X, Y, Z - 1);
            case SOUTH ->
                chunk.getWorld().hasBlockAt(X, Y, Z + 1);
            case EAST ->
                chunk.getWorld().hasBlockAt(X + 1, Y, Z);
            case WEST ->
                chunk.getWorld().hasBlockAt(X - 1, Y, Z);
            case TOP ->
                chunk.getWorld().hasBlockAt(X, Y + 1, Z);
            case BOTTOM ->
                chunk.getWorld().hasBlockAt(X, Y - 1, Z);
        };
    }
}