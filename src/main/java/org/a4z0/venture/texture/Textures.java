package org.a4z0.venture.texture;

/**
* ...
*/

public final class Textures {

    public static final TextureArray TEXTURE_ARRAY = new TextureArray(48, 16);

    public static final int DIRT = TEXTURE_ARRAY.set(0, "assets/world/block/dirt.png");
    public static final int BEDROCK = TEXTURE_ARRAY.set(1, "assets/world/block/bedrock.png");
    public static final int GLOWSTONE = TEXTURE_ARRAY.set(2, "assets/world/block/glowstone.png");
    public static final int OAK_LOG = TEXTURE_ARRAY.set(3, "assets/world/block/oak_log.png");
    public static final int OAK_LOG_CRUMB = TEXTURE_ARRAY.set(4, "assets/world/block/oak_log_crumb.png");

    /**
    * ...
    */

    public static void init() {

    }
}