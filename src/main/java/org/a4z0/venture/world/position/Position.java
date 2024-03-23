package org.a4z0.venture.world.position;

import org.joml.Vector3f;

/**
* Represents a Position.
*/

public class Position {

    protected static final double $2PI = 2 * Math.PI;

    protected float x, y, z;
    protected float pitch;
    protected float yaw;

    /**
    * Construct a {@link Position}.
    */

    public Position() {
        this(0, 0, 0, 0, -90);
    }

    /**
    * Construct a {@link Position}.
    *
    * @param x ...
    * @param y ...
    * @param z ...
    * @param yaw ...
    * @param pitch ...
    */

    public Position(final float x, final float y, final float z, final float yaw, final float pitch) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
    }

    /**
    * @return the X-Axis.
    */

    public float getX() {
        return this.x;
    }

    /**
    * @return the Y-Axis.
    */

    public float getY() {
        return this.y;
    }

    /**
    * @return the Z-Axis.
    */

    public float getZ() {
        return this.z;
    }

    /**
    * @return ...
    */

    public int getBlockX() {
        return (int) Math.floor(this.x);
    }

    /**
    * @return ...
    */

    public int getBlockY() {
        return (int) Math.floor(this.y);
    }

    /**
    * @return ...
    */

    public int getBlockZ() {
        return (int) Math.floor(this.z);
    }

    /**
    * @return ...
    */

    public float getYaw() {
        return this.yaw;
    }

    /**
    * @return ...
    */

    public float getPitch() {
        return this.pitch;
    }

    /**
    * ...
    *
    * @param yaw ...
    */

    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    /**
    * ...
    *
    * @param pitch ...
    */

    public void setPitch(float pitch) {
        this.pitch = (float) Math.min(89.9d, Math.max(-89.9d, pitch));
    }

    /**
    * @return ...
    */

    public Vector3f getDirection() {
        float XZ = (float) Math.cos(Math.toRadians(this.getPitch()));

        return new Vector3f(
            (float) (-XZ * Math.sin(Math.toRadians(this.getYaw()))),
            (float) -Math.sin(Math.toRadians(this.getPitch())),
            (float) (XZ * Math.cos(Math.toRadians(this.getYaw())))
        );
    }

    /**
    * ...
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    *
    * @return this {@link Position}.
    */

    public Position setDirection(float x, float y, float z) {
        if(x == 0 && z == 0) {
            this.setPitch(y > 0 ? -90 : 90);

            return this;
        }

        this.yaw = (float) Math.toDegrees(((Math.atan2(-x, z)) + $2PI) % $2PI);
        this.pitch = (float) Math.toDegrees(Math.atan(-y / (Math.sqrt((Math.pow(x, 2)) + (Math.pow(z, 2))))));

        return this;
    }

    /**
    * ...
    *
    * @param position ...
    *
    * @return this {@link Position}.
    */

    public Position add(Position position) {
        return this.add(position.getX(), position.getY(), position.getZ());
    }

    /**
    * ...
    *
    * @param Vector ...
    *
    * @return this {@link Position}.
    */

    public Position add(Vector3f Vector) {
        return this.add(Vector.x, Vector.y, Vector.z);
    }

    /**
    * ...
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    *
    * @return this {@link Position}.
    */

    public Position add(float x, float y, float z) {
        this.x += x;
        this.y += y;
        this.z += z;

        return this;
    }

    /**
    * ...
    *
    * @param other ...
    *
    * @return this {@link Position}.
    */

    public Position subtract(Position other) {
        return this.subtract(other.getX(), other.getY(), other.getZ());
    }

    /**
    * ...
    *
    * @param other ...
    *
    * @return this {@link Position}.
    */

    public Position subtract(Vector3f other) {
        return this.subtract(other.x, other.y, other.z);
    }

    /**
    * ...
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    *
    * @return this {@link Position}.
    */

    public Position subtract(float x, float y, float z) {
        this.x -= x;
        this.y -= y;
        this.z -= z;

        return this;
    }

    /**
    * Calculates the distance between positions.
    *
    * @param other Other {@link Position}.
    *
    * @return the distance between positions.
    */

    public float distance(Position other) {
        return this.distance(other.getX(), other.getY(), other.getZ());
    }

    /**
    * Calculates the distance between positions.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    *
    * @return the distance between positions.
    */

    public float distance(float x, float y, float z) {
        return (float) Math.pow((this.x - x), 2) + (float) Math.pow((this.y - y), 2) + (float) Math.pow((this.z - z), 2);
    }
}