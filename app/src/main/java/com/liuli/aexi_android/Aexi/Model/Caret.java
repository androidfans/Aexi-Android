package com.liuli.aexi_android.Aexi.Model;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.liuli.aexi_android.Aexi.Interface.CaretListener;

/**
 * Created by Administrator on 2016/2/3 0003.
 */
public class Caret extends GlyphImpl {
    public CaretListener caretListener;
    private boolean show = true;
    private Composition composition;
    private boolean run = true;
    private GlyphImpl hostGlyph;
    private Paint paint;

    public Caret(Paint paint) {
        this.paint = paint;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (run) {
                    show = !show;
                    if (caretListener != null) {
                        caretListener.CaretRefresh();
                    }
                    try {
                        //闪烁频率应该由配置文件来管理
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public void calculateFrame() {
        if (hostGlyph == null) {
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
        if (caretListener != null) {
            caretListener.CaretRefresh();
        }
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

    public int getInsertIndex() {
        int index = 0;
        if (hostGlyph != null) {
            index = composition.getDocument().indexOf(hostGlyph) + 1;
        }
        return index;
    }

    public void setHostGlyph(GlyphImpl hostGlyph) {
        this.hostGlyph = hostGlyph;
        calculateFrame();
    }

    public void setCaretListener(CaretListener caretListener) {
        this.caretListener = caretListener;
    }

    public void setComposition(Composition composition) {
        this.composition = composition;
    }

    public GlyphImpl getHostGlyph() {
        return hostGlyph;
    }
}
