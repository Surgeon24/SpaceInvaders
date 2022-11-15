package elements;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Enemy extends Instance{
    String color = "#2daaff";

    Enemy(Position pos) {
        super(pos);
    }


    public void draw(TextGraphics s){
        s.setBackgroundColor(TextColor.Factory.fromString(color));
        s.enableModifiers(SGR.BOLD);
        s.putString(new TerminalPosition(getX(), getY()), "   ");
        s.putString(new TerminalPosition(getX()-1, getY()-1), "   ");
        s.putString(new TerminalPosition(getX()-2,getY()-2)," ");
    }
}
