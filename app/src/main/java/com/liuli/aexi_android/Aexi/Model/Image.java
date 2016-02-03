package com.liuli.aexi_android.Aexi.Model;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by Administrator on 2016/2/3 0003.
 */
public class Image extends GlyphImpl {
    private Bitmap bitmap;
    private float ratio;

    public Image(Bitmap bitmap) {
        this.bitmap = bitmap;
        setWidth(bitmap.getWidth());
        setHeight(bitmap.getHeight());
        if (height != 0) {
            ratio = (float) width / (float)height;
        }
    }

    public void shrinkWidth(int width) {
        if (width >= this.width) {
            return;
        }
        setWidth(width);
        setHeight((int) (width / ratio));
    }

    public void scaleHeight(int height) {
        setHeight(height);
        setWidth((int) (height * ratio));
    }

    @Override
    public void drawMe(Canvas canvas) {
//        canvas.drawImage(image, x, y, width,height,this);
    }

}
