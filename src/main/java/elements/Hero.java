package elements;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.ArrayList;
import java.util.List;

public class Hero extends Instance {
    String color = "#2d94cc";
    private List<Bullet> shots = new ArrayList<>();
    public Hero(Position pos) {
        super(pos);
    }

    public void shoot(){
        Position pos = new Position(getX()+2, getY()-3);
        Bullet newShot = new Bullet(pos, -1);
        shots.add(newShot);
    }
    public void draw(TextGraphics s){
        s.setBackgroundColor(TextColor.Factory.fromString(color));
        s.enableModifiers(SGR.BOLD);
        s.putString(new TerminalPosition(getX(), getY()), "     ");
        s.putString(new TerminalPosition(getX()+1, getY()-1), "   ");
        s.putString(new TerminalPosition(getX()+2,getY()-2)," ");
        if (!shots.isEmpty()){
            for (Bullet shot : shots){
                shot.draw(s);
            }
        }
    }
}
