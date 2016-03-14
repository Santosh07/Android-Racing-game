package com.extremeracer.mrnom;

import android.graphics.Color;
import android.util.Log;

import com.extremeracer.framework.Game;
import com.extremeracer.framework.Graphics;
import com.extremeracer.framework.Input;
import com.extremeracer.framework.Input.TouchEvent;
import com.extremeracer.framework.Pixmap;
import com.extremeracer.framework.Screen;

import java.util.List;

/**
 * Created by root on 6/28/15.
 */
public class GameScreen extends Screen {
    enum GameState {
        Ready,
        Running,
        Paused,
        GameOver
    }

    GameState state = GameState.Ready;

    World world;
    int oldScore = 0;
    String score =  "0";

    public GameScreen(Game game) {
        super(game);
        Log.d("GameScreen", "Game Screen Called");
        world = new World();
    }

    @Override
    public void update(float deltaTime) {
        //Log.d("GameScreen", "GameScreen UPDATE called " + state);
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
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

    private void updateGameOver(List<TouchEvent> touchEvents) {
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if(event.type == TouchEvent.TOUCH_UP) {
                if(event.x >= 128 && event.x <= 192 &&
                        event.y >= 200 && event.y <= 264) {
                    if(Settings.soundEnabled)
                        Assets.click.play(1);
                    game.setScreen(new MainMenuScreen(game));
                    return;
                }
            }
        }
    }

    private void updatePaused(List<TouchEvent> touchEvents) {
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if(event.type == TouchEvent.TOUCH_UP) {
                if(event.x > 80 && event.x <= 240) {
                    if(event.y > 100 && event.y <= 148) {
                        if(Settings.soundEnabled)
                            Assets.click.play(1);
                        state = GameState.Running;
                        return;
                    }
                    if(event.y > 148 && event.y < 196) {
                        if(Settings.soundEnabled)
                            Assets.click.play(1);
                        game.setScreen(new MainMenuScreen(game));
                        return;
                    }
                }
            }
        }
    }

    private void updateRunning(List<TouchEvent> touchEvents, float deltaTime) {
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent touchEvent = touchEvents.get(i);
            if (touchEvent.type == TouchEvent.TOUCH_UP) {
                if (touchEvent.x < 64 && touchEvent.y < 64) {
                    state = GameState.Paused;
                }
            }
            if (touchEvent.type == TouchEvent.TOUCH_DOWN) {
                if (touchEvent.x < 64 && touchEvent.y > 416) {
                    world.snake.turnLeft();
                }
            }

            if (touchEvent.type == TouchEvent.TOUCH_DOWN) {
                if (touchEvent.x > 280 && touchEvent.y > 416) {
                    world.snake.turnRight();
                }
            }
        }

        world.update(deltaTime);
        if (world.gameOver) {
            if (Settings.soundEnabled) {
                Assets.bitten.play(1);
            }
            state = GameState.GameOver;
        }

        if (oldScore != world.score) {
            oldScore = world.score;
            score = " " + oldScore;
            if (Settings.soundEnabled) {
                Assets.eat.play(1);
            }
        }
    }

    private void updateReady(List<TouchEvent> touchEvents) {
        Log.d("GameScreen2", "Changing state to running");
        if (touchEvents.size() > 0) {
            state = GameState.Running;
        }
    }

    @Override
    public void present(float deltaTime) {

        Graphics g = game.getGraphics();

        g.drawPixmap(Assets.background, 0, 0);
        drawWorld(world);
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

        drawText(g, score, g.getWidth() / 2 - score.length() * 20 / 2, g.getHeight() - 42 );
    }


    private void drawText(Graphics g, String line, int x, int y) {
        int len = line.length();
        for (int i = 0; i < len; i++) {
            char character = line.charAt(i);
            if (character == ' ') {
                x += 20;
                continue;
            }
            int srcX = 0;
            int srcWidth = 0;
            if (character == '.') {
                srcX = 200;
                srcWidth = 10;
            } else {
                srcX = (character - '0') * 20;
                srcWidth = 20;
            }
            g.drawPixmap(Assets.numbers, x, y, srcX, 0, srcWidth, 32);
            x += srcWidth;
        }
    }

    private void drawGameOverUI() {
        Graphics g = game.getGraphics();
        g.drawPixmap(Assets.gameOver, 62, 100);
        g.drawPixmap(Assets.buttons, 128, 200, 0, 128, 64, 64);
        g.drawLine(0, 416, 480, 416, Color.BLACK);
    }

    private void drawPausedUI() {
        Graphics g = game.getGraphics();
        g.drawPixmap(Assets.pause, 80, 100);
        g.drawLine(0, 416, 480, 416, Color.BLACK);

    }

    private void drawRunningUI() {
        Graphics g = game.getGraphics();

        Log.d("GameScreen", "Drawing UI");
        g.drawPixmap(Assets.buttons, 0, 0, 64, 128, 64, 64);
        g.drawLine(0, 416, 480, 416, Color.BLACK);
        g.drawPixmap(Assets.buttons, 0, 416, 64, 64, 64, 64);
        g.drawPixmap(Assets.buttons, 256, 416, 0, 64, 64, 64);

    }

    private void drawReadyUI() {
        Graphics g = game.getGraphics();

        g.drawPixmap(Assets.ready, 47, 100);
        g.drawLine(0, 416, 480, 416, Color.BLACK);
    }

    private void drawWorld(World world) {
        Graphics g = game.getGraphics();
        Snake snake = world.snake;
        Stains stain = world.stain;

        Pixmap stainPixmap = null;
        if (world.stain.TYPE_1 == 1) {
            stainPixmap = Assets.stain1;
        } else if (world.stain.TYPE_2 == 2) {
            stainPixmap = Assets.stain2;
        } else if (world.stain.TYPE_3 == 3) {
            stainPixmap = Assets.stain3;
        }
        int x = stain.x * 32;
        int y = stain.y * 32;
        g.drawPixmap(stainPixmap, x, y);

        int len = snake.parts.size();
        for (int i = 1; i < len; i++ ) {
            SnakePart part = snake.parts.get(i);
            x = part.x * 32;
            y = part.y * 32;
            g.drawPixmap(Assets.tail, x, y);
        }

        Pixmap headPixmap = null;
        SnakePart head = snake.parts.get(0);
        if (snake.direction == snake.UP) {
            headPixmap = Assets.headUp;
        } if (snake.direction == snake.DOWN) {
            headPixmap = Assets.headDown;
        } if (snake.direction == snake.LEFT) {
            headPixmap = Assets.headLeft;
        } if (snake.direction == snake.RIGHT) {
            headPixmap = Assets.headRight;
        }
        x = head.x * 32 + 16;
        y = head.y * 32 + 16;
        g.drawPixmap(headPixmap, x - headPixmap.getWidth() / 2, y - headPixmap.getHeight() / 2);
    }

    @Override
    public void pause() {
        if (state == GameState.Running) {
            state = GameState.Paused;
        }
        if (world.gameOver) {
            Settings.addScore(world.score);
            Settings.save(game.getFileIO());
        }
    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
