package com.extremeracer.framework;


public interface Music {
    public void play();
    public void pause();
    public void stop();
    public void setLooping(boolean looping);
    public void setVolume(float volume);
    public boolean isLooping();
    public boolean isPlaying();
    public boolean isStopped();
    public void dispose();
}
