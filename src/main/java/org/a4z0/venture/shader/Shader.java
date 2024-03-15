package org.a4z0.venture.shader;

import static org.lwjgl.opengl.GL20.*;

/**
* ...
*/

public class Shader {

    protected int SHADER_ID;

    /**
    * Construct a {@link Shader}.
    *
    * @param SHADER_TYPE ...
    */

    public Shader(int SHADER_TYPE) {
        this.SHADER_ID = glCreateShader(SHADER_TYPE);
    }

    /**
    * @return this {@link Shader} ID.
    */

    public int getID() {
        return this.SHADER_ID;
    }

    /**
    * Defines the source code of this {@link Shader}.
    *
    * @param s Source code that will be set.
    */

    public void source(String s) {
        glShaderSource(this.SHADER_ID, s);
    }

    /**
    * Compile this {@link Shader}.
    */

    public void compile() {
        glCompileShader(this.SHADER_ID);

        if(glGetShaderi(this.SHADER_ID, GL_COMPILE_STATUS) == 0)
            throw new RuntimeException("Unable to compile Shader." + "\n" + glGetShaderInfoLog(this.SHADER_ID));
    }

    /**
    * Deletes this {@link Shader}.
    */

    public void delete() {
        glDeleteShader(this.SHADER_ID);
    }
}