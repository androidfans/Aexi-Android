package com.liuli.aexi_android.Aexi.Model;

import android.graphics.Paint;
import android.view.MotionEvent;

import com.liuli.aexi_android.Aexi.Interface.Glyph;
import com.liuli.aexi_android.Aexi.Interface.GlyphListener;

/**
 * Created by Administrator on 2016/2/3 0003.
 */
public abstract class GlyphImpl implements Glyph {
    protected GlyphImpl parent;
    protected GlyphListener listener;
    protected Paint paint;



    private int documentIndex = 0;
    private boolean isSelected = false;
    protected int x;
    protected int y;
    protected int width;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }
    protected int height;

    public GlyphImpl getParent() {
        return parent;
    }

    public void setParent(GlyphImpl parent) {
        this.parent = parent;
    }

    public boolean hitRect(int x, int y) {
        if (x > (x + width) || x < this.x)
            return false;
        return !(y > (y + height) || y < this.y);
    }

    public void setListener(GlyphListener listener) {
        this.listener = listener;
    }

    @Override
    public boolean dispatchClickEvent(MotionEvent e) {
        return false;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public int getDocumentIndex() {
        return documentIndex;
    }

    public void setDocumentIndex(int documentIndex) {
        this.documentIndex = documentIndex;
    }

}
