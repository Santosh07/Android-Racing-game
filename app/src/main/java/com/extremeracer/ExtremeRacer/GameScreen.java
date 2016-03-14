package com.extremeracer.ExtremeRacer;

import android.graphics.Color;
import android.util.Log;

import com.extremeracer.framework.Game;
import com.extremeracer.framework.Graphics;
import com.extremeracer.framework.Input;
import com.extremeracer.framework.Screen;
import com.extremeracer.mrnom.*;
import com.extremeracer.mrnom.Assets;

import java.util.List;

/**
 * Created by root on 7/3/15.
 */
public class GameScreen extends Screen {

    enum GameState {
        Ready,
        Running,
        Paused,
        GameOver
    }

    GameState state = GameState.Ready;


    public GameScreen(Game game) {
        super(game);
    }

    @Override
    public void update(float deltaTime) {
        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();
        game.getInput().getKeyEvents();

        if (state == GameState.Ready) {
            //Log.d("GameScreen0", "If condition true " + touchEvents.size());
            updateReady(touchEvents);
        }

        if (state == GameState.Running) {
            updateRunning(touchEvents, deltaTime);
        }

        if (state == GameState.Paused) {
            updatePaused(touchEvents);
        }

        if (state == GameState.GameOver) {
            updateGameOver(touchEvents);
        }
    }

    private void updateGameOver(List<Input.TouchEvent> touchEvents) {
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            Input.TouchEvent event = touchEvents.get(i);
            if(event.type == Input.TouchEvent.TOUCH_UP) {
                Log.d("GAMESCREEN", "Game Over called");
            }
        }
    }

    private void updatePaused(List<Input.TouchEvent> touchEvents) {
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            Input.TouchEvent event = touchEvents.get(i);
            if(event.type == Input.TouchEvent.TOUCH_UP) {
                Log.d("GAMESCREEN", "Game Over called");
            }
        }
    }

    private void updateReady(List<Input.TouchEvent> touchEvents) {
        Log.d("GameScreen2", "Changing state to running");
        if (touchEvents.size() > 0) {
            state = GameState.Running;
        }
    }

    @Override
    public void present(float deltaTime) {
        Graphics g = game.getGraphics();
        g.drawPixmap(Assets.background, 0, 0);

        if (state == GameState.Ready) {
            drawReadyUI();
        }
        if (state == GameState.Running) {
            Log.d("GameScreen", "if loop ui running");
            drawRunningUI();
        }
        if (state == GameState.Paused) {
            drawPausedUI();
        }
        if (state == GameState.GameOver) {
            drawGameOverUI();
        }

    }

    private void drawGameOverUI() {
        Graphics g = game.getGraphics();


    }


    private void drawPausedUI() {
        Graphics g = game.getGraphics();


    }

    private void drawRunningUI() {
        Graphics g = game.getGraphics();

    }


    private void drawReadyUI() {
        Graphics g = game.getGraphics();

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
