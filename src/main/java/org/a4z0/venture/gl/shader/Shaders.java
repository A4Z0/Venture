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

    private static final String TEXT_VERTEX_SHADER_SOURCE = getSource("assets/render/ui/text.vert");
    private static final String TEXT_FRAGMENT_SHADER_SOURCE = getSource("assets/render/ui/text.frag");

    private static final String VOXEL_VERTEX_SHADER_SOURCE = getSource("assets/render/world/voxel.vert");
    private static final String VOXEL_FRAGMENT_SHADER_SOURCE = getSource("assets/render/world/voxel.frag");

    private static final String OUTLINE_VERTEX_SHADER_SOURCE = getSource("assets/render/world/outline.vert");
    private static final String OUTLINE_FRAGMENT_SHADER_SOURCE = getSource("assets/render/world/outline.frag");

    private static final String CROSS_VERTEX_SHADER_SOURCE = getSource("assets/render/ui/cross.vert");
    private static final String CROSS_FRAGMENT_SHADER_SOURCE = getSource("assets/render/ui/cross.frag");

    public static ShaderProgram BLOCK_SHADER_PROGRAM;
    private static Shader BLOCK_VERTEX_SHADER, BLOCK_FRAGMENT_SHADER;

    public static ShaderProgram TEXT_SHADER_PROGRAM;
    private static Shader TEXT_VERTEX_SHADER, TEXT_FRAGMENT_SHADER;

    public static ShaderProgram VOXEL_SHADER_PROGRAM;
    private static Shader VOXEL_VERTEX_SHADER, VOXEL_FRAGMENT_SHADER;

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
            TEXT_SHADER_PROGRAM = new ShaderProgram();
            TEXT_SHADER_PROGRAM.attribute(0, "vertex_position");
            TEXT_SHADER_PROGRAM.attribute(1, "vertex_texture_coordinates");
            TEXT_SHADER_PROGRAM.attribute(2, "text_color");

            TEXT_VERTEX_SHADER = new Shader(ShaderType.VERTEX);
            TEXT_VERTEX_SHADER.source(TEXT_VERTEX_SHADER_SOURCE);
            TEXT_VERTEX_SHADER.compile();

            TEXT_FRAGMENT_SHADER = new Shader(ShaderType.FRAGMENT);
            TEXT_FRAGMENT_SHADER.source(TEXT_FRAGMENT_SHADER_SOURCE);
            TEXT_FRAGMENT_SHADER.compile();

            TEXT_SHADER_PROGRAM.addShader(TEXT_VERTEX_SHADER);
            TEXT_SHADER_PROGRAM.addShader(TEXT_FRAGMENT_SHADER);

            TEXT_SHADER_PROGRAM.link();
        }
        {
            VOXEL_SHADER_PROGRAM = new ShaderProgram();
            VOXEL_SHADER_PROGRAM.attribute(0, "vertex_position");
            VOXEL_SHADER_PROGRAM.attribute(1, "vertex_color");

            VOXEL_VERTEX_SHADER = new Shader(ShaderType.VERTEX);
            VOXEL_VERTEX_SHADER.source(VOXEL_VERTEX_SHADER_SOURCE);
            VOXEL_VERTEX_SHADER.compile();

            VOXEL_FRAGMENT_SHADER = new Shader(ShaderType.FRAGMENT);
            VOXEL_FRAGMENT_SHADER.source(VOXEL_FRAGMENT_SHADER_SOURCE);
            VOXEL_FRAGMENT_SHADER.compile();

            VOXEL_SHADER_PROGRAM.addShader(VOXEL_VERTEX_SHADER);
            VOXEL_SHADER_PROGRAM.addShader(VOXEL_FRAGMENT_SHADER);

            VOXEL_SHADER_PROGRAM.link();
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