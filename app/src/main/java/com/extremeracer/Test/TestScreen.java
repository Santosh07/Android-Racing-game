package com.extremeracer.Test;

import android.util.Log;

import com.extremeracer.framework.Game;
import com.extremeracer.framework.Graphics;
import com.extremeracer.framework.Pixmap;
import com.extremeracer.framework.Screen;
import com.extremeracer.framework.impl.AndroidGraphics;
import com.extremeracer.framework.impl.AndroidPixmap;


public class TestScreen extends Screen {
    AndroidPixmap pixTest;
    public TestScreen(Game game) {
        super(game);
    }

    @Override
    public void update(float deltaTime) {
        Log.d("TESTSCREEN", "UPDATING");
        AndroidGraphics g = (AndroidGraphics) game.getGraphics();
        pixTest = (AndroidPixmap) g.newPixmap("bobrgb888.png", Graphics.Pixmapformat.RGB565);
    }

    @Override
    public void present(float deltaTime) {
        AndroidGraphics g = (AndroidGraphics) game.getGraphics();
        Log.d("TESTSCREEN", "PRESENT DRAWING");
        g.drawPixmap(pixTest, 0, 0);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
