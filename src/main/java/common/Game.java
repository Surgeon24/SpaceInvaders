package common;

import arenas.Arena;
import arenas.Arena_1;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
    Body of the game. Initialises arenas, draws all instances and checks user input.
 */
public class Game {
    private static Screen screen;
    private boolean runGame;
    MainMenu mainMenu = new MainMenu();
    List<Arena> allLevels = new ArrayList<Arena>();
    int currentLevel = 0;
    int FPS = 20;
    int frameTime = 1000 / FPS;

    public Game() {
        try {
            TerminalSize terminalSize = new TerminalSize(Globals.width, Globals.height);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
            terminalFactory.setForceAWTOverSwing(true);
            //terminalFactory.setTerminalEmulatorFontConfiguration(fontConfig);
            Terminal terminal = terminalFactory.createTerminal(); //new DefaultTerminalFactory().createTerminal();
            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null);     // we don't need a cursor
            screen.startScreen();               // screens must be started
            screen.doResizeIfNecessary();       // resize screen if necessary
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
                KeyStroke key = screen.pollInput();
                //KeyStroke key = screen.readInput();
                processKey(key);

                //crutch that removes delay in input, but forbid you to press two buttons simultaneously
                /*
                while (key != null){
                    key = screen.pollInput();
                }*/

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

    private void processKey (KeyStroke key){
        if (key == null){
            System.out.println("nothing pressed");
        }
        else {
            switch (key.getKeyType()) {
                case EOF -> runGame = false;
                case Character -> {
                    switch (key.getCharacter()) {
                        case 'q' -> runGame = false;
                        case 'a' -> {
                            if (allLevels.get(currentLevel).hero.getX() > 1)
                                allLevels.get(currentLevel).hero.setX(allLevels.get(currentLevel).hero.getX() - 1);
                        }
                        case 'd' -> {
                            if (allLevels.get(currentLevel).hero.getX() < Globals.width - 6)
                                allLevels.get(currentLevel).hero.setX(allLevels.get(currentLevel).hero.getX() + 1);
                        }
                        case ' ' -> allLevels.get(currentLevel).hero.shoot();
                    }
                }
            }
        }
    }
}
