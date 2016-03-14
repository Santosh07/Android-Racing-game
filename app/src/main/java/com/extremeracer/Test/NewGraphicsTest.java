package com.extremeracer.Test;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;

import com.extremeracer.framework.Graphics;
import com.extremeracer.framework.Screen;
import com.extremeracer.framework.impl.AndroidFastRenderView;
import com.extremeracer.framework.impl.AndroidGame;
import com.extremeracer.framework.impl.AndroidGraphics;

public class NewGraphicsTest extends AndroidGame {
    AndroidFastRenderView renderView;
    AndroidGraphics g;
    NewGraphicsTest gTest = new NewGraphicsTest();
    Screen screen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bitmap frameBuffer = Bitmap.createBitmap(1280, 720, Bitmap.Config.RGB_565);
        Log.d("NEWGRAPHICSTEST", "Creating RenderView");
        renderView = new AndroidFastRenderView(this, frameBuffer);
        Log.d("NEWGRAPHICSTEST", "CreatinG GRAPHICS");
        g = new AndroidGraphics(gTest.getAssets(), frameBuffer);
        Log.d("NEWGRAPHICSTEST", "SETTING SCREEN");
        screen = getStartScreen();
        setContentView(renderView);
        getStartScreen();
    }


    @Override
    protected void onResume() {
        super.onResume();

        renderView.resume();
    }

    @Override
    public Screen getStartScreen() {
        return new TestScreen(this);
    }

    @Override
    public Screen getCurrentScreen() {
        Log.d("NEWGRAPHICSTEST", "Getting Current Screen");
        return screen;
    }

    @Override
    public Graphics getGraphics() {
        return g;
    }
}
