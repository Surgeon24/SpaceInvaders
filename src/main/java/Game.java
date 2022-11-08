import arenas.Arena;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.SimpleTerminalResizeListener;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.TerminalResizeListener;

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

    public Game() {
        try {
            Terminal terminal = new DefaultTerminalFactory().createTerminal();
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
        allLevels.add(new Arena());
        //allLevels.add(new Arena_1());
    }

    private void draw() {
        try {
            screen.clear();
            //draw all instances on the level
            allLevels.get(currentLevel).draw(screen.newTextGraphics());
            System.out.println("cehck3\n");
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
                draw();
                KeyStroke key = screen.readInput();
                processKey(key);
            }
            screen.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processKey (KeyStroke key){
        switch (key.getKeyType()){
            case EOF -> runGame = false;
            case Character -> {
                switch (key.getCharacter()){
                    case 'q' -> runGame = false;
                }
            }
        }
    }
}
