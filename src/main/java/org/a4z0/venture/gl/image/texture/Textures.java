package org.a4z0.venture.gl.image.texture;

import org.a4z0.venture.gl.image.atlas.Address;
import org.a4z0.venture.gl.image.atlas.Atlas;
import org.a4z0.venture.gl.font.Font;

/**
* ...
*/

public final class Textures {

    public static final Atlas TEXTURE_ARRAY = new Atlas(48, 16);

    public static final Address DIRT = TEXTURE_ARRAY.set(0, "assets/textures/world/block/dirt.png");
    public static final Address BEDROCK = TEXTURE_ARRAY.set(1, "assets/textures/world/block/bedrock.png");
    public static final Address GLOWSTONE = TEXTURE_ARRAY.set(2, "assets/textures/world/block/glowstone.png");
    public static final Address OAK_LOG = TEXTURE_ARRAY.set(3, "assets/textures/world/block/oak_log.png");
    public static final Address OAK_LOG_CRUMB = TEXTURE_ARRAY.set(4, "assets/textures/world/block/oak_log_crumb.png");

    public static final Atlas FONT_ARRAY = new Atlas(128, 16);

    public static final Font FONT = new Font(24, 512);

    /**
    * ...
    */

    public static void init() {

    }
}