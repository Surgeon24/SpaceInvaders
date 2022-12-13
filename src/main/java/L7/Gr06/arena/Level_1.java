package L7.Gr06.arena;

import L7.Gr06.elements.Enemies.Enemy;
import L7.Gr06.elements.Enemies.EnemyAlfa;
import L7.Gr06.elements.Enemies.EnemyTeta;
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

public class Level_1 extends Arena{
    private long moveEnemyTimer;
    private long moveEnemySpeed = 700;
    public Level_1() {
        enemies = createEnemies();
        walls = createWalls();
    }
    private List<Enemy> createEnemies(){
        List<Enemy> list = new ArrayList<>();
        for (int i = 1; i < Globals.width-3; i+=6) {
            list.add(new EnemyAlfa(new Position(i, 6),1));
            list.add(new EnemyAlfa(new Position(i, 9),-1));
        }
        return list;
    }

    private List<Wall> createWalls(){
        List<Wall> list = new ArrayList<>();
        for (int i = 5; i < Globals.width; i+=20)
            list.add(new Wall(new Position(i,Globals.height - 8)));
        return list;
    }

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
        hero.draw(graphics);
        for (Enemy enemy : enemies){
            enemy.draw(graphics);
        }
        for (Wall wall : walls){
            wall.draw(graphics);
        }


    }
}
