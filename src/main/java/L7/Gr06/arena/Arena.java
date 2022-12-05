package L7.Gr06.arena;
/*
    Generic Arena for all game levels.
 */

import L7.Gr06.elements.Enemies.Enemy;
import L7.Gr06.elements.Hero;
import L7.Gr06.elements.Position;
import L7.Gr06.elements.Wall;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import L7.Gr06.common.Globals;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.emptyList;

public class Arena {
    Integer score = 0;
                                        //instances
    public Hero hero = new Hero(new Position(Globals.width/2, Globals.height-2));

    public List<Enemy> enemies = new ArrayList<>();
    public List<Wall> walls = new ArrayList<>();
                                        //constructors
    public Arena(){}

    public Integer getScore(){ return score; }
                                        //instances behaviour
    public void changePositions(){}
    public void checkCollisions(){}
    public boolean enemiesReachedFinish(){return false;}

    public boolean nextLevel(){
        return enemies.equals(emptyList());
    }

    //if the draw function wouldn't be overridden, default graphic appears
    public void draw(TextGraphics graphics){
        graphics.setBackgroundColor(TextColor.Factory.fromString(Globals.bgColor));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(Globals.width, Globals.height), ' ');
        graphics.enableModifiers(SGR.BOLD);
        graphics.setForegroundColor(TextColor.Factory.fromString(Globals.textColor));
        graphics.putString(new TerminalPosition(Globals.width/2-7, Globals.height/2), "default graphic");

    }
}
