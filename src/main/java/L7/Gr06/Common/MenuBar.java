package L7.Gr06.Common;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class MenuBar {

    public void draw(TextGraphics s, Integer lives, Integer level) {
        s.setBackgroundColor(TextColor.Factory.fromString(Globals.bgColor));
        s.setForegroundColor(TextColor.Factory.fromString(Globals.textColor));
        s.enableModifiers(SGR.BOLD);
        s.putString(new TerminalPosition(2, 0), "LIVES: " + lives);
        s.putString(new TerminalPosition(Globals.width / 2 - 3, 0), "LEVEL " + (level + 1));
        s.putString(new TerminalPosition(Globals.width - 13, 0), "SCORE: " + Globals.score);
    }
}
