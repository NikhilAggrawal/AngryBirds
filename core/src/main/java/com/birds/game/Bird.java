package com.birds.game;

public class Bird {
    int type;
    String texture;
    int damage;
    int width;
    int height;
    public Bird(int type, String texture, int damage, int width, int height){
        this.type = type;
        this.texture = texture;
        this.damage = damage;
        this.width = width;
        this.height = height;
    }
    public String getTexture(){
        return this.texture;
    }

}
