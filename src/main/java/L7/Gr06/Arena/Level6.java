package L7.Gr06.Arena;

import L7.Gr06.Elements.Enemies.*;
import L7.Gr06.Elements.Hero;
import L7.Gr06.Elements.Position;
import L7.Gr06.Elements.Wall;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import L7.Gr06.Common.Globals;

import java.util.ArrayList;
import java.util.List;

public class Level6 extends Arena{
    protected long moveEnemySpeed = 200;
    public Level6() {
        enemies = createEnemies();
        walls = createWalls();
    }
    protected List<Enemy> createEnemies(){
        List<Enemy> list = new ArrayList<>();
        list.add(new EnemyDelta(new Position(Globals.width/2, -3),1));
        for (int i = 3; i < Globals.width-3; i+=6) {
            list.add(new EnemyBeta(new Position(i, 6),1));
            if (i == 3 || i == 27 || i == 51)
                list.add(new EnemyTeta(new Position(i, 9),-1));
            else
                list.add(new EnemyBeta(new Position(i, 9),-1));
            list.add(new EnemyAlfa(new Position(i, 12), 1));
            if (i > 5)
                list.add(new EnemyGamma(new Position(i, 15),-1));
            list.add(new EnemyGamma(new Position(i, 18),1));

        }
        return list;
    }

    protected List<Wall> createWalls(){
        List<Wall> list = new ArrayList<>();
        for (int i = 5; i < Globals.width; i+=20)
            list.add(new Wall(new Position(i,Globals.height - 8)));
        return list;
    }
    //instances behaviour
    @Override
    public void changePositions(){
        long currentTime = System.currentTimeMillis();
        if (currentTime > moveEnemyTimer + moveEnemySpeed) {
            super.changePositions();
            moveEnemyTimer = System.currentTimeMillis();
        }
    }
    @Override
    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString(Globals.bgColor));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(Globals.width, Globals.height), ' ');
        graphics.enableModifiers(SGR.BOLD);
        graphics.setForegroundColor(TextColor.Factory.fromString(Globals.textColor));
        Hero.getHero().draw(graphics);
        for (Enemy enemy : enemies){
            enemy.draw(graphics);
        }
        for (Wall wall : walls){
            wall.draw(graphics);
        }


    }
}
