package com.extremeracer.framework.impl;

import android.view.View;

import com.extremeracer.framework.Input;

import java.util.List;

/**
 * Created by root on 6/24/15.
 */
public interface TouchHandler extends View.OnTouchListener {
    public boolean isTouchedDown(int pointer);
    public int getTouchX(int pointer);
    public int getTouchY(int pointer);
    public List<Input.TouchEvent> getTouchEvents();
}
