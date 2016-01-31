package com.liuli.aexi_android.Aexi.Model;

import android.content.Context;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2015/3/21.
 */
public class Page extends ViewGroup {
    public Page(Context context) {
        super(context);
    }
//    @Override
//    public boolean append(GlyphImpl glyph) {
//        super.append(glyph);
//        Row row = (Row) glyph;
//        List<GlyphImpl> children = getChildren();
//        row.setWidth(width);
//        row.setHeight(20);
//        row.setX(x);
//        if (children.size() <= 1) {
//            row.setY(y);
//        } else {
//            Row preRow = (Row) children.get(children.size() - 2);
//            row.setY(preRow.getY() + preRow.getHeight());
//        }
//        return true;
//    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}
