package org.a4z0.venture.util;

import org.a4z0.venture.Venture;
import org.a4z0.venture.world.position.BlockPosition;
import org.a4z0.venture.world.position.Direction;
import org.a4z0.venture.world.material.Material;
import org.a4z0.venture.world.position.Position;
import org.joml.Vector3d;
import org.joml.Vector3f;

/**
* ...
*/

public class Utils {

    /**
    * ...
    *
    * @param X ...
    * @param Y ...
    * @param Z ...
    * @param Face ...
    *
    * @return ...
    */

    public static Material getAt(int X, int Y, int Z, Direction Face) {
        return switch (Face) {
            case NORTH ->
                Venture.WORLD.getBlock(X , Y, Z - 1);
            case SOUTH ->
                Venture.WORLD.getBlock(X, Y, Z + 1);
            case EAST ->
                Venture.WORLD.getBlock(X + 1, Y, Z);
            case WEST ->
                Venture.WORLD.getBlock(X - 1, Y, Z);
            case TOP ->
                Venture.WORLD.getBlock(X, Y + 1, Z);
            case BOTTOM ->
                Venture.WORLD.getBlock(X, Y - 1, Z);
        };
    }

    // This need to be remade

    @Deprecated
    public static BlockPosition getDirAt(Position Position, int maxDistance) {
        Vector3d direction = Position.getDirection();

        for (int i = 1; i <= maxDistance; i++) {
            Vector3f voxelPosition = new Vector3f(
                    (float) (Position.getX() + direction.x * i),
                    (float) (Position.getY() + direction.y * i),
                    (float) (Position.getZ() + direction.z * i)
            );

            int x = Math.round(voxelPosition.x);
            int y = Math.round(voxelPosition.y);
            int z = Math.round(voxelPosition.z);

            Material mat = Venture.WORLD.getBlock(x, y, z);

            if(mat != null)
                return new BlockPosition(x, y, z);
        }

        return null;
    }
}