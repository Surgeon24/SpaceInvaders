package L7.Gr06.elements;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Bullet extends Instance {
    String color = "#2d24ff";
    String bg = "#117491";
    int direction;
    Bullet(Position pos, int direction) {
        super(pos);
        this.direction = direction;
    }
@Override
    public void draw(TextGraphics s){
        s.setBackgroundColor(TextColor.Factory.fromString(bg));
        s.setForegroundColor(TextColor.Factory.fromString(color));
        s.enableModifiers(SGR.BOLD);
        s.putString(new TerminalPosition(getX(), getY()), "*");
        setY(getY()+direction);
    }
}
