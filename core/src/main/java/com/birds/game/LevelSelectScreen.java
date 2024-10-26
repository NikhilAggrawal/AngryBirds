package com.birds.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class LevelSelectScreen implements Screen {
    private AngryBirds1 game;
    private Stage stage;
    private Texture background;
    private Texture level1;
    private Texture level2;
    private Texture level3;
    private Texture level4;
    private Texture level5;

    public LevelSelectScreen(AngryBirds1 game) {
        this.game = game;
        stage = new Stage(new StretchViewport(1920, 1080));
        background = new Texture("background.jpg");
        level1 = new Texture("l1.jpg");
        level2 = new Texture("l2.jpg");
        level3 = new Texture("l3.jpg");
        level4 = new Texture("lockedlevel.jpg");
        level5 = new Texture("lockedlevel.jpg");
    }

    @Override
    public void show() {}

    @Override
    public void render(float v) {
        stage.act();
        stage.draw();
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        stage.getBatch().begin();

        stage.getBatch().draw(background, 0, 0, stage.getWidth(), stage.getHeight());
        stage.getBatch().draw(level1, 300, 800, 100, 100);
        stage.getBatch().draw(level2, 450, 800, 100, 100);
        stage.getBatch().draw(level3, 600, 800, 100, 100);
        stage.getBatch().draw(level4, 750, 800, 100, 100);
        stage.getBatch().draw(level5, 900, 800, 100, 100);
        stage.getBatch().end();

        double ratioX = Gdx.graphics.getWidth() / 1920.0;
        double ratioY = Gdx.graphics.getHeight() / 1080.0;
        int x = Gdx.input.getX();
        int y = Gdx.input.getY();
        int height = Gdx.graphics.getHeight();

        if (x > 300 * ratioX && x < 400 * ratioX && y < height - 800 * ratioY && y > height - 900 * ratioY) {
            if (Gdx.input.justTouched()) {
                game.setScreen(new GameScreen(game, 1));
            }
        }
        if (x > 450 * ratioX && x < 550 * ratioX && y < height - 800 * ratioY && y > height - 900 * ratioY) {
            if (Gdx.input.justTouched()) {
                game.setScreen(new GameScreen(game, 2));
            }
        }
        if (x > 600 * ratioX && x < 700 * ratioX && y < height - 800 * ratioY && y > height - 900 * ratioY) {
            if (Gdx.input.justTouched()) {
                game.setScreen(new GameScreen(game, 3));
            }
        }
    }

    @Override
    public void resize(int i, int i1) {}

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
        level1.dispose();
        level2.dispose();
        level3.dispose();
        level4.dispose();
        level5.dispose();
    }
}
