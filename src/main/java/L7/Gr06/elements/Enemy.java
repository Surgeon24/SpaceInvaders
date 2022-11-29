package L7.Gr06.elements;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;


public class Enemy extends Instance{
    String color = "#2daaff";
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
        s.setBackgroundColor(TextColor.Factory.fromString("#117491"));
        s.setForegroundColor(TextColor.Factory.fromString("#ffffff"));
        s.putString(new TerminalPosition(getX(), getY()), "ab");
        s.putString(new TerminalPosition(getX(), getY()+1), "cd");


        // OLD VERSION
        /*
        s.setBackgroundColor(TextColor.Factory.fromString(color));
        s.enableModifiers(SGR.BOLD);
        s.putString(new TerminalPosition(getX(), getY()), "   ");
        s.putString(new TerminalPosition(getX()+1,getY()+1)," ");
         */
    }
}
