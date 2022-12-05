package L7.Gr06.elements;

import L7.Gr06.common.Globals;
import L7.Gr06.elements.Instance;
import L7.Gr06.elements.Position;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class MenuBar extends Instance {
    public MenuBar(Position pos){
        super(pos);
    }

    public void draw(TextGraphics s, Integer lives, Integer score, Integer level){
        s.setBackgroundColor(TextColor.Factory.fromString(Globals.bgColor));
        s.setForegroundColor(TextColor.Factory.fromString(Globals.textColor));
        s.enableModifiers(SGR.BOLD);
        s.putString(new TerminalPosition(getX()+2, getY()), "LIVES: " + lives);
        s.putString(new TerminalPosition(Globals.width/2-7, getY()), "~('W')~ LEVEL " + (level+1));
        s.putString(new TerminalPosition(Globals.width-13, getY()), "SCORE: " + score);
    }
}
