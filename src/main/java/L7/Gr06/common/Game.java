package L7.Gr06.common;

import L7.Gr06.arenas.Arena;
import L7.Gr06.arenas.Arena_1;
import L7.Gr06.arenas.Arena_2;

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
    MainMenu mainMenu = new MainMenu();
    List<Arena> allLevels = new ArrayList<>();
    int currentLevel = 0;
    int FPS = 30;
    int frameTime = 1000 / FPS;



    enum ACTION {LEFT, RIGHT, QUIT, SHOOT}

    public Game() throws FontFormatException, IOException, URISyntaxException {
        this.gui = new GUI(Globals.width, Globals.height);
        runGame = true;
        //to simplify rotation to the next level we create ordered list of all levels in the game.
        createListOfAllLevels();
    }

    private void createListOfAllLevels(){
        allLevels.add(new Arena_1());
        allLevels.add(new Arena_2());
    }

    private void draw() {
        try {
            gui.screen.clear();
            //draw all instances on the level
            allLevels.get(currentLevel).draw(gui.screen.newTextGraphics());

            //draw all global information (not implemented yet)
            //mainbar.draw(arena.score, screen.newTextGraphics());
            gui.screen.refresh();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    public void run() {
        try {
            //if showMenu returns False - exit the game, if True - continue
            if (!mainMenu.showMenu(gui.screen))
                runGame = false;


            while (runGame) {
                long startTime = System.currentTimeMillis();
                draw();
                List<ACTION> actions = gui.getNextActions();
                processKeys(actions);

                allLevels.get(currentLevel).changePositions();
                allLevels.get(currentLevel).checkCollisions();
                if (allLevels.get(currentLevel).enemiesReachedFinish()){
                    System.out.println("GAME OVER!");
                }
                if (allLevels.get(currentLevel).nextLevel()){
                    if (currentLevel == 1)
                        System.out.println("Congrats! You finished the game!");
                    else
                        currentLevel++;
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
                    if (allLevels.get(currentLevel).hero.getX() < Globals.width - 6)
                        allLevels.get(currentLevel).hero.setX(allLevels.get(currentLevel).hero.getX() + 1);
                }
                case SHOOT -> allLevels.get(currentLevel).hero.shoot();
                case QUIT -> runGame = false;
            }
        }
    }





}
