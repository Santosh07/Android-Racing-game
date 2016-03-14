package com.extremeracer.Test;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.extremeracer.framework.Input.TouchEvent;
import com.extremeracer.framework.impl.AndroidGame;
import com.extremeracer.framework.impl.AndroidInput;
import com.extremeracer.framework.impl.TouchHandler;

import java.util.List;

public class AndroidSingleTouchTest extends Activity{
    TextView textView;
    StringBuilder builder = new StringBuilder();
    AndroidInput input = null;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        textView = new TextView(this);
        textView.append("Touch Me!!");
        input = new AndroidInput(this, textView , 1, 1);
        setContentView(textView);

    }

    /*
    @Override
    public boolean onTouch(View v, MotionEvent event) {

        builder.setLength(0);
        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                builder.append("down, ");
                break;
            case MotionEvent.ACTION_UP:
                builder.append("up, ");
                break;
            case MotionEvent.ACTION_MOVE:
                builder.append("dragged, ");
        }
        builder.append(event.getX());
        builder.append(", ");
        builder.append(event.getY());
        builder.append(", ");
        textView.setText(builder.toString());

        return true;
    }
    */


    @Override
    protected void onResume() {
        super.onResume();
        List<TouchEvent> touchList = input.getTouchEvents();
        //textView.setText("First YOU : \n");
        textView.setText("Size = " + touchList.size());
        for (int i = 0; i < touchList.size(); i++) {
            TouchEvent touchEvent = touchList.get(i);
            textView.append("Event " + i + " " + "PointerID = " + touchEvent.pointer + " ");
            if (touchEvent.type == 0) {
                textView.append("Down , " + " x = " + touchEvent.x + " y = " + touchEvent.y + "\n");
            } else if (touchEvent.type == 1) {
                textView.append("UP , " + touchEvent.x + "   " + touchEvent.y + "\n");
            } else if (touchEvent.type == 2) {
                textView.append("Dragged , " + touchEvent.x + "   " + touchEvent.y + "\n");
            }
        }
    }
}
