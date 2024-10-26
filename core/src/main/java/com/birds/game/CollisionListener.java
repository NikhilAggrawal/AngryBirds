package com.birds.game;

import com.badlogic.gdx.physics.box2d.*;

import java.util.ArrayList;
import java.util.List;

public class CollisionListener implements ContactListener {
    static List<Body> bodiesToDestroy = new ArrayList<>();
    private static Level levelInstance;
    private Body groundBody;

    public CollisionListener(Level level, Body groundBody) {
        levelInstance = level;
        this.groundBody = groundBody;
    }

    @Override
    public void beginContact(Contact contact) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        if (fixtureA.getBody().getUserData() instanceof Block && fixtureB.getBody().getUserData() instanceof Bird) {
            Block block1 = (Block) fixtureA.getBody().getUserData();
            Bird bird = (Bird) fixtureB.getBody().getUserData();
            block1.health -= bird.damage;
            if (block1.health <= 0) {
                bodiesToDestroy.add(block1.body);
                levelInstance.setScore(levelInstance.getScore() + 100);
                for (Block block : levelInstance.blockList) {
                    if (block.body.getPosition().y > block1.body.getPosition().y && block.body.getPosition().x > block1.body.getPosition().x - block1.width/2f && block.body.getPosition().x < block1.body.getPosition().x + block1.width/2f) {
                        block.health -= 10;
                        if (block.health <= 0) {
                            bodiesToDestroy.add(block.body);
                            levelInstance.setScore(levelInstance.getScore() + 100);
                        }
                    }
                }

                for (Pig pig : levelInstance.pigList) {
                    if (pig.body.getPosition().y > block1.body.getPosition().y && pig.body.getPosition().x > block1.body.getPosition().x - block1.width/2f && pig.body.getPosition().x < block1.body.getPosition().x + block1.width/2f) {
                        pig.health -= 10;
                        if (pig.health <= 0) {
                            bodiesToDestroy.add(pig.body);
                            levelInstance.setScore(levelInstance.getScore() + 500);
                        }
                    }
                }
            }
        } else if (fixtureA.getBody().getUserData() instanceof Bird && fixtureB.getBody().getUserData() instanceof Block) {
            Bird bird = (Bird) fixtureA.getBody().getUserData();
            Block block = (Block) fixtureB.getBody().getUserData();
            block.health -= bird.damage;
            if (block.health <= 0) {
                bodiesToDestroy.add(block.body);
                levelInstance.setScore(levelInstance.getScore() + 100);
            }
        } else if (fixtureA.getBody().getUserData() instanceof Pig && fixtureB.getBody().getUserData() instanceof Bird) {
            Pig pig = (Pig) fixtureA.getBody().getUserData();
            Bird bird = (Bird) fixtureB.getBody().getUserData();
            pig.health -= bird.damage;
            if (pig.health <= 0) {
                bodiesToDestroy.add(pig.body);
                levelInstance.setScore(levelInstance.getScore() + 500);
            }
        } else if (fixtureA.getBody().getUserData() instanceof Bird && fixtureB.getBody().getUserData() instanceof Pig) {
            Bird bird = (Bird) fixtureA.getBody().getUserData();
            Pig pig = (Pig) fixtureB.getBody().getUserData();
            pig.health -= bird.damage;
            if (pig.health <= 0) {
                bodiesToDestroy.add(pig.body);
                levelInstance.setScore(levelInstance.getScore() + 500);
            }
        }
        if (fixtureA.getBody().getUserData() instanceof Bird || fixtureB.getBody().getUserData() instanceof Bird) {
            Bird bird = (Bird) (fixtureA.getBody().getUserData() instanceof Bird ? fixtureA.getBody().getUserData() : fixtureB.getBody().getUserData());
            if ((fixtureA.getBody() == groundBody || fixtureB.getBody() == groundBody) && bird.body.getPosition().x > 500) { // Assuming ground level is at y = 330
                bodiesToDestroy.add(bird.body);
            }
        }
    }

    @Override
    public void endContact(Contact contact) {
        // Not used
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
        // Not used
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
        // Not used
    }

    public static void processDestructions(World world) {
        for (Body body : bodiesToDestroy) {
            Object userData = body.getUserData();
            if (userData instanceof Block) {
                levelInstance.blockList.remove(userData);
                // For any block destroyed, All the blocks and pigs above it will take a hit that will reduce their health by 10
                // The above block should be
            } else if (userData instanceof Pig) {
                levelInstance.pigList.remove(userData);
            } else if (userData instanceof Bird) {
                levelInstance.birdList.remove(userData);
                // Change the position of the bird at 0 index to the slingshot position
                if (!levelInstance.birdList.isEmpty()) {
                    // Special case for the blue bird copies created by the special ability
                    // if the destroyed body is of blue bird that is not the original bird no need to reset the position
                    // Check if the bird is of type 2 (Bluebird) and if it is not the original bird
                    if (((Bird) userData).type == 2 && ((Bluebird) userData).copy == 1) {
                        world.destroyBody(body);
                        continue;
                    }
                    System.out.println("Bird destroyed. Resetting the bird position.");
                    Bird bird = levelInstance.birdList.get(0);
                    bird.setPosition(430, 500);
                    bird.getBody().setTransform(430, 500, 0); // Update the Box2D body position
                }
            }
            world.destroyBody(body);
        }
        bodiesToDestroy.clear();
    }

}
