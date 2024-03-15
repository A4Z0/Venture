package org.a4z0.venture;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;

/**
* ...
*/

@Deprecated
public class Frustum {

    /**
    * Construct a {@link Frustum}.
    */

    public Frustum() {

    }

    /**
    * ...
    *
    * @param Positions ...
    * @param Projection ...
    * @param View ...
    *
    * @return ...
    */

    public static boolean isObjectInFrustum(float[] Positions, Matrix4f Projection, Matrix4f View) {
        Vector3f[] Vectors = new Vector3f[Positions.length / 3];

        for(int i = 0; i < Vectors.length; i++) {
            Vectors[i] = new Vector3f(Positions[i * 3], Positions[i * 3 + 1], Positions[i * 3 + 2]);
        }

        return isObjectInFrustum(Vectors, Projection, View);
    }

    /**
    * ...
    *
    * @param Vertices ...
    * @param Projection ...
    * @param View ...
    *
    * @return ...
    */

    public static boolean isObjectInFrustum(Vector3f[] Vertices, Matrix4f Projection, Matrix4f View) {
        for(Vector3f vertex : Vertices)
            if(isPointInFrustum(vertex, Projection, View))
                return true;

        return false;
    }

    /**
    * ...
    *
    * @param Point ...
    * @param Projection ...
    * @param View ...
    *
    * @return ...
    */

    public static boolean isPointInFrustum(Vector3f Point, Matrix4f Projection, Matrix4f View) {
        Vector4f pointInClipSpace = new Vector4f(Point, 1.0f)
            .mul(Projection)
            .mul(View);

        return pointInClipSpace.w > 0
            && pointInClipSpace.x >= -pointInClipSpace.w
            && pointInClipSpace.x <= pointInClipSpace.w
            && pointInClipSpace.y >= -pointInClipSpace.w
            && pointInClipSpace.y <= pointInClipSpace.w
            && pointInClipSpace.z >= -pointInClipSpace.w
            && pointInClipSpace.z <= pointInClipSpace.w;
    }
}