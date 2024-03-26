package org.a4z0.venture.gl.image;

import org.a4z0.venture.gl.image.texture.Texture;
import org.a4z0.venture.gl.image.texture.context.DrawTextureContext2D;
import org.lwjgl.BufferUtils;
import org.lwjgl.stb.STBImage;
import org.lwjgl.stb.STBTTBakedChar;
import org.lwjgl.stb.STBTTFontinfo;
import org.lwjgl.stb.STBTruetype;
import org.lwjgl.system.MemoryStack;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.Arrays;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_WRAP_T;
import static org.lwjgl.opengl.GL13.GL_CLAMP_TO_BORDER;

/**
* ...
*/

public interface Image {

    /**
    * @return ...
    */

    int getID();

    /**
    * @return ...
    */

    int getWidth();

    /**
    * @return ...
    */

    int getHeight();

    /**
    * @param TEXTURE_INPUT_STREAM ...
    *
    * @return ...
    */

    static ByteBuffer getImageData(InputStream TEXTURE_INPUT_STREAM) {
        try(MemoryStack MEMORY_STACK = MemoryStack.stackPush()) {
            IntBuffer WIDTH_BUFFER = MEMORY_STACK.mallocInt(1);
            IntBuffer HEIGHT_BUFFER = MEMORY_STACK.mallocInt(1);
            IntBuffer CHANNELS_BUFFER = MEMORY_STACK.mallocInt(1);

            byte[] FILE_DATA;

            try(InputStream INPUT_STREAM = TEXTURE_INPUT_STREAM) {
                FILE_DATA = INPUT_STREAM.readAllBytes();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            ByteBuffer IMAGE_BUFFER = BufferUtils.createByteBuffer(FILE_DATA.length);
            IMAGE_BUFFER.put(FILE_DATA);
            IMAGE_BUFFER.flip();

            STBImage.stbi_set_flip_vertically_on_load(true);
            ByteBuffer IMAGE_DATA = STBImage.stbi_load_from_memory(IMAGE_BUFFER, WIDTH_BUFFER, HEIGHT_BUFFER, CHANNELS_BUFFER, 0);

            if(IMAGE_DATA == null)
                throw new RuntimeException("Failed to load texture file: " + STBImage.stbi_failure_reason());

            return IMAGE_DATA;
        }
    }

    /**
    * ...
    *
    * @param TEXTURE_INPUT_STREAM ...
    *
    * @return ...
    */

    static Texture getGlyphData(InputStream TEXTURE_INPUT_STREAM) {
        byte[] FILE_DATA;

        try(InputStream INPUT_STREAM = TEXTURE_INPUT_STREAM) {
            FILE_DATA = INPUT_STREAM.readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ByteBuffer FONT_BUFFER = BufferUtils.createByteBuffer(FILE_DATA.length);
        FONT_BUFFER.put(FILE_DATA);
        FONT_BUFFER.flip();

        ByteBuffer BITMAP = BufferUtils.createByteBuffer(512 * FILE_DATA.length);

        STBTTBakedChar.Buffer CHAR_DATA = STBTTBakedChar.malloc(96);

        STBTruetype.stbtt_BakeFontBitmap(FONT_BUFFER, 24, BITMAP, 512, 512, 32, CHAR_DATA);

        Texture texture = new Texture(512, 512);
        texture.bind();

        texture.param(GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        texture.param(GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        texture.param(GL_TEXTURE_WRAP_S, GL_CLAMP_TO_BORDER);
        texture.param(GL_TEXTURE_WRAP_T, GL_CLAMP_TO_BORDER);

        texture.draw(new DrawTextureContext2D(texture.getWidth(), texture.getHeight(), BITMAP));
        texture.unbind();

        return texture;
    }
}