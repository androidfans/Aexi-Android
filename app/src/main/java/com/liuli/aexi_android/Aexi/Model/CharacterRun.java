package com.liuli.aexi_android.Aexi.Model;

import android.graphics.Color;

/**
 * Created by Administrator on 2016/2/2 0002.
 */
public class CharacterRun implements Glyph {
    private int color;
    private boolean underLine;
    private boolean bold;
    private float textSize;
    private String content;

    public CharacterRun(String content) {
        this.content = content == null ? "" : content;
        color = Color.BLACK;
        underLine = false;
        bold = false;
        textSize = 20f;
    }
}
