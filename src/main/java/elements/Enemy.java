package elements;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Enemy extends Instance{
    String color = "#2daaff";
    int vector = 1;

    public Enemy(Position pos) {
        super(pos);
    }

    public int getVector(){return vector;}
    public void setVector(int newV){vector=newV;}

    public boolean collide(Position object){
        int obX = object.getX();
        int obY = object.getY();
        int enX = this.getX();
        int enY = this.getY();
        if ((enX <= obX && enX+2 >= obX) && (enY <= obY && enY+2 >= obY))
            return true;
        return false;
    }
    public void draw(TextGraphics s){
        s.setBackgroundColor(TextColor.Factory.fromString(color));
        s.enableModifiers(SGR.BOLD);
        s.putString(new TerminalPosition(getX(), getY()), "   ");
        s.putString(new TerminalPosition(getX()+1,getY()+1)," ");
    }
}
