package com.liuli.aexi_android.Aexi.Model;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/1/31 0031.
 */
public class Character extends View {
    private Paint paint;

    private String content;

    public Character(Context context, String content , Paint paint) {
        super(context);
        this.paint = paint;
        this.content = content;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawText(content, 0, 70, paint);
    }
}
