package org.a4z0.venture.block;

import org.a4z0.venture.camera.FirstPersonCamera;
import org.a4z0.venture.Frustum;
import org.a4z0.venture.chunk.BlockRegister;
import org.a4z0.venture.position.Position;

/**
* ...
*/

public class CraftMagicBlocks {

    /**
    * ...
    *
    * @param POSITION ...
    * @param FACE ...
    *
    * @return ...
    */

    public static Block getBlockAt(Position POSITION, Face FACE) {
        return getBlockAt(POSITION.getX(), POSITION.getY(), POSITION.getZ(), FACE);
    }

    /**
    * @param X ...
    * @param Y ...
    * @param Z ...
    * @param Face ...
    *
    * @return ...
    */

    public static Block getBlockAt(int X, int Y, int Z, Face Face) {
        return switch (Face) {
            case NORTH ->
                BlockRegister.getBlock(X , Y, Z - 1);
            case SOUTH ->
                BlockRegister.getBlock(X, Y, Z + 1);
            case EAST ->
                BlockRegister.getBlock(X + 1, Y, Z);
            case WEST ->
                BlockRegister.getBlock(X - 1, Y, Z);
            case TOP ->
                BlockRegister.getBlock(X, Y + 1, Z);
            case BOTTOM ->
                BlockRegister.getBlock(X, Y - 1, Z);
        };
    }

    @Deprecated
    public static boolean frustum(FirstPersonCamera camera, float[] positions) {
        return Frustum.isObjectInFrustum(positions, camera.getProjection(), camera.getView());
    }
}