import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import java.io.IOException;

/*
    Body of the game. Initialises arenas, draws all instances and checks user input.
 */
public class Game {
    private static Screen screen;
    private boolean runGame;

    public Game() {
        try {
            Terminal terminal = new DefaultTerminalFactory().createTerminal();
            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null);     // we don't need a cursor
            screen.startScreen();               // screens must be started
            screen.doResizeIfNecessary();       // resize screen if necessary
            runGame = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void draw() {
        try {
            screen.clear();
            //future implements:
            //arena.draw(screen.newTextGraphics());
            //mainbar.draw(arena.score, screen.newTextGraphics());
            screen.refresh();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void run() {
        try {
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
