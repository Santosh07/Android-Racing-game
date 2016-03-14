package com.extremeracer.Test;

import com.extremeracer.framework.Screen;
import com.extremeracer.framework.impl.AndroidGame;


public class NewGraphicsTest2 extends AndroidGame {
    @Override
    public Screen getStartScreen() {
        return new TestScreen(this);
    }
}
