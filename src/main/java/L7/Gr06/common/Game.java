package L7.Gr06.common;

import L7.Gr06.arena.*;
import L7.Gr06.elements.MenuBar;
import L7.Gr06.elements.Position;
import com.googlecode.lanterna.TerminalPosition;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;
import java.util.List;



/***
    Body of the game. Initialises arenas, draws all instances and checks user input.
 ***/

public class Game {
    private final GUI gui;
    private boolean runGame;
    private boolean gamePaused = true;
    MainMenu mainMenu = new MainMenu();
    List<Arena> allLevels = new ArrayList<>();
    MenuBar menuBar = new MenuBar(new Position(0,0));
    int currentLevel = 0;
    int lastLevel = 3;
    int FPS = 20;
    int frameTime = 1000 / FPS;
    Integer totalScore = 0;



    enum ACTION {LEFT, RIGHT, PAUSE, SHOOT}

    public Game() throws FontFormatException, IOException, URISyntaxException {
        this.gui = new GUI(Globals.width, Globals.height);
        runGame = true;
        //to simplify rotation to the next level we create ordered list of all levels in the game.
        createListOfAllLevels();
    }

    private void createListOfAllLevels(){
        allLevels.add(new Level_1());
        allLevels.add(new Level_2());
        allLevels.add(new Level_3());
        allLevels.add(new Level_4());
    }

    private void draw() {
        try {
            gui.screen.clear();
            allLevels.get(currentLevel).draw(gui.screen.newTextGraphics());
            menuBar.draw(gui.screen.newTextGraphics(), allLevels.get(currentLevel).hero.getLives(),totalScore+allLevels.get(currentLevel).getScore(), currentLevel);
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

        try {
            while (runGame) {
                if (gamePaused) {
                    if (!mainMenu.showMenu(gui.screen, MainMenu.STATUS.valueOf("RESUME")))
                        runGame = false;
                    gamePaused = false;
                }
                long startTime = System.currentTimeMillis();
                draw();
                List<ACTION> actions = gui.getNextActions();
                processKeys(actions);

                allLevels.get(currentLevel).changePositions();
                allLevels.get(currentLevel).checkCollisions();
                if (allLevels.get(currentLevel).enemiesReachedFinish()
                        || allLevels.get(currentLevel).hero.getLives() < 1){
                    try {
                        gui.screen.newTextGraphics().putString(new TerminalPosition(Globals.width/2-4, Globals.height/2), "GAME OVER!");
                        currentLevel = 0;
                        totalScore = 0;
                        Thread.sleep(2000);
                        runGame = mainMenu.showMenu(gui.screen, MainMenu.STATUS.valueOf("GAMEOVER"));
                        allLevels.clear();
                        createListOfAllLevels();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                if (allLevels.get(currentLevel).nextLevel()){
                    if (lastLevel == currentLevel){
                        try {
                            gui.screen.newTextGraphics().putString(new TerminalPosition(Globals.width/2-4, Globals.height/2), "GAME OVER!");
                            currentLevel = 0;
                            totalScore = 0;
                            Thread.sleep(2000);
                            runGame = mainMenu.showMenu(gui.screen, MainMenu.STATUS.valueOf("WIN"));
                            allLevels.clear();
                            createListOfAllLevels();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    else {
                        totalScore += allLevels.get(currentLevel).getScore();
                        currentLevel++;
                    }
                }

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
    }

    private void processKeys (List<ACTION> actions){

        for (ACTION action : actions) {
            switch (action) {
                case LEFT -> {
                    if (allLevels.get(currentLevel).hero.getX() > 1)
                        allLevels.get(currentLevel).hero.setX(allLevels.get(currentLevel).hero.getX() - 1);
                }
                case RIGHT -> {
                    if (allLevels.get(currentLevel).hero.getX() < Globals.width - 2)
                        allLevels.get(currentLevel).hero.setX(allLevels.get(currentLevel).hero.getX() + 1);
                }
                case SHOOT -> allLevels.get(currentLevel).hero.shoot();
                case PAUSE -> gamePaused = true;
            }
        }
    }
}
