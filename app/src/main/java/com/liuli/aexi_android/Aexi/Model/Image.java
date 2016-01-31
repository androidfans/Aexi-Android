package com.liuli.aexi_android.Aexi.Model;

/**
 * Created by Liuli on 2015/3/21.
 */
public class Image extends GlyphImpl{
    private Image image;
    private float ratio;

    public Image(Image image) {
        this.image = image;
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
}