package L7.Gr06.elements;

import L7.Gr06.common.Globals;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Lightning extends Bullet {
    Integer startOfTheLighting;
    public Lightning(Position pos, int direction) {
        super(new Position(pos.getX(), Globals.height-1), direction);
        startOfTheLighting = pos.getY();
    }
    public void setStartOfTheLighting(Integer x) {startOfTheLighting = x;}

    @Override
    public void draw(TextGraphics s){
        s.setBackgroundColor(TextColor.Factory.fromString(Globals.bgColor));
        //s.setForegroundColor(TextColor.Factory.fromString(Globals.textColor));
        s.enableModifiers(SGR.BOLD);
        for (int i= startOfTheLighting; i < Globals.height; i++){
            if (i % 2 == 0)
                s.putString(new TerminalPosition(getX(), i), "_");
            else
                s.putString(new TerminalPosition(getX(), i), "`");
        }
    }
    public void drawNothing(TextGraphics s) {}
}
