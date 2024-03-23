package org.a4z0.venture.render;

import org.a4z0.venture.gl.shader.Shaders;
import org.a4z0.venture.gl.vertex.Vertex;
import org.a4z0.venture.gl.image.texture.Textures;
import org.a4z0.venture.gl.vertex.VertexBuffer;
import org.joml.Matrix4f;

import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

/**
* ...
*/

public class BlockRenderer {

    /**
    * Construct a {@link BlockRenderer}.
    */

    public BlockRenderer() {

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
        Shaders.BLOCK_SHADER_PROGRAM.bind();

        glBindVertexArray(VertexBuffer.getVAO().getID());

        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);
        glEnableVertexAttribArray(2);
        glEnableVertexAttribArray(3);

        glActiveTexture(GL_TEXTURE0);
        glBindTexture(GL_TEXTURE_2D, Textures.TEXTURE_ARRAY.getID());

        Shaders.BLOCK_SHADER_PROGRAM.setUniform("transformation", transform(X, Y, Z));

        glDrawArrays(Mode, 0, Length);

        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);
        glDisableVertexAttribArray(2);
        glEnableVertexAttribArray(3);
        glBindVertexArray(0);

        VertexBuffer.getVAO().unbind();
        Shaders.BLOCK_SHADER_PROGRAM.unbind();

        VertexBuffer.delete();
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