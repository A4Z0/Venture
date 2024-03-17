package org.a4z0.venture.shader;

import org.a4z0.venture.Venture;

import java.io.IOException;
import java.io.InputStream;

/**
* ...
*/

public final class Shaders {

    public static final String BLOCK_VERTEX_SHADER = getSource("assets/render/world.vert");
    public static final String BLOCK_FRAGMENT_SHADER = getSource("assets/render/world.frag");

    public static final String BLOCK_OUTLINE_VERTEX_SHADER = getSource("assets/render/block_outline.vert");
    public static final String BLOCK_OUTLINE_FRAGMENT_SHADER = getSource("assets/render/block_outline.frag");

    /**
    * ...
    */

    public static void init() {

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