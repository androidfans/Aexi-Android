package com.liuli.aexi_android.Aexi.Model;

import com.liuli.aexi_android.Aexi.Interface.Compositor;

import java.util.Iterator;

/**
 * Created by Administrator on 2016/2/3 0003.
 */
public class StandardCompositor implements Compositor {

    public Composition composition;

    @Override
    public void compose() {
        composition.clear();
        Iterator it = composition.getDocument().iterator();
        //TODO :如果document是空  那么应该恢复为初始状态
        //TODO :可以加入Row池和Page池,以及Character池,减少内存消耗
        //TODO :只考虑了一个page的情况
        Page page = new Page();
        composition.append(page);
        Row row = new Row();
        page.append(row);
        //当前的排版算法非常不安全
        for (int i = 0; it.hasNext(); i ++) {
            GlyphImpl glyph = (GlyphImpl) it.next();
            glyph.setDocumentIndex(i);
            if (glyph instanceof LineBreaker || !row.append(glyph)) {
                row = new Row();
                page.append(row);
                row.append(glyph);
                continue;
            }
        }
    }

    @Override
    public void setComposition(Composition composition) {
        this.composition = composition;
    }
}
