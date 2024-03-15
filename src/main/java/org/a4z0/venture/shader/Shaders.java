package org.a4z0.venture.shader;

import org.a4z0.venture.Venture;

import java.io.IOException;
import java.io.InputStream;

/**
* ...
*/

public final class Shaders {

    public static final String VERTEX_SHADER_V0_1 = getSource("assets/render/shader/vertex/0.1.glsl");
    public static final String FRAGMENT_SHADER_V0_1 = getSource("assets/render/shader/fragment/0.1.glsl");

    public static final String VERTEX_SHADER_V0_2 = getSource("assets/render/shader/vertex/0.2.glsl");
    public static final String FRAGMENT_SHADER_V0_2 = getSource("assets/render/shader/fragment/0.2.glsl");

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