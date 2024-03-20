package org.a4z0.venture.gl.shader;

import static org.lwjgl.opengl.GL20.*;

/**
* ...
*/

public class Shader {

    protected int glShader;

    /**
    * Construct a {@link Shader}.
    *
    * @param shaderType {@link ShaderType} that will be set.
    */

    public Shader(ShaderType shaderType) {
        this.glShader = glCreateShader(shaderType.getType());
    }

    /**
    * @return this {@link Shader} ID.
    */

    public int getID() {
        return this.glShader;
    }

    /**
    * Sets the source code of this {@link Shader}.
    *
    * @param glSource Source code that will be set.
    */

    public void source(String glSource) {
        glShaderSource(this.glShader, glSource);
    }

    /**
    * Compile this {@link Shader}.
    */

    public void compile() {
        glCompileShader(this.glShader);

        if(glGetShaderi(this.glShader, GL_COMPILE_STATUS) == 0)
            throw new RuntimeException("Unable to compile Shader." + "\n" + glGetShaderInfoLog(this.glShader));
    }

    /**
    * Deletes this {@link Shader}.
    */

    public void delete() {
        glDeleteShader(this.glShader);
    }
}