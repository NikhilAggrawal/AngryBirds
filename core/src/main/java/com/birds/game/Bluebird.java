package com.birds.game;

import com.badlogic.gdx.physics.box2d.World;

public class Bluebird extends Bird {
    int copy;
    public Bluebird(){
        super(2, "blue.png", 10, 50, 50);
        copy = 0;

    }

    public void setCopy(int copy){
        this.copy = copy;
    }

    public void specialAbility(Level level, World world){
        //This function will split the blue bird into three birds
        //Two new birds will be created and added to the world

        //Create two new birds
        Bluebird bird1 = new Bluebird();
        Bluebird bird2 = new Bluebird();

        bird1.setPosition((int) (getBody().getPosition().x + 100), (int) (getBody().getPosition().y + 100));
        bird2.setPosition((int) (getBody().getPosition().x), (int) (getBody().getPosition().y - 100));
        bird1.setReleased();
        bird2.setReleased();
        bird1.setSpecialAbilityUsed(true);//Set the special ability used to true so that the new birds don't split
        bird2.setSpecialAbilityUsed(true);
        bird1.setCopy(1);
        bird2.setCopy(1);
        // Create their bodies
        level.createBirdBody(bird1, world);
        level.createBirdBody(bird2, world);

        // Add them to the bird list
        level.birdList.add(bird1);
        level.birdList.add(bird2);

        //Give them an initial velocity equal to the velocity of the original bird
        bird1.body.setLinearVelocity(body.getLinearVelocity());
        bird2.body.setLinearVelocity(body.getLinearVelocity());

//
//        //Set the position of the three birds
//        bird1.setPosition(x, y);
//        bird2.setPosition(x, y);
//        bird3.setPosition(x, y);
//
    }
}
