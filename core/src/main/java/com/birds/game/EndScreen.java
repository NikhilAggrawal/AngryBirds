package com.birds.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class EndScreen implements Screen {
    private AngryBirds1 game;
    private Stage stage;
    private int level = 0;
    private int state = 0;
    private Texture background;
    private Texture message;
    private Texture menu;
    private Texture retry;
    private Texture nextlevel;
    public EndScreen(AngryBirds1 game,int level,int state){
        this.game = game;
        this.level = level;
        this.state = state;
        stage =  new Stage(new StretchViewport(1920,1080));
        background = new Texture("background.jpg");
        menu = new Texture("menu.png");
        retry = new Texture("retry.png");
        nextlevel = new Texture("nextlevel.png");

//        message = new Texture("lose.jpg");
    }
    @Override
    public void show() {
        if (state == 1){
            message = new Texture("win.png");
        }else{
            message = new Texture("lose.png");
        }

    }

    @Override
    public void render(float v) {
        stage.act();
        stage.draw();
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        stage.getBatch().begin();

        stage.getBatch().draw(background, 0, 0, stage.getWidth(), stage.getHeight());
        stage.getBatch().draw(message, 480, 600,1000 ,150 );
        if (state == 1){
            stage.getBatch().draw(menu, 650, 300,150 ,150 );
            stage.getBatch().draw(retry, 900, 300, 150 ,150 );
            stage.getBatch().draw(nextlevel, 1150, 300, 150 ,150 );

        }else{
            stage.getBatch().draw(menu, 800, 300,150 ,150 );
            stage.getBatch().draw(retry, 1000, 300, 150 ,150 );
        }
        stage.getBatch().end();

        //Go to LevelSelectScreen if just touched on the menu button
        double ratioX = Gdx.graphics.getWidth() / 1920.0;
        double ratioY = Gdx.graphics.getHeight() / 1080.0;
        int x = Gdx.input.getX();
        int y = Gdx.graphics.getHeight() - Gdx.input.getY();
        if(state == 1){
            if (Gdx.input.justTouched()) {
                System.out.println("x: " + x + " y: " + y);
                //clickable regions in this screen
                System.out.println("Menu: " + 650 * ratioX + " " + 800 * ratioX + " " + 300 * ratioY + " " + 450 * ratioY);
                System.out.println("Retry: " + 900 * ratioX + " " + 1050 * ratioX + " " + 300 * ratioY + " " + 450 * ratioY);
                System.out.println("Next Level: " + 1150 * ratioX + " " + 1300 * ratioX + " " + 300 * ratioY + " " + 450 * ratioY);

                if (x > 650 * ratioX && x < 800 * ratioX && y > 300 * ratioY && y < 450 * ratioY) {
                    System.out.println("Menu");
                    game.setScreen(new LevelSelectScreen(game));

                }
                if (x > 900 * ratioX && x < 1050 * ratioX && y > 300 * ratioY && y < 450 * ratioY) {
                    game.setScreen(new GameScreen(game, level));

                }
                //Go to GameScreen if just touched on the next level button
                if (x > 1150 * ratioX && x < 1300 * ratioX && y > 300 * ratioY && y < 450 * ratioY) {
                    game.setScreen(new GameScreen(game, level + 1));
                }
            }
        }else {
            if (Gdx.input.justTouched()) {
                System.out.println("x: " + x + " y: " + y);
                //clickable regions in this screen
                System.out.println("Menu: " + 800 * ratioX + " " + 950 * ratioX + " " + 300 * ratioY + " " + 450 * ratioY);
                System.out.println("Retry: " + 1000 * ratioX + " " + 1150 * ratioX + " " + 300 * ratioY + " " + 450 * ratioY);

                if (x > 800 * ratioX && x < 900 * ratioX && y > 300 * ratioY && y < 450 * ratioY) {
                    game.setScreen(new LevelSelectScreen(game));
                }
                //Go to GameScreen if just touched on the retry button
                if (x > 1000 * ratioX && x < 1100 * ratioX && y > 300 * ratioY && y < 450 * ratioY) {
                    game.setScreen(new GameScreen(game, level));
                }
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
        message.dispose();
        menu.dispose();
        retry.dispose();
        nextlevel.dispose();


    }
}
