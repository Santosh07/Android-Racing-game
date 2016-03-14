package com.extremeracer.framework.impl;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.util.Log;

import com.extremeracer.framework.Audio;
import com.extremeracer.framework.Music;
import com.extremeracer.framework.Sound;

import java.io.IOException;

/**
 * Created by root on 6/24/15.
 */
public class AndroidAudio implements Audio {
    AssetManager assetManager;
    AssetFileDescriptor descriptor;
    SoundPool soundPool;

    public AndroidAudio(Activity activity) {
        activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        assetManager = activity.getAssets();
        soundPool = new SoundPool(20, AudioManager.STREAM_MUSIC, 0);
    }

    @Override
    public Music newMusic(String fileName) {
        try {
            descriptor = assetManager.openFd(fileName);
            return new AndroidMusic(descriptor);
        } catch (IOException e) {
            throw new RuntimeException("Couldn't load music '" + fileName + "'");
        }
    }

    @TargetApi(Build.VERSION_CODES.CUPCAKE)
    @Override
    public Sound newSound(String fileName) {
        try {
            Log.d("ANDROIDAUDIO", "OPENING..");
            descriptor = assetManager.openFd(fileName);
            int soundId = soundPool.load(descriptor, 0);
            return new AndroidSound(soundPool, soundId);
        } catch (IOException e) {
            Log.d("ANDROIDAUDIO", "CANNOT OPEN SOUND FILE");
            throw new RuntimeException("Couldn't load Sound '" + fileName + "'");
        }
    }
}
