package common;

import arenas.Arena;
import arenas.Arena_1;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFrame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.*;


/***
    Body of the game. Initialises arenas, draws all instances and checks user input.
 ***/

public class Game {
    private static Screen screen;
    private boolean runGame;
    MainMenu mainMenu = new MainMenu();
    List<Arena> allLevels = new ArrayList<Arena>();
    int currentLevel = 0;
    int FPS = 30;
    int frameTime = 1000 / FPS;

    Set<Integer> pressedKeys = new HashSet<>();

    enum ACTION {LEFT, RIGHT, QUIT, SHOOT}

    public Game() {
        try {
            TerminalSize terminalSize = new TerminalSize(Globals.width, Globals.height);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
            terminalFactory.setForceAWTOverSwing(true);
            Terminal terminal = terminalFactory.createTerminal();
            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null);     // we don't need a cursor
            screen.startScreen();               // screens must be started
            screen.doResizeIfNecessary();       // resize screen if necessary
            //definition of a pressed button for work with input
            ((AWTTerminalFrame)terminal).getComponent(0).addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    pressedKeys.add(e.getKeyCode());
                }
                @Override
                public void keyReleased(KeyEvent e) {
                    pressedKeys.remove(e.getKeyCode());
                }
            });

            runGame = true;
            //to simplify rotation to the next level we create ordered list of all levels in the game.
            createListOfAllLevels();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createListOfAllLevels(){
        allLevels.add(new Arena_1());
        //allLevels.add(new Arena_2());
    }

    private void draw() {
        try {
            screen.clear();
            //draw all instances on the level
            allLevels.get(currentLevel).draw(screen.newTextGraphics());

            //draw all global information (not implemented yet)
            //mainbar.draw(arena.score, screen.newTextGraphics());
            screen.refresh();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    public void run() {
        try {
            //if showMenu returns False - exit the game, if True - continue
            if (!mainMenu.showMenu(screen))
                runGame = false;

            while (runGame) {
                long startTime = System.currentTimeMillis();
                draw();
                List<ACTION> actions = getNextActions();
                processKeys(actions);

                allLevels.get(currentLevel).changePositions();
                allLevels.get(currentLevel).checkCollisions();
                if (allLevels.get(currentLevel).enemiesRichedFinish()){
                    System.out.println("GAME OVER!");
                }

                long elapsedTime = System.currentTimeMillis() - startTime;
                long sleepTime = frameTime - elapsedTime;
                try {
                    if (sleepTime > 0) Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                }
            }
            screen.close();
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

    private List<ACTION> getNextActions (){
        List<ACTION> actions = new LinkedList<>();

        if (pressedKeys.contains(KeyEvent.VK_Q)) actions.add(ACTION.QUIT);
        if (pressedKeys.contains(KeyEvent.VK_D)) actions.add(ACTION.RIGHT);
        if (pressedKeys.contains(KeyEvent.VK_A)) actions.add(ACTION.LEFT);
        if (pressedKeys.contains(KeyEvent.VK_SPACE)) actions.add(ACTION.SHOOT);

        return actions;
    }




}
