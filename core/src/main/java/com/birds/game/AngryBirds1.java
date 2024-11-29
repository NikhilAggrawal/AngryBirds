package com.birds.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

import java.io.*;


/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class AngryBirds1 extends Game {
    public static LevelManager levelManager;
    public static GameScreen tempScreen;
    public static boolean soundOn;
    Music backgroundMusic;
//    private static int test;

//    public AngryBirds1(int test) {
//        // This constructor is used for testing purposes
//        AngryBirds1.test = test;
//    }
//
//    public AngryBirds1() {
//        // This constructor is used for testing purposes
//        AngryBirds1.test = 0;
//    }

    public void saveGame() {
        try (FileOutputStream fileOut = new FileOutputStream("gameData.ser");
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            GameData[] gameDataArray = new GameData[LevelManager.levels.size()];
            int index = 0;
            for (Level level : LevelManager.levels) {
                gameDataArray[index++] = new GameData(level.getLevelNumber(), level.getScore(), level.getStars(), level.getLocked());
            }
            out.writeObject(gameDataArray);
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public void loadGame() {
        // if the file is not found, then the game is not saved yet
        if (!new File("gameData.ser").exists()) {
            return;
        }

        GameData[] gameDataArray = null;
        try (FileInputStream fileIn = new FileInputStream("gameData.ser");
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            gameDataArray = (GameData[]) in.readObject();
        } catch (IOException | ClassNotFoundException i) {
            i.printStackTrace();
        }

        if (gameDataArray != null) {
            int levelno = 1;
            for (GameData gameData : gameDataArray) {
                if (!gameData.isLocked()) {
                    levelManager.unlockLevel(levelno);
                    levelManager.setScore(levelno, gameData.getScore());
                    levelManager.setStars(levelno, gameData.getStars());
                }
                levelno++;
            }
        }
    }


    @Override
    public void create() {
        levelManager = new LevelManager();
        loadGame();
//        if (test == 0){
//            backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("backmusic.mp3"));
//            backgroundMusic.setLooping(true);
//            backgroundMusic.setVolume(0.1f);
//            backgroundMusic.play();
//            this.setScreen(new HomeScreen(this));
//        }
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("backmusic.mp3"));
        backgroundMusic.setLooping(true);
        backgroundMusic.setVolume(0.1f);
        backgroundMusic.play();
        this.setScreen(new HomeScreen(this));

    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        // Save game data when the game is disposed
        backgroundMusic.dispose();
        saveGame();
        super.dispose();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    // This method is used to save data of any one level in the following format
    // LevelNumber
    // Bird:
    // BirdType, PositionX, PositionY
    // BirdType, PositionX, PositionY
    // BirdType, PositionX, PositionY
    // Block:
    // BlockType, PositionX, PositionY, Width, Height
    // BlockType, PositionX, PositionY, Width, Height
    // BlockType, PositionX, PositionY, Width, Height
    // Pig:
    // PigType, PositionX, PositionY, Width, Height
    // PigType, PositionX, PositionY, Width, Height
    // PigType, PositionX, PositionY, Width, Height

    public void saveLevel(GameScreen gameScreen) {
        // Using gamescreen save the data as i mentioned above in a file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("savedlevel.txt"))) {
            writer.write(String.valueOf(gameScreen.level));
            writer.newLine();
            writer.write("Bird:" + gameScreen.levelInstance.birdList.size() + "\n");
            for (Bird bird : gameScreen.levelInstance.birdList) {
                writer.write(bird.type + "," + bird.getX() + "," + bird.getY());
                writer.newLine();
            }
            writer.write("Block:" + gameScreen.levelInstance.blockList.size() + "\n");
            for (Block block : gameScreen.levelInstance.blockList) {
                writer.write(block.type + "," + block.getX() + "," + block.getY() + "," + block.getWidth() + "," + block.getHeight());
                writer.newLine();
            }
            writer.write("Pig:" + gameScreen.levelInstance.pigList.size() + "\n");
            for (Pig pig : gameScreen.levelInstance.pigList) {
                writer.write(pig.type + "," + pig.getX() + "," + pig.getY() + "," + pig.getWidth() + "," + pig.getHeight());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveLevel(Level Testlevel) {
        // Using gamescreen save the data as i mentioned above in a file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("savedlevel.txt"))) {
            writer.write(String.valueOf(Testlevel.getLevelNumber()));
            writer.newLine();
            writer.write("Bird:" + Testlevel.birdList.size() + "\n");
            for (Bird bird : Testlevel.birdList) {
                writer.write(bird.type + "," + bird.getX() + "," + bird.getY());
                writer.newLine();
            }
            writer.write("Block:" + Testlevel.blockList.size() + "\n");
            for (Block block : Testlevel.blockList) {
                writer.write(block.type + "," + block.getX() + "," + block.getY() + "," + block.getWidth() + "," + block.getHeight());
                writer.newLine();
            }
            writer.write("Pig:" + Testlevel.pigList.size() + "\n");
            for (Pig pig : Testlevel.pigList) {
                writer.write(pig.type + "," + pig.getX() + "," + pig.getY() + "," + pig.getWidth() + "," + pig.getHeight());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean isnotsaved() {
        // if the file is not found, then the game is not saved yet
        if (!new File("savedlevel.txt").exists()) {
            System.out.println("No saved level found");
            return true;
        }
        else{
            if (new File("savedlevel.txt").length() == 0){
                System.out.println("No saved level found");
                return true;
            }
            System.out.println("Saved level found");
            return false;
        }
    }

    public static void loadsavedLevel(Level level) {
        // if the file is not found, then the game is not saved yet
        if (AngryBirds1.isnotsaved()){
            return;
        }

        // Load the saved level from the file and set the level object with the data
        try (BufferedReader reader = new BufferedReader(new FileReader("savedlevel.txt"))) {
            String line = reader.readLine();
            if (line != null) {
                level.setLevelNumber(Integer.parseInt(line));
            }
            else{
                // Should not happen
                System.out.println("No saved level found HAHAHA");
                return;
            }
            line = reader.readLine();
            if (line != null) {
                String[] birdData = line.split(":");
                int birdCount = Integer.parseInt(birdData[1]);
                for (int i = 0; i < birdCount; i++) {
                    String[] bird = reader.readLine().split(",");
                    int type = Integer.parseInt(bird[0]);
                    if (type == 1){
                        level.birdList.add(new Redbird());
                    }
                    else if (type == 2){
                        level.birdList.add(new Bluebird());
                    }
                    else if (type == 3){
                        level.birdList.add(new Bombbird());
                    }
                    level.birdList.get(i).setPosition(Integer.parseInt(bird[1]), Integer.parseInt(bird[2]));
                }
            }
            line = reader.readLine();
            if (line != null) {
                String[] blockData = line.split(":");
                int blockCount = Integer.parseInt(blockData[1]);
                for (int i = 0; i < blockCount; i++) {
                    String[] block = reader.readLine().split(",");
                    int type = Integer.parseInt(block[0]);
                    if (type == 1){
                        level.blockList.add(new GlassBlock());
                    }
                    else if (type == 2){
                        level.blockList.add(new WoodBlock());
                    }
                    else if (type == 3){
                        level.blockList.add(new StoneBlock());
                    }
                    level.blockList.get(i).setPos(Integer.parseInt(block[1]), Integer.parseInt(block[2]));
                    level.blockList.get(i).setScale(Integer.parseInt(block[3]), Integer.parseInt(block[4]));
                }
            }

            line = reader.readLine();
            if (line != null) {
                String[] pigData = line.split(":");
                int pigCount = Integer.parseInt(pigData[1]);
                for (int i = 0; i < pigCount; i++) {
                    String[] pig = reader.readLine().split(",");
                    int type = Integer.parseInt(pig[0]);
                    if (type == 1){
                        level.pigList.add(new SmallPig());
                    }
                    else if (type == 2){
                        level.pigList.add(new ArmorPig());
                    }
                    level.pigList.get(i).setPos(Integer.parseInt(pig[1]), Integer.parseInt(pig[2]));
                    level.pigList.get(i).setScale(Integer.parseInt(pig[3]), Integer.parseInt(pig[4]));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
