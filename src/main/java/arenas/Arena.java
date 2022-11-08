package arenas;

/*
    Generic Arena for all game levels.
 */

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Arena {
    int length = 80;
    int high   = 24;
    String bgColor = "#117491";
    String fgColor = "#ede9dd";

    public Arena(){
        //constructor
    }

    public void draw(TextGraphics graphics){
        graphics.setBackgroundColor(TextColor.Factory.fromString(bgColor));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(length, high), ' ');
        graphics.enableModifiers(SGR.BOLD);
        graphics.setForegroundColor(TextColor.Factory.fromString(fgColor));
        graphics.putString(new TerminalPosition(30, 10), "default graphic");
    }
}
