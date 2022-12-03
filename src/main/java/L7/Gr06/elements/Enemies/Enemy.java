package L7.Gr06.elements.Enemies;

import L7.Gr06.common.Globals;
import L7.Gr06.elements.Instance;
import L7.Gr06.elements.Position;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public abstract class Enemy extends Instance {
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

    public void shoot(){}

    @Override
    public void draw(TextGraphics s){
    }
}
