package com.extremeracer.framework.impl;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.extremeracer.framework.Audio;
import com.extremeracer.framework.FileIO;
import com.extremeracer.framework.Game;
import com.extremeracer.framework.Graphics;
import com.extremeracer.framework.Input;
import com.extremeracer.framework.Screen;


public class AndroidGame extends Activity implements Game,View.OnTouchListener {
    AndroidFastRenderView renderView;
    Audio audio;
    Graphics graphics;
    FileIO fileIO;
    Input input;
    Screen screen;
    PowerManager.WakeLock wakeLock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("TESTACTIVITYREACHED", "REACED!!!! in main oncreate");
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Log.d("ANDROIDGAME", "WINDOW FULL SCREEN DONE");
        Log.d("TESTACTIVITYREACHED", "REACED!!!!......");
        boolean isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;

        int FrameBufferWidth = isLandscape ? 1280 : 720;
        int FrameBufferHeight = isLandscape ? 720 : 1280;

        Bitmap frameBuffer = Bitmap.createBitmap(FrameBufferWidth, FrameBufferHeight, Bitmap.Config.RGB_565);

        float scaleX = (float)FrameBufferWidth / getWindowManager().getDefaultDisplay().getWidth();
        float scaleY = (float)FrameBufferHeight /getWindowManager().getDefaultDisplay().getHeight();
        Log.d("ANDROID", "scalex = " + scaleX);
        Log.d("ANDROID", "Scaley = " + scaleY);

        renderView = new AndroidFastRenderView(this, frameBuffer);
        audio = new AndroidAudio(this);
        input = new AndroidInput(this, renderView, scaleX, scaleY);
        graphics = new AndroidGraphics(this.getAssets(), frameBuffer);
        fileIO = new AndroidFileIO(this);
        screen = getStartScreen();
        setContentView(renderView);

        //PowerManager powerManager = (PowerManager) this.getSystemService(Context.POWER_SERVICE);
        //PowerManager.WakeLock wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "GLGAME");
    }

    @Override
    protected void onPause() {
        super.onPause();

        //wakeLock.release();
        screen.pause();
        renderView.pause();
        if (isFinishing()) {
            screen.dispose();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        //wakeLock.acquire();
        screen.resume();
        renderView.resume();
    }

    @Override
    public Input getInput() {
        return input;
    }

    @Override
    public FileIO getFileIO() {
        return fileIO;
    }

    @Override
    public Audio getAudio() {
        return audio;
    }

    @Override
    public Graphics getGraphics() {
        return graphics;
    }

    @Override
    public void setScreen(Screen screen) {
        if (screen == null) {
            throw new IllegalArgumentException("Screen Must Not Be NULL");
        }

        this.screen.pause();
        this.screen.dispose();
        screen.resume();
        screen.update(0);
        this.screen = screen;
    }

    @Override
    public Screen getCurrentScreen() {
        return screen;
    }

    @Override
    public Screen getStartScreen() {
        return null;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Log.d("AndroidGame", "OnTouch on Android game called");
        return true;
    }
}
