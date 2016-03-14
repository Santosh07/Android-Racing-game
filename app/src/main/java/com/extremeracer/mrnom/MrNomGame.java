package com.extremeracer.mrnom;


import com.extremeracer.framework.Screen;
import com.extremeracer.framework.impl.AndroidGame;

public class MrNomGame extends AndroidGame {
    @Override
    public Screen getStartScreen() {
        return new LoadingScreen(this);
    }
}
