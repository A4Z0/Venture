package org.a4z0.venture.render;

import org.a4z0.venture.Venture;
import org.a4z0.venture.gl.shader.Shaders;
import org.a4z0.venture.gl.vertex.VertexArrayObject;
import org.a4z0.venture.gl.vertex.VertexBufferObject;
import org.a4z0.venture.gl.vertex.VertexStream;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.GL_DYNAMIC_DRAW;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;

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
    *
    * @param lineSize ...
    */

    public void render(int lineSize) {
        float screenWidth = Venture.WINDOW.getWidth();
        float screenHeight = Venture.WINDOW.getHeight();

        VertexStream Stream = new VertexStream()
            .vertex(-((lineSize / screenWidth) * lineSize),  0, 0, 0, 0, 0, 0, 0, 0)
            .vertex(((lineSize / screenWidth) * lineSize),  0, 0, 0, 0, 0, 0, 0, 0)
            .vertex(0.0f,  ((lineSize / screenHeight) * lineSize), 0, 0, 0, 0, 0, 0, 0)
            .vertex(0.0f, -((lineSize / screenHeight) * lineSize), 0, 0, 0, 0, 0, 0, 0);

        VertexArrayObject VAO = new VertexArrayObject();
        VAO.bind();

        VertexBufferObject VBO = new VertexBufferObject();
        VBO.bind();

        VBO.buffer(Stream.elements(), GL_DYNAMIC_DRAW);

        VAO.attribute(0, 3, GL_FLOAT, true, VertexStream.VERTEX_SIZE_ELEMENTS * Float.BYTES, 0);
        VAO.attribute(1, 2, GL_FLOAT, true, VertexStream.VERTEX_SIZE_ELEMENTS * Float.BYTES, (3) * Float.BYTES);
        VAO.attribute(2, 3, GL_FLOAT, true, VertexStream.VERTEX_SIZE_ELEMENTS * Float.BYTES, (3 + 2) * Float.BYTES);
        VAO.attribute(3, 1, GL_FLOAT, true, VertexStream.VERTEX_SIZE_ELEMENTS * Float.BYTES, (3 + 2 + 3) * Float.BYTES);

        VBO.unbind();
        VAO.unbind();

        glDisable(GL_DEPTH_TEST);

        Shaders.CROSS_SHADER_PROGRAM.bind();

        VAO.bind();
        glEnableVertexAttribArray(0);

        glLineWidth(1f);
        glDrawArrays(GL_LINES, 0, Stream.vertices());
        glDisableVertexAttribArray(0);

        glLineWidth(1f);
        VAO.unbind();
        Shaders.CROSS_SHADER_PROGRAM.unbind();

        glEnable(GL_DEPTH_TEST);

        VAO.delete();
        VBO.delete();
    }
}