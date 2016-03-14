package com.extremeracer.Test;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.extremeracer.framework.impl.AndroidAudio;
import com.extremeracer.framework.impl.AndroidSound;

public class NewSoundTest extends Activity implements View.OnTouchListener {
    TextView textView;
    AndroidAudio audio;
    AndroidSound sound;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        textView = new TextView(this);
        audio = new AndroidAudio(this);
        sound = (AndroidSound) (audio.newSound("bomb-06.ogg"));
        setContentView(textView);
        textView.setOnTouchListener(this);
        textView.setText("Touch To Play");

        Log.d("ANDROIDSOUNDTEST", "PLAYED???");
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            textView.setText("Playing Sound");
            sound.play(1.0f);
        }
        return true;
    }
}
