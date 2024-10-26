package com.birds.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
//add play button, restart, and menu button
public class PauseScreen implements Screen {
    private AngryBirds1 game;
    private Stage stage;
    private Texture background;
    private int level;
    private Texture resume;
    private Texture restart;
    private Texture menu;
    private Texture message;
    public PauseScreen(AngryBirds1 game,int level){
        this.game = game;
        this.level = level;
        stage =  new Stage(new StretchViewport(1920,1080));
        background = new Texture("background.jpg");
        resume = new Texture("resume.png");
        restart = new Texture("retry.png");
        menu = new Texture("menu.png");
        message = new Texture("paused.png");
    }



    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        stage.act();
        stage.draw();

        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

        stage.getBatch().begin();

        stage.getBatch().draw(background, 0, 0, stage.getWidth(), stage.getHeight());
        stage.getBatch().draw(resume, 700, 280, 150, 150);
        stage.getBatch().draw(restart, 900, 280, 150, 150);
        stage.getBatch().draw(menu, 1100, 280, 150, 150);
        stage.getBatch().draw(message, 500, 600,900 ,200 );
        stage.getBatch().end();

        double ratioX = Gdx.graphics.getWidth() / 1920.0;
        double ratioY = Gdx.graphics.getHeight() / 1080.0;
        int x = Gdx.input.getX();
        int y = Gdx.graphics.getHeight() - Gdx.input.getY();
        //If the resume button is clicked, the game will go back to the GameScreen
        if (x > 700*ratioX && x < 850*ratioX && y > 280*ratioY  && y < 430*ratioY){
            if (Gdx.input.justTouched()){
                System.out.println("Resume");
                game.setScreen(new GameScreen(game,level));
            }
        }
//        // If screen is clicked it will show the coordinates of the click and the clickable regions of the screen also gdxgraphic width and height
//        if (Gdx.input.justTouched()){
//            System.out.println("X: " + x + " Y: " + y);
//            System.out.println("Gdx.graphics - Width: " + Gdx.graphics.getWidth() + ", Height: " + Gdx.graphics.getHeight());
//            System.out.println("Resume: " + 700*ratioX + " " + 850*ratioX + " " + 280*ratioY + " " + 430*ratioY);
//            System.out.println("Restart: " + 900*ratioX + " " + 1050*ratioX + " " + 280*ratioY + " " + 430*ratioY);
//            System.out.println("Menu: " + 1100*ratioX + " " + 1250*ratioX + " " + 280*ratioY + " " + 430*ratioY);
//
//        }

        //If the restart button is clicked, the game will restart the current level
        if (x > 900*ratioX && x < 1050*ratioX && y > 280*ratioY  && y < 430*ratioY){
            if (Gdx.input.justTouched()){
                System.out.println("Restart");
                game.setScreen(new GameScreen(game,level));
            }
        }
        //If the menu button is clicked, the game will go back to the LevelSelectScreen
        if (x > 1100*ratioX && x < 1250*ratioX && y >280*ratioY  && y < 430*ratioY){
            if (Gdx.input.justTouched()){
                System.out.println("Menu");
                game.setScreen(new LevelSelectScreen(game));
            }
        }
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
        background.dispose();
        resume.dispose();
        restart.dispose();
        menu.dispose();

    }
}
