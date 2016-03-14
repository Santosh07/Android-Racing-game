package com.extremeracer.ExtremeRacer;

import com.extremeracer.framework.Game;
import com.extremeracer.framework.Graphics;
import com.extremeracer.framework.Input;
import com.extremeracer.framework.Screen;

import java.util.List;

/**
 * Created by root on 7/3/15.
 */
public class GameHelpScreen extends Screen {
    public GameHelpScreen(Game game) {
        super(game);
    }

    @Override
    public void update(float deltaTime) {
        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();
        game.getInput().getKeyEvents();

        for (int i = 0; i < touchEvents.size(); i++) {
            Input.TouchEvent touchEvent = touchEvents.get(i);

            if (touchEvent.type == Input.TouchEvent.TOUCH_UP) {
                if (touchEvent.x > 580 && touchEvent.y > 1140) {
                    game.setScreen(new MainMenu(game));
                }
            }
        }
    }

    @Override
    public void present(float deltaTime) {
        Graphics g = game.getGraphics();
        g.drawPixmap(Assets.helpMenu, 0, 0);
        g.drawPixmap(Assets.backButton, 580, 1140);
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
