package org.a4z0.venture.util;

/**
* ...
*/

@Deprecated
public final class FPS {

    private static double MAX_FRAME_RATE = 60;

    private static double TIME = ((double) System.nanoTime() / 1_000_000_000d);
    private static double PROCESSED_TIME = 0d;

    private static double FPS_COUNT = 0;
    private static volatile double FPS = 0;

    /**
    * ...
    */

    @Deprecated
    public static void update() {
        double nextTime = ((double) System.nanoTime()) / 1_000_000_000d;
        double passedTime = nextTime - TIME;

        PROCESSED_TIME += passedTime;
        TIME = nextTime;

        while(PROCESSED_TIME > (1d / MAX_FRAME_RATE)) {
            PROCESSED_TIME -= (1d / MAX_FRAME_RATE);
            FPS_COUNT++;
        }

        if(nextTime - TIME >= 1) {
            FPS = FPS_COUNT;
            FPS_COUNT = 0;
            TIME = nextTime;
        }
    }

    /**
    * ...
    *
    * @param MAX_FRAME_RATE ...
    */

    public static void setFPS(int MAX_FRAME_RATE) {
        //MAX_FRAME_RATE = MAX_FRAME_RATE;
    }

    public static double getFPS() {
        return FPS;
    }
}