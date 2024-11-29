package com.birds.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import java.util.ArrayList;
import java.util.List;

public class LevelSelectScreen implements Screen {
    private AngryBirds1 game;
    private Stage stage;
    private Texture background;
    List<Texture> levelTextures;
    private Texture savedGame;

    public LevelSelectScreen(AngryBirds1 game) {
        this.game = game;
        stage = new Stage(new StretchViewport(1920, 1080));
        background = new Texture("background.jpg");
        levelTextures = new ArrayList<>();
        savedGame = new Texture("savedlevel.png");
        //depending on if the level is locked or unlocked, the texture will be different
        for (Level level : LevelManager.levels) {
            if (level.getLocked()) {
                levelTextures.add(new Texture("lockedlevel.jpg"));
            } else {
                levelTextures.add(new Texture("l" + level.getLevelNumber() + ".jpg"));
            }
        }

        // Create and add background image
        Image backgroundImage = new Image(background);
        backgroundImage.setSize(stage.getWidth(), stage.getHeight());
        stage.addActor(backgroundImage);

        // Create level buttons
        createLevelButton(levelTextures.get(0), 300, 800, 1);
        createLevelButton(levelTextures.get(1), 450, 800, 2);
        createLevelButton(levelTextures.get(2), 600, 800, 3);
        createLevelButton(savedGame, 750, 200, 99);
    }

    public void startLevel(int levelNumber) {
        if (levelNumber == 99) {
            System.out.println("Level 99 is a saved game. Implement loading saved game.");
            // if file savedlevel.txt is empty or does not exist, then there is no saved game
            if (AngryBirds1.isnotsaved()){
                System.out.println("This is happening because there is no saved level.");
                // Close the creating of game screen and go back to the level select screen
                return;
            };
            GameScreen gameScreen = new GameScreen(game, levelNumber);
            game.setScreen(gameScreen);
            return;
        }

        Level level = AngryBirds1.levelManager.getLevel(levelNumber);
        if (level != null && !level.getLocked()) {
            game.setScreen(new GameScreen(game, levelNumber));
        } else {
            System.out.println("Level " + levelNumber + " is locked. Complete the previous level first.");
        }
    }

    private void createLevelButton(Texture texture, float x, float y, final int level) {
        Button button = new Button(new TextureRegionDrawable(new TextureRegion(texture)));
        button.setPosition(x, y);
        button.setSize(100, 100);
        if (level == 99) {
            button.setSize(300, 100);
        }
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
                // Start the game screen with the selected level if level is unlocked
                startLevel(level);
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, com.badlogic.gdx.scenes.scene2d.Actor fromActor) {
                button.addAction(Actions.scaleTo(1.1f, 1.1f, 0.1f));
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, com.badlogic.gdx.scenes.scene2d.Actor toActor) {
                button.addAction(Actions.scaleTo(1f, 1f, 0.1f));
            }
        });

        stage.addActor(button);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(1, 1, 1, 1);
        stage.act();
        stage.draw();
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
        for (Texture texture : levelTextures) {
            texture.dispose();
        }
    }
}
