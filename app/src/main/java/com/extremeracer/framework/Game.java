package com.extremeracer.framework;

public interface Game {
    public Input getInput();
    public FileIO getFileIO();
    public Audio getAudio();
    public Graphics getGraphics();
    public void setScreen(Screen screen);
    public Screen getCurrentScreen();
    public Screen getStartScreen();
}
