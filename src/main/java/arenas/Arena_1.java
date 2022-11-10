package arenas;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import elements.*;

public class Arena_1 extends Arena{


    @Override
    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString(bgColor));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(length, high), ' ');
        graphics.enableModifiers(SGR.BOLD);

        graphics.setForegroundColor(TextColor.Factory.fromString(fgColor));
        graphics.putString(new TerminalPosition(30, 1), "~('w')~ Arena 1");
        graphics.putString(new TerminalPosition(25, 2), "A and D to go LEFT and RIGHT");
        graphics.putString(new TerminalPosition(22, 3), "SPACE to shoot. Q to exit the game");
        hero.draw(graphics);


    }
}
