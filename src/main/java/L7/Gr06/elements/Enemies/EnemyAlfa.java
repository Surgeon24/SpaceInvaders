package L7.Gr06.elements.Enemies;

import L7.Gr06.common.Globals;
import L7.Gr06.elements.Instance;
import L7.Gr06.elements.Position;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;


public class EnemyAlfa extends Enemy {
    int vector = 1;

    public EnemyAlfa(Position pos, int vector) {
        super(pos, vector);
    }

    public boolean collide(Position object){
        return  (getX() <= object.getX() && getX() + 2 >= object.getX()) &&
                (getY() <= object.getY() && getY() + 2 >= object.getY());
    }

@Override
    public void draw(TextGraphics s){
        s.setBackgroundColor(TextColor.Factory.fromString(Globals.bgColor));
        s.setForegroundColor(TextColor.Factory.fromString(Globals.textColor));
        s.putString(new TerminalPosition(getX(), getY()), "ab");
        s.putString(new TerminalPosition(getX(), getY()+1), "cd");
    }
}
