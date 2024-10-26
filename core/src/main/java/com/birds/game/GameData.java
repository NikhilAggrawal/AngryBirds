package com.birds.game;

import java.io.Serializable;

public class GameData implements Serializable {
    private static final long serialVersionUID = 1L;

    private int levelNumber;
    private int score;
    private boolean isLocked;
    private int stars;

    public GameData(int levelNumber, int score, int stars, boolean isLocked) {
        this.levelNumber = levelNumber;
        this.score = score;
        this.isLocked = isLocked;
        this.stars = stars;
    }

    // Getters and setters
    public int getLevelNumber() {
        return levelNumber;
    }

    public void setLevelNumber(int levelNumber) {
        this.levelNumber = levelNumber;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }
}
