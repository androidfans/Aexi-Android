package com.liuli.aexi_android.Aexi.Model;

import com.liuli.aexi_android.Aexi.Interface.Compositor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 2016/2/3 0003.
 */
public class StandardCompositor implements Compositor {

    public Composition composition;
    private int compositionWidth;
    private int pageHeight;
    private int defaultRowHeight;



    @Override
    public void compose() {
        composition.clear();
        Iterator it = composition.getDocument().iterator();
        //TODO :如果document是空  那么应该恢复为初始状态
        //TODO :加入Row池和Page池,以及Character池,减少内存消耗
        //TODO :只考虑了一个page的情况
        Row row = new Row(compositionWidth,defaultRowHeight);
        List<GlyphImpl> rows = new ArrayList<>();
        GlyphImpl glyph = null;
        while (it.hasNext()) {
            glyph = (GlyphImpl) it.next();
            if (glyph instanceof LineBreaker || !row.append(glyph)) {
                rows.add(row);
                row = new Row(compositionWidth,defaultRowHeight);
                row.append(glyph);
            }
        }
        rows.add(row);
        it = rows.iterator();
        Page page = new Page(compositionWidth,pageHeight);
        while (it.hasNext()) {
            glyph = (GlyphImpl) it.next();
            if (!page.append(glyph)) {
                composition.append(page);
                page = new Page();
                page.append(glyph);
            }
        }
        composition.append(page);
//        Page page = new Page();
//        composition.append(page);
//        Row row = new Row();
//        page.append(row);
//        for (int i = 0; it.hasNext(); i ++) {
//            GlyphImpl glyph = (GlyphImpl) it.next();
//            glyph.setDocumentIndex(i);
//            if (glyph instanceof LineBreaker || !row.append(glyph)) {
//                row = new Row();
//                page.append(row);
//                row.append(glyph);
//                continue;
//            }
//        }
    }

    @Override
    public void setComposition(Composition composition) {
        this.composition = composition;
    }

    public void setCompositionWidth(int compositionWidth) {
        this.compositionWidth = compositionWidth;
    }

    public void setPageHeight(int pageHeight) {
        this.pageHeight = pageHeight;
    }

    public void setDefaultRowHeight(int defaultRowHeight) {
        this.defaultRowHeight = defaultRowHeight;
    }
}
