package com.birds.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class GameScreen implements Screen {
    private AngryBirds1 game;
    private Stage stage;
    private Texture background;
    public int level;
    public Bird bird1;
    public Bird bird2;
    public Bird bird3;
    private Pig pig1;
    private Pig pig2;
    private Pig pig3;
    private Block block1;
    private Block block2;
    private Block block3;
    private Texture slingshot;
    private Texture pause;
    private Texture ground;

    public GameScreen(AngryBirds1 game, int level){
        this.game = game;
        this.level = level;
        stage =  new Stage(new StretchViewport(1920,1080));
        creategenerallevel();
        createlevel(level);
        pause = new Texture("pause.png");

    }
    public void creategenerallevel(){
        background = new Texture("background.jpg");
        slingshot = new Texture("sling.png");
        ground = new Texture("ground.png");
    }
    public void createlevel(int level){
        if(level!=0){
            bird1 = new Redbird();
            bird2 = new Bluebird();
            bird3 = new Bombbird();
            pig1 = new SmallPig();
            pig2 = new BigPig();
            pig3 = new ArmorPig();
            block1 = new GlassBlock();
            block2 = new WoodBlock();
            block3 = new StoneBlock();
        }
    }
    public void renderlevel(int level){
        // general level
        stage.getBatch().draw(background, 0, 0, stage.getWidth(), stage.getHeight());
        stage.getBatch().draw(ground, 0, 0, stage.getWidth(), 330);
        stage.getBatch().draw(slingshot, 400, 330,100 ,200 );


        if(level!=0){
            // following are the birds
            stage.getBatch().draw(new Texture(bird1.getTexture()), 430, 500,bird1.width ,bird1.height );
            stage.getBatch().draw(new Texture(bird2.getTexture()), 200, 330,bird2.width ,bird2.height );
            stage.getBatch().draw(new Texture(bird3.getTexture()), 300, 330,bird3.width ,bird3.height );
            // following are the blocks and pigs
            stage.getBatch().draw(new Texture(block1.getTexture()), 1000, 330,block1.width ,block1.height );
            stage.getBatch().draw(new Texture(block1.getTexture()), 1050, 330,block1.width ,block1.height );
            stage.getBatch().draw(new Texture(block1.getTexture()), 1100, 330,block1.width ,block1.height );
            stage.getBatch().draw(new Texture(block1.getTexture()), 1150, 330,block1.width ,block1.height );
            stage.getBatch().draw(new Texture(block2.getTexture()), 1200, 330,block2.width ,block2.height );
            stage.getBatch().draw(new Texture(block2.getTexture()), 1250, 330,block2.width ,block2.height );
            stage.getBatch().draw(new Texture(block2.getTexture()), 1300, 330,block2.width ,block2.height );
            stage.getBatch().draw(new Texture(block3.getTexture()), 1350, 330,block3.width ,block3.height );
            stage.getBatch().draw(new Texture(block3.getTexture()), 1400, 330,block3.width ,block3.height );
            stage.getBatch().draw(new Texture(block3.getTexture()), 1450, 330,block3.width ,block3.height );
            stage.getBatch().draw(new Texture(pig1.getTexture()), 1000, 330+50,pig1.width ,pig1.height );
            stage.getBatch().draw(new Texture(block3.getTexture()), 1100, 330+50,block3.width ,block3.height );
            stage.getBatch().draw(new Texture(pig2.getTexture()), 1100, 330+100,pig2.width ,pig2.height );
            stage.getBatch().draw(new Texture(pig3.getTexture()), 1200, 330+50,pig3.width ,pig3.height );
            stage.getBatch().draw(new Texture(block1.getTexture()), 1300, 330,block1.width ,block1.height );
            stage.getBatch().draw(new Texture(block2.getTexture()), 1400, 330,block2.width ,block2.height );
            stage.getBatch().draw(new Texture(block3.getTexture()), 1500, 330,block3.width ,block3.height );

        }
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
        double ratioX = Gdx.graphics.getWidth() / 1920.0;
        double ratioY = Gdx.graphics.getHeight() / 1080.0;
        renderlevel(level);
        stage.getBatch().draw(pause, 50, stage.getHeight()-150,100 ,100 );

        stage.getBatch().end();
        int x = Gdx.input.getX();
        int y = Gdx.input.getY();
//  If the screen is touched, the game will go to the EndScreen
        if (x < 100*ratioX && Gdx.input.justTouched() && y > 500* ratioY){
            game.setScreen(new EndScreen(game,this.level,1));
        }else if( x > 1500*ratioX && Gdx.input.justTouched() && y > 500*ratioY){
            game.setScreen(new EndScreen(game,this.level,0 ));
        }
        if (x > 50*ratioX && x < 150*ratioX && y < 150*ratioY  && y > 50*ratioY){
            if (Gdx.input.justTouched()){
                game.setScreen(new PauseScreen(game,this.level));
            }
        }


    }

    @Override
    public void resize(int width, int height) {
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
        slingshot.dispose();
        ground.dispose();
        pause.dispose();
    }
}
