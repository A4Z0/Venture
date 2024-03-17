package org.a4z0.venture.shader;

import org.joml.Matrix4fc;
import org.joml.Vector3fc;
import org.lwjgl.system.MemoryStack;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL20.*;

/**
* ...
*/

public class ShaderProgram {

    protected final int SHADER_PROGRAM_ID;

    /**
    * Construct a {@link ShaderProgram}.
    */

    public ShaderProgram() {
        this.SHADER_PROGRAM_ID = glCreateProgram();
    }

    /**
    * @return ...
    */

    public int getID() {
        return this.SHADER_PROGRAM_ID;
    }

    /**
    * ...
    *
    * @param Shader ...
    */

    public void add(Shader Shader) {
        glAttachShader(this.SHADER_PROGRAM_ID, Shader.getID());
    }

    /**
    * ...
    *
    * @param attr ...
    * @param var ...
    */

    public void bind(int attr, String var) {
        glBindAttribLocation(this.SHADER_PROGRAM_ID, attr, var);
    }

    /**
    * @param name ...
    *
    * @return ...
    */

    public int getUniformLocation(String name) {
        return glGetUniformLocation(this.SHADER_PROGRAM_ID, name);
    }

    /**
    * ...
    *
    * @param name ...
    * @param x ...
    * @param y ...
    * @param z ...
    */

    public void setUniform(String name, float x, float y, float z) {
        glUniform3f(this.getUniformLocation(name), x, y, z);
    }

    public void setUniform(String name, float x, float y, float z, float w) {
        glUniform4f(this.getUniformLocation(name), x, y, z, w);
    }

    public void setUniform(String name, float f) {
        glUniform1f(this.getUniformLocation(name), f);
    }

    /**
    * ...
    *
    * @param name ...
    * @param vec ...
    */

    public void setUniform(String name, Vector3fc vec) {
        this.setUniform(name, vec.x(), vec.y(), vec.z());
    }

    /**
    * ...
    *
    * @param name ...
    * @param matrix ..
    */

    public void setUniform(String name, Matrix4fc matrix) {
        try(MemoryStack stack = MemoryStack.stackPush()) {
            FloatBuffer buffer = stack.mallocFloat(4 * 4);
            matrix.get(buffer);

            glUniformMatrix4fv(this.getUniformLocation(name), false, buffer);
        }
    }

    /**
    * ...
    */

    public void use() {
        glUseProgram(this.SHADER_PROGRAM_ID);
    }

    /**
    * ...
    */

    public void link() {
        glLinkProgram(this.SHADER_PROGRAM_ID);

        if(glGetProgrami(this.SHADER_PROGRAM_ID, GL_LINK_STATUS) == 0)
            throw new RuntimeException("Unable to link this Shader Program.");
    }

    public void unbind() {
        glUseProgram(0);
    }

    /**
    * ...
    */

    public void delete() {
        glDeleteProgram(this.SHADER_PROGRAM_ID);
    }
}