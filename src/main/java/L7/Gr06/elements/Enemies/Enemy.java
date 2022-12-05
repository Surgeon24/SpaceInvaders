package L7.Gr06.elements.Enemies;

import L7.Gr06.elements.Bullet;
import L7.Gr06.elements.Instance;
import L7.Gr06.elements.Position;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.ArrayList;
import java.util.List;

public abstract class Enemy extends Instance {
    int vector = 1;
    private List<Bullet> shots = new ArrayList<>();

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

    public List<Bullet> getShots(){ return shots;}
    public void addShot(Bullet newShot){ shots.add(newShot);}

    @Override
    public void draw(TextGraphics s){
    }
}
