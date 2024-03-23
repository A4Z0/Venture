package org.a4z0.venture.render;

import org.a4z0.venture.gl.shader.ShaderProgram;
import org.a4z0.venture.gl.shader.Shaders;
import org.a4z0.venture.gl.vertex.Vertex;
import org.a4z0.venture.gl.vertex.VertexBuffer;
import org.a4z0.venture.world.position.block.BlockPosition;
import org.joml.Matrix4f;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

/**
* ...
*/

public class OutlineRenderer {

    public final float[] OUTLINE_OFFSET = new float[]{
        0.5f, 0.5f, 0.5f,
        0.5f, 0.5f, 0.5f,
        0.5f, 0.5f, 0.5f,
        0.5f, 0.5f, 0.5f,
        0.5f, 0.5f, 0.5f,
        0.5f, 0.5f, 0.5f,
        0.5f, 0.5f, 0.5f,
        0.5f, 0.5f, 0.5f,
        0.5f, 0.5f, 0.5f,
        0.5f, 0.5f, 0.5f,
        0.5f, 0.5f, 0.5f,
        0.5f, 0.5f, 0.5f,
        0.5f, 0.5f, 0.5f,
        0.5f, 0.5f, 0.5f,
        0.5f, 0.5f, 0.5f,
        0.5f, 0.5f, 0.5f,
        0.5f, 0.5f, 0.5f,
        0.5f, 0.5f, 0.5f,
        0.5f, 0.5f, 0.5f,
        0.5f, 0.5f, 0.5f,
        0.5f, 0.5f, 0.5f,
        0.5f, 0.5f, 0.5f,
        0.5f, 0.5f, 0.5f,
        0.5f, 0.5f, 0.5f
    };

    public static final Matrix4f TRANSFORMATION = new Matrix4f().translate(0, 0, 0);

    /**
    * Construct a {@link OutlineRenderer}.
    */

    public OutlineRenderer() {

    }

    /**
    * ...
    *
    * @param block ...
    * @param r ...
    * @param g ...
    * @param b ...
    * @param alpha ...
    * @param lineWidth ...
    */

    public void render(BlockPosition block, float r, float g, float b, float alpha, float lineWidth) {
        this.render(
            block.getX(),
            block.getY(),
            block.getZ(),
            -1 - (-block.getX()),
            -1 - (-block.getY()),
            -1 - (-block.getZ()),
            r, g, b, alpha, lineWidth
        );
    }

    /**
    * ...
    *
    * @param x1 ...
    * @param y1 ...
    * @param z1 ...
    * @param x2 ...
    * @param y2 ...
    * @param z2 ...
    * @param r ...
    * @param g ...
    * @param b ...
    * @param alpha ...
    * @param lineWidth ...
    */

    public void render(float x1, float y1, float z1, float x2, float y2, float z2, float r, float g, float b, float alpha, float lineWidth) {
        this.render(new Vertex().vertex(new float[]{
            x1, y1, z1,
            x2, y1, z1,
            x1, y1, z2,
            x2, y1, z2,
            x1, y2, z1,
            x2, y2, z1,
            x1, y2, z2,
            x2, y2, z2,
            x1, y1, z1,
            x1, y2, z1,
            x2, y1, z1,
            x2, y2, z1,
            x1, y1, z2,
            x1, y2, z2,
            x2, y1, z2,
            x2, y2, z2,
            x1, y1, z1,
            x1, y1, z2,
            x2, y1, z1,
            x2, y1, z2,
            x1, y2, z1,
            x1, y2, z2,
            x2, y2, z1,
            x2, y2, z2
        }).offset(0, 0, OUTLINE_OFFSET), r, g, b, alpha, lineWidth);
    }

    /**
    * ...
    *
    * @param Vertex ...
    * @param r ...
    * @param g ...
    * @param b ...
    * @param alpha ...
    * @param lineWidth ...
    */

    protected void render(Vertex Vertex, float r, float g, float b, float alpha, float lineWidth) {
        this.render(VertexBuffer.create(Vertex), Vertex.getLength(), r, g, b, alpha, lineWidth);
    }

    /**
    * ...
    *
    * @param VertexBuffer ...
    * @param Length ...
    * @param r ...
    * @param g ...
    * @param b ...
    * @param alpha ...
    * @param lineWidth ...
    */

    protected void render(VertexBuffer VertexBuffer, int Length, float r, float g, float b, float alpha, float lineWidth) {
        Shaders.OUTLINE_SHADER_PROGRAM.bind();

        glBindVertexArray(VertexBuffer.getVAO().getID());
        glEnableVertexAttribArray(0);

        Shaders.OUTLINE_SHADER_PROGRAM.setUniform("outline_color", r, g, b, alpha);
        Shaders.OUTLINE_SHADER_PROGRAM.setUniform("transformation", TRANSFORMATION);

        glLineWidth(lineWidth);
        glDrawArrays(GL_LINES, 0, Length);
        glDisableVertexAttribArray(0);

        glLineWidth(1f);
        VertexBuffer.getVAO().unbind();
        Shaders.OUTLINE_SHADER_PROGRAM.unbind();
        
        VertexBuffer.delete();
    }
}