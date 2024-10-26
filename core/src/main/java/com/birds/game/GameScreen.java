package com.birds.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import java.io.*;

public class GameScreen extends InputAdapter implements Screen, Serializable {
    private static final long serialVersionUID = 1L;

    private  AngryBirds1 game;
    private Stage stage;
    private SpriteBatch batch;
    private World world;
    private  Texture background;
    public int level;
    public Level levelInstance;
    private  Texture slingshot;
    private  Texture pause;
    private  Texture ground;
//    Vector2 birdPos = new Vector2();
//    boolean isDragging = false;
//    private float releaseX, releaseY;
    private Body groundBody;
//    private Body BoxBody;
//    private  Texture box;
//    private  Body birdBody;
    private Body draggedBody;
    private Vector2 dragOffset = new Vector2();
    private  Texture arrow;
    private  boolean showArrow = false;
    private float arrowRotation = 0f;
    private  Box2DDebugRenderer debugRenderer;
    private  float endScreenDelay = 1.0f; // 2 seconds delay
    private float endScreenTimer = -1.0f; // -1 means no timer is active
    private int endScreenState = -1; // -1 means no end screen state
    private boolean paused = false;
    private InputMultiplexer inputMultiplexer = new InputMultiplexer();

    public GameScreen(AngryBirds1 game, int level){
        this.game = game;
        this.level = level;
        stage =  new Stage(new StretchViewport(1920,1080));
        batch = new SpriteBatch();
        world = new World(new Vector2(0, -5), true);
        debugRenderer = new Box2DDebugRenderer();

        creategenerallevel();
        createlevel(level);

        // Create an InputMultiplexer and add both the stage and this screen as input processors
        inputMultiplexer.addProcessor(stage);
        inputMultiplexer.addProcessor(this);
        inputMultiplexer.addProcessor(new InputAdapter() {
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                if (button == Input.Buttons.LEFT) {
                    triggerSpecialAbility();
                }
                return true;
            }
        });
        Gdx.input.setInputProcessor(inputMultiplexer);



        // Create pause button
        pause = new Texture("pause.png");
        Button Pausebutton = new Button(new TextureRegionDrawable(new TextureRegion(pause)));
        Pausebutton.setPosition(50, stage.getHeight()-150);
        Pausebutton.setSize(100, 100);
        Pausebutton.setTouchable(Touchable.enabled);
        Pausebutton.setTransform(true);
        Pausebutton.setOrigin(Pausebutton.getWidth() / 2, Pausebutton.getHeight() / 2);
        stage.setDebugAll(true);


        // Make click on pause button to go to PauseScreen
        Pausebutton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Pausebutton.addAction(Actions.sequence(
                    Actions.scaleTo(0.9f, 0.9f, 0.05f),
                    Actions.scaleTo(1f, 1f, 0.05f)
                ));
//                game.setScreen(new PauseScreen(game, level));
                togglePause();
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                Pausebutton.addAction(Actions.scaleTo(1.2f, 1.2f, 0.1f)); // Increase size on hover
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                Pausebutton.addAction(Actions.scaleTo(1.0f, 1.0f, 0.1f)); // Reset size when not hovering
            }
        });
        stage.addActor(Pausebutton);

    }


    private void triggerSpecialAbility() {
        if (!levelInstance.birdList.isEmpty()) {
            Bird firstBird = levelInstance.birdList.get(0);
            if (firstBird.isReleased()) {
                firstBird.useSpecialAbility(levelInstance, world);
            }
        }
    }

    public void togglePause() {
        paused = !paused;
        if (paused) {
            game.setScreen(new PauseScreen(game, this));
        } else {
            Gdx.input.setInputProcessor(inputMultiplexer);
        }
    }
    public void creategenerallevel(){
        background = new Texture("background.jpg");
        slingshot = new Texture("sling.png");
        ground = new Texture("ground.png");
        arrow = new Texture("lockedlevel.jpg");

        // Create ground body
        BodyDef groundBodyDef = new BodyDef();
        groundBodyDef.position.set(0, 0);
        groundBody = world.createBody(groundBodyDef);

        EdgeShape groundShape = new EdgeShape();
        groundShape.set(0, 330, stage.getWidth(), 330);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = groundShape;
        fixtureDef.friction = 1.0f;
        fixtureDef.restitution = 0.0f;

        groundBody.createFixture(fixtureDef);
        groundShape.dispose();

        // Create slingshot body
        BodyDef slingshotBodyDef = new BodyDef();
        slingshotBodyDef.type = BodyDef.BodyType.StaticBody;
        slingshotBodyDef.position.set(430, 330);
        Body slingshotBody = world.createBody(slingshotBodyDef);

        PolygonShape slingshotShape = new PolygonShape();
        slingshotShape.setAsBox(20, 160);

        fixtureDef.shape = slingshotShape;
        fixtureDef.density = 1.0f;
        fixtureDef.friction = 0.3f;
        fixtureDef.restitution = 0.5f;

        slingshotBody.createFixture(fixtureDef);
        slingshotShape.dispose();

    }
    public void createlevel(int level){
        levelInstance = new Level(level);
        levelInstance.setworld(world,levelInstance);
        world.setContactListener(new CollisionListener(levelInstance, groundBody));
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector2 touchPos = new Vector2(screenX, Gdx.graphics.getHeight() - screenY);
        for (Bird bird : levelInstance.birdList) {
            //bird should only be released if it is not released and the touch is on the bird and bird is at index 0
            if (bird.getBody().getFixtureList().first().testPoint(touchPos) && !bird.released && levelInstance.birdList.indexOf(bird) == 0) {
                draggedBody = bird.getBody();
                dragOffset.set(draggedBody.getPosition()).sub(touchPos);
                showArrow = true;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (draggedBody != null) {
            Vector2 touchPos = new Vector2(screenX, Gdx.graphics.getHeight() - screenY);
            draggedBody.setTransform(touchPos.add(dragOffset), draggedBody.getAngle());

            // Calculate the angle for the arrow rotation
            Vector2 direction = new Vector2(430, 500).sub(touchPos);
            arrowRotation = direction.angleDeg();
            return true;
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (draggedBody != null) {
            // Calculate the velocity based on the difference between the original position and the release position
            Vector2 releasePos = new Vector2(screenX, Gdx.graphics.getHeight() - screenY);
            Vector2 originalPos = new Vector2(430,500);
            // I want to scale the impulse to make the bird move faster with diffent scales for x and y
            Vector2 velocity = originalPos.sub(releasePos).scl(10f); // Adjust the scale factor as needed


            //When the bird is released, it will be first sent to the slingshot position that is (430,500)
            draggedBody.setTransform(430, 500, 0);
            //Then the bird will be set to active and move with calculated velocity
            draggedBody.setActive(true);
            //set the released of bird user of dragged body to true
            ((Bird) draggedBody.getUserData()).setReleased();
            // Apply the calculated velocity to the bird's body
//            draggedBody.applyLinearImpulse(impulse, draggedBody.getWorldCenter(), true);
            draggedBody.setLinearVelocity(velocity);

            showArrow = false;
        }
        draggedBody = null;
        return true;
    }


    @Override
    public void show() {
    }

    @Override
    public void render(float v) {

        if (paused) {
            return;
        }

//        System.out.println("FPS: " + Gdx.graphics.getFramesPerSecond());
        // Step the Box2D world
        world.step(1/10f, 8, 2);
        CollisionListener.processDestructions(world);

        // Check win condition
        if (levelInstance.pigList.isEmpty() && endScreenTimer == -1.0f) {
            endScreenState = 1;
            endScreenTimer = 0.0f;
        }

        // Check lose condition
        if (levelInstance.birdList.isEmpty() && !levelInstance.pigList.isEmpty() && endScreenTimer == -1.0f) {
            endScreenState = 0;
            endScreenTimer = 0.0f;
        }

        // Update the end screen timer
        if (endScreenTimer >= 0.0f) {
            endScreenTimer += v;
            if (endScreenTimer >= endScreenDelay) {
                game.setScreen(new EndScreen(game, this.levelInstance.getLevelNumber(), endScreenState, levelInstance));
                return;
            }
        }

        // Update birds
        for (Bird bird : levelInstance.birdList) {
            bird.update(v);
        }

//        System.out.println("Number of bodies: " + world.getBodyCount());
        // Clear the screen
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

        // Begin drawing
        batch.begin();
        batch.draw(background, 0, 0, stage.getWidth(), stage.getHeight());
//        batch.draw(box, BoxBody.getPosition().x - 50, BoxBody.getPosition().y - 50, 100, 100);
        batch.draw(ground, 0, 0, stage.getWidth(), 330);
        batch.draw(slingshot, 380, 330, 100, 200);
        if (showArrow) {
            batch.draw(arrow, 430, 500, 0, 5, 100, 10, 1, 1, arrowRotation, 0, 0, arrow.getWidth(), arrow.getHeight(), false, false);
        }
//        batch.draw(levelInstance.birdList.get(0).getTexture(), levelInstance.birdList.get(0).getX(), levelInstance.birdList.get(0).getY(), 50, 50);
        for (Bird bird : levelInstance.birdList) {
            batch.draw(bird.getTexture(), bird.getBody().getPosition().x - bird.getWidth() / 2f, bird.getBody().getPosition().y - bird.getHeight() / 2f,bird.getWidth(), bird.getHeight());
        }
        for (Block block : levelInstance.blockList) {
            batch.draw(block.getTexture(), block.getBody().getPosition().x - block.getWidth() / 2f, block.getBody().getPosition().y - block.getHeight() / 2f, block.getWidth(), block.getHeight());
        }
        for (Pig pig : levelInstance.pigList) {
            batch.draw(pig.getTexture(), pig.getBody().getPosition().x - pig.getWidth() / 2f, pig.getBody().getPosition().y - pig.getHeight() / 2f, pig.getWidth(), pig.getHeight());
        }

        batch.end();

        debugRenderer.render(world, stage.getCamera().combined);
        // Update the stage
        stage.act(v);
        stage.draw();


    }

    @Override
    public void resize(int i, int i1) {
        stage.getViewport().update(i, i1, true);
    }

    @Override
    public void pause() {
    }

    public void saveGameScreenState() {
        try (FileOutputStream fileOut = new FileOutputStream("gameScreenState.ser");
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        batch.dispose();
        background.dispose();
        slingshot.dispose();
        ground.dispose();
        pause.dispose();
        arrow.dispose(); // Ensure arrow texture is disposed
        world.dispose();
    }
}
