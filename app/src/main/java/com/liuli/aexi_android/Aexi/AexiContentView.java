package com.liuli.aexi_android.Aexi;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;

import com.liuli.aexi_android.Aexi.Control.AexiInputConnection;
import com.liuli.aexi_android.Aexi.Model.Caret;
import com.liuli.aexi_android.Aexi.Interface.CaretListener;
import com.liuli.aexi_android.Aexi.Model.Character;
import com.liuli.aexi_android.Aexi.Interface.Command;
import com.liuli.aexi_android.Aexi.Control.CommandManager;
import com.liuli.aexi_android.Aexi.Model.Composition;
import com.liuli.aexi_android.Aexi.Control.DeleteCommand;
import com.liuli.aexi_android.Aexi.Interface.Glyph;
import com.liuli.aexi_android.Aexi.Model.GlyphImpl;
import com.liuli.aexi_android.Aexi.Control.InsertCommand;
import com.liuli.aexi_android.Aexi.Model.LineBreaker;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/1/31 0031.
 */
public class AexiContentView extends View implements CaretListener {
    private Paint paint;
    private List<Glyph> children;
    private Caret caret;
    private Composition composition;
    private float textSize;
    private CommandManager commandManager;

    public AexiContentView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setAntiAlias(true);
        children = new ArrayList<>();
        textSize = 60f;
        caret = new Caret(paint);
        caret.setCaretListener(this);
        composition = new Composition();
        composition.setPaint(paint);
        composition.setCaret(caret);
        caret.setComposition(composition);
        caret.setHostGlyph(null);
        commandManager = new CommandManager();
        setFocusable(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        setMeasuredDimension(width,height);
        Log.i("ime", "width : " + width);
        Log.i("ime", "height : " + height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        composition.drawMe(canvas);
        caret.drawMe(canvas);
    }

    @Override
    public InputConnection onCreateInputConnection(EditorInfo outAttrs) {
        return new AexiInputConnection(this, false);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        }
        return true;
    }

    public void onFunctionalKeyTyped(KeyEvent keyEvent) {
        //长按会自动连发按键up 和 down事件,不需要单独处理长按事件
        if (keyEvent.getAction() == KeyEvent.ACTION_UP) {
            Command command = null;
            switch (keyEvent.getKeyCode()) {
                case 66:
                    GlyphImpl glyph = new LineBreaker(textSize,paint);
                    command = new InsertCommand(composition, glyph);
                    break;
                case 67:
                    command = new DeleteCommand(composition);
            }
            commandManager.setCurrentCommand(command);
            commandManager.excuteCommand();
        }
    }

    public void onTextInputed(String newText) {
        for (int i = 0 ; i < newText.length() ; i ++) {
            commandManager.setCurrentCommand(new InsertCommand(composition,new Character(newText.charAt(i),textSize,paint)));
            commandManager.excuteCommand();
        }
        invalidate();
    }

    @Override
    public void CaretRefresh() {
        postInvalidate();
    }
}