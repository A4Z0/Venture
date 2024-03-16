import org.a4z0.venture.Interval;
import org.a4z0.venture.model.Model;

/**
* How to load a ".obj".
*/

public class LoadModelExample {

    public static Model CUBE_MODEL;

    /**
    * 1. Start counter
    * 2. Upload template.
    * 3. Print Vertices, UVs and Normals.
    * 4. Print elapsed time.
    */

    public static void main(String[] args) {
        Interval.reset();

        CUBE_MODEL = Model.getFrom("assets/models/cube.obj");

        System.out.println("Load elapsed time -> " + (Interval.stop() / 1000.0) + " seconds!");
        System.out.println();

        float[] VERTEX_ARRAY = CUBE_MODEL.getVertices();
        float[] UV_ARRAY = CUBE_MODEL.getUVs();
        float[] NORMAL_ARRAY = CUBE_MODEL.getNormals();

        for(int i = 0; i < (VERTEX_ARRAY.length); i += 3)
            System.out.println("v " + VERTEX_ARRAY[i] + ", " + (VERTEX_ARRAY[i + 1]) + ", " + (VERTEX_ARRAY[i + 2]));

        System.out.println();

        for(int i = 0; i < UV_ARRAY.length; i += 2)
            System.out.println("vt " + UV_ARRAY[i] + ", " + (UV_ARRAY[i + 1]));

        System.out.println();

        for(int i = 0; i < NORMAL_ARRAY.length; i += 3)
            System.out.println("vn " + NORMAL_ARRAY[i] + ", " + (NORMAL_ARRAY[i + 1]) + ", " + (NORMAL_ARRAY[i + 2]));

        System.out.println();
        System.out.println("Execution elapsed time -> " + (Interval.stop() / 1000.0) + " seconds!");
    }
}