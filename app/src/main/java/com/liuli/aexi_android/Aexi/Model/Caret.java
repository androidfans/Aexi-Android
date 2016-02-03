package com.liuli.aexi_android.Aexi.Model;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by Administrator on 2016/2/3 0003.
 */
public class Caret extends GlyphImpl {
    private static Caret instance = new Caret();
    public CaretListener caretListener;
    private boolean show = true;
    private Composition composition;
    private Thread thread;
    private boolean run = true;
    private GlyphImpl hostGlyph;
    private Paint paint;

    private Caret() {
        this.thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (run) {
                    show = !show;
                    if (caretListener != null)
                        caretListener.CaretRefresh(Caret.this);
                    try {
                        //闪烁频率应该由配置文件来管理
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
    }

    public void setHostGlyph(GlyphImpl hostGlyph) {
        this.hostGlyph = hostGlyph;
        calculateFrame();
    }

    public GlyphImpl getHostGlyph() {
        return hostGlyph;
    }

    public static Caret getInstance() {
        return instance;
    }

    public int getInsertIndex() {
        int index = 0;
        if (hostGlyph != null) {
            index = composition.getDocument().indexOf(hostGlyph) + 1;
        }
        return index;
    }

    public void setCaretListener(CaretListener caretListener) {
        this.caretListener = caretListener;
    }

    public Composition getComposition() {
        return composition;
    }

    public void setComposition(Composition composition) {
        this.composition = composition;
    }

    @Override
    public void drawMe(Canvas canvas) {
        if (paint != null) {
            paint.setColor(Color.BLACK);
            if (show) {
                canvas.drawLine(x, y, x, y + height,paint);
            }
        }
    }

    public void calculateFrame() {
        if (hostGlyph == null) {
            //如果是空应该到第一行的起始位置
            //TODO : 这种对composition的假设是否合理?应该加上泛型
            Page page = (Page) composition.getChildren().get(0);
            Row row = (Row) page.getChildren().get(0);
            x = row.getX();
            y = row.getY();
            width = row.getWidth();
            height = row.getHeight();
        } else {
            x = hostGlyph.getX() + hostGlyph.getWidth();
            y = hostGlyph.getY();
            width = hostGlyph.getWidth();
            height = hostGlyph.getHeight();
        }
        show = true;
        caretListener.CaretRefresh(this);
    }
}
