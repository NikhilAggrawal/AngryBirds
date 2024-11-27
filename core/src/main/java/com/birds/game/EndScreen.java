package com.birds.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
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

public class EndScreen implements Screen {
    private AngryBirds1 game;
    private Level levelInstance;
    private BitmapFont font;
    private SpriteBatch batch;
    private Stage stage;
    private int levelNumber;
    private int state;
    private Texture background;
    private Texture message;
    private Texture menu;
    private Texture retry;
    private Texture nextlevel;

    public EndScreen(AngryBirds1 game, int levelNumber, int state, Level levelInstance) {
        this.game = game;
        this.levelNumber = levelNumber;
        this.levelInstance = levelInstance;
        font = new BitmapFont();
        font.getData().setScale(2);
        font.setColor(Color.BLACK);
        batch = new SpriteBatch();
        this.state = state;
        stage = new Stage(new StretchViewport(1920, 1080));
        background = new Texture("background.jpg");
        menu = new Texture("menu.png");
        retry = new Texture("retry.png");
        nextlevel = new Texture("nextlevel.png");

        Image backgroundImage = new Image(background);
        backgroundImage.setSize(stage.getWidth(), stage.getHeight());
        stage.addActor(backgroundImage);

        if (state == 1) {
            message = new Texture("win.png");
            createButton(retry, 700, 280, "restart");
            createButton(menu, 900, 280, "menu");
            createButton(nextlevel, 1100, 280, "nextlevel");
        } else {
            message = new Texture("lose.png");
            createButton(retry, 800, 280, "restart");
            createButton(menu, 1000, 280, "menu");
        }

        Image messageImage = new Image(message);
        messageImage.setSize(900, 200);
        messageImage.setPosition(500, 600);
        stage.addActor(messageImage);
    }

    private void createButton(Texture texture, float x, float y, final String action) {
        Button button = new Button(new TextureRegionDrawable(new TextureRegion(texture)));
        button.setPosition(x, y);
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
                    case "restart":
                        game.setScreen(new GameScreen(game, levelNumber));
                        break;
                    case "menu":
                        game.setScreen(new LevelSelectScreen(game));
                        break;
                    case "nextlevel":
                        if (levelNumber == LevelManager.levels.size()) {
                            game.setScreen(new LevelSelectScreen(game));
                            break;
                        }
                        game.setScreen(new GameScreen(game, levelNumber + 1));
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
        Level orglevel = AngryBirds1.levelManager.getLevel(levelNumber);
        System.out.println("Level: " + levelNumber + " State: " + state);
        if (state == 1) { // Win condition
            orglevel.setWin(true);
            AngryBirds1.levelManager.unlockLevel(levelNumber + 1);
        }
        AngryBirds1.levelManager.updateLevel(levelInstance);
    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        Level level = AngryBirds1.levelManager.getLevel(levelNumber);
        stage.act(v);
        stage.draw();
        batch.begin();
        font.draw(batch, "Best Score: " + level.getScore(), 500, 400); // Draw variable value
        font.draw(batch, "Current Score: " + levelInstance.getScore(), 900, 400); // Draw variable value
        batch.end();
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
        message.dispose();
        menu.dispose();
        retry.dispose();
        nextlevel.dispose();


    }
}
