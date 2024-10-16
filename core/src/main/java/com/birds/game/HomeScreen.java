package com.birds.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class HomeScreen implements Screen {
    private AngryBirds1 game;

    private Texture background;
    private Texture theme;
    private Texture playButton;
    private Texture exitButton;

    public HomeScreen(AngryBirds1 game) {
        this.game = game;
        background = new Texture("background.jpg");
        theme = new Texture("theme.png");
        playButton = new Texture("playButton.png");
        exitButton = new Texture("quit.png");
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        game.batch.begin();

        game.batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        game.batch.draw(theme, 640, 250, 640, 480);
        game.batch.draw(playButton, 780, 280, 350, 180);
        game.batch.draw(exitButton, 890, 80, 150, 150);

        game.batch.end();
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
        background.dispose();
        theme.dispose();
        playButton.dispose();
        exitButton.dispose();
    }
}
