package org.a4z0.venture.gl.shader;

import org.a4z0.venture.Venture;

import java.io.IOException;
import java.io.InputStream;

/**
* ...
*/

public final class Shaders {

    private static final String BLOCK_VERTEX_SHADER_SOURCE = getSource("assets/render/world/block.vert");
    private static final String BLOCK_FRAGMENT_SHADER_SOURCE = getSource("assets/render/world/block.frag");

    private static final String OUTLINE_VERTEX_SHADER_SOURCE = getSource("assets/render/world/outline.vert");
    private static final String OUTLINE_FRAGMENT_SHADER_SOURCE = getSource("assets/render/world/outline.frag");

    private static final String CROSS_VERTEX_SHADER_SOURCE = getSource("assets/render/ui/cross.vert");
    private static final String CROSS_FRAGMENT_SHADER_SOURCE = getSource("assets/render/ui/cross.frag");

    public static ShaderProgram BLOCK_SHADER_PROGRAM;
    private static Shader BLOCK_VERTEX_SHADER, BLOCK_FRAGMENT_SHADER;

    public static ShaderProgram OUTLINE_SHADER_PROGRAM;
    private static Shader OUTLINE_VERTEX_SHADER, OUTLINE_FRAGMENT_SHADER;

    public static ShaderProgram CROSS_SHADER_PROGRAM;
    private static Shader CROSS_VERTEX_SHADER, CROSS_FRAGMENT_SHADER;

    /**
    * ...
    */

    public static void init() {
        {
            BLOCK_SHADER_PROGRAM = new ShaderProgram();
            BLOCK_SHADER_PROGRAM.attribute(0, "vertex_position");
            BLOCK_SHADER_PROGRAM.attribute(1, "vertex_texture_coordinates");
            BLOCK_SHADER_PROGRAM.attribute(2, "vertex_normal");
            BLOCK_SHADER_PROGRAM.attribute(3, "vertex_ao");

            BLOCK_VERTEX_SHADER = new Shader(ShaderType.VERTEX);
            BLOCK_VERTEX_SHADER.source(BLOCK_VERTEX_SHADER_SOURCE);
            BLOCK_VERTEX_SHADER.compile();

            BLOCK_FRAGMENT_SHADER = new Shader(ShaderType.FRAGMENT);
            BLOCK_FRAGMENT_SHADER.source(BLOCK_FRAGMENT_SHADER_SOURCE);
            BLOCK_FRAGMENT_SHADER.compile();

            BLOCK_SHADER_PROGRAM.addShader(BLOCK_VERTEX_SHADER);
            BLOCK_SHADER_PROGRAM.addShader(BLOCK_FRAGMENT_SHADER);

            BLOCK_SHADER_PROGRAM.link();
        }
        {
            OUTLINE_SHADER_PROGRAM = new ShaderProgram();
            OUTLINE_SHADER_PROGRAM.attribute(0, "vertex_position");

            OUTLINE_VERTEX_SHADER = new Shader(ShaderType.VERTEX);
            OUTLINE_VERTEX_SHADER.source(Shaders.OUTLINE_VERTEX_SHADER_SOURCE);
            OUTLINE_VERTEX_SHADER.compile();

            OUTLINE_FRAGMENT_SHADER = new Shader(ShaderType.FRAGMENT);
            OUTLINE_FRAGMENT_SHADER.source(Shaders.OUTLINE_FRAGMENT_SHADER_SOURCE);
            OUTLINE_FRAGMENT_SHADER.compile();

            OUTLINE_SHADER_PROGRAM.addShader(OUTLINE_VERTEX_SHADER);
            OUTLINE_SHADER_PROGRAM.addShader(OUTLINE_FRAGMENT_SHADER);

            OUTLINE_SHADER_PROGRAM.link();
        }
        {
            CROSS_SHADER_PROGRAM = new ShaderProgram();
            CROSS_SHADER_PROGRAM.attribute(0, "vertex_position");

            CROSS_VERTEX_SHADER = new Shader(ShaderType.VERTEX);
            CROSS_VERTEX_SHADER.source(Shaders.CROSS_VERTEX_SHADER_SOURCE);
            CROSS_VERTEX_SHADER.compile();

            CROSS_FRAGMENT_SHADER = new Shader(ShaderType.FRAGMENT);
            CROSS_FRAGMENT_SHADER.source(Shaders.CROSS_FRAGMENT_SHADER_SOURCE);
            CROSS_FRAGMENT_SHADER.compile();

            CROSS_SHADER_PROGRAM.addShader(CROSS_VERTEX_SHADER);
            CROSS_SHADER_PROGRAM.addShader(CROSS_FRAGMENT_SHADER);

            CROSS_SHADER_PROGRAM.link();
        }
    }

    /**
    * @return ...
    */

    public static String getSource(String URL) {
        try(InputStream INPUT_STREAM = Venture.class.getClassLoader().getResourceAsStream(URL)) {

            assert INPUT_STREAM != null;
            return new String(INPUT_STREAM.readAllBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}