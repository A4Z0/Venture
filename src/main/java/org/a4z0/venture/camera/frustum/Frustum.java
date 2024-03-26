package org.a4z0.venture.camera.frustum;

import org.a4z0.venture.position.Position;
import org.joml.Matrix4f;

/**
* ...
*/

public class Frustum {

    protected final Plane[] planes = new Plane[]{
        new Plane(0, 0, -1, 1), // Near
        new Plane(0, 0, 1, 1), // Far
        new Plane(1, 0, 0, 1), // Left
        new Plane(-1, 0, 0, 1), // Right
        new Plane(0, -1, 0, 1), // Top
        new Plane(0, 1, 0, 1), // Bottom
    };

    /**
    * Construct a {@link Frustum}.
    */

    public Frustum() {

    }

    public void frustrate(Matrix4f projection, Matrix4f view) {
        Matrix4f projectionView = new Matrix4f(projection).mul(view);
       
        this.planes[0].set(projectionView.m03() + projectionView.m00(), projectionView.m13() + projectionView.m10(), projectionView.m23() + projectionView.m20(), projectionView.m33() + projectionView.m30()).normalize();
        this.planes[1].set(projectionView.m03() - projectionView.m00(), projectionView.m13() - projectionView.m10(), projectionView.m23() - projectionView.m20(), projectionView.m33() - projectionView.m30()).normalize();
        this.planes[2].set(projectionView.m03() + projectionView.m01(), projectionView.m13() + projectionView.m11(), projectionView.m23() + projectionView.m21(), projectionView.m33() + projectionView.m31()).normalize();
        this.planes[3].set(projectionView.m03() - projectionView.m01(), projectionView.m13() - projectionView.m11(), projectionView.m23() - projectionView.m21(), projectionView.m33() - projectionView.m31()).normalize();
        this.planes[4].set(projectionView.m03() + projectionView.m02(), projectionView.m13() + projectionView.m12(), projectionView.m23() + projectionView.m22(), projectionView.m33() + projectionView.m32()).normalize();
        this.planes[5].set(projectionView.m03() - projectionView.m02(), projectionView.m13() - projectionView.m12(), projectionView.m23() - projectionView.m22(), projectionView.m33() - projectionView.m32()).normalize();
    }

    /**
    * @return ...
    */

    public Plane getLeft() {
        return this.planes[0];
    }

    /**
    * @return ...
    */

    public Plane getRight() {
        return this.planes[1];
    }

    /**
    * @return ...
    */

    public Plane getTop() {
        return this.planes[2];
    }

    /**
    * @return ...
    */

    public Plane getBottom() {
        return this.planes[3];
    }

    /**
    * @return ...
    */

    public Plane getNear() {
        return this.planes[4];
    }

    /**
    * @return ...
    */

    public Plane getFar() {
        return this.planes[5];
    }

    /**
    * Analyzes if the {@link Position} resides inside the {@link Frustum}.
    *
    * @param position {@link Position}.
    * @param r Radius.
    *
    * @return true if the {@link Position} resides inside the {@link Frustum}, false otherwise.
    */

    public boolean resides(Position position, float r) {
        return this.resides(position.getX(), position.getY(), position.getZ(), r);
    }

    /**
    * Analyzes if the coordinates resides inside the {@link Frustum}.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    * @param r Radius.
    *
    * @return true if the coordinates resides inside the {@link Frustum}, false otherwise.
    */

    public boolean resides(float x, float y, float z, float r) {
        for(Plane plane : this.planes)
            if(plane.distance(x, y, z) <= -r)
                return false;

        return true;
    }
}