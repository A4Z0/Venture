package org.a4z0.venture.gl.image.atlas;

import org.a4z0.venture.gl.image.Image;
import org.a4z0.venture.gl.image.texture.Texture;
import org.a4z0.venture.gl.image.texture.context.DrawSubTextureContext2D;
import org.a4z0.venture.gl.image.texture.context.DrawTextureContext2D;

import java.io.InputStream;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.GL_CLAMP_TO_BORDER;

/**
* ...
*/

public class Atlas {

    protected final Texture glTexture;

    protected final Address[] addressMapArray;
    protected final int tileMapSize;
    protected final int tileSize;

    /**
    * Construct a {@link Atlas}.
    *
    * @param tileMapSize {@link Atlas} size.
    * @param tileSize Size of each {@link Address}.
    */

    public Atlas(int tileMapSize, int tileSize) {
        this.glTexture = new Texture(tileMapSize, tileMapSize);

        this.tileMapSize = tileMapSize;
        this.tileSize = tileSize;
        this.addressMapArray = new Address[(this.glTexture.getWidth() * this.glTexture.getHeight()) / (tileSize * tileSize)];

        this.glTexture.bind();
        this.glTexture.param(GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        this.glTexture.param(GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        this.glTexture.param(GL_TEXTURE_WRAP_S, GL_CLAMP_TO_BORDER);
        this.glTexture.param(GL_TEXTURE_WRAP_T, GL_CLAMP_TO_BORDER);

        this.glTexture.draw(new DrawTextureContext2D(this.glTexture.getWidth(), this.glTexture.getHeight(), 0));
        this.glTexture.unbind();
    }

    /**
    * @return the {@link Texture} ID from this {@link Atlas}.
    */

    public int getID() {
        return this.glTexture.getID();
    }

    /**
    * @return the {@link Atlas} size.
    */

    public int getSize() {
        return this.tileMapSize;
    }

    /**
    * Sets a {@link Address} to an Index.
    *
    * @param tileIndex {@link Address} Index.
    * @param tilePath {@link Address} Path.
    */

    public Address set(int tileIndex, String tilePath) {
        try {
            return this.set(tileIndex, Atlas.class.getClassLoader().getResourceAsStream(tilePath));
        } catch (NullPointerException e) {
            throw new RuntimeException(e);
        }
    }

    /**
    * Sets a {@link Address} to an Index.
    *
    * @param tileIndex {@link Address} Index.
    * @param tileInputStream {@link Address} {@link InputStream}.
    */

    public Address set(int tileIndex, InputStream tileInputStream) {
        if(tileIndex < 0)
            throw new ArrayIndexOutOfBoundsException("You can't set a texture to an index lower than 0.");

        if(tileIndex > (this.getLength() - 1))
            throw new ArrayIndexOutOfBoundsException("It isn't possible to set the texture at \"" + tileIndex + "\" as there are only \"" + this.getLength() + "\" indexes.");

        int offsetWidth = (tileIndex % (this.getLength() / (this.glTexture.getWidth() / this.tileSize))) * this.tileSize;
        int offsetHeight = (tileIndex / (this.getLength() / (this.glTexture.getHeight() / this.tileSize))) * this.tileSize;

        this.glTexture.bind();
        this.glTexture.draw(new DrawSubTextureContext2D(offsetWidth, offsetHeight, this.tileSize, this.tileSize, Image.getImageData(tileInputStream)));
        this.glTexture.unbind();

        return this.addressMapArray[tileIndex] = new Address(this, this.tileSize, offsetWidth, offsetHeight);
    }

    /**
    * @return the length of this {@link Atlas}.
    */

    public int getLength() {
        return this.addressMapArray.length;
    }

    /**
    * Deletes this {@link Atlas}.
    */

    public void delete() {
        this.glTexture.delete();
    }
}