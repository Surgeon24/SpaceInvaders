package L7.Gr06.common;

import L7.Gr06.Audio.MusicPlayer;
import L7.Gr06.Audio.SoundPlayer;
import L7.Gr06.arena.*;
import L7.Gr06.elements.MenuBar;
import com.googlecode.lanterna.TerminalPosition;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;
import java.util.List;

public class Game {
    private final GUI gui;
    private boolean runGame;
    private boolean gamePaused = true;
    private boolean openUpgrades = false;
    MainMenu mainMenu = new MainMenu();
    UpgradesMenu upgradesMenu = new UpgradesMenu();
    List<Arena> allLevels = new ArrayList<>();
    Prolog prolog = new Prolog();
    MenuBar menuBar = new MenuBar();
    int currentLevel = 5;
    int lastLevel = 5;
    int FPS = 20;
    int frameTime = 1000 / FPS;
    MusicPlayer musicPlayer = new MusicPlayer();
    SoundPlayer soundPlayer = new SoundPlayer();




    enum ACTION {LEFT, RIGHT, PAUSE, SHOOT, UPGRADE}

    public Game() throws FontFormatException, IOException, URISyntaxException {
        this.gui = new GUI(Globals.width, Globals.height);
        runGame = true;
        createListOfAllLevels();
    }

    private void createListOfAllLevels(){
        allLevels.add(new Level_1());
        allLevels.add(new Level_2());
        allLevels.add(new Level_3());
        allLevels.add(new Level_4());
        allLevels.add(new Level_5());
        allLevels.add(new Level_6());
    }

    private void draw() {
        try {
            gui.screen.clear();
            allLevels.get(currentLevel).draw(gui.screen.newTextGraphics());
            menuBar.draw(gui.screen.newTextGraphics(), allLevels.get(currentLevel).hero.getLives(), currentLevel);
            gui.screen.refresh();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    public void run() {
        if (!mainMenu.showMenu(gui.screen, MainMenu.STATUS.valueOf("START")))
            runGame = false;
        gamePaused = false;
        if (runGame)
            prolog.showProlog(gui.screen);
        musicPlayer.startInGameMusic();
        try {
            while (runGame) {
                checkIfGamePaused();
                long startTime = System.currentTimeMillis();
                draw();
                List<ACTION> actions = gui.getNextActions();
                processKeys(actions);
                allLevels.get(currentLevel).changePositions();
                allLevels.get(currentLevel).checkCollisions();
                checkIfLevelFinished();
                long elapsedTime = System.currentTimeMillis() - startTime;
                long sleepTime = frameTime - elapsedTime;
                try {
                    if (sleepTime > 0) Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            gui.screen.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            musicPlayer.stopMusic();
        }
    }

    private void checkIfGamePaused(){
        if (gamePaused) {
            if (!mainMenu.showMenu(gui.screen, MainMenu.STATUS.valueOf("RESUME")))
                runGame = false;
            gamePaused = false;
        }
        if (openUpgrades) {
            upgradesMenu.showUpgrades(gui.screen, allLevels.get(currentLevel).hero);
            openUpgrades = false;
        }
    }

    private void checkIfLevelFinished(){
        if (allLevels.get(currentLevel).enemiesReachedFinish()
                || allLevels.get(currentLevel).hero.getLives() < 1){
            try {
                gui.screen.newTextGraphics().putString(new TerminalPosition(Globals.width/2-4, Globals.height/2), "GAME OVER!");
                currentLevel = 0;
                Globals.score = 0;
                soundPlayer.playGameOver();
                Thread.sleep(2000);
                musicPlayer.stopMusic();
                runGame = mainMenu.showMenu(gui.screen, MainMenu.STATUS.valueOf("GAMEOVER"));
                Globals.maxLives = Globals.startLives;
                allLevels.clear();
                upgradesMenu.resetAll();
                createListOfAllLevels();
                musicPlayer.startInGameMusic();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (allLevels.get(currentLevel).nextLevel()) {
            try {
                if (lastLevel == currentLevel) {
                    gui.screen.newTextGraphics().putString(new TerminalPosition(Globals.width / 2 - 4, Globals.height / 2), "GAME OVER!");
                    currentLevel = 0;
                    Globals.score = 0;
                    soundPlayer.playGameOver();
                    Thread.sleep(2000);
                    musicPlayer.stopMusic();
                    runGame = mainMenu.showMenu(gui.screen, MainMenu.STATUS.valueOf("WIN"));
                    Globals.maxLives = Globals.startLives;
                    allLevels.clear();
                    upgradesMenu.resetAll();
                    createListOfAllLevels();
                    musicPlayer.startInGameMusic();
                }
                else{
                    soundPlayer.playWellDone();
                    Thread.sleep(2000);
                    allLevels.get(currentLevel + 1).hero = allLevels.get(currentLevel).hero;
                    currentLevel++;
                }
            } catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    private void processKeys (List<ACTION> actions){

        for (ACTION action : actions) {
            switch (action) {
                case LEFT -> {
                    if (allLevels.get(currentLevel).hero.getX() > 1)
                        allLevels.get(currentLevel).hero.setX(allLevels.get(currentLevel).hero.getX() - 1);
                }
                case RIGHT -> {
                    if (allLevels.get(currentLevel).hero.getX() < Globals.width - 3)
                        allLevels.get(currentLevel).hero.setX(allLevels.get(currentLevel).hero.getX() + 1);
                }
                case SHOOT -> allLevels.get(currentLevel).hero.shoot();
                case PAUSE -> gamePaused = true;
                case UPGRADE -> openUpgrades = true;
            }
        }
    }
}
