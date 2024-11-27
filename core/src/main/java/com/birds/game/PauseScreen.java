package com.birds.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import java.io.*;

public class PauseScreen implements Screen {
    private AngryBirds1 game;
    private Stage stage;
    private Texture background;
    private Texture resume;
    private Texture restart;
    private Texture menu;
    private Texture message;
    private GameScreen gameScreen;

    public PauseScreen(AngryBirds1 game, GameScreen gameScreen) {
        this.game = game;
        this.gameScreen = gameScreen;
        stage = new Stage(new StretchViewport(1920, 1080));
        background = new Texture("background.jpg");
        resume = new Texture("resume.png");
        restart = new Texture("retry.png");
        menu = new Texture("menu.png");
        message = new Texture("paused.png");

        // Create and add background image
        Image backgroundImage = new Image(background);
        backgroundImage.setSize(stage.getWidth(), stage.getHeight());
        stage.addActor(backgroundImage);

        // Create buttons
        createButton(resume, 700, "resume");
        createButton(restart, 900, "restart");
        createButton(menu, 1100, "menu");

        // Add message image
        Image messageImage = new Image(message);
        messageImage.setPosition(500, 600);
        messageImage.setSize(900, 200);
        stage.addActor(messageImage);
    }

    private void createButton(Texture texture, float x, final String action) {
        Button button = new Button(new TextureRegionDrawable(new TextureRegion(texture)));
        button.setPosition(x, 280);
        button.setSize(150, 150);
        button.setTouchable(Touchable.enabled);
        button.setOrigin(button.getWidth() / 2, button.getHeight() / 2);
        button.setTransform(true);

        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                button.addAction(Actions.sequence(
                    Actions.scaleTo(0.9f, 0.9f, 0.05f),
                    Actions.scaleTo(1f, 1f, 0.05f)
                ));
                switch (action) {
                    case "resume":
                        game.setScreen(gameScreen);
                        gameScreen.togglePause(); // Resume the game
                        break;
                    case "restart":
                        game.setScreen(new GameScreen(game, gameScreen.level));
                        break;
                    case "menu":
                        game.saveLevel(gameScreen);
                        System.out.println("Game saved");
                        game.setScreen(new LevelSelectScreen(game));
                        break;
                }
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                button.addAction(Actions.scaleTo(1.2f, 1.2f, 0.1f)); // Increase size on hover
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                button.addAction(Actions.scaleTo(1.0f, 1.0f, 0.1f)); // Reset size when not hovering
            }
        });

        stage.addActor(button);
    }


    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        stage.act();
        stage.draw();
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
        resume.dispose();
        restart.dispose();
        menu.dispose();
        message.dispose();
    }
}
