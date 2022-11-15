package arenas;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import common.Globals;
import elements.*;

public class Arena_1 extends Arena{


    @Override
    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString(bgColor));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(Globals.width, Globals.height), ' ');
        graphics.enableModifiers(SGR.BOLD);

        graphics.setForegroundColor(TextColor.Factory.fromString(fgColor));
        graphics.putString(new TerminalPosition(Globals.width/2-7, 1), "~('w')~ Arena 1");
        graphics.putString(new TerminalPosition(Globals.width/2-14, 2), "A and D to go LEFT and RIGHT");
        graphics.putString(new TerminalPosition(Globals.width/2-17, 3), "SPACE to shoot. Q to exit the game");
        hero.draw(graphics);


    }
}
