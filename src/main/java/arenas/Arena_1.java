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
    private long moveEnemyTimer;
    private long moveEnemySpeed = 300;
                                //constructors
    public Arena_1() {
        enemies = createEnemies();
        walls = createWalls();
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

    private List<Wall> createWalls(){
        List<Wall> list = new ArrayList<>();
        list.add(new Wall(new Position(10,10)));
        return list;
    }
                                //instances behaviour
    @Override
    public void changePositions(){
        long currentTime = System.currentTimeMillis();
        if (currentTime > moveEnemyTimer + moveEnemySpeed) {
            for (Enemy enemy : enemies) {
                if ((enemy.getX() > (2)) && (enemy.getX() < (Globals.width - 6))) {
                    enemy.setX(enemy.getX() + enemy.getVector());
                } else {
                    enemy.setY(enemy.getY() + 3);
                    enemy.setVector(enemy.getVector() * (-1));
                    enemy.setX(enemy.getX() + enemy.getVector());
                }
            }
            moveEnemyTimer = System.currentTimeMillis();
        }
    }

    @Override
    public void checkCollisions(){
        //check collisions hero's bullets with enemies
        List<Enemy> deadEnemies = new ArrayList<>();
        List<Bullet> goodShots = new ArrayList<>();
        List<Wall> brokenWalls = new ArrayList<>();
        for (Bullet shot : hero.getShots()){
            for (Enemy enemy: enemies){
                if (enemy.collide(shot.getPosition())){
                    deadEnemies.add(enemy);
                    goodShots.add(shot);
                }
            }
            for (Wall wall : walls){
                if (wall.collide(shot.getPosition())){
                    if (wall.getStrength() == 0)
                        brokenWalls.add(wall);
                    goodShots.add(shot);
                }
            }
        }
        for (Enemy enemy : deadEnemies){
            enemies.remove(enemy);
        }
        for (Bullet shot : goodShots){
            List<Bullet> tmp = hero.getShots();
            tmp.remove(shot);
            hero.setShots(tmp);
        }
        for (Wall wall : brokenWalls){
            walls.remove(wall);
        }
        //check collisions enemy's bullets with hero (not implemented yet)
    }
    @Override
    public boolean enemiesRichedFinish(){
        for (Enemy enemy : enemies) {
            if (enemy.getY() == Globals.height-10){
                return true;
            }
        }
        return false;
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
        for (Wall wall : walls){
            wall.draw(graphics);
        }


    }
}
