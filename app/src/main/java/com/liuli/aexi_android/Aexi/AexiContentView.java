package com.liuli.aexi_android.Aexi;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.liuli.aexi_android.Aexi.Model.Paragraph;

/**
 * Created by Administrator on 2016/1/31 0031.
 */
public class AexiContentView extends ViewGroup {

    private Paint paint;

    public AexiContentView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        Paragraph paragraph = new Paragraph(context,paint);
        addView(paragraph);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        View childView = getChildAt(0);
        childView.layout(l, t, r, b);
    }
}