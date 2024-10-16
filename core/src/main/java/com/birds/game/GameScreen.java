package com.birds.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class GameScreen implements Screen {
    private AngryBirds1 game;
    private Stage stage;
    private Texture background;
    public int level;
    private Texture bird1;
    private Texture bird2;
    private Texture bird3;
    private Texture pig1;
    private Texture pig2;
    private Texture pig3;
    private Texture block1;
    private Texture block2;
    private Texture block3;
    private Texture slingshot;
    private Texture pause;
    private Texture ground;

    public GameScreen(AngryBirds1 game, int level){
        this.game = game;
        this.level = level;
        stage =  new Stage(new StretchViewport(1920,1080));
        background = new Texture("background.jpg");
        bird1 = new Texture("red.png");
        bird2 = new Texture("blue.png");
        bird3 = new Texture("bomb.png");
        pig1 = new Texture("smallpig.png");
        pig2 = new Texture("bigpig.png");
        pig3 = new Texture("Armorpig.png");
        block1 = new Texture("glassblock.jpg");
        block2 = new Texture("woodblock.png");
        block3 = new Texture("stoneblock.jpg");
        slingshot = new Texture("sling.png");
        pause = new Texture("pause.png");
        ground = new Texture("ground.png");
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float v) {
        //Area where pig and structure will be drawn will be from x = 1000 to 1920 and y = 330 to 800
        stage.act();
        stage.draw();
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        stage.getBatch().begin();

        stage.getBatch().draw(background, 0, 0, stage.getWidth(), stage.getHeight());
        stage.getBatch().draw(ground, 0, 0, stage.getWidth(), 330);
        stage.getBatch().draw(slingshot, 400, 330,100 ,200 );
        stage.getBatch().draw(bird1, 430, 500,50 ,50 );
        stage.getBatch().draw(bird2, 200, 330,50 ,50 );
        stage.getBatch().draw(bird3, 300, 330,50 ,50 );


        stage.getBatch().draw(block1, 1000, 330,50 ,50 );
        stage.getBatch().draw(block1, 1050, 330,50 ,50 );
        stage.getBatch().draw(block1, 1100, 330,50 ,50 );
        stage.getBatch().draw(block1, 1150, 330,50 ,50 );
        stage.getBatch().draw(block2, 1200, 330,50 ,50 );
        stage.getBatch().draw(block2, 1250, 330,50 ,50 );
        stage.getBatch().draw(block2, 1300, 330,50 ,50 );
        stage.getBatch().draw(block3, 1350, 330,50 ,50 );
        stage.getBatch().draw(block3, 1400, 330,50 ,50 );
        stage.getBatch().draw(block3, 1450, 330,50 ,50 );
        stage.getBatch().draw(pig1, 1000, 330+50,50 ,50 );
        stage.getBatch().draw(block3, 1100, 330+50,50 ,50 );
        stage.getBatch().draw(pig2, 1100, 330+100,50 ,50 );
        stage.getBatch().draw(pig3, 1200, 330+50,50 ,50 );
        stage.getBatch().draw(block1, 1300, 330,50 ,50 );
        stage.getBatch().draw(block2, 1400, 330,50 ,50 );
        stage.getBatch().draw(block3, 1500, 330,50 ,50 );
        stage.getBatch().draw(pause, 50, stage.getHeight()-150,100 ,100 );

        stage.getBatch().end();
        int x = Gdx.input.getX();
        int y = Gdx.input.getY();
//  If the screen is touched, the game will go to the EndScreen
        if (x< stage.getWidth()/10 && Gdx.input.justTouched() && y > 500){
            game.setScreen(new EndScreen(game,this.level,1));
        }else if( x > stage.getWidth()*0.9f && Gdx.input.justTouched() && y > 500){
            game.setScreen(new EndScreen(game,this.level,0 ));
        }
        if (x > 50 && x < 150 && y < 150  && y > 50){
            if (Gdx.input.justTouched()){
                game.setScreen(new PauseScreen(game,this.level));
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
        bird1.dispose();
        bird2.dispose();
        bird3.dispose();
        slingshot.dispose();

    }
}
