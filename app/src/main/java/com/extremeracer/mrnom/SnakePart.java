package com.extremeracer.mrnom;

import android.util.Log;

/**
 * Created by root on 6/28/15.
 */
public class SnakePart {
    int x, y;

    public SnakePart(int x, int y) {
        Log.d("SNAKEPART", "Snake Part Initialized");
        this.x = x;
        this.y = y;
    }
}
