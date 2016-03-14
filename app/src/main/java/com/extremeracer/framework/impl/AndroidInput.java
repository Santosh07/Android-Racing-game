package com.extremeracer.framework.impl;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.View;

import com.extremeracer.framework.Input;

import java.util.List;


public class AndroidInput implements Input {
    TouchHandler touchHandler;
    KeyboardHandler keyboardHandler;
    AccelerometerHandler accelHandler;

    public AndroidInput(Context context, View view, float scaleX, float scaleY) {
        Log.d("ANDROIDINPUT", "IN CONSTRUCTOR");
        accelHandler = new AccelerometerHandler(context);
        keyboardHandler = new KeyboardHandler(view);
        touchHandler = new MultiTouchHandler(view, scaleX, scaleY);
        /*
        if (Integer.parseInt(Build.VERSION.SDK) < 5) {
            touchHandler = new SingleTouchHandler(view, scaleX, scaleY);
        } else
            touchHandler = new MultiTouchHandler(view, scaleX, scaleY);
        */

    }

    @Override
    public boolean isKeyPressed(int keyCode) {
        return keyboardHandler.iskeyPressed(keyCode);
    }

    @Override
    public boolean isTouchDown(int pointer) {
        //Log.d("ANDROIDINPUT", "IS TOUCH DOWN CALLED. RETURNED = " + touchHandler.isTouchedDown(pointer) );
        return touchHandler.isTouchedDown(pointer);
    }

    @Override
    public float getX(int pointer) {
        return touchHandler.getTouchX(pointer);
    }

    @Override
    public float getY(int pointer) {
        return touchHandler.getTouchY(pointer);

    }

    @Override
    public float getAccelX() {
        return accelHandler.getAccelX();
    }

    @Override
    public float getAccelY() {
        return accelHandler.getAccelY();
    }

    @Override
    public float getAccelZ() {
        return accelHandler.getAccelZ();
    }

    @Override
    public List<KeyEvent> getKeyEvents() {
        return keyboardHandler.getKeyEvents();

    }

    @Override
    public List<TouchEvent> getTouchEvents() {
        return touchHandler.getTouchEvents();

    }
}
