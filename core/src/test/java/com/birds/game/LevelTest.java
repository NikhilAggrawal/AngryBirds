package com.birds.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Files;
import com.badlogic.gdx.files.FileHandle;
import com.birds.game.Level;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;


public class LevelTest {
    private Level level;

    // Mock Files class
    private static class MockFiles implements Files {
        @Override
        public FileHandle getFileHandle(String s, FileType fileType) {
            return null;
        }

        @Override
        public FileHandle classpath(String s) {
            return null;
        }

        @Override
        public FileHandle internal(String path) {
            return new FileHandle(path);
        }

        @Override
        public FileHandle external(String s) {
            return null;
        }

        @Override
        public FileHandle absolute(String s) {
            return null;
        }

        @Override
        public FileHandle local(String s) {
            return null;
        }

        @Override
        public String getExternalStoragePath() {
            return "";
        }

        @Override
        public boolean isExternalStorageAvailable() {
            return false;
        }

        @Override
        public String getLocalStoragePath() {
            return "";
        }

        @Override
        public boolean isLocalStorageAvailable() {
            return false;
        }
    }

    @Before
    public void setUp() {
        level = new Level(1);
        Gdx.files = new MockFiles(); // Initialize with a sample level number
    }

    @Test
    public void testLevelInitialization() {
        assertNotNull(level);
        assertEquals(1, level.getLevelNumber());
    }

    @Test
    public void testAddBird() {
        Bird bird = new Redbird();
        level.birdList.add(bird);
        assertEquals(1, level.birdList.size());
        assertEquals(bird, level.birdList.get(0));
    }

    @Test
    public void testAddPig() {
        Pig pig = new SmallPig();
        level.pigList.add(pig);
        assertEquals(1, level.pigList.size());
        assertEquals(pig, level.pigList.get(0));
    }

    @Test
    public void testAddBlock() {
        Block block = new WoodBlock();
        level.blockList.add(block);
        assertEquals(1, level.blockList.size());
        assertEquals(block, level.blockList.get(0));
    }

    @Test
    public void testsaveLevel(){
        level = new Level(1);
        level.setLevelNumber(1);
        level.birdList.add(new Redbird());
        level.blockList.add(new WoodBlock());
        level.pigList.add(new SmallPig());
        AngryBirds1 angryBirds1 = new AngryBirds1();
        angryBirds1.saveLevel(level);
        assertFalse(AngryBirds1.isnotsaved());
    }
}
