package org.a4z0.venture.world.block;

import org.a4z0.venture.world.block.mat.Material;
import org.a4z0.venture.world.Position;
import org.a4z0.venture.world.Direction;
import org.a4z0.venture.world.World;

/**
* ...
*/

public interface Block {

    /**
    * @return ...
    */

    Position getPosition();

    /**
    * @return ...
    */

    Material getMaterial();

    /**
    * ...
    *
    * @param world ...
    * @param x ...
    * @param y ...
    * @param z ...
    *
    * @return ...
    */

    static Block getBlockAt(World world, int x, int y, int z) {
        return world.getBlock(x, y, z);
    }

    /**
    * ...
    *
    * @param world ...
    * @param position ...
    * @param direction ...
    *
    * @return ...
    */

    static boolean isFaceVisible(World world, Position position, Direction direction) {
        return isFaceVisible(world, position.getX(), position.getY(), position.getY(), direction);
    }

    /**
    * ...
    *
    * @param x ...
    * @param y ...
    * @param z ...
    * @param direction ...
    *
    * @return ...
    */

    static boolean isFaceVisible(World world, int x, int y, int z, Direction direction) {
        return switch (direction) {
            case NORTH ->
                world.getBlock(x , y, z - 1) == null;
            case SOUTH ->
                world.getBlock(x, y, z + 1) == null;
            case EAST ->
                world.getBlock(x + 1, y, z) == null;
            case WEST ->
                world.getBlock(x - 1, y, z) == null;
            case TOP ->
                world.getBlock(x, y + 1, z) == null;
            case BOTTOM ->
                world.getBlock(x, y - 1, z) == null;
        };
    }
}