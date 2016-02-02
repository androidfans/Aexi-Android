package com.liuli.aexi_android.Aexi;

import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.liuli.aexi_android.Aexi.Control.AexiInputConnection;
import com.liuli.aexi_android.Aexi.Model.Caret;
import com.liuli.aexi_android.Aexi.Model.CharacterRun;
import com.liuli.aexi_android.Aexi.Model.Glyph;
import com.liuli.aexi_android.Aexi.Model.LineBreaker;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/1/31 0031.
 */
public class AexiContentView extends ViewGroup {

    private Paint paint;

    private List<Glyph> children;

    private View caret;

    private float textSize;
    public AexiContentView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setAntiAlias(true);
        children = new ArrayList<>();
        textSize = 40f;
        Caret caret = new Caret(context, paint);
        this.caret = caret;
        addView(caret);
        setFocusable(true);
    }

    @Override
    public InputConnection onCreateInputConnection(EditorInfo outAttrs) {
        return new AexiInputConnection(this, false);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int childCount = getChildCount();
        for (int i = 0 ; i < childCount ; i ++) {
            measureChild(getChildAt(i), widthMeasureSpec, heightMeasureSpec);
        }
        super.onMeasure(widthMeasureSpec,heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        View childView = null;
        int x = l;
        int y = t;
        int measuredWidth = 0;
        int measuredHeight = 0;
        for (int i = 0 ; i < childCount; i ++) {
            childView = getChildAt(i);
            measuredWidth = childView.getMeasuredWidth();
            measuredHeight = childView.getMeasuredHeight();
            childView.layout(x, y, x + measuredWidth, y + measuredHeight);
            x += measuredWidth;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        }
        return true;
    }


    private void compose() {

    }

    public void onFunctionalKeyTyped(KeyEvent keyEvent) {
        //这里涉及到长按检测,先暂时放着 不做
        if (keyEvent.getAction() == KeyEvent.ACTION_UP) {
            switch (keyEvent.getKeyCode()) {
                case 66:
                    children.add(new LineBreaker());
                    break;
            }
        }
    }



    public void onTextInputed(String newText) {
        Log.i("ime", "文字输入事件 : " + newText);
        CharacterRun characterRun = new CharacterRun(newText);
        children.add(characterRun);
        compose();
    }
}