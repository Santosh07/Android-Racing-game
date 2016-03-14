package com.extremeracer.ExtremeRacer;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

import com.extremeracer.framework.Game;
import com.extremeracer.framework.Graphics;
import com.extremeracer.framework.Input.TouchEvent;
import com.extremeracer.framework.Screen;
import com.extremeracer.framework.impl.AndroidFileIO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Racer extends Screen {

    public static final int BIKE_SPEED = -5;

    int centerX = 290;
    int centerY = 930;

    int index = 0;

    int y = 0;

    int position1, position2, position3, position4, position5, position6;
    boolean hasValue = false;
    boolean turbo = false;

    int speedX = 0;
    int speedY = BIKE_SPEED;
    int counter = 0;

    boolean isMovingLeft;
    boolean isMovingRight;

    ArrayList<Integer> list = new ArrayList<>();
    Rect[] rect = new Rect[7];
    ArrayList<Projectile> projectiles = new ArrayList<>();

    public Racer(Game game) {
        super(game);
        getleveldata();
    }

    private void getleveldata() {
        InputStream in = null;
        try {
            in = file.readAsset("extremeracer/Level1a.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String str = null;
            int len = 0;
            while ((str = reader.readLine()) != null) {
                if (str != "") {
                    list.add(Integer.parseInt(str));
                }
                //Log.d("RACER", "PRINTING DATA = " + str);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    AndroidFileIO file = (AndroidFileIO) game.getFileIO();

    @Override
    public void update(float deltaTime) {
        centerX += speedX;
        if (centerX < 150) {
            centerX = 150;
        } else if (centerX > 395) {
            centerX = 395;
        }

        for (int i = 0; i < projectiles.size(); i++) {
            Projectile p = (Projectile) projectiles.get(i);
            if (p.isVisible() == true) {
                p.update();
            }else {
                projectiles.remove(i);
            }
        }

        int size = list.size();

        if (y >= 1136) {
            //Log.d("RAXER", "inside loop counter = " + counter);
            counter++;
            y = 0;
            hasValue = false;
            //Log.d("RAXER", "After inside loop counter = " + counter);
        }

        //Log.d("RACER", "----------------> Updating position value. index = " + index + " hasValue = " + hasValue + " size = " + size + " y = " + y);
        if (!hasValue) {
            if ((index + 2) < size) {
                //Log.d("racer", "+++++++++++Inside if counter = " + counter);

                if (counter >= 1) {
                    position4 = position1;
                    position5 = position2;
                    position6 = position3;
                }

                position1 = list.get(index);
                position2 = list.get(index + 1);
                position3 = list.get(index + 2);
                //Log.d("RACER", "Positions = " + position1 + " " + position2 + " " + position3);
            }
            //Log.d("RACER", "---------------------UPdating index----------------");
            index += 3;
            hasValue = true;
        }

        rect[0] = new Rect(centerX + 50, centerY, centerX + 100, centerY + 150);
        //Log.d("RACER", "left top right bottom 0 = " + rect[0].left + " "  + rect[0].top + " " + rect[0].right + " " + rect[0].bottom);
        rect[1] = new Rect(200, position1 - 1136, 300, position1 - 936);
        //Log.d("RACER", "left top right bottom 1 = " + rect[1].left + " "  + rect[1].top + " " +rect[1].right + " "  + rect[1].bottom);
        rect[2] = new Rect(305, position2 - 1136, 405, position2 - 936);
        rect[3] = new Rect(415, position3 - 1136, 515, position3 - 936);

        rect[4] = new Rect(200, position4 - 1136, 300, position4 - 936);
        rect[5] = new Rect(305, position5 - 1136, 405, position5 - 936);
        rect[6] = new Rect(415, position6 - 1136, 515, position6 - 936);

        detectCollision();

        /*
        if (!hasValue) {
            counter++;

            boolean checkDo = true;

            for (int i = 0; i < 2; i++) {
                obstaclesCoordinate[i + 2][0] = obstaclesCoordinate[i][0];
                obstaclesCoordinate[i + 2][1] = obstaclesCoordinate[i][1];
                obstaclesCoordinate[i + 2][2] = obstaclesCoordinate[i][2];
            }

            do {
                boolean check = false;

                //do {

                    do {
                        value1 = obstaclesCoordinate[0][0] = rand.nextInt(1136);
                        if (counter > 1) {
                            rect[0] = new Rect(200, value1, 300, value1 + 200);
                            g.drawRect(200, value1, 100, 200, Color.BLACK);
                            check = rect[3].intersect(rect[0]);
                        }
                        //Log.d("RACER", "CHECK IN SECOND PART OF 1ST COLUMN = " + check);
                    } while (check);

                    do {
                        value2 = obstaclesCoordinate[0][1] = rand.nextInt(1136);
                        if (counter > 1) {
                            rect[1] = new Rect(305, value2, 405, value2 + 200);
                            g.drawRect(305, value2, 100, 200, Color.BLACK);
                            check = rect[4].intersect(rect[1]);
                        }
                        //Log.d("RACER", "CHECK IN SECOND PART OF 2nd COLUMN = " + check);
                    } while (check);

                    do {
                        value3 = obstaclesCoordinate[0][2] = rand.nextInt(1136);
                        if (counter > 1) {
                            rect[2] = new Rect(415, value3, 515, value3 + 200);
                            g.drawRect(415, value3, 100, 200, Color.BLACK);
                            check = rect[5].intersect(rect[2]);
                        }
                        //} while (obstaclesCoordinate[0][2] < 100 || obstaclesCoordinate[0][2] > 1036);
                        //Log.d("RACER", "CHECK IN SECOND PART OF 3rd COLUMN = " + check);
                    } while (check);
                    /*
                    int a = 0;
                    int b = 0;
                    int k = 0;
                    if (counter > 1) {
                        for (int i = 0; i < 2; i++) {
                            for (int j = 0; j < 2; j++) {
                                if (i < 2 && j < 2) {
                                    a = obstaclesCoordinate[i][j] - obstaclesCoordinate[i][j + 1];
                                    b = obstaclesCoordinate[i][j] - obstaclesCoordinate[i + 1][j + 1];
                                }
                                if (Math.abs(a) >= 360) {
                                    testDistance[k][0] = true;
                                } else {
                                    testDistance[k][0] = false;
                                }

                                if (Math.abs(b) >= 360) {
                                    testDistance[k][1] = true;
                                } else {
                                    testDistance[k][1] = false;
                                }
                                k++;
                            }
                        }


                        if (testDistance[0][0] == true && testDistance[0][1] == true && testDistance[2][0] == true && testDistance[2][1] == true) {
                            checkDo = false;
                        }

                        if (testDistance[1][0] == true && testDistance[1][1] == true && testDistance[3][0] == true && testDistance[3][1] == true) {
                            checkDo = false;
                        }
                    } else {
                        checkDo = false;
                    }*/

                //} while (checkDo);
                /*
                do {
                    value4 = obstaclesCoordinate[1][0] = rand.nextInt(1136);
                    rect[0] = new Rect(200, value1, 300, value1 + 200);
                    rect[3] = new Rect(200, value4, 300, value4 + 200);
                    g.drawRect(200, value4, 100 , 200, Color.BLACK);
                    check = rect[0].intersect(rect[3]);
                    Log.d("RACER", "CHECK IN FIRST PART OF 2nd COLUMN = " + check);
                }while (check);

                do {
                    value5 = obstaclesCoordinate[1][1] = rand.nextInt(1136);
                    rect[1] = new Rect(305, value2, 405, value2 + 200);
                    rect[4] = new Rect(305, value5, 405, value5 + 200);
                    g.drawRect(305, value5, 405, 200, Color.BLACK);
                    check = rect[1].intersect(rect[4]);
                    Log.d("RACER", "CHECK IN FIRST PART OF 2nd COLUMN = " + check);
                } while (check);

                do {
                    value6 = obstaclesCoordinate[1][2] = rand.nextInt(1136);
                    rect[2] = new Rect(415, value3, 515, value3 + 200);
                    rect[5] = new Rect(415, value6, 515, value6 + 200);
                    check = rect[2].intersect(rect[5]);
                    g.drawRect(415, value6, 100, 200, 0);
                    Log.d("RACER", "CHECK IN FIRST PART OF 2nd COLUMN = " + check);
                } while (check);



                int a = 0;
                int b = 0;
                int k = 0;
                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < 2; j++) {
                        if (i < 2 && j < 2) {
                            a = obstaclesCoordinate[i][j] - obstaclesCoordinate[i][j + 1];
                            b = obstaclesCoordinate[i][j] - obstaclesCoordinate[i + 1][j + 1];
                        }
                        if (Math.abs(a) >= 360) {
                            testDistance[k][0] = true;
                        } else {
                            testDistance[k][0] = false;
                        }

                        if (Math.abs(b) >= 360) {
                            testDistance[k][1] = true;
                        } else {
                            testDistance[k][1] = false;
                        }
                        k++;
                    }
                }

                if (testDistance[0][0] == true && testDistance[0][1] == true && testDistance[2][0] == true && testDistance[2][1] == true){
                    checkDo = false;
                }

                if (testDistance[1][0] == true && testDistance[1][1] == true && testDistance[3][0] == true && testDistance[3][1] == true){
                    checkDo = false;
                }
                //checkDo = false;
            } while (checkDo);
            hasValue = true;
        }

        if (y >= 1136) {
            y = 0;
            hasValue = false;
        }
        */

        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        game.getInput().getKeyEvents();

        Log.d("RACER", "NEW EVEnt genterated");
        for (int i = 0; i < touchEvents.size(); i++) {

            TouchEvent touchEvent = touchEvents.get(i);
            int id = touchEvent.pointer;
            if (touchEvent.type == TouchEvent.TOUCH_DOWN) {
                if (touchEvent.x < 140 && touchEvent.y > 1138) {
                    int j = 0;
                    int k = 0;
                    isMovingLeft = true;


                    speedX += -5;
                }

                if (touchEvent.x < 280 && touchEvent.x > 142 && touchEvent.y > 1138) {
                    Log.d("RACER2", "APPLYING BRAKE");
                }

                if (touchEvent.x < 420 && touchEvent.x > 282 && touchEvent.y > 1138) {
                    Projectile p = new Projectile(centerX, centerY);
                    projectiles.add(p);

                    //while (game.getInput().isTouchDown(id)) {
                    //    Log.d("RACER3", "INSIDE LOOP SHOOTING BULLETS");
                    //    speedX = -5;
                    //}
                }

                if (touchEvent.x < 560 && touchEvent.x > 422 && touchEvent.y > 1138) {
                    turbo = true;

                }
                if (touchEvent.x < 700 && touchEvent.x > 562 && touchEvent.y > 1138) {
                    Log.d("RACER5", "MOVING RIGHT");
                    isMovingRight = true;
                    speedX += 5;
                }
            }

            if (touchEvent.type == TouchEvent.TOUCH_UP) {
                if (touchEvent.x < 140 && touchEvent.y > 1138) {
                    isMovingLeft = false;
                    stop();

                }

                if (touchEvent.x < 700 && touchEvent.x > 562 && touchEvent.y > 1138) {
                    isMovingRight = false;
                    stop();
                }

                if (touchEvent.x < 560 && touchEvent.x > 422 && touchEvent.y > 1138) {
                    turbo = false;
                }
            }
        }
    }

    private void stop() {
        if (isMovingRight == false && isMovingLeft == false) {
            speedX = 0;
        } else if (isMovingLeft == true && isMovingRight == false) {
            speedX -= 5;
        } else if (isMovingLeft == false && isMovingRight == true) {
            speedX += 5;
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
        //Log.d("Present", "present called");
        Graphics g = game.getGraphics();

        setSpeed(5);

        g.drawPixmap(Assets.blackBackground, 0, 0);
        g.drawPixmap(Assets.Background, 0, y);
        g.drawPixmap(Assets.Background, 0, y - 1136);

        g.drawPixmap(Assets.bike, centerX, centerY);
        g.drawRect(rect[0].left, rect[0].top, (rect[0].right - rect[0].left), rect[0].bottom - rect[0].top, Color.BLACK);


        updateObstacles(5);

        drawObstacles(g);

        drawBullets(g);

        g.drawPixmap(Assets.leftButton, 0, 1138);
        g.drawPixmap(Assets.brakeButton, 142, 1138);
        g.drawPixmap(Assets.shootButton, 284, 1138);
        g.drawPixmap(Assets.turbobutton, 426, 1138);
        g.drawPixmap(Assets.rightButton, 568, 1138);
    }

    private void drawBullets(Graphics g) {

        for (int i = 0; i < projectiles.size(); i++) {
            Projectile p = (Projectile) projectiles.get(i);
            g.drawRect(p.getX() + 70, p.getY(), 5, 10, Color.YELLOW);
        }


    }

    private void drawObstacles(Graphics g) {
        //Log.d("RACER", "POSITIONS = " + position1 + " " + position2 + " " + position3 + " " + position4 + " " + position5 + " " + position6);

        if (counter < 1) {
            g.drawPixmap(Assets.car1, 200, position1 - 1136);
            g.drawPixmap(Assets.car2, 305, position2 - 1136);
            g.drawPixmap(Assets.car3, 415, position3 - 1136);
            g.drawRect(rect[1].left, rect[1].top, rect[1].right - rect[1].left, rect[1].bottom - rect[1].top, Color.BLACK);
            g.drawRect(rect[2].left, rect[2].top, rect[2].right - rect[2].left, rect[2].bottom - rect[2].top, Color.BLACK);
            g.drawRect(rect[3].left, rect[3].top, rect[3].right - rect[3].left, rect[3].bottom - rect[3].top, Color.BLACK);

        }

        if (counter >= 1) {
            g.drawPixmap(Assets.car1, 200, position4 - 1136);
            g.drawPixmap(Assets.car2, 305, position5 - 1136);
            g.drawPixmap(Assets.car3, 415, position6 - 1136);

            g.drawPixmap(Assets.car1, 200, position1 - 1136);
            g.drawPixmap(Assets.car2, 305, position2 - 1136);
            g.drawPixmap(Assets.car3, 415, position3 - 1136);

            g.drawRect(200, position1 - 1136, 100, 200, Color.BLACK);
            g.drawRect(305, position2 - 1136, 100, 200, Color.BLACK);
            g.drawRect(415, position3 - 1136, 100, 200, Color.BLACK);

            g.drawRect(200, position4 - 1136, 100, 200, Color.BLACK);
            g.drawRect(305, position5 - 1136, 100, 200, Color.BLACK);
            g.drawRect(415, position6 - 1136, 100, 200, Color.BLACK);
        }
    }

    private void updateObstacles(int speed) {
        int carSpeed =  speed;

        if (turbo == false) {
            position1 += speed;
            position2 += speed;
            position3 += speed;
            position4 += speed;
            position5 += speed;
            position6 += speed;
        } else {
            position1 += speed + 5;
            position2 += speed + 5;
            position3 += speed + 5;
            position4 += speed + 5;
            position5 += speed + 5;
            position6 += speed + 5;
        }
    }

    public void setSpeed(int speed) {
        if (turbo == false) {
            y += speed;
        } else {
            y += speed + 5;
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
