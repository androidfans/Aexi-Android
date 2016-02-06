package com.liuli.aexi_android.Aexi.Control;

import com.liuli.aexi_android.Aexi.Model.Caret;
import com.liuli.aexi_android.Aexi.Interface.Command;
import com.liuli.aexi_android.Aexi.Model.Composition;
import com.liuli.aexi_android.Aexi.Model.Document;
import com.liuli.aexi_android.Aexi.Model.GlyphImpl;

/**
 * Created by Administrator on 2016/2/6 0006.
 */
public class DeleteCommand implements Command {

    private Composition composition;


    public DeleteCommand(Composition composition) {
        this.composition = composition;
    }

    @Override
    public boolean excute() {
        Caret caret = composition.getCaret();
        GlyphImpl glyph = caret.getHostGlyph();
        GlyphImpl nextGlyph = null;
        Document list = composition.getDocument();
        if (glyph == null) {
            return false;
        }
        int index = list.indexOf(glyph);
        if (index != 0) {
            nextGlyph = list.get(index - 1);
        }
        caret.setHostGlyph(nextGlyph);
        composition.remove(index);
        return true;
    }

    @Override
    public void unExcute() {

    }

    @Override
    public boolean canUndo() {
        return false;
    }
}
