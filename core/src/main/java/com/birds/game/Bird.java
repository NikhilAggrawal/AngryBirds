package com.birds.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

public class Bird {
    int type;
    String texture;
    int damage;
    int health;
    int width;
    int height;
    int x;
    int y;
    boolean released = false;
    boolean specialAbilityUsed = false;
    Body body;
    public Bird(int type, String texture, int damage, int width, int height){
        this.type = type;
        this.texture = texture;
        this.damage = damage;
        this.width = width;
        this.height = height;
    }
    public Texture getTexture(){
        return new Texture(texture);
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public void setBody(Body body){
        this.body = body;
    }

    public Body getBody() {
        return body;
    }

    public void setReleased(){
        this.released = true;
    }

    public void specialAbility(Level level, World world){
        // To be implemented in subclasses
    }

    public boolean isReleased() {
        return released;
    }

    public boolean isSpecialAbilityUsed() {
        return specialAbilityUsed;
    }

    public void setSpecialAbilityUsed(boolean specialAbilityUsed) {
        this.specialAbilityUsed = specialAbilityUsed;
    }

    public void useSpecialAbility(Level level, World world) {
        if (specialAbilityUsed) {
            return;
        }
        specialAbility(level, world);
        setSpecialAbilityUsed(true);
    }

    public void update(float delta) {
        float gravity = -20f;
        // if the bird goes off the screen, destroy it
        if (getBody().getPosition().x > Gdx.graphics.getWidth() || getBody().getPosition().y < 0 || getBody().getPosition().y > Gdx.graphics.getHeight()) {
            CollisionListener.bodiesToDestroy.add(this.getBody());
        }
        if (isReleased()) {
            // Update velocity with gravity
            getBody().setLinearVelocity(getBody().getLinearVelocity().x * 6, getBody().getLinearVelocity().y * 6 + gravity * delta);
            // Update position with velocity
            getBody().setTransform(getBody().getPosition().x + getBody().getLinearVelocity().x * delta, getBody().getPosition().y + (getBody().getLinearVelocity().y * delta + 0.5f * gravity * delta * delta), 0);
        }
    }
}
