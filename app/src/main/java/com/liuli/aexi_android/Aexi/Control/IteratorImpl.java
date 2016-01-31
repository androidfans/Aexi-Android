package com.liuli.aexi_android.Aexi.Control;


import com.liuli.aexi_android.Aexi.Model.Glyph;

/**
 * Created by Liuli on 2015/3/25.
 */
public abstract class IteratorImpl implements iterator {
    private Glyph glyph;

    public void setGlyph(Glyph glyph) {
        this.glyph = glyph;
    }
}
