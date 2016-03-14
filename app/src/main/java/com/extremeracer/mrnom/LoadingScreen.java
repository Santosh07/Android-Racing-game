package com.extremeracer.mrnom;

import android.util.Log;

import com.extremeracer.framework.Game;
import com.extremeracer.framework.Graphics;
import com.extremeracer.framework.Graphics.Pixmapformat;
import com.extremeracer.framework.Screen;
import com.extremeracer.framework.impl.AndroidGraphics;

/**
 * Created by root on 6/27/15.
 */
public class LoadingScreen extends Screen {
    public LoadingScreen(Game game) {
        super(game);
    }

    @Override
    public void update(float deltaTime) {
        Log.d("LOADINGSCREEN", "UPDATING ASSETS");
        Graphics g = game.getGraphics();
        Assets.background = g.newPixmap("background.png",Pixmapformat.RGB565);
        Assets.logo = g.newPixmap("logo.png", Pixmapformat.ARGB4444);
        Assets.mainMenu = g.newPixmap("mainmenu.png", Pixmapformat.ARGB4444);
        Assets.buttons = g.newPixmap("buttons.png", Pixmapformat.ARGB4444);
        Assets.help1 = g.newPixmap("help1.png", Pixmapformat.ARGB4444);
        Assets.help2 = g.newPixmap("help2.png", Pixmapformat.ARGB4444);
        Assets.help3 = g.newPixmap("help3.png", Pixmapformat.ARGB4444);
        Assets.numbers = g.newPixmap("numbers.png", Pixmapformat.ARGB4444);
        Assets.ready = g.newPixmap("ready.png", Pixmapformat.ARGB4444);
        Assets.pause = g.newPixmap("pausemenu.png", Pixmapformat.ARGB4444);
        Assets.gameOver = g.newPixmap("gameover.png", Pixmapformat.ARGB4444);
        Assets.headUp = g.newPixmap("headup.png", Pixmapformat.ARGB4444);
        Assets.headLeft = g.newPixmap("headleft.png", Pixmapformat.ARGB4444);
        Assets.headDown = g.newPixmap("headdown.png", Pixmapformat.ARGB4444);
        Assets.headRight = g.newPixmap("headright.png", Pixmapformat.ARGB4444);
        Assets.tail = g.newPixmap("tail.png", Pixmapformat.ARGB4444);
        Assets.stain1 = g.newPixmap("stain1.png", Pixmapformat.ARGB4444);
        Assets.stain2 = g.newPixmap("stain2.png", Pixmapformat.ARGB4444);
        Assets.stain3 = g.newPixmap("stain3.png", Pixmapformat.ARGB4444);

        Assets.click = game.getAudio().newSound("click.ogg");
        Assets.eat = game.getAudio().newSound("eat.ogg");
        Assets.bitten = game.getAudio().newSound("bitten.ogg");
        Log.d("LOADINGSCREEN", "UPDATING SETTINGS TO FILE");
        Settings.load(game.getFileIO());
        Log.d("LOADINGSCREEN", "UPDATE SUCCESSFULL");
        game.setScreen(new MainMenuScreen(game));
    }

    @Override
    public void present(float deltaTime) {

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
