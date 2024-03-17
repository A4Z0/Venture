package org.a4z0.venture.render;

import org.a4z0.venture.vertex.Vertex;
import org.a4z0.venture.shader.ShaderProgram;
import org.a4z0.venture.texture.Textures;
import org.a4z0.venture.vertex.VertexBuffer;
import org.joml.Matrix4f;

import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

/**
* ...
*/

public class VertexRenderer {

    protected final ShaderProgram SHADER_PROGRAM;

    /**
    * Construct a {@link VertexRenderer}.
    *
    * @param SHADER_PROGRAM ...
    */

    public VertexRenderer(ShaderProgram SHADER_PROGRAM) {
        this.SHADER_PROGRAM = SHADER_PROGRAM;
    }

    /**
    * ...
    *
    * @param X ...
    * @param Y ...
    * @param Z ...
    * @param Vertex ...
    */

    public void render(int X, int Y, int Z, Vertex Vertex) {
        this.render(X, Y, Z, VertexBuffer.create(Vertex), Vertex.getLength());
    }

    /**
    * ...
    *
    * @param X ...
    * @param Y ...
    * @param Z ...
    * @param VertexBuffer ...
    * @param Length ...
    */

    public void render(int X, int Y, int Z, VertexBuffer VertexBuffer, int Length) {
        this.render(X, Y, Z, VertexBuffer, GL_TRIANGLES, Length);
    }

    /**
    * ...
    *
    * @param X ...
    * @param Y ...
    * @param Z ...
    * @param VertexBuffer ...
    * @param Mode ...
    * @param Length ....
    */

    public void render(int X, int Y, int Z, VertexBuffer VertexBuffer, int Mode, int Length) {
        this.SHADER_PROGRAM.use();

        glBindVertexArray(VertexBuffer.getVAO().getID());

        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);
        glEnableVertexAttribArray(2);
        glEnableVertexAttribArray(3);

        glActiveTexture(GL_TEXTURE0);
        glBindTexture(GL_TEXTURE_2D, Textures.TEXTURE_ARRAY.getID());
        
        this.SHADER_PROGRAM.setUniform("transformation", transform(X, Y, Z));

        glDrawArrays(Mode, 0, Length);

        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);
        glDisableVertexAttribArray(2);
        glEnableVertexAttribArray(3);
        glBindVertexArray(0);

        VertexBuffer.getVAO().unbind();
        this.SHADER_PROGRAM.unbind();
    }

    /**
    * ...
    *
    * @param X ...
    * @param Y ...
    * @param Z ...
    *
    * @return ...
    */

    public static Matrix4f transform(int X, int Y, int Z) {
        return new Matrix4f().translate(X, Y, Z);
    }
}