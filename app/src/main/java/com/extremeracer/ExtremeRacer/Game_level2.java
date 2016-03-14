package com.extremeracer.ExtremeRacer;

import android.graphics.Rect;
import android.util.Log;

import com.extremeracer.framework.Game;
import com.extremeracer.framework.Graphics;
import com.extremeracer.framework.Input;
import com.extremeracer.framework.Screen;
import com.extremeracer.framework.impl.AndroidFileIO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 7/2/15.
 */

public class Game_level2 extends Screen {
    int centerX = 290;
    int centerY = 930;
    AndroidFileIO file = (AndroidFileIO) game.getFileIO();

    int speedX;

    Rect[] rect = new Rect[7];

    public Game_level2(Game game) {
        super(game);
        getleveldata();
    }

    private void getleveldata() {
        InputStream in = null;
        try {
            in = file.readAsset("extremeracer/Level 2");
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String str = null;
            int len = 0;
            while ((str = reader.readLine()) != null) {
                if (str != "") {
                    list.add(Integer.parseInt(str));
                }
                Log.d("RACER", "PRINTING DATA = " + str);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    ArrayList<Integer> list = new ArrayList<>();
    int y;
    int counter = 0;
    int index = 0;

    boolean hasValue = false;

    int position1, position2, position3, position4, position5, position6;

    @Override
    public void update(float deltaTime) {
        centerX += speedX;
        if (centerX < 150) {
            centerX = 150;
        } else if (centerX > 395) {
            centerX = 395;
        }

        int size = list.size();

        if (y >= 1136) {
            counter++;
            y = 0;
            hasValue = false;
        }
        if (!hasValue) {
            if ((index + 2) < size) {
                Log.d("racer", "+++++++++++Inside if counter = " + counter);

                if (counter >= 1) {
                    position4 = position1;
                    position5 = position2;
                    position6 = position3;
                }

                position1 = list.get(index);
                position2 = list.get(index + 1);
                position3 = list.get(index + 2);
                Log.d("RACER", "Positions = " + position1 + " " + position2 + " " + position3);
            }
            Log.d("RACER", "---------------------UPdating index----------------");
            index += 3;
            hasValue = true;
        }

        rect[0] = new Rect(centerX + 50, centerY, centerX + 100, centerY + 150);
        Log.d("RACER", "left top right bottom 0 = " + rect[0].left + " "  + rect[0].top + " " + rect[0].right + " " + rect[0].bottom);
        rect[1] = new Rect(200, position1 - 1136, 300, position1 - 936);
        Log.d("RACER", "left top right bottom 1 = " + rect[1].left + " "  + rect[1].top + " " +rect[1].right + " "  + rect[1].bottom);
        rect[2] = new Rect(305, position2 - 1136, 405, position2 - 936);
        rect[3] = new Rect(415, position3 - 1136, 515, position3 - 936);

        rect[4] = new Rect(200, position4 - 1136, 300, position4 - 936);
        rect[5] = new Rect(305, position5 - 1136, 405, position5 - 936);
        rect[6] = new Rect(415, position6 - 1136, 515, position6 - 936);

        detectCollision();

        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();
        game.getInput().getKeyEvents();

        for (int i = 0; i < touchEvents.size(); i++) {
            Input.TouchEvent touchEvent = touchEvents.get(i);
            int id = touchEvent.pointer;
            if (touchEvent.type == Input.TouchEvent.TOUCH_DOWN) {
                if (touchEvent.x < 140 && touchEvent.y > 1138) {
                    int j = 0;
                    int k = 0;

                    speedX += -5;

                }

                if (touchEvent.x < 280 && touchEvent.x > 142 && touchEvent.y > 1138) {
                    Log.d("RACER2", "APPLYING BRAKE");
                }

                if (touchEvent.x < 420 && touchEvent.x > 282 && touchEvent.y > 1138) {
                    Log.d("RACER3", "SHOOTING BULLETS");

                    //while (game.getInput().isTouchDown(id)) {
                    //    Log.d("RACER3", "INSIDE LOOP SHOOTING BULLETS");
                    //    speedX = -5;
                    //}
                }

                if (touchEvent.x < 560 && touchEvent.x > 422 && touchEvent.y > 1138) {
                    Log.d("RACER4", "TURBO MODE ACTIVATED");

                }
                if (touchEvent.x < 700 && touchEvent.x > 562 && touchEvent.y > 1138) {
                    Log.d("RACER5", "MOVING RIGHT");
                    speedX += 5;

                }
            }

            if (touchEvent.type == Input.TouchEvent.TOUCH_UP) {
                if (touchEvent.x < 140 && touchEvent.y > 1138) {
                    speedX = 0;
                }

                if (touchEvent.x < 700 && touchEvent.x > 562 && touchEvent.y > 1138) {
                    speedX = 0;
                }

            }
        }
    }

    private void detectCollision() {
        int len;
        if (counter > 1) {
            len = 6;
        } else {
            len = 3;

        }
        for (int i = 1;i <= len ; i++) {
            //Log.d("RACER", "DETECTING COLLISSION INSIDE FOR LOOP");

            if (rect[0].intersect(rect[i])) {
                Log.d("RACER", "COLLISSION DETECTED. GAME OVER");
            }
        }

    }

    @Override
    public void present(float deltaTime) {
        Graphics g = game.getGraphics();

        setSpeed(5);

        g.drawPixmap(Assets.blackBackground, 0, 0);
        g.drawPixmap(Assets.Background, 0, y);
        g.drawPixmap(Assets.Background, 0, y - 1136);

        g.drawPixmap(Assets.bike, centerX, centerY);


        updateObstacles(5);

        drawObstacles(g);

        g.drawPixmap(Assets.leftButton, 0, 1138);
        g.drawPixmap(Assets.brakeButton, 142, 1138);
        g.drawPixmap(Assets.shootButton, 284, 1138);
        g.drawPixmap(Assets.turbobutton, 426, 1138);
        g.drawPixmap(Assets.rightButton, 568, 1138);
    }

    private void setSpeed(int speed) {
        y += speed;
    }

    private void updateObstacles(int speed) {
        position1 += speed;
        position2 += speed;
        position3 += speed;
        position4 += speed;
        position5 += speed;
        position6 += speed;
    }

    private void drawObstacles(Graphics g) {
        if (counter < 1) {
            g.drawPixmap(Assets.car1, 200, position1 - 1136);
            g.drawPixmap(Assets.car2, 305, position2 - 1136);
            g.drawPixmap(Assets.car3, 415, position3 - 1136);
        }

        if (counter >= 1) {
            g.drawPixmap(Assets.car1, 200, position4 - 1136);
            g.drawPixmap(Assets.car2, 305, position5 - 1136);
            g.drawPixmap(Assets.car3, 415, position6 - 1136);

            g.drawPixmap(Assets.car1, 200, position1 - 1136);
            g.drawPixmap(Assets.car2, 305, position2 - 1136);
            g.drawPixmap(Assets.car3, 415, position3 - 1136);
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
