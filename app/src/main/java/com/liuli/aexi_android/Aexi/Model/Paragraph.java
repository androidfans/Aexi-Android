package com.liuli.aexi_android.Aexi.Model;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2016/1/31 0031.
 */
public class Paragraph extends ViewGroup {

    private Paint paint;

    public Paragraph(Context context,Paint paint) {
        super(context);
        this.paint = paint;
        Character character = new Character(context,paint);
        addView(character);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        View childView = getChildAt(0);
        childView.layout(l, t, r, b);
    }
}
