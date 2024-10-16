package com.birds.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class HomeScreen implements Screen {
    private AngryBirds1 game;
    private Stage stage;
    private Texture background;
    private Texture theme;
    private Texture playButton;
    private Texture cursor;
    private Texture ground;
//    private Texture exitButton;

    public HomeScreen(AngryBirds1 game) {
        this.game = game;
        stage =  new Stage(new StretchViewport(1920,1080));
        background = new Texture("background.jpg");
        theme = new Texture("theme.png");
        playButton = new Texture("playButton.png");
        cursor = new Texture("lockedlevel.jpg");
        ground = new Texture("ground.png");
//        exitButton = new Texture("quit.png");
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        Gdx.input.setInputProcessor(stage);
        stage.act();
        stage.draw();
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        stage.getBatch().begin();

        stage.getBatch().draw(background, 0, 0, stage.getWidth(), stage.getHeight());
        stage.getBatch().draw(ground, 0, 0, stage.getWidth(), 330);
        stage.getBatch().draw(theme, 640, 250, 640, 480);
        stage.getBatch().draw(playButton, 780, 280, 350, 180);
        stage.getBatch().draw(cursor, 100, 100, 100, 100);
//        stage.getBatch().draw(exitButton, 890, 80, 150, 150);

        stage.getBatch().end();
        int x = Gdx.input.getX();
        int y = Gdx.input.getY();
        int height = Gdx.graphics.getHeight();

        if (Gdx.input.justTouched()){
            System.out.println(Gdx.input.getX() + " " + Gdx.input.getY());
//                game.setScreen(new GameScreen(game,1));
        }

        if (x > 780 && x < 1130 && y < height - 280  && y > height - 460){
            if (Gdx.input.justTouched()){
                game.setScreen(new LevelSelectScreen(game));
            }
        }


    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        stage.dispose();
        background.dispose();
        theme.dispose();
        playButton.dispose();
//        exitButton.dispose();
    }
}
