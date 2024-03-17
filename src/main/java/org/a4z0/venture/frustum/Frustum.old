package org.a4z0.venture.frustum;

import org.a4z0.venture.Venture;
import org.a4z0.venture.camera.Camera;
import org.a4z0.venture.camera.FirstPersonCamera;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;

/**
* ...
*/

public class Frustum {

    public static final Plane NEAR = new Plane(0, 0, -1, 1);
    public static final Plane FAR = new Plane(0, 0, 1, 1);
    public static final Plane LEFT = new Plane(1, 0, 0, 1);
    public static final Plane RIGHT = new Plane(-1, 0, 0, 1);
    public static final Plane TOP = new Plane(0, -1, 0, 1);
    public static final Plane BOTTOM = new Plane(0, 1, 0, 1);

    protected final Plane left;
    protected final Plane right;
    protected final Plane bottom;
    protected final Plane top;
    protected final Plane near;
    protected final Plane far;

    /**
    * Construct a {@link Frustum}.
    */

    public Frustum() {
        this(NEAR, FAR, LEFT, RIGHT, TOP, BOTTOM);
    }

    /**
    * Construct a {@link Frustum}.
    *
    * @param near ...
    * @param far ...
    * @param left ...
    * @param right ...
    * @param top ...
    * @param bottom ...
    */

    public Frustum(
        Plane near,
        Plane far,
        Plane left,
        Plane right,
        Plane top,
        Plane bottom
    ) {
        this.left = left;
        this.right = right;
        this.bottom = bottom;
        this.top = top;
        this.near = near;
        this.far = far;
    }

    public void updateFrustum(FirstPersonCamera camera) {
        Matrix4f viewProjectionMatrix = camera.getProjection().mul(camera.getView());

        // Extrai os planos do frustum a partir da matriz de projeção
        // Os índices das linhas e colunas na matriz de projeção que definem os planos do frustum são:
        // Near: 3rd row
        // Far: 4th row
        // Left: 1st column
        // Right: 2nd column
        // Top: 3rd column
        // Bottom: 4th column

        // Planeos do frustum são definidos como:
        // Ax + By + Cz + D = 0
        // Onde (A, B, C) é o normal do plano e D é a distância do plano ao ponto de origem.

        Vector4f nearPlane = new Vector4f(viewProjectionMatrix.m30(), viewProjectionMatrix.m31(), viewProjectionMatrix.m32(), viewProjectionMatrix.m33());
        Vector4f farPlane = new Vector4f(viewProjectionMatrix.m20(), viewProjectionMatrix.m21(), viewProjectionMatrix.m22(), viewProjectionMatrix.m23());
        Vector4f leftPlane = new Vector4f(viewProjectionMatrix.m00(), viewProjectionMatrix.m01(), viewProjectionMatrix.m02(), viewProjectionMatrix.m03());
        Vector4f rightPlane = new Vector4f(viewProjectionMatrix.m10(), viewProjectionMatrix.m11(), viewProjectionMatrix.m12(), viewProjectionMatrix.m13());
        Vector4f topPlane = new Vector4f(viewProjectionMatrix.m10(), viewProjectionMatrix.m11(), viewProjectionMatrix.m12(), viewProjectionMatrix.m13());
        Vector4f bottomPlane = new Vector4f(viewProjectionMatrix.m20(), viewProjectionMatrix.m21(), viewProjectionMatrix.m22(), viewProjectionMatrix.m23());

        // Atualiza os planos do frustum
        near.set(nearPlane);
        far.set(farPlane);
        left.set(leftPlane);
        right.set(rightPlane);
        top.set(topPlane);
        bottom.set(bottomPlane);
    }

    public boolean intersects(AABB AABB) {
        Vector3f[] corners = AABB.getCorners();

        // Verifica se pelo menos um vértice do AABB está dentro do frustum
        for (Vector3f corner : corners) {
            if (isInside(corner)) {
                return true;
            }
        }

        // Verifica se o AABB intersecta os planos do frustum
        if (intersectsPlane(AABB, left) ||
                intersectsPlane(AABB, right) ||
                intersectsPlane(AABB, bottom) ||
                intersectsPlane(AABB, top) ||
                intersectsPlane(AABB, near) ||
                intersectsPlane(AABB, far)) {
            return true;
        }

        return false;
    }

    /**
     * Verifica se um vértice está dentro do frustum.
     *
     * @param vertex O vértice a ser verificado.
     * @return true se o vértice está dentro do frustum, false caso contrário.
     */
    private boolean isInside(Vector3f vertex) {
        // Converte o Vector3f para Vector4f, assumindo que o vértice está na origem do espaço do plano
        Vector4f vertex4f = new Vector4f(vertex.x, vertex.y, vertex.z, 1.0f);

        return !(left.distance(vertex4f) < 0 ||
                right.distance(vertex4f) < 0 ||
                bottom.distance(vertex4f) < 0 ||
                top.distance(vertex4f) < 0 ||
                near.distance(vertex4f) < 0 ||
                far.distance(vertex4f) < 0);
    }

    /**
     * Verifica se o AABB intersecta um plano do frustum.
     *
     * @param AABB O AABB a ser verificado.
     * @param plane O plano a ser verificado.
     * @return true se o AABB intersecta o plano, false caso contrário.
     */
    private boolean intersectsPlane(AABB AABB, Plane plane) {
        Vector3f[] corners = AABB.getCorners();
        int positiveCorners = 0;
        int negativeCorners = 0;

        for (Vector3f corner : corners) {
            // Converte o Vector3f para Vector4f, assumindo que o vértice está na origem do espaço do plano
            Vector4f corner4f = new Vector4f(corner.x, corner.y, corner.z, 1.0f);
            float distance = plane.distance(corner4f);

            if (distance > 0) {
                positiveCorners++;
            } else if (distance < 0) {
                negativeCorners++;
            }
        }

        // Se houver pelo menos um vértice positivo e pelo menos um vértice negativo, o AABB intersecta o plano
        return positiveCorners > 0 && negativeCorners > 0;
    }
}