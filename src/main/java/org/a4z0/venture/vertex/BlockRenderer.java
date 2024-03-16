package org.a4z0.venture.vertex;

import org.a4z0.venture.frustum.AABB;
import org.a4z0.venture.model.old.Model;
import org.a4z0.venture.shader.ShaderProgram;
import org.a4z0.venture.texture.Textures;
import org.joml.Matrix4f;
import org.joml.Vector3f;

import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

/**
* ...
*/

public class BlockRenderer {

    protected final ShaderProgram SHADER_PROGRAM;

    /**
    * Construct a {@link BlockRenderer}.
    *
    * @param SHADER_PROGRAM ...
    */

    public BlockRenderer(ShaderProgram SHADER_PROGRAM) {
        this.SHADER_PROGRAM = SHADER_PROGRAM;
    }

    /**
    * ...
    *
    * @param X ...
    * @param Y ...
    * @param Z ...
    * @param Model ...
    */

    public void render(int X, int Y, int Z, Model Model) {
        glBindVertexArray(Model.getVAO().getID());
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);
        glEnableVertexAttribArray(2);
        glEnableVertexAttribArray(3);

        glActiveTexture(GL_TEXTURE0);
        glBindTexture(GL_TEXTURE_2D, Textures.TEXTURE_ARRAY.getID());
        
        this.SHADER_PROGRAM.setUniform("transformation", transform(X, Y, Z));

        glDrawArrays(GL_TRIANGLES, 0, Model.getLength());

        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);
        glDisableVertexAttribArray(2);
        glEnableVertexAttribArray(3);
        glBindVertexArray(0);
    }

    /**
    * ...
    *
    * @param aabb ...
    */

    public void render(AABB aabb) {

        Vector3f min = aabb.getMin();
        Vector3f max = aabb.getMax();

        glColor3f(1.0f, 1.0f, 0.0f);

        glBegin(GL_LINES);

        glVertex3f(min.x, min.y, min.z);
        glVertex3f(max.x, min.y, min.z);
        glVertex3f(max.x, min.y, min.z);
        glVertex3f(max.x, max.y, min.z);
        glVertex3f(max.x, max.y, min.z);
        glVertex3f(min.x, max.y, min.z);
        glVertex3f(min.x, max.y, min.z);
        glVertex3f(min.x, min.y, min.z);

        glVertex3f(min.x, min.y, max.z);
        glVertex3f(max.x, min.y, max.z);
        glVertex3f(max.x, min.y, max.z);
        glVertex3f(max.x, max.y, max.z);
        glVertex3f(max.x, max.y, max.z);
        glVertex3f(min.x, max.y, max.z);
        glVertex3f(min.x, max.y, max.z);
        glVertex3f(min.x, min.y, max.z);

        glVertex3f(min.x, min.y, min.z);
        glVertex3f(min.x, min.y, max.z);
        glVertex3f(max.x, min.y, min.z);
        glVertex3f(max.x, min.y, max.z);
        glVertex3f(min.x, max.y, min.z);
        glVertex3f(min.x, max.y, max.z);
        glVertex3f(max.x, max.y, min.z);
        glVertex3f(max.x, max.y, max.z);

        glEnd();
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