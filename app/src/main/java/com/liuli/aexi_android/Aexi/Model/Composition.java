package com.liuli.aexi_android.Aexi.Model;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.liuli.aexi_android.Aexi.Interface.Compositor;
import com.liuli.aexi_android.Aexi.Interface.Glyph;
import com.liuli.aexi_android.Aexi.Interface.GlyphListener;

import java.util.List;

/**
 * Created by Administrator on 2016/2/3 0003.
 */
public class Composition extends GlyphImplGroup {
    private Compositor compositor;
    private Document document;
    private Caret caret;

    public Composition(Paint paint) {
        this.paint = paint;
        init();
    }

    public void init() {
        document = new Document();
        //设置页面的大小
        x = 0;
        y = 0;
    }

    public void onMeasure(int width) {
        this.width = width;
        StandardCompositor compositor = new StandardCompositor();
        compositor.setComposition(this);
        compositor.setCompositionWidth(width);
        compositor.setDefaultRowHeight(20);
        compositor.setPageHeight(1000);
        setCompositor(compositor);
        compose();
    }

    private void compose() {
        compositor.compose();
        GlyphImpl glyph = children.get(children.size() - 1);
        setHeight(glyph.getY() + glyph.getHeight());
    }

    @Override
    public void drawMe(Canvas canvas) {
        //画背景
        paint.setColor(Color.BLUE);
        canvas.drawRect(x,y,width,height,paint);
        super.drawMe(canvas);
    }

    @Override
    public boolean hitRect(int x, int y) {
        List<GlyphImpl> children = getChildren();
        for (Glyph glyph : children) {
            glyph.hitRect(x, y);
        }
        return true;
    }

    @Override
    public boolean append(GlyphImpl glyph) {
        //TODO 不可能只有一个page需要改进
        //TODO 不需要重新new一个frame
        glyph.setX(x);
        glyph.setY(y);
        super.append(glyph);
        return true;
    }

    @Override
    public boolean insert(GlyphImpl glyph, int index) {
        if (compositor == null) {
            return false;
        }
        document.add(index,glyph);
        compose();
        caret.setHostGlyph(glyph);
        return true;
    }

    @Override
    public Glyph remove(int index) {
        Glyph glyph = document.remove(index);
        if (compositor != null) {
            compositor.compose();
        }
        return glyph;
    }

    public Document getDocument() {
        return document;
    }

    public Caret getCaret() {
        return caret;
    }

    public void setCompositor(Compositor compositor) {
        this.compositor = compositor;
    }

    public void setCaret(Caret caret) {
        this.caret = caret;
    }

}
