package com.liuli.aexi_android.Aexi.Model;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

/**
 * Created by Administrator on 2016/2/3 0003.
 */
public class Character extends GlyphImpl {
    private String content;
    private Paint paint;
    private float textSize;

    public Character(String content, float textSize, Paint paint) {
        this.paint = paint;
        this.content = content;
        this.textSize = textSize;
        paint.setTextSize(textSize);
        //初始化frame
        setWidth((int) paint.measureText(content));
        setHeight((int) (-paint.ascent() + paint.descent()));
    }

    @Override
    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public void drawMe(Canvas g) {
        paint.setColor(Color.BLACK);
        paint.setTextSize(textSize);
        g.drawText(content, x, y, paint);
    }


    @Override
    public boolean hitRect(int x, int y) {
        if (!super.hitRect(x, y))
            return false;
        /**
         * 在这里应该修改caret的position.
         * 可是会出现问题
         * 问题一:在这里修改了position的话,那么对应的documentIndex也应该修改,怎么样通过position计算出DocumentIndex
         * 问题二:每个"基本图元",都要对点击事件响应以便修改caret的坐标,但是又不能写在glyphImpl里面,因为glyphImplGroup也是继承自glyphImpl,
         * 难道每增加一种"基本图元"类,就要复写一个完全相同的东西吗
         */
        System.out.println("击中字符" + content);
        return true;
    }
}

