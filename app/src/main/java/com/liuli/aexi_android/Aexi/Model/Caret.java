package com.liuli.aexi_android.Aexi.Model;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by Administrator on 2016/2/1 0001.
 */
public class Caret extends View {
    private boolean show;
    private boolean run;
    private int documentIndex;
    private boolean front;
    private Paint paint;
    public Caret(Context context,Paint paint) {
        super(context);
        this.paint = paint;
        run = true;
        documentIndex = -1;
        front = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (run) {
                    show = !show;
                    postInvalidate();
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(5,100);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (show) {
            paint.setColor(Color.BLACK);
            canvas.drawLine(20,getY(),20,getY() + getMeasuredHeight(),paint);
        }
    }
}