package org.a4z0.venture.render;

import org.a4z0.venture.Venture;
import org.a4z0.venture.gl.shader.ShaderProgram;
import org.a4z0.venture.gl.shader.Shaders;
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

    /**
    * Construct a {@link CrossRenderer}.
    */

    public CrossRenderer() {

    }

    /**
    * ...
    */

    public void render() {
        float screenWidth = Venture.WINDOW.getWidth();
        float screenHeight = Venture.WINDOW.getHeight();

        float lineSize = 4f;

        float[] adjustedVertices = new float[]{
            -((lineSize / screenWidth) * lineSize),  0.0f, 0.0f, // Left
            ((lineSize / screenWidth) * lineSize),  0.0f, 0.0f, // Right
            0.0f,  ((lineSize / screenHeight) * lineSize), 0.0f, // Top
            0.0f, -((lineSize / screenHeight) * lineSize), 0.0f // Bottom
        };

        Vertex Vertex = new Vertex().vertex(adjustedVertices);

        VertexBuffer VB = VertexBuffer.create(Vertex);

        glDisable(GL_DEPTH_TEST);

        Shaders.CROSS_SHADER_PROGRAM.bind();

        glBindVertexArray(VB.getVAO().getID());
        glEnableVertexAttribArray(0);

        glLineWidth(1.1f);
        glDrawArrays(GL_LINES, 0, Vertex.getLength());
        glDisableVertexAttribArray(0);

        glLineWidth(1f);
        VB.getVAO().unbind();
        Shaders.CROSS_SHADER_PROGRAM.unbind();

        glEnable(GL_DEPTH_TEST);
    }
}