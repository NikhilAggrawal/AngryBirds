package com.birds.game;

import com.badlogic.gdx.physics.box2d.World;

public class Redbird extends Bird {
    public Redbird(){
        super(1, "red.png", 10, 50, 50);
    }

    public void specialAbility(Level level, World world){
        //This function will boost the red bird

        //Increase the velocity of the bird
        body.setLinearVelocity(body.getLinearVelocity().scl(10f));

        //Increase the damage of the bird
        damage *= 2;

    }
}
