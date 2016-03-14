package com.extremeracer.mrnom;

import android.util.Log;

import com.extremeracer.framework.Game;
import com.extremeracer.framework.Graphics;
import com.extremeracer.framework.Input;
import com.extremeracer.framework.Input.TouchEvent;
import com.extremeracer.framework.Screen;

import java.util.List;


public class MainMenuScreen extends Screen {
    public MainMenuScreen(Game game) {
        super(game);
    }

    @Override
    public void update(float deltaTime) {
        //Log.d("MAINMENUSCREEN", "UPDATING MAIN MENU");
        Graphics g = game.getGraphics();
        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();
        game.getInput().getKeyEvents();

        int len = touchEvents.size();

        if(len > 0) {
            Log.d("MAINMENSCREEN", "length of touch list = " + len);
        }

        for (int i = 0; i < len; i++) {
            TouchEvent touchEvent = touchEvents.get(i);
            Log.d("MAINMENSCREEN", "x and y = " + touchEvent.x + touchEvent.y + " " + touchEvent.type);
            if (touchEvent.type == TouchEvent.TOUCH_UP) {
                if (inBounds(touchEvent, 0, g.getHeight() - 64 , 64, 64)) {
                    Settings.soundEnabled = !Settings.soundEnabled;
                    if (Settings.soundEnabled) {
                        Assets.click.play(1);
                    }
                }

                if (inBounds(touchEvent, 64, 220, 192, 42)) {
                    game.setScreen(new GameScreen(game));
                    if (Settings.soundEnabled) {
                        Assets.click.play(1);
                    }
                }

                if (inBounds(touchEvent, 64, 220 + 42, 192,42)) {
                    game.setScreen(new HighScoresScreen(game));
                    if (Settings.soundEnabled) {
                        Assets.click.play(1);
                    }
                }

                if (inBounds(touchEvent, 63, 220 + 84, 192, 42)) {
                    game.setScreen(new HelpScreen(game));
                    if (Settings.soundEnabled) {
                        Assets.click.play(1);
                    }
                }
            }
        }
    }

    private boolean inBounds(TouchEvent touchEvent, int x, int y, int width, int height) {
        if (touchEvent.x > x && touchEvent.y > y  && touchEvent.x < x + width && touchEvent.y < y + height){
            return true;
        }
        return false;
    }

    @Override
    public void present(float deltaTime) {
        //Log.d("LOADINGSCREEN", "DRAWING ASSETS");
        Graphics g = game.getGraphics();

        g.drawPixmap(Assets.background, 0, 0);
        g.drawPixmap(Assets.logo, 32, 20);
        g.drawPixmap(Assets.mainMenu, 64, 220);
        g.drawPixmap(Assets.buttons, 0, 416, 64, 0, 64, 64);
        //Log.d("MAINMENUSCREEN" ,"SOUNDENABLED = " + Settings.soundEnabled);

        if (Settings.soundEnabled) {
            g.drawPixmap(Assets.buttons, 0, 416, 0, 0, 64, 64);
        } else {
            //Log.d("MAINMENUSCREEN" ,"Drawing button");
            g.drawPixmap(Assets.buttons, 0, 416, 64, 0, 64, 64);
        }
    }

    @Override
    public void pause() {
        Settings.save(game.getFileIO());
    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
