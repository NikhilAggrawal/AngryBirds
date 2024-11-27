package com.birds.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class Bombbird extends Bird
{
    public Bombbird(){
        super(3, "bomb.png", 10, 100, 100);
    }

    public void specialAbility(Level level, World world){
        //This function will explode
        //It will destroy all the pigs within a certain radius
        //It will also destroy itself

        //Destroy all the pigs within a certain radius
        //For each pig in the level
        //If the distance between the pig and the bomb is less than the radius
        //Destroy the pig
        //Remove the pig from the pig list

        //Destroy itself
        //Remove itself from the bird list
        //

        Vector2 blastCenter = new Vector2(getBody().getPosition().x, getBody().getPosition().y);
        float blastRadius = 200;

        for (Pig pig : level.pigList) {
            Vector2 pigPosition = new Vector2(pig.getX(), pig.getY());
            float distance = blastCenter.dst(pigPosition);
            if (distance < blastRadius) {
                level.setScore(level.getScore() + 500);
                CollisionListener.bodiesToDestroy.add(pig.body);
            }
        }

        for (Block block : level.blockList) {
            Vector2 blockPosition = new Vector2(block.getX(), block.getY());
            float distance = blastCenter.dst(blockPosition);
            if (distance < blastRadius) {
                level.setScore(level.getScore() + 100);
                CollisionListener.bodiesToDestroy.add(block.body);
            }
        }

        CollisionListener.bodiesToDestroy.add(getBody());
    }
}
