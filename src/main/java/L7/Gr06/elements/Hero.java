package L7.Gr06.elements;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.ArrayList;
import java.util.List;

public class Hero extends Instance {
    String color = "#2d94cc";
    private List<Bullet> shots = new ArrayList<>();
    private long gunTimer;
    private long gunSpeed = 300;
    public Hero(Position pos) {
        super(pos);
    }

    public List<Bullet> getShots(){
        return shots;
    }

    public void setShots(List<Bullet> netList){
        this.shots = netList;
    }
    public void shoot(){
        long currentTime = System.currentTimeMillis();
        if (currentTime > gunTimer + gunSpeed) {
            Position pos = new Position(getX() + 2, getY() - 3);
            Bullet newShot = new Bullet(pos, -1);
            shots.add(newShot);
            gunTimer = System.currentTimeMillis();
        }
    }
    @Override
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
