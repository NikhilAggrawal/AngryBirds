package com.birds.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class HomeScreen implements Screen {
    private AngryBirds1 game;
    private Stage stage;
    private Texture backgroundTexture;
    private Texture groundTexture;
    private Texture playButtonTexture;
    public HomeScreen(AngryBirds1 game) {
        this.game = game;
        stage = new Stage(new FitViewport(1920, 1080));
        Gdx.input.setInputProcessor(stage);
//        stage.setDebugAll(true);

        // Load textures
        backgroundTexture = new Texture("background.jpg");
        playButtonTexture = new Texture("playButton.png");


        // Create and add background and ground images
        Image backgroundImage = new Image(backgroundTexture);
        backgroundImage.setSize(stage.getWidth(), stage.getHeight());

        stage.addActor(backgroundImage);

        // Create the play button
        Button playButton = new Button(new TextureRegionDrawable(new TextureRegion(playButtonTexture)));
        playButton.setPosition(800, 90);
        playButton.setSize(250, 150);
        playButton.setTouchable(Touchable.enabled);
        playButton.setOrigin(playButton.getWidth() / 2, playButton.getHeight() / 2);
        playButton.setTransform(true);
//        playButton.addAction(Actions.scaleTo(6.9f, 1.9f));

        // Add hover and click effects
        playButton.addListener(new ClickListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                System.out.println("Mouse entered");
                playButton.addAction(Actions.scaleTo(1.2f, 1.2f, 0.1f));
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                System.out.println("Mouse exited");
                playButton.addAction(Actions.scaleTo(1.0f, 1.0f, 0.1f));
            }

            @Override
            public void clicked(InputEvent event, float x, float y) {
                playButton.addAction(Actions.sequence(
                    Actions.scaleTo(0.9f, 0.9f, 0.05f),
                    Actions.scaleTo(1f, 1f, 0.05f)
                ));
                game.setScreen(new LevelSelectScreen(game));
            }
        });

        stage.addActor(playButton);

        // Schedule a task to change the font color every second
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
            }
        }, 0, 1f);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        stage.act(delta);
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
        backgroundTexture.dispose();
        playButtonTexture.dispose();
    }
}
