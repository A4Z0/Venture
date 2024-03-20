package org.a4z0.venture.gl.image.atlas;

/**
* ...
*/

public class Address {

    protected final Atlas atlas;

    protected final int tileSize;
    protected final int tileOffsetX;
    protected final int tileOffsetY;

    /**
    * Construct a {@link Address}.
    *
    * @param atlas ...
    * @param tileSize ...
    * @param tileX ...
    * @param tileY ...
    */

    protected Address(Atlas atlas, int tileSize, int tileX, int tileY) {
        this.atlas = atlas;
        this.tileSize = tileSize;
        this.tileOffsetX = tileX;
        this.tileOffsetY = tileY;
    }

    /**
    * @return the {@link Atlas} this Tile is attached.
    */

    public Atlas getTileMap() {
        return this.atlas;
    }

    /**
    * @return the Width of this {@link Address}.
    */

    public int getSize() {
        return this.tileSize;
    }

    /**
    * @return the X-Axis of this {@link Address}.
    */

    public int getX() {
        return this.tileOffsetX;
    }

    /**
    * @return the Y-Axis of this {@link Address}.
    */

    public int getY() {
        return this.tileOffsetY;
    }
}