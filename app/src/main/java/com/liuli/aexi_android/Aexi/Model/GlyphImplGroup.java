package com.liuli.aexi_android.Aexi.Model;

import android.graphics.Canvas;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/2/3 0003.
 */
public abstract class GlyphImplGroup extends GlyphImpl {
    protected List<GlyphImpl> children;

    public GlyphImplGroup() {
        children = new ArrayList<GlyphImpl>();
    }

    public boolean insert(GlyphImpl glyph, int index) {
        children.add(index, glyph);
        return true;
    }

    public List<GlyphImpl> getChildren() {
        return children;
    }

    public boolean append(GlyphImpl glyph) {
        children.add(glyph);
        glyph.setParent(this);
        return true;
    }

    public Glyph remove(int index) {
        return children.remove(index);
    }

    public boolean clear() {
        children.clear();
        return true;
    }

    @Override
    public void drawMe(Canvas canvas) {
        for (Glyph glyph : children) {
            glyph.drawMe(canvas);
        }
    }

    @Override
    public boolean dispatchClickEvent(MotionEvent e) {

        return true;
    }
}
