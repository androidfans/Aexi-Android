package com.liuli.aexi_android.Aexi.Model;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by Administrator on 2016/2/2 0002.
 */
public class LineBreaker extends GlyphImpl {
    public LineBreaker(float textSize,Paint paint) {
        paint.setTextSize(textSize);
        setHeight((int) (-paint.ascent() + paint.descent()));
    }

    @Override
    public void drawMe(Canvas g) {

    }
}
