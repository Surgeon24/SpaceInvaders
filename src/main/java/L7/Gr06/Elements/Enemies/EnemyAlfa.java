package L7.Gr06.Elements.Enemies;

import L7.Gr06.Common.Globals;
import L7.Gr06.Elements.Position;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class EnemyAlfa extends Enemy {
    public EnemyAlfa(Position pos, int vector) {
        super(pos, vector);
    }

@Override
    public void draw(TextGraphics s){
        s.setBackgroundColor(TextColor.Factory.fromString(Globals.bgColor));
        s.setForegroundColor(TextColor.Factory.fromString(Globals.textColor));
        s.putString(new TerminalPosition(getX(), getY()), "ab");
        s.putString(new TerminalPosition(getX(), getY()+1), "cd");
    }
}
