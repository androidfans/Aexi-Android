package com.liuli.aexi_android.Aexi.Control;

import android.view.MotionEvent;

import com.liuli.aexi_android.Aexi.Interface.CaretListener;
import com.liuli.aexi_android.Aexi.Interface.CompositionListener;
import com.liuli.aexi_android.Aexi.Model.Composition;
import com.liuli.aexi_android.Aexi.Model.Document;
import com.liuli.aexi_android.Aexi.Model.Glyph;
import com.liuli.aexi_android.Aexi.Model.GlyphImpl;

/**
 *处理键盘及鼠标逻辑的控制器类
 * Created by Liuli on 2015/3/19.
 */
public class Controller implements CaretListener, CompositionListener{

    private Composition composition;

    public void setComposition(Composition composition) {
        composition.setCompositionListener(this);
        this.composition = composition;
    }
    @Override
    public void documentRefresh(Composition composition) {
    }

    @Override
    public void CaretRefresh(Glyph glyph) {
    }




    private GlyphImpl findHitedGlyph(MotionEvent e) {
        Document doc =  composition.getDocument();
        GlyphImpl hitedGlyph = null;
        for (GlyphImpl glyph : doc) {
            if (glyph.hitRect((int)e.getX(), (int)e.getY())) {
                hitedGlyph = glyph;
                break;
            }
        }
        return hitedGlyph;
    }
}
