package com.extremeracer.mrnom;

import android.util.Log;

import com.extremeracer.framework.Game;
import com.extremeracer.framework.Graphics;
import com.extremeracer.framework.Input;
import com.extremeracer.framework.Screen;

import java.util.List;

/**
 * Created by root on 6/28/15.
 */
public class HighScoresScreen extends Screen {
    String line[] = new String[5];

    public HighScoresScreen(Game game) {
        super(game);
        for (int i = 0; i < 5; i++) {
            line[i] = " " + (i + 1) + "." + Settings.highScore[i];
        }

    }

    @Override
    public void update(float deltaTime) {
        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();
        game.getInput().getKeyEvents();
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            Input.TouchEvent event = touchEvents.get(i);
            if(event.type == Input.TouchEvent.TOUCH_UP) {
                if(event.x < 64 && event.y > 416  ) {
                    game.setScreen(new MainMenuScreen(game));
                    if(Settings.soundEnabled)
                        Assets.click.play(1);
                    return;
                }
            }
        }

    }

    @Override
    public void present(float deltaTime) {
        Graphics g = game.getGraphics();

        g.drawPixmap(Assets.background, 0, 0);
        g.drawPixmap(Assets.mainMenu, 64, 20, 0, 42, 196, 42);

        int y = 100;
        for (int i = 0; i < 5; i++) {
            drawText(g, line[i] , 20, y);
            y += 50;
        }


        g.drawPixmap(Assets.buttons, 0, 416, 64, 64, 64, 64);
    }

    private void drawText(Graphics g, String line, int x, int y) {
        //Log.d("HIGHSCORESCREEN", "DRAWING HIGHSCORE");
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);

            if (c == ' ') {
                x += 20;
                continue;
            } else if (c == '.') {
                g.drawPixmap(Assets.numbers, x, y, 200, 0, 10, 32);
            } else {
                g.drawPixmap(Assets.numbers, x, y, (c - '0') * 20, 0, 20, 32);
            }
            x += 20;
        }

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
