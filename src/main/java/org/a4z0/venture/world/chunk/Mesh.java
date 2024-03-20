package org.a4z0.venture.world.chunk;

import org.a4z0.venture.gl.vertex.Vertex;
import org.a4z0.venture.world.material.Material;

/**
* ...
*/

public class Mesh {

    /**
    * ...
    *
    * @param Chunk ...
    *
    * @return ...
    */

    public static Vertex create(Chunk Chunk) {
        Vertex Stream = new Vertex();

        for(int x = 0; x < 32; x++) {
            for(int y = 0; y < 32; y++) {
                for(int z = 0; z < 32; z++) {
                    Material Mat = Chunk.getBlock(x, y, z);

                    if(Mat != null)
                        Mat.vertex(Stream, x, y, z);
                }
            }
        }

        return Stream;
    }
}