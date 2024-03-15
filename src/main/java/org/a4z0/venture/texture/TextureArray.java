package org.a4z0.venture.texture;

import java.io.InputStream;
import java.nio.file.Path;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.GL_CLAMP_TO_BORDER;

/**
* ...
*/

public class TextureArray implements Image {

    protected final Texture TEXTURE;

    protected final int TEXTURE_SIZE;
    protected final int TEXTURE_AMOUNT;

    /**
    * Construct a {@link TextureArray}.
    *
    * @param TEXTURE_ARRAY_SIZE ...
    * @param TEXTURE_SIZE ...
    */

    public TextureArray(int TEXTURE_ARRAY_SIZE, int TEXTURE_SIZE) {
        this.TEXTURE = new Texture(TEXTURE_ARRAY_SIZE, TEXTURE_ARRAY_SIZE);
        this.TEXTURE_SIZE = TEXTURE_SIZE;
        this.TEXTURE_AMOUNT = (this.TEXTURE.getWidth() * this.getHeight()) / (TEXTURE_SIZE * TEXTURE_SIZE);

        this.TEXTURE.bind();
        this.TEXTURE.setParam(GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        this.TEXTURE.setParam(GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        this.TEXTURE.setParam(GL_TEXTURE_WRAP_S, GL_CLAMP_TO_BORDER);
        this.TEXTURE.setParam(GL_TEXTURE_WRAP_T, GL_CLAMP_TO_BORDER);

        this.TEXTURE.setData(GL_RGBA, this.TEXTURE.getWidth(), this.TEXTURE.getHeight(), GL_RGBA, 0);
    }

    @Override
    public int getID() {
        return this.TEXTURE.getID();
    }

    @Override
    public int getWidth() {
        return this.TEXTURE.getWidth();
    }

    @Override
    public int getHeight() {
        return this.TEXTURE.getHeight();
    }

    /**
    * ...
    *
    * @param TEXTURE_INDEX ...
    * @param TEXTURE_LOCATION ...
    */

    public int set(int TEXTURE_INDEX, String TEXTURE_LOCATION) {
        return this.set(TEXTURE_INDEX, Path.of(TEXTURE_LOCATION));
    }

    /**
    * ...
    *
    * @param TEXTURE_INDEX ...
    * @param TEXTURE_LOCATION ...
    */

    public int set(int TEXTURE_INDEX, Path TEXTURE_LOCATION) {
        try {
            return this.set(TEXTURE_INDEX, TextureArray.class.getClassLoader().getResourceAsStream(TEXTURE_LOCATION.toString()));
        } catch (NullPointerException e) {
            throw new RuntimeException(e);
        }
    }

    /**
    * ...
    *
    * @param TEXTURE_INDEX ...
    * @param TEXTURE_INPUT_STREAM ...
    */

    public int set(int TEXTURE_INDEX, InputStream TEXTURE_INPUT_STREAM) {
        if(TEXTURE_INDEX < 0)
            throw new ArrayIndexOutOfBoundsException("...");

        if(TEXTURE_INDEX > TEXTURE_AMOUNT)
            throw new ArrayIndexOutOfBoundsException("...");

        this.TEXTURE.uploadData(
    (TEXTURE_INDEX % (this.getLength() / (this.TEXTURE.getWidth() / this.TEXTURE_SIZE))) * this.TEXTURE_SIZE,
    (TEXTURE_INDEX / (this.getLength() / (this.TEXTURE.getHeight() / this.TEXTURE_SIZE))) * this.TEXTURE_SIZE,
              this.TEXTURE_SIZE, this.TEXTURE_SIZE, TEXTURE_INPUT_STREAM
        );

        return TEXTURE_INDEX;
    }

    /**
    * @return ...
    */

    public int getLength() {
        return this.TEXTURE_AMOUNT;
    }

    /**
    * ...
    */

    public void delete() {
        this.TEXTURE.delete();
    }
}