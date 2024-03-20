package org.a4z0.venture.world.position;

import org.joml.Vector3d;

/**
* ...
*/

public class Position {

    public static final double $2PI = 2 * Math.PI;

    protected double X, Y, Z;

    protected double Pitch;
    protected double Yaw;

    /**
    * Construct a {@link Position}.
    */

    public Position() {
        this(0, 0, 0, 0, 0);
    }

    /**
    * Construct a {@link Position}.
    *
    * @param X ...
    * @param Y ...
    * @param Z ...
    * @param Yaw ...
    * @param Pitch ...
    */

    public Position(final double X, final double Y, final double Z, final double Yaw, final double Pitch) {
        this.X = X;
        this.Y = Y;
        this.Z = Z;
        this.Yaw = Yaw;
        this.Pitch = Pitch;
    }

    /**
    * @return ...
    */

    public double getX() {
        return this.X;
    }

    /**
    * @return ...
    */

    public double getY() {
        return this.Y;
    }

    /**
    * @return ...
    */

    public double getZ() {
        return this.Z;
    }

    /**
    * @return ...
    */

    public int getBlockX() {
        return (int) Math.floor(this.X);
    }

    /**
    * @return ...
    */

    public int getBlockY() {
        return (int) Math.floor(this.Y);
    }

    /**
    * @return ...
    */

    public int getBlockZ() {
        return (int) Math.floor(this.Z);
    }

    /**
    * @return ...
    */

    public double getYaw() {
        return this.Yaw;
    }

    /**
    * @return ...
    */

    public double getPitch() {
        return this.Pitch;
    }

    /**
    * ...
    *
    * @param Yaw ...
    */

    public void setYaw(double Yaw) {
        this.Yaw = Yaw;
    }

    /**
    * ...
    *
    * @param Pitch ...
    */

    public void setPitch(double Pitch) {
        this.Pitch = Math.min(89.9d, Math.max(-89.9d, Pitch));
    }

    /**
    * @return ...
    */

    public Vector3d getDirection() {
        double XZ = Math.cos(Math.toRadians(this.getPitch()));

        return new Vector3d(
            (-XZ * Math.sin(Math.toRadians(this.getYaw()))),
            (-Math.sin(Math.toRadians(this.getPitch()))),
            (XZ * Math.cos(Math.toRadians(this.getYaw()))
        ));
    }

    /**
    * ...
    *
    * @param X ...
    * @param Y ...
    * @param Z ...
    *
    * @return ...
    */

    public Position setDirection(double X, double Y, double Z) {
        if(X == 0 && Z == 0) {
            this.setPitch(Y > 0 ? -90 : 90);

            return this;
        }

        this.Yaw = (float) Math.toDegrees(((Math.atan2(-X, Z)) + $2PI) % $2PI);
        this.Pitch = (float) Math.toDegrees(Math.atan(-Y / (Math.sqrt((Math.pow(X, 2)) + (Math.pow(Z, 2))))));

        return this;
    }

    /**
    * ...
    *
    * @param Location ...
    *
    * @return ...
    */

    public Position add(Position Location) {
        return this.add(Location.getX(), Location.getY(), Location.getZ());
    }

    /**
    * ...
    *
    * @param Vector ...
    *
    * @return ...
    */

    public Position add(Vector3d Vector) {
        return this.add(Vector.x, Vector.y, Vector.z);
    }

    /**
    * ...
    *
    * @param X ...
    * @param Y ...
    * @param Z ...
    *
    * @return ...
    */

    public Position add(double X, double Y, double Z) {
        this.X += X;
        this.Y += Y;
        this.Z += Z;

        return this;
    }

    /**
    * ...
    *
    * @param Location ...
    *
    * @return ...
    */

    public Position subtract(Position Location) {
        return this.subtract(Location.getX(), Location.getY(), Location.getZ());
    }

    /**
    * ...
    *
    * @param Vector ...
    *
    * @return ...
    */

    public Position subtract(Vector3d Vector) {
        return this.subtract(Vector.x, Vector.y, Vector.z);
    }

    /**
    * ...
    *
    * @param X ...
    * @param Y ...
    * @param Z ...
    *
    * @return ...
    */

    public Position subtract(double X, double Y, double Z) {
        this.X -= X;
        this.Y -= Y;
        this.Z -= Z;

        return this;
    }

    /**
    * ...
    *
    * @param X ...
    * @param Y ...
    * @param Z ...
    *
    * @return ...
    */

    public double distance(double X, double Y, double Z) {
        return Math.pow((this.X - X), 2) + Math.pow((this.Y - Y), 2) + Math.pow((this.Z - Z), 2);
    }
}