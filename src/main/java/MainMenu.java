import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class MainMenu {
    private Boolean check = true;
    private int options = 0;
    String bgColor       = "#112491";
    String selectedColor = "#ede9dd";
    String idleColor     = "#968e5a";
    //default size of the window. Can't be changed
    private int length = 80;
    private int high = 24;

    public boolean showMenu(Screen screen) {
        try {
            while (check) {
                screen.clear();
                draw(screen.newTextGraphics());
                screen.refresh();
                KeyStroke key = screen.readInput();
                processKey(key);
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        check = true;
        if (options == 0){
            return true;
        } else if (options == 1) {
            //call method instructions
            System.out.println("nothing to be shown");
            return true;
        }
        return false;
    }


    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString(bgColor));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(length, high), ' ');
        graphics.enableModifiers(SGR.BOLD);
        graphics.setForegroundColor(TextColor.Factory.fromString(selectedColor));
        graphics.putString(new TerminalPosition(30, 10), "Space Invaders v0.1");
        if (options == 0) graphics.setForegroundColor(TextColor.Factory.fromString(selectedColor));
        else graphics.setForegroundColor(TextColor.Factory.fromString(idleColor));
        graphics.putString(new TerminalPosition(35, 13), "START GAME");
        if (options == 1) graphics.setForegroundColor(TextColor.Factory.fromString(selectedColor));
        else graphics.setForegroundColor(TextColor.Factory.fromString(idleColor));
        graphics.putString(new TerminalPosition(35, 16), "INSTRUCTIONS");
        if (options == 2) graphics.setForegroundColor(TextColor.Factory.fromString(selectedColor));
        else graphics.setForegroundColor(TextColor.Factory.fromString(idleColor));
        graphics.putString(new TerminalPosition(35, 19), "EXIT");
    }

    private void processKey(KeyStroke key) {
        switch (key.getKeyType()) {
            case EOF -> check = false;
            case ArrowUp -> options = (options-1) % 3;
            case ArrowDown -> options = (options+1) % 3;
            case Enter -> check = false;
            }
        }
    }

