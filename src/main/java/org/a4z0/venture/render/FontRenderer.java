package org.a4z0.venture.render;

import org.a4z0.venture.gl.vertex.VertexStream;
import org.a4z0.venture.gl.font.Font;
import org.a4z0.venture.gl.image.texture.Textures;
import org.a4z0.venture.gl.shader.Shaders;
import org.a4z0.venture.gl.vertex.VertexBuffer;
import org.joml.Matrix4f;
import org.lwjgl.stb.STBTTBakedChar;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

/**
* ...
*/

public class FontRenderer {

    /**
    * Construct a {@link FontRenderer}.
    */

    public FontRenderer() {

    }

    public void renderText(String text, int x, int y, int z, float r, float g, float b, float a, float scale) {
        this.renderText(Textures.FONT, text, x, y, z, r, g, b, a, scale);
    }

    public void renderText(Font font, String text, int x, int y, int z, float r, float g, float b, float a, float scale) {
        VertexStream vertexArray = new VertexStream();
        float xOffset = 0.0f;
        for (int i = 0; i < text.length(); i++) {
            STBTTBakedChar data = font.getGlyphs()[i].getData();
            float x0 = x + xOffset + data.x0() * scale;
            float y0 = y + data.y0() * scale;
            float x1 = x + xOffset + data.x1() * scale;
            float y1 = y + data.y1() * scale;

            // Add vertices for the quad representing the character
            vertexArray.vertex(x0, y0, z, 0.0f, 0.0f, 0, 0, 0, 0); // Bottom left
            vertexArray.vertex(x1, y0, z, 1.0f, 0.0f, 0, 0, 0, 0); // Bottom right
            vertexArray.vertex(x1, y1, z, 1.0f, 1.0f, 0, 0, 0, 0); // Top right
            vertexArray.vertex(x0, y1, z, 0.0f, 0.0f, 0, 0, 0, 0);
        }

        this.renderText(x, y, z, VertexBuffer.create(vertexArray), vertexArray.vertices(), r, g, b, a, scale);
    }

    public void renderText(int x, int y, int z, VertexBuffer VertexBuffer, int Length, float r, float g, float b, float alpha, float lineWidth) {
        Shaders.TEXT_SHADER_PROGRAM.bind();

        glBindVertexArray(VertexBuffer.getVAO().getID());
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);

        glActiveTexture(GL_TEXTURE0);
        glBindTexture(GL_TEXTURE_2D, Textures.FONT.getID());

        Shaders.TEXT_SHADER_PROGRAM.setUniform("text_color", r, g, b, alpha);
        Shaders.TEXT_SHADER_PROGRAM.setUniform("transformation", new Matrix4f().translate(x, y, z));

        glLineWidth(lineWidth);
        glDrawArrays(GL_LINES, 0, Length);
        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);

        glLineWidth(1f);
        VertexBuffer.getVAO().unbind();
        Shaders.TEXT_SHADER_PROGRAM.unbind();

        VertexBuffer.delete();
    }
}