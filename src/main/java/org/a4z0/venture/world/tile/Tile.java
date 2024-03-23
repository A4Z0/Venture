package org.a4z0.venture.world.tile;

import org.a4z0.venture.gl.image.atlas.Address;

/**
* ...
*/

public class Tile {

    protected final Address[] addresses = new Address[6];

    /**
    * Construct a {@link Tile}.
    *
    * @param address ...
    */

    public Tile(Address address) {
        this(address, address, address, address, address, address);
    }

    /**
    * Construct a {@link Tile}.
    *
    * @param north ...
    * @param south ...
    * @param east ...
    * @param west ...
    * @param top ...
    * @param bottom ...
    */

    public Tile(Address north, Address south, Address east, Address west, Address top, Address bottom) {
        this.addresses[0] = north;
        this.addresses[1] = south;
        this.addresses[2] = east;
        this.addresses[3] = west;
        this.addresses[4] = top;
        this.addresses[5] = bottom;
    }

    /**
    * @return ...
    */

    public Address getNorth() {
        return this.addresses[0];
    }

    /**
    * @return ...
    */

    public Address getSouth() {
        return this.addresses[1];
    }

    /**
    * @return ...
    */

    public Address getEast() {
        return this.addresses[2];
    }

    /**
    * @return ...
    */

    public Address getWest() {
        return this.addresses[3];
    }

    /**
    * @return ...
    */

    public Address getTop() {
        return this.addresses[4];
    }

    /**
    * @return ...
    */

    public Address getBottom() {
        return this.addresses[5];
    }
}