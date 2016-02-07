package com.liuli.aexi_android.Aexi.Model;

import java.util.List;

/**
 * Created by Administrator on 2016/2/3 0003.
 */
public class Page extends GlyphImplGroup {
    public Page() {
    }

    public Page(int width, int height) {
        this.width = width;
        this.height = height;
    }
    @Override
    public boolean append(GlyphImpl glyph) {
        //判断空隙是否足够插入
        List<GlyphImpl> children = getChildren();
        int y;
        if (children.size() <= 0) {
            y = 0;
        } else {
            GlyphImpl preGlyph = children.get(children.size() - 1);
            y = preGlyph.getY() + preGlyph.getHeight();
        }
        int space = this.y + this.getHeight() - y;
        if (space < glyph.getHeight()) {
            return false;
        }
        Row row = (Row) glyph;
        row.setX(this.x);
        row.setY(y);
        glyph.setParent(glyph);
        return super.append(glyph);
    }
}
