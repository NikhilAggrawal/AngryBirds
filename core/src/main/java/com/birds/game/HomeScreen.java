package com.birds.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import static com.badlogic.gdx.utils.Align.center;

public class HomeScreen implements Screen {
    private AngryBirds1 game;
    private Stage stage;
    private Texture background;
    private Texture theme;
    private Texture playButton;
    private Texture ground;
//    private Texture exitButton;

    public HomeScreen(AngryBirds1 game) {
        this.game = game;
        stage =  new Stage(new FitViewport(1920,1080));
        background = new Texture("background.jpg");
        theme = new Texture("theme.png");
        playButton = new Texture("playButton.png");
        ground = new Texture("ground.png");
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
        double ratioX = Gdx.graphics.getWidth() / 1920.0;
        double ratioY = Gdx.graphics.getHeight() / 1080.0;
        stage.getBatch().draw(background, 0, 0, stage.getWidth(), stage.getHeight());
        stage.getBatch().draw(ground, 0, 0, stage.getWidth(), 330);
        stage.getBatch().draw(theme, 640, 250, 640, 480);
        stage.getBatch().draw(playButton, 780, 280, 350, 180);


        stage.getBatch().end();

        if (Gdx.input.justTouched()) {
            int x = Gdx.input.getX();
            int y = Gdx.graphics.getHeight() -  Gdx.input.getY();

            // If the play button is clicked, go to the level select screen
            if (x > 780*ratioX && x < (780 + 350)*ratioX &&
                y > 280*ratioY && y < (280 + 180)*ratioY) {
                System.out.println("Play button clicked");
                game.setScreen(new LevelSelectScreen(game));
            }
        }
    }


    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

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
