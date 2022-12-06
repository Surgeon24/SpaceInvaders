package L7.Gr06.elements.Enemies;

import L7.Gr06.common.Globals;
import L7.Gr06.elements.Bullet;
import L7.Gr06.elements.Position;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class EnemyGamma extends Enemy {
    Integer value = 50;
    Integer health = 5;

    public EnemyGamma(Position pos, int vector) {
        super(pos, vector);
    }
    @Override
    public Integer getValue() { return value;}
    @Override
    public Integer getHealth() { return health;}
    @Override
    public void setHealth(Integer health) {this.health = health;}

    @Override
    public boolean collide(Position object){
        return  (getX() <= object.getX() && getX() + 3 >= object.getX()) &&
                (getY() <= object.getY() && getY() + 3 >= object.getY());
    }
    @Override
    public void draw(TextGraphics s){
        s.setBackgroundColor(TextColor.Factory.fromString(Globals.bgColor));
        s.setForegroundColor(TextColor.Factory.fromString(Globals.textColor));
        s.putString(new TerminalPosition(getX(), getY()), "nopq");
        s.putString(new TerminalPosition(getX(), getY()+1), "rstu");
        if (!getShots().isEmpty()){
            for (Bullet shot : getShots()){
                shot.draw(s);
            }
        }
    }
}
