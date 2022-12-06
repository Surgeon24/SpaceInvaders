package L7.Gr06.common;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class About {
    public void showAbout(Screen screen) {
        try {
            screen.clear();
            draw(screen.newTextGraphics());
            screen.refresh();
            screen.readInput();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString(Globals.bgColor));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(Globals.width, Globals.height), ' ');
        graphics.enableModifiers(SGR.BOLD);
        graphics.setForegroundColor(TextColor.Factory.fromString(Globals.textColor));
        graphics.putString(new TerminalPosition(2, 2), "ab  ef  jk  nopq");
        graphics.putString(new TerminalPosition(2, 3), "cd  gh  lm  rstu");
        graphics.putString(new TerminalPosition(Globals.width/2-9, Globals.height/2-6), "SPACE INVADERS V0.1");
        graphics.putString(new TerminalPosition(4, Globals.height/2), "SPACE INVADERS IS A TEXT BASED ARCADE GAME,");
        graphics.putString(new TerminalPosition(4, Globals.height/2+1), "THAT MAKE A REFERENCE TO THE GAME SERIES OF THE SAME TITLE,");
        graphics.putString(new TerminalPosition(4, Globals.height/2+2), "TAKING BEST MOMENTS FROM THOSE GAMES");
        graphics.putString(new TerminalPosition(4, Globals.height/2+3), "AND ADAPTING IT TO MODERN REALITIES.");
        graphics.putString(new TerminalPosition(Globals.width/2-4, Globals.height/2+5), "CONTROLS:");
        graphics.putString(new TerminalPosition(4, Globals.height/2+6), "'A' AND 'D' TO MOVE LEFT AND RIGHT");
        graphics.putString(new TerminalPosition(4, Globals.height/2+7), "'SPACE' TO SHOOT");
        graphics.putString(new TerminalPosition(4, Globals.height/2+9), "TRY TO GAIN MORE POINTS AND WATCH OUT ON YOUR LIVES.");
        graphics.putString(new TerminalPosition(4, Globals.height/2+10), "GOOD LUCK!");
        graphics.putString(new TerminalPosition(Globals.width-30, Globals.height-2), "PRESS ANY KEY TO RETURN...");
    }
}
