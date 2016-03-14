package com.extremeracer.Test;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.extremeracer.framework.impl.AndroidAudio;
import com.extremeracer.framework.impl.AndroidMusic;

public class NewMusicTest extends Activity implements View.OnTouchListener {
    TextView textView;
    AndroidAudio audio;
    AndroidMusic music;
    boolean playing = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        textView = new TextView(this);
        textView.setOnTouchListener(this);
        textView.setText("Touch To play music");
        setContentView(textView);

        audio = new AndroidAudio(this);
        music = (AndroidMusic)audio.newMusic("Forever.mp3");
        if (music == null) {
            textView.setText("MUSIC IS NULL");
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        //textView.append("Ready");
        if (event.getAction() == MotionEvent.ACTION_UP) {
            //textView.setText("PAUSED");
            if (playing) {
                textView.setText("Playing.Touch to Pause.");
                music.play();
            } else {
                textView.setText("Paused.Touch to Play.");
                music.pause();
            }
            textView.append("Slide to stop");
            playing = !playing;
        }


        if (event.getAction() == MotionEvent.ACTION_MOVE){
            textView.setText("Stopped");
            music.stop();
        }

        return true;
    }
}
