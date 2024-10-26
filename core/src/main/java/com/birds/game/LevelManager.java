package com.birds.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.World;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class LevelManager {
    static ArrayList<Level> levels;
    public List<Texture> levelTextures;


    public LevelManager(){
        levels = new ArrayList<Level>();
        createLevel(1);
        createLevel(2);
        createLevel(3);

        levelTextures = new ArrayList<Texture>();
        levelTextures.add(new Texture("l1.jpg"));
        for(int i = 1; i < levels.size(); i++){
            levelTextures.add(new Texture("lockedlevel.jpg"));
        }
    }

    public void addLevel(Level level){
        levels.add(level);
    }

    public void unlockLevel(int levelNumber) {
//        if (levelSelectScreen == null) {
//            throw new IllegalStateException("LevelSelectScreen is not initialized");
//        }

        if (levelNumber > levels.size()) {
            System.out.println("Max level number exceeded");
            return;
        }

        for (Level level : levels) {
            if (level.getLevelNumber() == levelNumber) {
                level.setLocked(false);
                Texture oldTexture = levelTextures.get(levelNumber - 1);
                if (oldTexture != null) {
                    oldTexture.dispose();
                }
                levelTextures.set(levelNumber - 1, new Texture("l" + levelNumber + ".jpg"));

            }
        }
    }

    public void setStars(int levelNumber, int stars){
        for(Level level : levels){
            if(level.getLevelNumber() == levelNumber){
                level.setStars(stars);
            }
        }
    }

    public void setScore(int levelNumber, int score){
        for(Level level : levels){
            if(level.getLevelNumber() == levelNumber){
                level.setScore(score);
            }
        }
    }

    public int getScore(int levelNumber){
        for(Level level : levels){
            if(level.getLevelNumber() == levelNumber){
                return level.getScore();
            }
        }
        return 0;
    }

    public void createLevel(int levelNumber){
        Level level = new Level(levelNumber);
        levels.add(level);
    }

    //get level by using level number
    public Level getLevel(int levelNumber){
        for(Level level : levels){
            if(level.getLevelNumber() == levelNumber){
                return level;
            }
        }
        return null;
    }

    public void updateLevel(Level updatedLevel) {
        for (Level level : levels) {
            if (level.getLevelNumber() == updatedLevel.getLevelNumber()) {
                // Update the level attributes stars and score if the updated level has a higher score or more stars
                if (level.getScore() < updatedLevel.getScore()) {
                    level.setScore(updatedLevel.getScore());
                }
                if (level.getStars() < updatedLevel.getStars()) {
                    level.setStars(updatedLevel.getStars());
                }
                // If the updated level is a win, set the level to win and unlock the next level
                if (updatedLevel.getWin()) {
                    level.setWin(true);
                    unlockLevel(level.getLevelNumber() + 1);
                }
                break;
            }
        }
    }

}
