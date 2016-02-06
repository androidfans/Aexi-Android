package com.liuli.aexi_android.Aexi.Model;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by Administrator on 2016/2/3 0003.
 */
public class Character extends GlyphImpl {
    private String content;
    private Paint paint;
    private float textSize;

    public Character(char content, float textSize, Paint paint) {
        this.paint = paint;
        this.content = content + "";
        this.textSize = textSize;
        paint.setTextSize(textSize);
        setWidth((int) paint.measureText(this.content));
        setHeight((int) (-paint.ascent() + paint.descent()));
    }

    @Override
    public void drawMe(Canvas g) {
        paint.setColor(Color.BLACK);
        paint.setTextSize(textSize);
        g.drawText(content, x, y + -paint.ascent(), paint);
    }

    @Override
    public void setPaint(Paint paint) {
        this.paint = paint;
    }
}