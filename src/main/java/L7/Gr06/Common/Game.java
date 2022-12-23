package L7.Gr06.Common;

import L7.Gr06.Audio.MusicPlayer;
import L7.Gr06.Audio.SoundPlayer;
import L7.Gr06.Arena.*;
import L7.Gr06.Elements.Hero;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;
import java.util.List;

public class Game {
    final GUI gui;
    private boolean runGame;
    private boolean gamePaused = true;
    private boolean openUpgrades = false;
    MainMenu mainMenu = new MainMenu();
    UpgradesMenu upgradesMenu = new UpgradesMenu();
    List<Arena> allLevels = new ArrayList<>();
    Prolog prolog = new Prolog();
    MenuBar menuBar = new MenuBar();
    int currentLevel = 0;
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
        allLevels.add(new Level1());
        allLevels.add(new Level2());
        allLevels.add(new Level3());
        allLevels.add(new Level4());
        allLevels.add(new Level5());
        allLevels.add(new Level6());
    }

    private void draw() {
        try {
            gui.screen.clear();
            allLevels.get(currentLevel).draw(gui.screen.newTextGraphics());
            menuBar.draw(gui.screen.newTextGraphics(), Hero.getHero().getLives(), currentLevel);
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
            upgradesMenu.showUpgrades(gui.screen);
            openUpgrades = false;
        }
    }

    private void checkIfLevelFinished() throws java.io.IOException {
        if (allLevels.get(currentLevel).enemiesReachedFinish()
                || Hero.getHero().getLives() < 1){
            try {
                showGameOverScreen();
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
                    showWinScreen();
                    Globals.maxLives = Globals.startLives;
                    allLevels.clear();
                    upgradesMenu.resetAll();
                    createListOfAllLevels();
                    musicPlayer.startInGameMusic();
                }
                else{
                    soundPlayer.playWellDone();
                    Thread.sleep(2000);
                    currentLevel++;
                }
            } catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    private void showWinScreen() throws java.io.IOException, java.lang.InterruptedException {
        TextGraphics tg = gui.screen.newTextGraphics();
        tg.enableModifiers(SGR.BOLD);
        tg.setForegroundColor(TextColor.Factory.fromString("#ede9dd"));
        tg.putString(new TerminalPosition(Globals.width/2-6, Globals.height/2-11), "              ");
        tg.putString(new TerminalPosition(Globals.width/2-6, Globals.height/2-10), "   YOU WON!   ");
        tg.putString(new TerminalPosition(Globals.width/2-6, Globals.height/2-9), "              ");
        gui.screen.refresh();
        currentLevel = 0;
        Globals.score = 0;
        musicPlayer.stopMusic();
        Thread.sleep(1000);
        soundPlayer.playVictorySound();
        Thread.sleep(3000);
        runGame = mainMenu.showMenu(gui.screen, MainMenu.STATUS.valueOf("WIN"));
    }

    private void showGameOverScreen() throws java.io.IOException, java.lang.InterruptedException {
        TextGraphics tg = gui.screen.newTextGraphics();
        tg.enableModifiers(SGR.BOLD);
        tg.setForegroundColor(TextColor.Factory.fromString("#ede9dd"));
        tg.putString(new TerminalPosition(Globals.width/2-6, Globals.height/2-11), "              ");
        tg.putString(new TerminalPosition(Globals.width/2-6, Globals.height/2-10), "  GAME OVER!  ");
        tg.putString(new TerminalPosition(Globals.width/2-6, Globals.height/2-9), "              ");
        gui.screen.refresh();
        currentLevel = 0;
        Globals.score = 0;
        soundPlayer.playGameOver();
        Thread.sleep(3000);
        musicPlayer.stopMusic();
        runGame = mainMenu.showMenu(gui.screen, MainMenu.STATUS.valueOf("GAMEOVER"));
    }

    private void processKeys (List<ACTION> actions){

        for (ACTION action : actions) {
            switch (action) {
                case LEFT -> {
                    if (Hero.getHero().getX() > 1)
                        Hero.getHero().setX(Hero.getHero().getX() - 1);
                }
                case RIGHT -> {
                    if (Hero.getHero().getX() < Globals.width - 3)
                        Hero.getHero().setX(Hero.getHero().getX() + 1);
                }
                case SHOOT -> Hero.getHero().shoot();
                case PAUSE -> gamePaused = true;
                case UPGRADE -> openUpgrades = true;
            }
        }
    }
}
