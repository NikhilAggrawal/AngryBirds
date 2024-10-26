package com.birds.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class Level implements Serializable {
    //This class is used to store the information of the level like level number and the state of the level (locked or unlocked) and for unlocked levels the number of stars and win or lose state along with the score
    //it will be used to store the information of objects in the level like birds, pigs, and blocks
    private int levelNumber;
    private boolean locked;
    private int stars;
    private boolean win;
    private int score;
    public ArrayList<Bird> birdList;
    public ArrayList<Pig> pigList;
    public ArrayList<Block> blockList;

    //constructor
    public Level(int levelNumber) {
        this.levelNumber = levelNumber;
        this.locked = levelNumber != 1 && levelNumber!=99;
        this.stars = 0;
        this.win = false;
        this.score = 0;
        this.birdList = new ArrayList<Bird>();
        this.pigList = new ArrayList<Pig>();
        this.blockList = new ArrayList<Block>();

    }
    void createBirdBody(Bird bird, World world) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(bird.getX(), bird.getY());
        bodyDef.fixedRotation = true; // Prevent rotation
        Body body = world.createBody(bodyDef);

        CircleShape shape = new CircleShape();
        shape.setRadius((bird.getWidth() / 2f));

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1.0f;
        fixtureDef.friction = 0.8f;
        fixtureDef.restitution = 0.0001f;

        body.createFixture(fixtureDef);
        shape.dispose();

        body.setUserData(bird);
        bird.setBody(body);
    }

    private void createPigBody(Pig pig, World world) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(pig.getX(), pig.getY() + pig.getHeight());
        Body body = world.createBody(bodyDef);

        CircleShape shape = new CircleShape();
        shape.setRadius(pig.getWidth() / 2f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 5.0f;
        fixtureDef.friction = 5f;
        fixtureDef.restitution = 0.0001f;

        body.createFixture(fixtureDef);
        shape.dispose();

        body.setUserData(pig);
        pig.setBody(body);
    }

    private void createBlockBody(Block block, World world) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(block.getX(),  block.getY() + block.getHeight());
        Body body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(block.getWidth()/2f, block.getHeight()/2f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 10.0f;
        fixtureDef.friction = 1f;
        fixtureDef.restitution = 0.001f;

        body.createFixture(fixtureDef);
        shape.dispose();

        body.setUserData(block);
        block.setBody(body);
    }

    public int getLevelNumber() {
        return levelNumber;
    }

    public void setLevelNumber(int levelNumber) {
        this.levelNumber = levelNumber;
    }

    public boolean getLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public boolean getWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setworld(World world, Level level) {
        if (levelNumber == 99) {
            AngryBirds1.loadsavedLevel(level);
            if (levelNumber == 99) {
                System.out.println("This is happening because there is no saved level.");
                // Close the creating of game screen and go back to the level select screen
                return;
            }
        }
        else{
            if(levelNumber == 1 || levelNumber == 2 || levelNumber == 3) {
                Bird bird1 = new Redbird();
                Bird bird2 = new Bluebird();
                Bird bird3 = new Bombbird();
                bird1.setPosition(430, 500);
                bird2.setPosition(200, 530);
                bird3.setPosition(300, 530);
                birdList.add(bird1);
                birdList.add(bird2);
                birdList.add(bird3);
            }

            if(levelNumber == 1) {
                Pig pig1 = new SmallPig();
                pig1.setPos(1100, 430);
                Block block1 = new GlassBlock();
                Block block2 = new WoodBlock();
                Block block3 = new StoneBlock();
                block1.setPos(1100, 330);
                block1.setScale(150, 100);
                block2.setScale(50, 100);
                block2.setPos(1150, 430);
                block3.setPos(1300, 330);
                pigList.add(pig1);
                blockList.add(block1);
                blockList.add(block2);
                blockList.add(block3);
            }

            if(levelNumber == 2) {
                Pig pig1 = new SmallPig();
                Pig pig2 = new ArmorPig();
                Pig pig3 = new SmallPig();
                pig1.setPos(1300, 380);
                pig2.setPos(1250, 330);
                pig3.setPos(1100, 380);
                Block block1 = new WoodBlock();
                Block block2 = new WoodBlock();
                Block block3 = new StoneBlock();
                block1.setPos(1100, 330);
                block2.setPos(1200, 330);
                block3.setPos(1300, 330);

                pigList.add(pig1);
                pigList.add(pig2);
                pigList.add(pig3);
                blockList.add(block1);
                blockList.add(block2);
                blockList.add(block3);
            }

            if(levelNumber == 3) {
                Pig pig1 = new BigPig();
                Pig pig2 = new ArmorPig();
                Pig pig3 = new BigPig();
                pig1.setPos(1400, 330);
                pig2.setPos(1100, 430);
                pig3.setPos(1200, 450);
                Block block1 = new GlassBlock();
                Block block2 = new StoneBlock();
                Block block3 = new StoneBlock();
                block1.setPos(1100, 330);
                block2.setPos(1200, 330);
                block3.setPos(1300, 330);
                block2.setScale(100, 100);
                block3.setScale(50, 150);

                pigList.add(pig1);
                pigList.add(pig2);
                pigList.add(pig3);
                blockList.add(block1);
                blockList.add(block2);
                blockList.add(block3);
            }
        }

        for (Bird bird : birdList) {
            createBirdBody(bird, world);
        }

        for (Pig pig : pigList) {
            createPigBody(pig, world);
        }

        for (Block block : blockList) {
            createBlockBody(block, world);
        }

    }
}
