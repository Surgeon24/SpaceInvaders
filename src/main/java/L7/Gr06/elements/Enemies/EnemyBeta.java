package L7.Gr06.elements.Enemies;

import L7.Gr06.common.Globals;
import L7.Gr06.elements.Bullet;
import L7.Gr06.elements.Position;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.Random;


public class EnemyBeta extends Enemy {
    Integer value = 20;
    Integer health = 2;
    Random rand = new Random();

    public EnemyBeta(Position pos, int vector) {
        super(pos, vector);
    }
    @Override
    public Integer getValue() { return value;}
    @Override
    public Integer getHealth() { return health;}
    @Override
    public void setHealth(Integer health) {this.health = health;}
@Override
    public void shoot(){
        if (rand.nextInt(100) > 90){
            Position pos = new Position(getX(), getY() + 1);
            Bullet newShot = new Bullet(pos, 1);
            addShot(newShot);
        }
    }

    @Override
    public void draw(TextGraphics s){
        s.setBackgroundColor(TextColor.Factory.fromString(Globals.bgColor));
        if (health == 2)
            s.setForegroundColor(TextColor.Factory.fromString(Globals.textColor));
        else
            s.setForegroundColor(TextColor.Factory.fromString("#db7e46"));
        s.putString(new TerminalPosition(getX(), getY()), "jk");
        s.putString(new TerminalPosition(getX(), getY()+1), "lm");
        if (!getShots().isEmpty()){
            for (Bullet shot : getShots()){
                shot.draw(s);
            }
        }
    }
}
