package com.liuli.aexi_android.Aexi.Model;

import android.view.MotionEvent;

/**
 * Created by Administrator on 2015/3/19.
 */
public interface Glyph {
    boolean hitRect(int x, int y);

    boolean dispatchClickEvent(MotionEvent e);

    boolean onClickEvent(MotionEvent e);

    boolean interceptClickEvent(MotionEvent e);
}
