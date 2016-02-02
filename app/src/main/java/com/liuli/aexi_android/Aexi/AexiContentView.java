package com.liuli.aexi_android.Aexi;

import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.liuli.aexi_android.Aexi.Model.Caret;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/1/31 0031.
 */
public class AexiContentView extends ViewGroup {

    private Paint paint;

    private List<View> children;

    private View caret;

    public AexiContentView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        Caret caret = new Caret(context, paint);
        addView(caret);
        this.caret = caret;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);;
        View childView = getChildAt(0);
        measureChild(childView,widthMeasureSpec,heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        View childView = getChildAt(0);
        childView.layout(l, t, r, b);
        caret.layout(l, t, r, b);
    }
}