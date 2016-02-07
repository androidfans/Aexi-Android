package com.liuli.aexi_android.Aexi.Model;

import java.util.List;

/**
 * Created by Administrator on 2016/2/3 0003.
 */
public class Row extends GlyphImplGroup {

    public Row() {

    }

    public Row(int width , int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public boolean append(GlyphImpl glyph) {
        List<GlyphImpl> children = getChildren();
        int x = 0;
        if (children.size() <= 0) {
            x = 0;
        } else {
            GlyphImpl preGlyph = children.get(children.size() - 1);
            x = preGlyph.getX() + preGlyph.getWidth();
        }
        //拿到空隙之后进行判断
        int space = this.width + this.x - x;
        if (space < 0) {
            return false;
        }
        if (glyph instanceof Image) {
            Image image = (Image) glyph;
            image.shrinkWidth(space);
        } else {
            if (glyph.width > space) {
                return false;
            }
        }
        glyph.setX(x);
        if (height > glyph.getHeight()) {
            int y = height + this.y - glyph.getHeight();
            glyph.setY(y);
        } else {
            glyph.setY(this.y);
        }
        //每插入一个图元就要检查一下高度是否超过限制,并调整自身高度
        if (glyph.getHeight() > this.height) {
            setHeight(glyph.getHeight());
            fixHeight(getChildren().size());
        }
        glyph.setParent(this);
        return super.append(glyph);
    }


    private void fixHeight(int index) {
        List<GlyphImpl> glyphs = getChildren();
        for (int i = 0; i < index; i++) {
            GlyphImpl glyph = glyphs.get(i);
            int y = height + this.y - glyph.getHeight();
            glyph.setY(y);
        }
    }
}
