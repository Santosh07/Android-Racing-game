package com.extremeracer.framework.impl;

import android.annotation.TargetApi;
import android.os.Build;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.extremeracer.framework.Input;
import com.extremeracer.framework.Input.TouchEvent;
import com.extremeracer.framework.Pool;

import java.util.ArrayList;
import java.util.List;

public class MultiTouchHandler implements TouchHandler  {
    public static final int MAXTOUCHPOINTS = 10;
    private boolean[] isTouched = new boolean[MAXTOUCHPOINTS];
    private int[] touchX = new int[MAXTOUCHPOINTS];
    private int[] touchY = new int[MAXTOUCHPOINTS];
    private int[] id = new int[MAXTOUCHPOINTS];

    float scaleX;
    float scaleY;

    Pool<TouchEvent> touchEventPool;
    List<TouchEvent> touchEventsBuffer = new ArrayList<TouchEvent>();
    List<TouchEvent> touchEvents = new ArrayList<TouchEvent>();


    public MultiTouchHandler(View view, float scaleX, float scaleY) {
        Pool.PoolObjectFactory<TouchEvent> factory = new Pool.PoolObjectFactory<TouchEvent>() {
            @Override
            public TouchEvent createObject() {
                return new TouchEvent();
            }
        };
        touchEventPool = new Pool<TouchEvent>(factory, 100);
        Log.d("MULTITOUCHHANDLER", "setting setontouchlistener to view");
        view.setOnTouchListener(this);

        this.scaleX = scaleX;
        this.scaleY = scaleY;
    }

    @Override
    public boolean isTouchedDown(int pointer) {
        synchronized (this) {
            int index = getIndex(pointer);
            if (index < 0 || index > MAXTOUCHPOINTS) {
                return false;
            } else
                return isTouched[index];
        }
    }

    private int getIndex(int pointerId) {
        for (int i = 0; i < MAXTOUCHPOINTS; i++) {
            if (id[i] == pointerId) {
                return i;
            }
        }
        return -1;

    }

    @Override
    public int getTouchX(int pointer) {
        synchronized (this) {
            int index = getIndex(pointer);
            if (index < 0 || index >= MAXTOUCHPOINTS)
                return 0;
            else
                return touchX[index];
        }
    }

    @Override
    public int getTouchY(int pointer) {
        synchronized (this) {
            int index = getIndex(pointer);
            if (index < 0 || index >= MAXTOUCHPOINTS)
                return 0;
            else
                return touchY[index];
        }
    }

    @Override
    public List<TouchEvent> getTouchEvents() {
        synchronized (this) {
            int len = touchEvents.size();
            for (int i = 0; i < len; i++)
                touchEventPool.free(touchEvents.get(i));
            touchEvents.clear();
            touchEvents.addAll(touchEventsBuffer);
            touchEventsBuffer.clear();
            return touchEvents;
        }
    }

    @TargetApi(Build.VERSION_CODES.ECLAIR)
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        //Log.d("MULTITOUCHHANDLER", "ON TOUCH CALLED");
        int action = event.getAction() & MotionEvent.ACTION_MASK;
        int pointerIndex = (event.getAction() & MotionEvent.ACTION_POINTER_ID_MASK) >> MotionEvent.ACTION_POINTER_ID_SHIFT;
        int pointerCount = event.getPointerCount();
        TouchEvent touchEvent;
        for (int i = 0; i < MAXTOUCHPOINTS; i++) {
            if (i >= pointerCount) {
                isTouched[i] = false;
                id[i] = -1;
                continue;
            }

            if (event.getAction() != MotionEvent.ACTION_MOVE && i != pointerIndex) {
                continue;
            }
            int pointerId = event.getPointerId(i);
            switch(action) {
                case MotionEvent.ACTION_DOWN:

                case MotionEvent.ACTION_POINTER_DOWN:
                    //Log.d("MULTITOUCHHANDLER", "DOWN CALLED ID = " + pointerId);
                    touchEvent = touchEventPool.newObject();
                    isTouched[i] = true;
                    touchEvent.type = touchEvent.TOUCH_DOWN;
                    touchEvent.x = touchX[i] = (int) (event.getX() * scaleX);
                    touchEvent.y = touchY[i] = (int) (event.getY() * scaleY);
                    touchEvent.pointer = pointerId;
                    id[i] = pointerId;
                    touchEventsBuffer.add(touchEvent);
                    break;
                case MotionEvent.ACTION_MOVE:
                    touchEvent = touchEventPool.newObject();
                    isTouched[i] = true;
                    touchEvent.pointer = pointerId;
                    touchEvent.type = touchEvent.TOUCH_DRAGGED;
                    touchEvent.x = touchX[i] = (int) (event.getX() * scaleX);
                    touchEvent.y = touchY[i] = (int) (event.getY() * scaleY);
                    id[i] = pointerId;
                    touchEventsBuffer.add(touchEvent);
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_POINTER_UP:
                case MotionEvent.ACTION_CANCEL:
                    //Log.d("MULTITOUCHHANDLER", "UP CALLED ID = " + pointerId);
                    touchEvent = touchEventPool.newObject();
                    touchEvent.pointer = pointerId;
                    isTouched[i] = false;
                    touchEvent.type = touchEvent.TOUCH_UP;
                    touchEvent.x = touchX[i] = (int) (event.getX() * scaleX);
                    touchEvent.y = touchY[i] = (int) (event.getY() * scaleY);
                    id[i] = -1;
                    touchEventsBuffer.add(touchEvent);
                    break;
            }
        }
        return true;
    }
}
