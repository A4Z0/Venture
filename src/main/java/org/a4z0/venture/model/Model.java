package org.a4z0.venture.model;

import org.a4z0.venture.Token;
import org.a4z0.venture.vertex.Vertex;

import java.io.*;
import java.nio.file.Path;
import java.util.Arrays;

/**
* A {@link Model} represented by Vertices, UVs and Normals.
*/

public class Model {

    public static final int DEFAULT_BUFFER_SIZE = 4096;

    public static final Token VERTEX_TOKEN = new Token("v");
    public static final Token UV_TOKEN = new Token("vt");
    public static final Token NORMAL_TOKEN = new Token("vn");

    protected final float[] vertices;
    protected final float[] uvs;
    protected final float[] normals;

    /**
    * Construct a {@link Model}.
    *
    * @param vertex ...
    */

    public Model(Vertex vertex) {
        this(vertex.getPositions(), vertex.getUVs(), vertex.getNormals());
    }

    /**
    * Construct a {@link Model}.
    *
    * @param vertices {@link Float Float} Array representing vertices of this {@link Model}.
    * @param uvs {@link Float Float} Array representing UVs of this {@link Model}.
    * @param normals {@link Float Float} Array representing normals of this {@link Model}.
    */

    public Model(float[] vertices, float[] uvs, float[] normals) {
        this.vertices = vertices;
        this.uvs = uvs;
        this.normals = normals;
    }

    /**
    * @return a {@link Float} array with this {@link Model} vertices.
    */

    public float[] getVertices() {
        return this.vertices.clone();
    }

    /**
    * @return a {@link Float} array with this {@link Model} UVs.
    */

    public float[] getUVs() {
        return this.uvs.clone();
    }

    /**
    * @return a {@link Float} array with this {@link Model} normals.
    */

    public float[] getNormals() {
        return this.normals.clone();
    }

    /**
    * Constructs a {@link Model} from an ".obj".
    *
    * @param URI {@link String String} URI of the ".obj" {@link File} in "resources/".
    *
    * @return a new {@link Model}.
    */

    public static Model getFrom(String URI) {
        return getFrom(Path.of(URI));
    }

    /**
    * Constructs a {@link Model} from an ".obj".
    *
    * @param path {@link Path} of the ".obj" {@link File} in "resources/".
    *
    * @return a new {@link Model}.
    */

    public static Model getFrom(Path path) {
        try {
            return getFrom(Model.class.getClassLoader().getResourceAsStream(path.toString()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
    * Constructs a {@link Model} from an ".obj".
    *
    * @param in {@link InputStream} from an ".obj" {@link File}.
    *
    * @return a new {@link Model}.
    */

    public static Model getFrom(InputStream in) throws IOException {
        return getFrom(in, DEFAULT_BUFFER_SIZE);
    }

    /**
    * Constructs a {@link Model} from an ".obj".
    *
    * @param in {@link InputStream} from an ".obj" {@link File}.
    * @param bufferSize {@link Float} buffer size.
    *
    * @return a new {@link Model}.
    */

    public static Model getFrom(InputStream in, int bufferSize) throws IOException {
        float[] vertices = new float[3 * bufferSize];
        float[] uvs = new float[2 * bufferSize];
        float[] normals = new float[3 * bufferSize];

        int vIndex = 0;
        int uIndex = 0;
        int nIndex = 0;

        try(BufferedReader bR = new BufferedReader(new InputStreamReader(in))) {
            String l;

            while((l = bR.readLine()) != null) {
                String[] Parts = l.split("\\s+");

                if(Parts.length == 0 || Parts[0].equals("#"))
                    continue;

                if(VERTEX_TOKEN.matches(Parts[0])) {
                    for(int i = 1; i < Parts.length; i++)
                        vertices[vIndex++] = Float.parseFloat(Parts[i]);
                } else if(UV_TOKEN.matches(Parts[0])) {
                    for(int i = 1; i < Parts.length; i++)
                        uvs[uIndex++] = Float.parseFloat(Parts[i]);
                } else if(NORMAL_TOKEN.matches(Parts[0])) {
                    for(int i = 1; i < Parts.length; i++)
                        normals[nIndex++] = Float.parseFloat(Parts[i]);
                }
            }
        }

        return new Model(
            Arrays.copyOfRange(vertices, 0, vIndex),
            Arrays.copyOfRange(uvs, 0, uIndex),
            Arrays.copyOfRange(normals, 0, nIndex)
        );
    }
}