package com.liuli.aexi_android.Aexi.Model;


import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;

/**
 * Created by Liuli on 2015/3/21.
 */
public class Character extends GlyphImpl {
    private char aChar;
    private Paint.FontMetrics fm;

    public Character(char aChar) {
        this.aChar = aChar;
    }

    public char getaChar() {
        return aChar;
    }

    public void setaChar(char aChar) {
        this.aChar = aChar;
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
        System.out.println("击中字符" + aChar);
        return true;
    }

    @Override
    public boolean onClickEvent(MotionEvent e) {
        System.out.println("onClickEvent:" + aChar);
        return super.onClickEvent(e);
    }
}
