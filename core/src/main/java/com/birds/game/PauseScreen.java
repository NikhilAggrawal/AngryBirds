package com.birds.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
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

        int x = Gdx.input.getX();
        int y = Gdx.input.getY();
        int height = Gdx.graphics.getHeight();
        //If the resume button is clicked, the game will go back to the GameScreen
        if (x > 700 && x < 850 && y < height - 280  && y > height - 430){
            if (Gdx.input.justTouched()){
                game.setScreen(new GameScreen(game,level));
            }
        }
        //If the restart button is clicked, the game will restart the current level
        if (x > 900 && x < 1050 && y < height - 280  && y > height - 430){
            if (Gdx.input.justTouched()){
                game.setScreen(new GameScreen(game,level));
            }
        }
        //If the menu button is clicked, the game will go back to the LevelSelectScreen
        if (x > 1100 && x < 1250 && y < height - 280  && y > height - 430){
            if (Gdx.input.justTouched()){
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
