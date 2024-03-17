package org.a4z0.venture.render.world.block.outline;

import org.a4z0.venture.render.VertexRenderer;
import org.a4z0.venture.world.block.BlockVertex;
import org.a4z0.venture.vertex.Vertex;
import org.a4z0.venture.world.Position;
import org.a4z0.venture.shader.ShaderProgram;
import org.a4z0.venture.texture.Textures;
import org.a4z0.venture.vertex.VertexBuffer;
import org.a4z0.venture.world.Direction;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.GL_ALIASED_LINE_WIDTH_RANGE;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

/**
* ...
*/

public class BlockOutlineRenderer extends VertexRenderer {

    public static final float LINE_MIN;
    public static final float LINE_MAX;

    static {
        int[] ALIASED = new int[2];
        glGetIntegerv(GL_ALIASED_LINE_WIDTH_RANGE, ALIASED);

        LINE_MIN = ALIASED[0];
        LINE_MAX = ALIASED[1];
    }

    /**
    * Construct a {@link BlockOutlineRenderer}.
    *
    * @param SHADER_PROGRAM ...
    */

    public BlockOutlineRenderer(ShaderProgram SHADER_PROGRAM) {
        super(SHADER_PROGRAM);
    }

    /**
    * Renders the Outline to a {@link Position}.
    *
    * @param Position ...
    * @param R ...
    * @param G ...
    * @param B ...
    * @param W ...
    */

    public void render(Position Position, float R, float G, float B, float A, float W) {
        this.render(Position.getX(), Position.getY(), Position.getZ(), R, G, B, A, W);
    }

    /**
    * Renders the Outline to a coordinate.
    *
    * @param X X Axis.
    * @param Y Y Axis.
    * @param Z Z Axis.
    * @param W Outline Length.
    */

    public void render(int X, int Y, int Z, float W) {
        this.render(X, Y, Z, 0f, 0f, 0f, W);
    }

    /**
    * Renders the Outline to a coordinate.
    *
    *
    * @param X X Axis.
    * @param Y Y Axis.
    * @param Z Z Axis.
    * @param R Red
    * @param G Green
    * @param B Blue
    * @param W Outline Length.
    */

    public void render(int X, int Y, int Z, float R, float G, float B, float W) {
        this.render(X, Y, Z, R, G, B, 1f, W);
    }

    /**
    * Renders the Outline to a coordinate.
    *
    *
    * @param X X Axis.
    * @param Y Y Axis.
    * @param Z Z Axis.
    * @param R Red
    * @param G Green
    * @param B Blue
    * @param A Alpha
    * @param W Outline Length.
    */

    @Deprecated
    public void render(int X, int Y, int Z, float R, float G, float B, float A, float W) {
        Vertex Vertex = new Vertex();

        for(Direction Dir : Direction.values())
            BlockVertex.stream(Vertex, X, Y, Z, Dir);

        VertexBuffer VB = VertexBuffer.create(Vertex);

        this.SHADER_PROGRAM.use();

        glBindVertexArray(VB.getVAO().getID());
        glEnableVertexAttribArray(0);

        glActiveTexture(GL_TEXTURE0);
        glBindTexture(GL_TEXTURE_2D, Textures.TEXTURE_ARRAY.getID());

        this.SHADER_PROGRAM.setUniform("outline_color", R, G, B, A);
        this.SHADER_PROGRAM.setUniform("transformation", transform(X, Y, Z));

        glLineWidth(Math.max(LINE_MIN, Math.min(LINE_MAX, W)));
        glDrawArrays(GL_LINES, 0, Vertex.getLength());
        glDisableVertexAttribArray(0);

        glLineWidth(1f);
        VB.getVAO().unbind();
        this.SHADER_PROGRAM.unbind();
    }
}