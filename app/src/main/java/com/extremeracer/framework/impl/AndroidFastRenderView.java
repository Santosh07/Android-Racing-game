package com.extremeracer.framework.impl;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class AndroidFastRenderView extends SurfaceView implements Runnable{
    AndroidGame game;
    Bitmap frameBuffer;
    Thread renderThread = null;
    SurfaceHolder holder;
    private volatile boolean running = false;

    public AndroidFastRenderView(AndroidGame game, Bitmap frameBuffer) {
        super(game);
        this.game = game;
        this.holder = getHolder();
        this.frameBuffer = frameBuffer;
    }

    public void resume() {
        running = true;
        renderThread = new Thread(this);
        renderThread.start();
    }

    public void pause() {
        running = false;
        while (true) {
            try {
                renderThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        //Log.d("ANDROIDFASTRENDERVIEW", "CREATING NEW THREAD");
        Rect dst = new Rect();
        long startTime = System.nanoTime();
        while(running) {
            if (!holder.getSurface().isValid()) {
                continue;
            }

            float deltaTime = (System.nanoTime() - startTime) / 1000000000.0f;
            startTime = System.nanoTime();

            //Log.d("RENDERVIEW", "DELTATIME" + deltaTime);
            game.getCurrentScreen().update(deltaTime);
            game.getCurrentScreen().present(deltaTime);


            Canvas canvas = holder.lockCanvas();
            canvas.getClipBounds(dst);
            canvas.drawBitmap(frameBuffer, null, dst, null);
            holder.unlockCanvasAndPost(canvas);
        }
    }
}
