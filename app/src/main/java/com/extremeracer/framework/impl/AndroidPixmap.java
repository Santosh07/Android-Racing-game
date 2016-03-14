package com.extremeracer.framework.impl;

import android.graphics.Bitmap;

import com.extremeracer.framework.Graphics;
import com.extremeracer.framework.Pixmap;

public class AndroidPixmap implements Pixmap {
    Bitmap bitmap;
    Graphics.Pixmapformat format;

    public AndroidPixmap(Bitmap bitmap, Graphics.Pixmapformat format) {
        this.bitmap = bitmap;
        this.format = format;
    }

    @Override
    public int getWidth() {
        return bitmap.getWidth();
    }

    @Override
    public int getHeight() {
        return bitmap.getHeight();
    }

    @Override
    public Graphics.Pixmapformat getFormat() {
        return format;
    }

    @Override
    public void dispose() {
        bitmap.recycle();
    }
}
