package com.birds.game;

public class Block {
    int type;
    String texture;
    int health;
    int width;
    int height;
    public Block(int type, String texture, int health, int width, int height){
        this.type = type;
        this.texture = texture;
        this.health = health;
        this.width = width;
        this.height = height;
    }
    public String getTexture(){
        return this.texture;
    }
}
