package org.a4z0.venture.render.world.chunk;

import org.a4z0.venture.render.VertexRenderer;
import org.a4z0.venture.world.chunk.Chunk;
import org.a4z0.venture.world.chunk.Mesh;
import org.a4z0.venture.shader.ShaderProgram;

/**
* ...
*/

public class ChunkRenderer extends VertexRenderer {

    /**
    * Construct a {@link ChunkRenderer}.
    *
    * @param SHADER_PROGRAM ...
    */

    public ChunkRenderer(ShaderProgram SHADER_PROGRAM) {
        super(SHADER_PROGRAM);
    }

    /**
    * ...
    *
    * @param Chunk ...
    */

    public void render(Chunk Chunk) {
        this.render(Chunk.getX(), Chunk.getZ(), Chunk);
    }

    /**
    * ...
    *
    * @param X ...
    * @param Z ...
    * @param Chunk ...
    */

    public void render(int X, int Z, Chunk Chunk) {
        this.render(X, 0, Z, Mesh.transform(Chunk));
    }

    /**
    * ...
    *
    * @param X ...
    * @param Y ...
    * @param Z ...
    * @param Chunk ...
    */

    public void render(int X, int Y, int Z, Chunk Chunk) {
        this.render(X, Y, Z, Mesh.transform(Chunk));
    }
}