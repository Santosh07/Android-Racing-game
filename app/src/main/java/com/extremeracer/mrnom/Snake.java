package com.extremeracer.mrnom;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 6/28/15.
 */
public class Snake {
    public static final int UP = 1;
    public static final int LEFT = 2;
    public static final int DOWN = 3;
    public static final int RIGHT = 4;

    public List<SnakePart> parts = new ArrayList<SnakePart>();
    int direction;

    public Snake() {
        Log.d("SNAKE", "SNAKE INITIALIZED");
        direction = 1;
        parts.add(new SnakePart(5, 6));
        parts.add(new SnakePart(5, 7));
        parts.add(new SnakePart(5, 8));
    }

    public void turnLeft() {
        direction += 1;
        if (direction > RIGHT) {
            direction = 1;
        }
    }

    public void turnRight() {
        direction -= 1;
        if (direction < UP) {
            direction = 4;
        }
    }


    public void eat() {
        Log.d("Snake", "Snake ate and has a vudi");
        SnakePart part = parts.get(parts.size() - 1);
        parts.add(new SnakePart(part.x, part.y));
    }

    public void advance() {
        Log.d("Snake", "Snake is andvancing");
        SnakePart head = parts.get(0);

        int len = parts.size() - 1;

        for (int i = len; i > 0; i--) {
            SnakePart last = parts.get(i);
            SnakePart secondLast = parts.get(i - 1);
            last.x = secondLast.x;
            last.y = secondLast.y;
        }

        if (direction == UP) {
            head.y--;
        }
        if (direction == LEFT) {
            head.x--;
        }
        if (direction == RIGHT) {
            head.x++;
        }
        if (direction == DOWN) {
            head.y++;
        }

        if (head.x < 0) {
            head.x = 9;
        }
        if (head.x > 9) {
            head.x = 0;
        }
        if (head.y < 0) {
            head.y = 12;
        }
        if (head.y > 12) {
            head.y = 0;
        }
    }

    public boolean checkBitten() {
        SnakePart head = parts.get(0);

        for (int i = 1; i < parts.size() - 1; i++) {
            SnakePart part = parts.get(i);
            if (part.x == head.x && part.y == head.y) {
                return true;
            }
        }
        return false;
    }
}
