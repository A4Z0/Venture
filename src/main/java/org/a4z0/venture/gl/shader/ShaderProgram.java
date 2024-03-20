package org.a4z0.venture.gl.shader;

import org.joml.Matrix4dc;
import org.joml.Matrix4fc;
import org.joml.Vector3fc;
import org.lwjgl.system.MemoryStack;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL20.*;

/**
* ...
*/

public class ShaderProgram {

    protected final int glShaderProgram;

    /**
    * Construct a {@link ShaderProgram}.
    */

    public ShaderProgram() {
        this.glShaderProgram = glCreateProgram();
    }

    /**
    * @return ...
    */

    public int getID() {
        return this.glShaderProgram;
    }

    /**
    * Binds this {@link ShaderProgram} to the current context.
    */

    public void bind() {
        glUseProgram(this.glShaderProgram);
    }

    /**
    * ...
    *
    * @param shader ...
    */

    public void addShader(Shader shader) {
        glAttachShader(this.glShaderProgram, shader.getID());
    }

    /**
    * ...
    *
    * @param attr ...
    * @param var ...
    */

    public void attribute(int attr, String var) {
        glBindAttribLocation(this.glShaderProgram, attr, var);
    }

    /**
    * Gets the name of the given Uniform.
    *
    * @param glName Name of the Uniform to be located.
    *
    * @return the location of the Uniform as an {@link Integer}.
    */

    public int getUniformLocation(String glName) {
        return glGetUniformLocation(this.glShaderProgram, glName);
    }

    /**
    * ...
    *
    * @param glName ...
    * @param glMatrix ..
    */

    public void setUniform(String glName, Matrix4fc glMatrix) {
        try(MemoryStack stack = MemoryStack.stackPush()) {
            FloatBuffer x = stack.mallocFloat(16);
            glMatrix.get(x);

            glUniformMatrix4fv(this.getUniformLocation(glName), false, x);
        }
    }

    /**
    * ...
    *
    * @param glName ...
    * @param glMatrix ...
    */

    public void setUniform(String glName, Matrix4dc glMatrix) {
        try(MemoryStack stack = MemoryStack.stackPush()) {
            FloatBuffer x = stack.mallocFloat(16);
            glMatrix.get(x);

            glUniformMatrix4fv(this.getUniformLocation(glName), false, x);
        }
    }

    /**
    * ...
    *
    * @param glName ...
    * @param glVector ...
    */

    public void setUniform(String glName, Vector3fc glVector) {
        this.setUniform(glName, glVector.x(), glVector.y(), glVector.z());
    }

    /**
    * ...
    *
    * @param glName ...
    * @param glX ...
    * @param glY ...
    * @param glZ ...
    */

    public void setUniform(String glName, float glX, float glY, float glZ) {
        glUniform3f(this.getUniformLocation(glName), glX, glY, glZ);
    }

    /**
    * ...
    *
    * @param glName ...
    * @param glX ...
    * @param glY ...
    * @param glZ ...
    * @param glW ...
    */

    public void setUniform(String glName, float glX, float glY, float glZ, float glW) {
        glUniform4f(this.getUniformLocation(glName), glX, glY, glZ, glW);
    }

    /**
    * ...
    *
    * @param glName ...
    * @param glFloat ...
    */

    public void setUniform(String glName, float glFloat) {
        glUniform1f(this.getUniformLocation(glName), glFloat);
    }

    /**
    * Links this {@link ShaderProgram} to the current context.
    */

    public void link() {
        glLinkProgram(this.glShaderProgram);

        if(glGetProgrami(this.glShaderProgram, GL_LINK_STATUS) == 0)
            throw new RuntimeException("Unable to link this Shader Program.");
    }

    /**
    * Unbinds this {@link ShaderProgram} from the current context.
    */

    public void unbind() {
        glUseProgram(0);
    }

    /**
    * Deletes this {@link ShaderProgram}.
    */

    public void delete() {
        glDeleteProgram(this.glShaderProgram);
    }
}