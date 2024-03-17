package org.a4z0.venture.texture;

/**
* ...
*/

public final class Textures {

    public static final TextureArray TEXTURE_ARRAY = new TextureArray(48, 16);

    public static final int DIRT = TEXTURE_ARRAY.set(0, "assets/textures/world/block/dirt.png");
    public static final int BEDROCK = TEXTURE_ARRAY.set(1, "assets/textures/world/block/bedrock.png");
    public static final int GLOWSTONE = TEXTURE_ARRAY.set(2, "assets/textures/world/block/glowstone.png");
    public static final int OAK_LOG = TEXTURE_ARRAY.set(3, "assets/textures/world/block/oak_log.png");
    public static final int OAK_LOG_CRUMB = TEXTURE_ARRAY.set(4, "assets/textures/world/block/oak_log_crumb.png");

    /**
    * ...
    */

    public static void init() {

    }
}