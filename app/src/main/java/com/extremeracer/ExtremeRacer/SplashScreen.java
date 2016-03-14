package com.extremeracer.ExtremeRacer;

import com.extremeracer.framework.Game;
import com.extremeracer.framework.Graphics;
import com.extremeracer.framework.Input.TouchEvent;
import com.extremeracer.framework.Screen;

import java.util.List;

/**
 * Created by root on 7/3/15.
 */
public class SplashScreen extends Screen {


    public SplashScreen(Game game) {
        super(game);
    }

    @Override
    public void update(float deltaTime) {
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        game.getInput().getKeyEvents();

        if (touchEvents.size() > 0) {
            game.setScreen(new MainMenu(game));

        }
    }

    @Override
    public void present(float deltaTime) {
        Graphics g = game.getGraphics();
        g.drawPixmap(Assets.loadingScreen, 0, 0);
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
