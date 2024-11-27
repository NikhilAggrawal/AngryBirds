package com.birds.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;

public class Block {
    int type;
    String texture;
    int health;
    int width;
    int height;
    int x;
    int y;
    Body body;
    public Block(int type, String texture, int health, int width, int height){
        this.type = type;
        this.texture = texture;
        this.health = health;
        this.width = width;
        this.height = height;
        this.x = 1100;
        this.y = 330;
    }
    public Texture getTexture(){
        return new Texture(texture);
    }

    public void setPos(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void setScale(int width, int height){
        this.width = width;
        this.height = height;
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
}
