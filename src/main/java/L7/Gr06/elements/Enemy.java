package L7.Gr06.elements;

import L7.Gr06.common.Globals;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;


public class Enemy extends Instance{
    int vector = 1;


    public Enemy(Position pos, int vector) {
        super(pos);
        this.vector = vector;
    }

    public int getVector(){return vector;}
    public void setVector(int newV){vector=newV;}


    public boolean collide(Position object){
        return  (getX() <= object.getX() && getX() + 2 >= object.getX()) &&
                (getY() <= object.getY() && getY() + 2 >= object.getY());
    }

@Override
    public void draw(TextGraphics s){
        //NEW VERSION
        s.setBackgroundColor(TextColor.Factory.fromString(Globals.bgColor));
        s.setForegroundColor(TextColor.Factory.fromString(Globals.textColor));
        s.putString(new TerminalPosition(getX(), getY()), "ab");
        s.putString(new TerminalPosition(getX(), getY()+1), "cd");
    }
}
