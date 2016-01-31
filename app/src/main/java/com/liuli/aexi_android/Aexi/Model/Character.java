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
    public Character(Context context, Paint paint) {
        super(context);
        this.paint = paint;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawText("你牛逼", 0, 70, paint);
    }
}
