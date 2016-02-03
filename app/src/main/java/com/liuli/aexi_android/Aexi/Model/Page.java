package com.liuli.aexi_android.Aexi.Model;

import java.util.List;

/**
 * Created by Administrator on 2016/2/3 0003.
 */
public class Page extends GlyphImplGroup {
    @Override
    public boolean append(GlyphImpl glyph) {
        super.append(glyph);
        Row row = (Row) glyph;
        List<GlyphImpl> children = getChildren();
        row.setWidth(width);
        row.setHeight(20);
        row.setX(x);
        if (children.size() <= 1) {
            row.setY(y);
        } else {
            Row preRow = (Row) children.get(children.size() - 2);
            row.setY(preRow.getY() + preRow.getHeight());
        }
        return true;
    }
}
