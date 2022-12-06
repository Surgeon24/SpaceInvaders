package L7.Gr06.elements;

import L7.Gr06.common.Globals;
import L7.Gr06.elements.Instance;
import L7.Gr06.elements.Position;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class MenuBar  {

    public void draw(TextGraphics s, Integer lives, Integer score, Integer level){
        s.setBackgroundColor(TextColor.Factory.fromString(Globals.bgColor));
        s.setForegroundColor(TextColor.Factory.fromString(Globals.textColor));
        s.enableModifiers(SGR.BOLD);
        s.putString(new TerminalPosition(2, 0), "LIVES: " + lives);
        s.putString(new TerminalPosition(Globals.width/2-7, 0), "~('W')~ LEVEL " + (level+1));
        s.putString(new TerminalPosition(Globals.width-13, 0), "SCORE: " + score);
    }
}
