package com.extremeracer.framework.impl;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.extremeracer.framework.Input;
import com.extremeracer.framework.Input.TouchEvent;
import com.extremeracer.framework.Pool;

import java.util.ArrayList;
import java.util.List;

public class SingleTouchHandler implements TouchHandler {
    public SingleTouchHandler(View view, float scaleX, float scaleY) {
        Pool.PoolObjectFactory<TouchEvent> factory = new Pool.PoolObjectFactory<TouchEvent>() {
            @Override
            public TouchEvent createObject() {
                return new TouchEvent();
            }
        };
        touchEventPool = new Pool<TouchEvent>(factory, 100);
        view.setOnTouchListener(this);
        this.scaleX = scaleX;
        this.scaleY = scaleY;
    }

    boolean isTouched;
    int touchX;
    int touchY;
    Pool<TouchEvent> touchEventPool;
    List<TouchEvent> touchEventsBuffer = new ArrayList<>();
    List<TouchEvent> touchEvents = new ArrayList<>();
    float scaleX;
    float scaleY;

    @Override
    public boolean isTouchedDown(int pointer) {
        synchronized (this) {
            if (pointer == 0) {
                return isTouched;
            }
        }
        return false;
    }

    @Override
    public int getTouchX(int pointer) {
        synchronized (this) {
            return touchX;
        }
    }

    @Override
    public int getTouchY(int pointer) {
        synchronized (this) {
            return touchY;
        }
    }

    @Override
    public List<Input.TouchEvent> getTouchEvents() {
        synchronized (this) {
            for (int i = 0; i < touchEvents.size(); i++) {
                touchEventPool.free(touchEvents.get(i));
            }
            touchEvents.clear();
            touchEvents.addAll(touchEventsBuffer);
            touchEventsBuffer.clear();
            return touchEvents;
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        synchronized (this) {
            TouchEvent touchEvent = touchEventPool.newObject();

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    Log.d("SINGLETOUCHHANDLER", "REGISTERING DOWN ACTION");
                    touchEvent.type = TouchEvent.TOUCH_DOWN;
                    isTouched = true;
                    break;

                case MotionEvent.ACTION_UP:
                    Log.d("SINGLETOUCHHANDLER", "REGISTERING UP ACTION");
                    touchEvent.type = TouchEvent.TOUCH_UP;
                    isTouched = false;
                    break;

                case MotionEvent.ACTION_MOVE:
                    Log.d("SINGLETOUCHHANDLER", "REGISTERING MOVE ACTION");
                    touchEvent.type = TouchEvent.TOUCH_DRAGGED;
                    isTouched = true;
                    break;
            }
            touchEvent.x = touchX = (int) (event.getX() * scaleX);
            touchEvent.y = touchY = (int) (event.getY() * scaleY);
            touchEventsBuffer.add(touchEvent);
        }
        return true;
    }

}
