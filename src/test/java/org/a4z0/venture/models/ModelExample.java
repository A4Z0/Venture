package org.a4z0.venture.models;

import org.a4z0.venture.Interval;
import org.a4z0.venture.model.Model;

/*
* Example of Loading an ".obj".
*/

public class ModelExample {

    public static Model CUBE_MODEL;

    public static void main(String[] args) {
        Interval.reset();

        CUBE_MODEL = Model.getFrom("assets/geometry/block.obj");

        System.out.println("Execution elapsed time -> " + (Interval.stop() / 1000.0) + " seconds!");
    }
}