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



    private Boolean check = true;
    private int options = 0;
    String bgColor       = "#112491";
    String selectedColor = "#ede9dd";
    String idleColor     = "#968e5a";

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
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(Globals.width, Globals.height), ' ');
        graphics.enableModifiers(SGR.BOLD);
        graphics.setForegroundColor(TextColor.Factory.fromString(selectedColor));
        graphics.putString(new TerminalPosition(Globals.width/5, Globals.height/2), "ab  ef");
        graphics.putString(new TerminalPosition(Globals.width/5, Globals.height/2+1), "cd  gh");

        graphics.putString(new TerminalPosition(Globals.width/2-9, Globals.height/2-6), "SPACE INVADERS V0.1");
        if (options == 0) graphics.setForegroundColor(TextColor.Factory.fromString(selectedColor));
        else graphics.setForegroundColor(TextColor.Factory.fromString(idleColor));
        graphics.putString(new TerminalPosition(Globals.width/2-5, Globals.height/2), "START GAME");
        if (options == 1) graphics.setForegroundColor(TextColor.Factory.fromString(selectedColor));
        else graphics.setForegroundColor(TextColor.Factory.fromString(idleColor));
        graphics.putString(new TerminalPosition(Globals.width/2-6, Globals.height/2+3), "INSTRUCTIONS");
        if (options == 2) graphics.setForegroundColor(TextColor.Factory.fromString(selectedColor));
        else graphics.setForegroundColor(TextColor.Factory.fromString(idleColor));
        graphics.putString(new TerminalPosition(Globals.width/2-2, Globals.height/2+6), "EXIT");
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

