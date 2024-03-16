package org.a4z0.venture;

/**
* ...
*/

public final class Interval {

    private static long START_TIME;
    private static long END_TIME;

    /**
    * ...
    */

    public static void reset() {
        START_TIME = System.currentTimeMillis();
    }

    /**
    * ...
    */

    public static long stop() {
        return (System.currentTimeMillis() - START_TIME);
    }
}