package com.extremeracer.framework.impl;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.SoundPool;
import android.util.Log;

import com.extremeracer.framework.Sound;

/**
 * Created by root on 6/24/15.
 */
public class AndroidSound implements Sound {
    SoundPool soundPool;
    int soundId;

    public AndroidSound(SoundPool soundPool, int soundId) {
        Log.d("ANDROIDSOUND", "INITIALIZING");
        this.soundPool = soundPool;
        this.soundId = soundId;
    }

    @Override
    public void play(float volume) {
        Log.d("ANDROIDSOUND", "PLAYING SOUND..");
        soundPool.play(soundId, volume, volume, 0, 0, 1);
    }

    @Override
    public void dispose() {
        soundPool.unload(soundId);
    }
}
