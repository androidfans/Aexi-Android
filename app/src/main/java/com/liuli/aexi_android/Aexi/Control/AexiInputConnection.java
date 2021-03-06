package com.liuli.aexi_android.Aexi.Control;

import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.BaseInputConnection;
import com.liuli.aexi_android.Aexi.AexiContentView;

/**
 * Created by Administrator on 2016/2/2 0002.
 */
public class AexiInputConnection extends BaseInputConnection {
    private AexiContentView aexiContentView;
    public AexiInputConnection(View targetView, boolean fullEditor) {
        super(targetView, fullEditor);
        this.aexiContentView = (AexiContentView)targetView;
    }

    @Override
    public boolean sendKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_ENTER || event.getKeyCode() == KeyEvent.KEYCODE_DEL) {
            aexiContentView.onFunctionalKeyTyped(event);
        }
        return true;
    }

    @Override
    public boolean commitText(CharSequence text, int newCursorPosition) {
        aexiContentView.onTextInputed(text.toString());
        return true;
    }
}
