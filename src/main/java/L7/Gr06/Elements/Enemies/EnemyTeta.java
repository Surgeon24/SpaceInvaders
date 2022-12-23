package L7.Gr06.Elements.Enemies;

import L7.Gr06.Audio.SoundPlayer;
import L7.Gr06.Common.Globals;
import L7.Gr06.Elements.Lightning;
import L7.Gr06.Elements.Position;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.Random;


public class EnemyTeta extends Enemy {
    public SoundPlayer soundPlayer = new SoundPlayer();
    public Integer value = 100;
    public Boolean ready = true;
    public Lightning lightning;
    public Integer counter = 0;

    public EnemyTeta(Position pos, int vector) {
        super(pos, vector);
    }
    @Override
    public Integer getValue() { return value;}
    @Override
    public Integer getHealth() { return health;}
    @Override
    public void setHealth(Integer health) {this.health = health;}
    @Override
    public void shoot(int randomNum){
        if (ready && randomNum >= 75){
            lightning = new Lightning(new Position(getX(),getY()+2), 1);
            addShot(lightning);
            counter = 5;
            ready = false;
            soundPlayer.playMonsterSound();
        }
        if (!ready){
            lightning.setX(getX());
            lightning.setStartOfTheLighting(getY()+2);
        }
        if (!ready && counter > 0)
            counter  --;
        else if (!ready && counter == 0){
            addShot(lightning);
            counter --;
        }
        else if (!ready && counter > -5)
            counter --;
        else if (!ready && counter == -5){
            counter = 5;
            lightning = null;
            ready = true;
            removeAllshots();
        }
    }

    @Override
    public void draw(TextGraphics s){
        s.setBackgroundColor(TextColor.Factory.fromString(Globals.bgColor));
        if (health == 5)
            s.setForegroundColor(TextColor.Factory.fromString(Globals.textColor));
        else
            s.setForegroundColor(TextColor.Factory.fromString("#db7e46"));
        s.putString(new TerminalPosition(getX(), getY()), "[\\");
        s.putString(new TerminalPosition(getX(), getY()+1), "]^");
        if (lightning != null){
            if (counter == 4 || counter == 2 || counter <= 0) {
                s.setForegroundColor(TextColor.Factory.fromString(Globals.textColor));
                lightning.draw(s);
            }
        }
    }
}
