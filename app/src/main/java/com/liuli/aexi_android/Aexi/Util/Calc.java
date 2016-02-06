package com.liuli.aexi_android.Aexi.Util;

/**
 * Created by Administrator on 2016/2/3 0003.
 */
public abstract class Calc {

    private static final float CM_TO_PIXEL = 28.35f;
    private static final float PIXEL_TO_CM = 1 / 28.35f;

    /**
     * Convert cm to pixel.
     *
     * @param cm centimeters.
     * @return pixels.
     */
    public static int cm2pixel(float cm) {
        return (int)(cm * CM_TO_PIXEL + 0.5);
    }

    /**
     * Convert pixel to cm.
     *
     * @param pixel pixels.
     * @return centimeters.
     */
    public static float pixel2cm(int pixel) {
        return pixel * PIXEL_TO_CM;
    }
}
