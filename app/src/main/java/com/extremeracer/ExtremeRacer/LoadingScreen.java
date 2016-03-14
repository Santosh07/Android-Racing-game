package com.extremeracer.ExtremeRacer;

import com.extremeracer.framework.Game;
import com.extremeracer.framework.Graphics;
import com.extremeracer.framework.Graphics.Pixmapformat;
import com.extremeracer.framework.Screen;

/**
 * Created by root on 6/29/15.
 */
public class LoadingScreen extends Screen {

    public LoadingScreen(Game game) {
        super(game);
    }

    @Override
    public void update(float deltaTime) {
        Assets.blackBackground = game.getGraphics().newPixmap("extremeracer/BlackBackground.png", Pixmapformat.RGB565);
        Assets.Background = game.getGraphics().newPixmap("extremeracer/Background.png", Pixmapformat.RGB565);
        Assets.leftButton = game.getGraphics().newPixmap("extremeracer/leftbutton.png", Pixmapformat.RGB565);
        Assets.rightButton = game.getGraphics().newPixmap("extremeracer/rightButton.png", Pixmapformat.RGB565);
        Assets.turbobutton = game.getGraphics().newPixmap("extremeracer/turbo.png", Pixmapformat.RGB565);
        Assets.brakeButton = game.getGraphics().newPixmap("extremeracer/BrakeButton.png", Pixmapformat.RGB565);
        Assets.bike = game.getGraphics().newPixmap("extremeracer/bike.png", Pixmapformat.RGB565);
        Assets.shootButton = game.getGraphics().newPixmap("extremeracer/shoot.png", Pixmapformat.RGB565);
        Assets.car1 = game.getGraphics().newPixmap("extremeracer/car1.png", Pixmapformat.RGB565);
        Assets.car2 = game.getGraphics().newPixmap("extremeracer/car2.png", Pixmapformat.RGB565);
        Assets.car3 = game.getGraphics().newPixmap("extremeracer/car3.png", Pixmapformat.RGB565);
        Assets.loadingScreen = game.getGraphics().newPixmap("extremeracer/loadingScreenRed.png", Pixmapformat.RGB565);
        Assets.playButton = game.getGraphics().newPixmap("extremeracer/playButton2.png", Pixmapformat.RGB565);
        Assets.helpButton = game.getGraphics().newPixmap("extremeracer/helpButton.png", Pixmapformat.RGB565);
        Assets.helpMenu = game.getGraphics().newPixmap("extremeracer/helpMenu.png", Pixmapformat.RGB565);
        Assets.singlePlayerButton = game.getGraphics().newPixmap("extremeracer/singlePlayerButton.png", Pixmapformat.RGB565);
        Assets.multiPlayerButton = game.getGraphics().newPixmap("extremeracer/multiPlayerButton.png", Pixmapformat.RGB565);
        Assets.backButton = game.getGraphics().newPixmap("extremeracer/backButton.png", Pixmapformat.RGB565);
        Assets.mainMenuBackground = game.getGraphics().newPixmap("extremeracer/homeScreenBackground.jpg", Pixmapformat.RGB565);
        Assets.modeSelectBackground = game.getGraphics().newPixmap("extremeracer/modeSelectBackground.jpg", Pixmapformat.RGB565);



        game.setScreen(new SplashScreen(game));
        //game.setScreen(new Game_level2(game));
        //game.setScreen(new Racer(game));
    }

    @Override
    public void present(float deltaTime) {
        Graphics g = game.getGraphics();

        while (deltaTime > 3) {
            g.drawPixmap(Assets.loadingScreen, 0, 0);
        }


    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
