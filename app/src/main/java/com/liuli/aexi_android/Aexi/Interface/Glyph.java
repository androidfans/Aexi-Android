package com.liuli.aexi_android.Aexi.Interface;

import android.graphics.Canvas;
import android.view.MotionEvent;

/**
 * Created by Administrator on 2016/2/2 0002.
 */
public interface Glyph {
    void drawMe(Canvas g);

    boolean hitRect(int x, int y);

    boolean dispatchClickEvent(MotionEvent e);
}