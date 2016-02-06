package com.liuli.aexi_android.Aexi.Model;

import com.liuli.aexi_android.Aexi.Interface.Glyph;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 2016/2/3 0003.
 */
public class Document extends GlyphImplGroup implements Iterable<GlyphImpl> {
    @Override
    public boolean insert(GlyphImpl glyph, int index) {
        super.insert(glyph, index);
        return true;
    }

    @Override
    public Glyph remove(int index) {
        Glyph glyph = super.remove(index);

        return glyph;
    }

    public GlyphImpl getNext(Glyph glyph) {
        List<GlyphImpl> list = getChildren();
        if (list.size() == 0) {
            return null;
        }
        int index = list.indexOf(glyph);
        if (index == list.size() - 1) {
            return null;
        }
        GlyphImpl nextGlyph = list.get(index + 1);
        return nextGlyph;
    }

    public GlyphImpl getPrevious(Glyph glyph) {
        List<GlyphImpl> list = getChildren();
        if (list.size() == 0) {
            return null;
        }
        int index = list.indexOf(glyph);
        if (index == 0) {
            return null;
        }
        GlyphImpl previousGlyph = list.get(index - 1);
        return previousGlyph;
    }

    public <T> int indexOf(T glyph) {
        return children.indexOf(glyph);
    }

    public void add(int index, GlyphImpl glyph) {
        children.add(index,glyph);
    }

    public <T> T get(int index) {
        return (T) children.get(index);
    }

    public int size() {
        return children.size();
    }


    @Override
    public Iterator iterator() {
        return children.iterator();
    }
}
