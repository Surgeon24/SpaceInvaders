package L7.Gr06.common;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class MainMenu {



    private Boolean buttonPressed = false;
    private int options = 0;
    String selectedColor = "#ede9dd";
    String idleColor     = "#968e5a";

    private boolean inGame = false;

    public boolean showMenu(Screen screen) {
        About about = new About();
        try {
            while (true) {
                screen.clear();
                draw(screen.newTextGraphics());
                screen.refresh();
                    // warning - key refresh should be implemented to avoid blinking of the screen
                KeyStroke key = screen.readInput();
                processKey(key);
                if (buttonPressed){
                    buttonPressed = false;
                    if (options == 0){
                        inGame = true;
                        return true;
                    } else if (options == 1) {
                        about.showAbout(screen);
                    }
                    else {
                        return false;
                    }
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return false;

    }


    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString(Globals.bgColor));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(Globals.width, Globals.height), ' ');
        graphics.enableModifiers(SGR.BOLD);
        graphics.setForegroundColor(TextColor.Factory.fromString(selectedColor));
        graphics.putString(new TerminalPosition(2, 2), "ab  ef");
        graphics.putString(new TerminalPosition(2, 3), "cd  gh");

        graphics.putString(new TerminalPosition(Globals.width/2-9, Globals.height/2-6), "SPACE INVADERS V0.1");
        if (options == 0) graphics.setForegroundColor(TextColor.Factory.fromString(selectedColor));
        else graphics.setForegroundColor(TextColor.Factory.fromString(idleColor));
        if (inGame)
            graphics.putString(new TerminalPosition(Globals.width/2-5, Globals.height/2), "RESUME GAME");
        else
            graphics.putString(new TerminalPosition(Globals.width/2-5, Globals.height/2), "START GAME");

        if (options == 1) graphics.setForegroundColor(TextColor.Factory.fromString(selectedColor));
        else graphics.setForegroundColor(TextColor.Factory.fromString(idleColor));
        graphics.putString(new TerminalPosition(Globals.width/2-5, Globals.height/2+3), "ABOUT GAME");
        if (options == 2) graphics.setForegroundColor(TextColor.Factory.fromString(selectedColor));
        else graphics.setForegroundColor(TextColor.Factory.fromString(idleColor));
        graphics.putString(new TerminalPosition(Globals.width/2-2, Globals.height/2+6), "EXIT");
    }

    private void processKey(KeyStroke key) {
        switch (key.getKeyType()) {
            case EOF -> buttonPressed = true;
            case ArrowUp -> options = (options-1) % 3;
            case ArrowDown -> options = (options+1) % 3;
            case Enter -> buttonPressed = true;
            }
        }
    }

