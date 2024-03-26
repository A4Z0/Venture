package org.a4z0.venture.render;

import jdk.jfr.Experimental;
import org.a4z0.venture.box.AABB;
import org.a4z0.venture.gl.shader.Shaders;
import org.a4z0.venture.gl.vertex.VertexBuffer;
import org.a4z0.venture.gl.vertex.VertexStream;
import org.a4z0.venture.position.Position;
import org.a4z0.venture.position.block.BlockPosition;
import org.a4z0.venture.world.block.Block;
import org.a4z0.venture.world.chunk.Chunk;
import org.joml.Matrix4f;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

/**
* ...
*/

@Experimental
public class OutlineRenderer {

    /** Represents the bounding edges of a {@link Block}. */
    public static final AABB BLOCK_BOUNDARIES = new AABB(0, 0, 0, -1, -1, -1);

    /** Represents the bounding edges of a {@link Chunk}. */
    public static final AABB CHUNK_BOUNDARIES = new AABB(Chunk.CHUNK_SIZE_X -1, Chunk.CHUNK_SIZE_Y -1, Chunk.CHUNK_SIZE_Z -1, -1, -1, -1);

    /** Represents the bounding edges of a {@link Chunk} Layer. */
    public static final AABB LAYER_BOUNDARIES = new AABB(Chunk.CHUNK_SIZE_X -1, 0, Chunk.CHUNK_SIZE_Z -1, -1, -1, -1);

    /**
    * Construct a {@link OutlineRenderer}.
    */

    public OutlineRenderer() {

    }

    // TODO: Describe what each parameter does.
    public void render(Block block, float r, float g, float b, float a, float w) {
        this.render(BLOCK_BOUNDARIES, block.getPosition(), r, g, b, a, w);
    }

    // TODO: Describe what each parameter does.
    public void render(Chunk chunk, float r, float g, float b, float a, float w) {
        this.render(CHUNK_BOUNDARIES, chunk.getX(), 0, chunk.getZ(), r, g, b, a, w);

        for(int y = 0; y <= Chunk.CHUNK_SIZE_Y; y++)
            this.render(LAYER_BOUNDARIES, chunk.getX(), y, chunk.getZ(), r, g, b, a, w);
    }

    // TODO: Describe what each parameter does.
    public void render(AABB aabb, BlockPosition position, float r, float g, float b, float a, float w) {
        this.render(aabb, position.getX(), position.getY(), position.getZ(), r, g, b, a, w);
    }

    // TODO: Describe what each parameter does.
    public void render(AABB aabb, Position position, float r, float g, float b, float a, float w) {
        this.render(aabb, position.getX(), position.getY(), position.getZ(), r, g, b, a, w);
    }

    // TODO: Describe what each parameter does.
    public void render(AABB aabb, float x, float y, float z, float r, float g, float b, float a, float w) {
        this.render(aabb.getLowerX(), aabb.getLowerY(), aabb.getLowerZ(), aabb.getUpperX(), aabb.getUpperY(), aabb.getUpperZ(), x, y, z, r, g, b, a, w);
    }

    // TODO: Describe what each parameter does.
    public void render(float x1, float y1, float z1, float x2, float y2, float z2, float x, float y, float z, float r, float g, float b, float a, float width) {
        VertexStream stream = new VertexStream()
            .vertex(x1 + 0.5f, y1 + 0.5f, z1 + 0.5f, 0, 0, 0, 0, 0, 0)
            .vertex(x2 + 0.5f, y1 + 0.5f, z1 + 0.5f, 0, 0, 0, 0, 0, 0)
            .vertex(x1 + 0.5f, y1 + 0.5f, z2 + 0.5f, 0, 0, 0, 0, 0, 0)
            .vertex(x2 + 0.5f, y1 + 0.5f, z2 + 0.5f, 0, 0, 0, 0, 0, 0)
            .vertex(x1 + 0.5f, y2 + 0.5f, z1 + 0.5f, 0, 0, 0, 0, 0, 0)
            .vertex(x2 + 0.5f, y2 + 0.5f, z1 + 0.5f, 0, 0, 0, 0, 0, 0)
            .vertex(x1 + 0.5f, y2 + 0.5f, z2 + 0.5f, 0, 0, 0, 0, 0, 0)
            .vertex(x2 + 0.5f, y2 + 0.5f, z2 + 0.5f, 0, 0, 0, 0, 0, 0)
            .vertex(x1 + 0.5f, y1 + 0.5f, z1 + 0.5f, 0, 0, 0, 0, 0, 0)
            .vertex(x1 + 0.5f, y2 + 0.5f, z1 + 0.5f, 0, 0, 0, 0, 0, 0)
            .vertex(x2 + 0.5f, y1 + 0.5f, z1 + 0.5f, 0, 0, 0, 0, 0, 0)
            .vertex(x2 + 0.5f, y2 + 0.5f, z1 + 0.5f, 0, 0, 0, 0, 0, 0)
            .vertex(x1 + 0.5f, y1 + 0.5f, z2 + 0.5f, 0, 0, 0, 0, 0, 0)
            .vertex(x1 + 0.5f, y2 + 0.5f, z2 + 0.5f, 0, 0, 0, 0, 0, 0)
            .vertex(x2 + 0.5f, y1 + 0.5f, z2 + 0.5f, 0, 0, 0, 0, 0, 0)
            .vertex(x2 + 0.5f, y2 + 0.5f, z2 + 0.5f, 0, 0, 0, 0, 0, 0)
            .vertex(x1 + 0.5f, y1 + 0.5f, z1 + 0.5f, 0, 0, 0, 0, 0, 0)
            .vertex(x1 + 0.5f, y1 + 0.5f, z2 + 0.5f, 0, 0, 0, 0, 0, 0)
            .vertex(x2 + 0.5f, y1 + 0.5f, z1 + 0.5f, 0, 0, 0, 0, 0, 0)
            .vertex(x2 + 0.5f, y1 + 0.5f, z2 + 0.5f, 0, 0, 0, 0, 0, 0)
            .vertex(x1 + 0.5f, y2 + 0.5f, z1 + 0.5f, 0, 0, 0, 0, 0, 0)
            .vertex(x1 + 0.5f, y2 + 0.5f, z2 + 0.5f, 0, 0, 0, 0, 0, 0)
            .vertex(x2 + 0.5f, y2 + 0.5f, z1 + 0.5f, 0, 0, 0, 0, 0, 0)
            .vertex(x2 + 0.5f, y2 + 0.5f, z2 + 0.5f, 0, 0, 0, 0, 0, 0);

        VertexBuffer Buffer = VertexBuffer.create(stream);

        Shaders.OUTLINE_SHADER_PROGRAM.bind();

        glBindVertexArray(Buffer.getVAO().getID());
        glEnableVertexAttribArray(0);

        Shaders.OUTLINE_SHADER_PROGRAM.setUniform("outline_color", r, g, b, a);
        Shaders.OUTLINE_SHADER_PROGRAM.setUniform("transformation", new Matrix4f().translate(x, y, z));

        glLineWidth(width);
        glDrawArrays(GL_LINES, 0, stream.vertices());
        glDisableVertexAttribArray(0);

        glLineWidth(1f);
        Buffer.getVAO().unbind();
        Shaders.OUTLINE_SHADER_PROGRAM.unbind();

        Buffer.delete();
    }
}