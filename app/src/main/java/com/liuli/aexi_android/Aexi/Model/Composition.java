package com.liuli.aexi_android.Aexi.Model;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.List;

/**
 * Created by Administrator on 2016/2/3 0003.
 */
public class Composition extends GlyphImplGroup implements CaretListener,GlyphListener {
    private Compositor compositor;
    private Document document;
    private CompositionListener compositionListener;
    private Caret caret;
    private PageStyle pageStyle;
    private Paint paint;

    private Composition() {
        init();
    }

    public void init() {
        document = new Document();
        //设置页面的大小
        pageStyle = new PageStyle();
        x = 50;
        y = 100;
        height = pageStyle.getHeight();
        width = pageStyle.getWidth();

        //TODO 改成工厂模式,使用配置文件生成
        Compositor compositor = new StandardCompositor();
        compositor.setComposition(this);
        setCompositor(compositor);
        compositor.compose();
    }


    @Override
    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    private Selection selection = new Selection(this);
    private static Composition composition = new Composition();

    public Selection getSelection() {
        return selection;
    }

    public void setSelection(Selection selection) {
        this.selection = selection;
    }

    @Override
    public void drawMe(Canvas canvas) {
        //画背景
        paint.setColor(Color.WHITE);
        canvas.drawRect(x,y,width,height,paint);
        super.drawMe(canvas);
        caret.drawMe(canvas);
    }

    public void setCompositor(Compositor compositor) {
        this.compositor = compositor;
    }

    public void setCaret(Caret caret) {
        this.caret = caret;
    }

    @Override
    public boolean append(GlyphImpl glyph) {
        //TODO 不可能只有一个page需要改进
        //TODO 不需要重新new一个frame
        glyph.setX(x);
        glyph.setY(y);
        glyph.setWidth(width);
        glyph.setHeight(pageStyle.getHeight());
        super.append(glyph);
        if (compositionListener != null)
            compositionListener.documentRefresh(this);
        return true;
    }

    public void setCompositionListener(CompositionListener compositionListener) {
        this.compositionListener = compositionListener;
    }

    public Document getDocument() {
        return document;
    }

    @Override
    public void CaretRefresh(Glyph glyph) {
        if (compositor != null)
            compositor.compose();
        if (compositionListener != null)
            compositionListener.documentRefresh(this);
    }

    @Override
    public boolean insert(GlyphImpl glyph, int index) {
        if (compositor == null) {
            return false;
        }
        document.add(index,glyph);
        compositor.compose();
        caret.setHostGlyph(glyph);
        return true;
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
    public Glyph remove(int index) {
        Glyph glyph = document.remove(index);
        if (compositor != null) {
            compositor.compose();
        }
        return glyph;
    }

    public static Composition getInstance() {
        return composition;
    }

    @Override
    public void glyphRefresh() {
        if (compositor != null) {
            compositor.compose();
        }
    }
}
