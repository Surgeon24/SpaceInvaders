package elements;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Wall extends Instance{
    String color = "#2dccff";
    private int strength = 3;
    public Wall(Position pos) {
        super(pos);
    }

    public int getStrength(){return strength;}

    public boolean collide(Position object){
        if     ((getX() <= object.getX() && getX() + 2 >= object.getX()) &&
                (getY() <= object.getY() && getY() + 2 >= object.getY())){
            strength --;
            return true;
        }
        return false;
    }

    public void draw(TextGraphics s){
        s.setBackgroundColor(TextColor.Factory.fromString(color));
        s.enableModifiers(SGR.BOLD);
        s.putString(new TerminalPosition(getX(), getY()), "   ");
    }
}
