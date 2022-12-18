package L7.Gr06.elements;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Wall extends Instance{
    String color = "#8c92ac";
    private int strength = 5;
    public Wall(Position pos) {
        super(pos);
    }

    public int getStrength(){return strength;}

    public boolean collide(Position object){
        if     ((getX() <= object.getX() && getX() + 4 >= object.getX()) &&
                getY() == object.getY()){
            strength --;
            return true;
        }
        return false;
    }
@Override
    public void draw(TextGraphics s){
        s.setBackgroundColor(TextColor.Factory.fromString(color));
        s.enableModifiers(SGR.BOLD);
        s.putString(new TerminalPosition(getX(), getY()), "     ");
    }
}
