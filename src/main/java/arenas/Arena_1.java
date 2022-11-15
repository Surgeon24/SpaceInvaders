package arenas;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import common.Globals;
import elements.*;

import java.util.ArrayList;
import java.util.List;

public class Arena_1 extends Arena{
                                //constructors
    public Arena_1() {
        enemies = createEnemies();
    }
                                //instances initialisations
    private List<Enemy> createEnemies(){
        List<Enemy> list = new ArrayList<>();
        for (int i = 3; i < Globals.width;) {
            list.add(new Enemy(new Position(i, 6)));
            i += 10;
        }
        return list;
    }
                                //instances behaviour
    @Override
    public void changePositions(){
        for (Enemy enemy : enemies){
            if ((enemy.getX() > (2)) && (enemy.getX() < (Globals.width-6))){
                enemy.setX(enemy.getX()+enemy.getVector());
            }
            else {
                enemy.setY(enemy.getY()+3);
                enemy.setVector(enemy.getVector()*(-1));
                enemy.setX(enemy.getX()+enemy.getVector());
            }
        }
    }

    @Override
    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString(bgColor));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(Globals.width, Globals.height), ' ');
        graphics.enableModifiers(SGR.BOLD);

        graphics.setForegroundColor(TextColor.Factory.fromString(fgColor));
        graphics.putString(new TerminalPosition(Globals.width/2-7, 1), "~('w')~ Arena 1");
        graphics.putString(new TerminalPosition(Globals.width/2-14, 2), "A and D to go LEFT and RIGHT");
        graphics.putString(new TerminalPosition(Globals.width/2-17, 3), "SPACE to shoot. Q to exit the game");
        hero.draw(graphics);
        for (Enemy enemy : enemies){
            enemy.draw(graphics);
        }


    }
}
