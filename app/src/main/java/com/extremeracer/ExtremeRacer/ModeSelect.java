package com.extremeracer.ExtremeRacer;

import com.extremeracer.framework.Game;
import com.extremeracer.framework.Graphics;
import com.extremeracer.framework.Input;
import com.extremeracer.framework.Screen;

import java.util.List;

/**
 * Created by root on 7/3/15.
 */
public class ModeSelect extends Screen{

    public ModeSelect(Game game) {
        super(game);
    }

    @Override
    public void update(float deltaTime) {
        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();
        game.getInput().getKeyEvents();

        if (touchEvents.size() > 0) {
            for (int i = 0; i < touchEvents.size(); i++) {
                Input.TouchEvent touchEvent = touchEvents.get(i);

                if (touchEvent.type == Input.TouchEvent.TOUCH_UP) {
                    if (touchEvent.y > 400 && touchEvent.y < 600) {
                        game.setScreen(new Racer(game));
                    }
                }

                if (touchEvent.type == Input.TouchEvent.TOUCH_UP) {
                    if (touchEvent.y > 650 && touchEvent.y < 850) {
                        game.setScreen(new MainMenu(game));
                    }
                }
            }
        }
    }

    @Override
    public void present(float deltaTime) {
        Graphics g = game.getGraphics();
        g.drawPixmap(Assets.modeSelectBackground, 0, 0);
        g.drawPixmap(Assets.singlePlayerButton, 0, 400);
        g.drawPixmap(Assets.multiPlayerButton, 0, 650);

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
