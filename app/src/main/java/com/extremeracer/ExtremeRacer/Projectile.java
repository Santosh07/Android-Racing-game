package com.extremeracer.ExtremeRacer;

/**
 * Created by root on 7/3/15.
 */
public class Projectile {
    private int x, y, speedX;
    private boolean visible;

    public Projectile(int startX, int startY){
        x = startX;
        y = startY;
        speedX = 10;
        visible = true;
    }

    public void update() {
        y -= speedX;
        if (y > 1230) {
            visible = false;
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSpeedX() {
        return speedX;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
