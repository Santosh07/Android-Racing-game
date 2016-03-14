package com.extremeracer.ExtremeRacer;

import com.extremeracer.framework.Game;
import com.extremeracer.framework.Graphics;
import com.extremeracer.framework.Input.TouchEvent;
import com.extremeracer.framework.Screen;
import com.extremeracer.mrnom.HelpScreen;

import java.util.List;

/**
 * Created by root on 7/3/15.
 */
public class MainMenu extends Screen{
    public MainMenu(Game game) {
        super(game);
    }

    @Override
    public void update(float deltaTime) {
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        game.getInput().getKeyEvents();

        if (touchEvents.size() > 0) {
            for (int i = 0; i < touchEvents.size(); i++) {
                TouchEvent touchEvent = touchEvents.get(i);

                if (touchEvent.type == TouchEvent.TOUCH_UP) {
                    if (touchEvent.y > 400 && touchEvent.y < 600) {
                        game.setScreen(new ModeSelect(game));
                    }

                    if (touchEvent.y > 650 && touchEvent.y < 850) {
                        game.setScreen(new GameHelpScreen(game));
                    }
                }

            }
        }

    }

    @Override
    public void present(float deltaTime) {
        Graphics g = game.getGraphics();
        g.drawPixmap(Assets.mainMenuBackground, 0, 0);
        g.drawPixmap(Assets.playButton, 0, 400);
        g.drawPixmap(Assets.helpButton, 0, 650);

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
