package com.extremeracer.ExtremeRacer;

import com.extremeracer.framework.Game;
import com.extremeracer.framework.Screen;
import com.extremeracer.framework.impl.AndroidGame;

/**
 * Created by root on 6/29/15.
 */
public class StartingClass extends AndroidGame {
    @Override
    public Screen getStartScreen() {
        return new LoadingScreen(this);
    }
}
