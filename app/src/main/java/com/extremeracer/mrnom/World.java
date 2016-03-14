package com.extremeracer.mrnom;

import android.util.Log;

import java.util.Random;

public class World {
    public static final int WORLD_WIDTH = 10;
    public static final int WORLD_HEIGHT = 13;
    public static final int SCORE_INCREMENT = 10;
    public static final float TICK_INITIAL = 0.5f;
    public static final float TICK_DECREAMENT = 0.05f;

    public Snake snake;
    public Stains stain;
    public boolean gameOver = false;
    public int score = 0;

    boolean fields[][] = new boolean[WORLD_WIDTH][WORLD_HEIGHT];
    Random random = new Random();
    float tickTime = 0;
    float tick = TICK_INITIAL;

    public World() {
        Log.d("WORLD", "World Initialized");
        snake = new Snake();
        placeStains();
    }

    private void placeStains() {
        for (int i = 0; i < WORLD_WIDTH ; i++) {
            for (int j = 0; j < WORLD_HEIGHT; j++) {
                fields[i][j] = false;
            }
        }

        int len = snake.parts.size();
        for (int i = 0; i < len ; i++) {
            SnakePart part = snake.parts.get(i);
            fields[part.x][part.y] = true;
        }

        int x = random.nextInt(WORLD_WIDTH);
        int y = random.nextInt(WORLD_HEIGHT);
        Log.d("World", "Checking for Error x =" + x + " y = " + y);
        while (true) {
            if (fields[x][y] == false) {
                break;
            }
            x++;
            if (x >= WORLD_WIDTH) {
                x = 0;
                y++;
                if (y >= WORLD_HEIGHT) {
                    y++;
                }
            }
        }
        stain = new Stains(x, y, random.nextInt(3));
    }

    public void update(float deltaTime) {
        if (gameOver) {
            return;
        }

        tickTime += deltaTime;
        Log.d("World1", "World update called tickTime =" + tickTime + " tick = " + tick);
        while (tickTime > tick) {
            Log.d("World2", "World while loop entered  tickTime = " + tickTime);
            tickTime -= tick;
            snake.advance();

            if (snake.checkBitten()) {
                gameOver = true;
            }
            Log.d("World3", "Not Bitten");
            SnakePart head = snake.parts.get(0);
            if (head.x == stain.x && head.y == stain.y) {
                score += SCORE_INCREMENT;
                snake.eat();
                if (fields.length > WORLD_HEIGHT * WORLD_WIDTH) {
                    gameOver = true;
                    break;
                } else {
                    placeStains();
                }
                if (score % 100 == 0 && tick - TICK_DECREAMENT > 0) {
                    tick -= TICK_DECREAMENT;
                }
            }
        }
        Log.d("World4", "Out of while loop");
    }
}
