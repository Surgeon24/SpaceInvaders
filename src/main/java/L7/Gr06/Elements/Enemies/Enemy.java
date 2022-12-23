package L7.Gr06.Elements.Enemies;

import L7.Gr06.Elements.Bullet;
import L7.Gr06.Elements.Instance;
import L7.Gr06.Elements.Position;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.ArrayList;
import java.util.List;

public abstract class Enemy extends Instance {
    public int vector = 1;
    public Integer health = 1;
    public Integer value = 10;
    private List<Bullet> shots = new ArrayList<>();

    public Enemy(Position pos, int vector) {
        super(pos);
        this.vector = vector;
    }

    public int getVector() {
        return vector;
    }

    public void setVector(int newV) {
        vector = newV;
    }

    public Integer getValue() {
        return value;
    }

    public Integer getHealth() {
        return health;
    }

    public void setHealth(Integer health) {
        this.health = health;
    }

    @Override
    public boolean collide(Position object) {
        return (getX() <= object.getX() && getX() + 1 >= object.getX()) &&
                (getY() <= object.getY() && getY() + 1 >= object.getY());
    }

    public void shoot(int randomNum) {
    }

    public List<Bullet> getShots() {
        return shots;
    }

    public void addShot(Bullet newShot) {
        shots.add(newShot);
    }

    public void removeAllshots() {
        shots.clear();
    }

}
