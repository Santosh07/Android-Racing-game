package com.extremeracer.Test;


import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.extremeracer.framework.impl.AndroidGame;

public class AndroidActivityTest extends AndroidGame {
    TextView textView;
    StringBuilder builder = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("TESTACTIVITYREACHED", "REACED!!!!");
        builder.append("App Created.\n");
        textView = new TextView(this);
        textView.setText(builder.toString());
        setContentView(textView);
        Log.d("TESTACTIVITYREACHED", "setContectView activated!!");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("TESTACTIVITY", "ONRESUME REACHED.\n");
        builder.append("Resume Called.\n");
        textView.setText(builder.toString());
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("TESTACTIVITYREACHED", "ONPAUSE REACHED.\n");
        builder.append("App paused.\n");
        textView.setText(builder.toString());
    }
}
