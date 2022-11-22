package arenas;
/*
    Generic Arena for all game levels.
 */

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import common.Globals;
import elements.*;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.emptyList;

public class Arena {
                                        //colors
    String bgColor = "#117491";
    String fgColor = "#ede9dd";
                                        //instances
    public Hero hero = new Hero(new Position(Globals.width/2, Globals.height-2));

    public List<Enemy> enemies = new ArrayList<>();
    public List<Wall> walls = new ArrayList<>();
                                        //constructors
    public Arena(){}
                                        //instances behaviour
    public void changePositions(){}
    public void checkCollisions(){}
    public boolean enemiesRichedFinish(){return false;}

    public boolean nextLevel(){
        return enemies.equals(emptyList());
    }

    //if the draw function wouldn't be overridden, default graphic appears
    public void draw(TextGraphics graphics){
        graphics.setBackgroundColor(TextColor.Factory.fromString(bgColor));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(Globals.width, Globals.height), ' ');
        graphics.enableModifiers(SGR.BOLD);
        graphics.setForegroundColor(TextColor.Factory.fromString(fgColor));
        graphics.putString(new TerminalPosition(Globals.width/2-7, Globals.height/2), "default graphic");

    }
}
