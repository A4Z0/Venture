package org.a4z0.venture.gl.render.renderer;

import org.a4z0.venture.Venture;
import org.a4z0.venture.gl.shader.ShaderProgram;
import org.a4z0.venture.gl.vertex.Vertex;
import org.a4z0.venture.gl.vertex.VertexBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

/**
* ...
*/

public class CrossRenderer {

    protected final ShaderProgram SHADER_PROGRAM;

    /**
    * Construct a {@link CrossRenderer}.
    *
    * @param SHADER_PROGRAM ...
    */

    public CrossRenderer(ShaderProgram SHADER_PROGRAM) {
        this.SHADER_PROGRAM = SHADER_PROGRAM;
    }

    public void renderAim() {
        float screenWidth = Venture.WINDOW.getWidth();
        float screenHeight = Venture.WINDOW.getHeight();

        float lineSize = 4f;

        float[] adjustedVertices = new float[]{
            -((lineSize / screenWidth) * lineSize),  0.0f, 0.0f, // Esquerda
            ((lineSize / screenWidth) * lineSize),  0.0f, 0.0f, // Direita
            0.0f,  ((lineSize / screenHeight) * lineSize), 0.0f, // Cima
            0.0f, -((lineSize / screenHeight) * lineSize), 0.0f // Baixo
        };

        Vertex Vertex = new Vertex().vertex(adjustedVertices);

        VertexBuffer VB = VertexBuffer.create(Vertex);

        glDisable(GL_DEPTH_TEST);

        this.SHADER_PROGRAM.bind();

        glBindVertexArray(VB.getVAO().getID());
        glEnableVertexAttribArray(0);

        glLineWidth(1.1f);
        glDrawArrays(GL_LINES, 0, Vertex.getLength());
        glDisableVertexAttribArray(0);

        glLineWidth(1f);
        VB.getVAO().unbind();
        this.SHADER_PROGRAM.unbind();

        glEnable(GL_DEPTH_TEST);
    }
}