package org.a4z0.venture.gl.shader;

import static org.lwjgl.opengl.GL20.GL_FRAGMENT_SHADER;
import static org.lwjgl.opengl.GL20.GL_VERTEX_SHADER;

/**
* Represents a {@link Shader} Type.
*/

public enum ShaderType {
    VERTEX(GL_VERTEX_SHADER),
    FRAGMENT(GL_FRAGMENT_SHADER);

    private final int glShaderType;

    /**
    * Construct a {@link ShaderType}.
    *
    * @param glShaderType Shader type.
    */

    ShaderType(int glShaderType) {
        this.glShaderType = glShaderType;
    }

    /**
    * @return a {@link Integer glShaderType}.
    */

    public int getType() {
        return this.glShaderType;
    }
}