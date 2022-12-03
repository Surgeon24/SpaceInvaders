package L7.Gr06.arenas;

import L7.Gr06.elements.Bullet;
import L7.Gr06.elements.Enemies.Enemy;
import L7.Gr06.elements.Enemies.EnemyAlfa;
import L7.Gr06.elements.Enemies.EnemyBeta;
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
    private long moveEnemySpeed = 800;
                                //constructors
    public Level_1() {
        enemies = createEnemies();
        walls = createWalls();
    }
                                //instances initialisations
    private List<Enemy> createEnemies(){
        List<Enemy> list = new ArrayList<>();
        for (int i = 3; i < Globals.width; i+=10) {
            list.add(new EnemyAlfa(new Position(i, 6),1));
            //list.add(new EnemyAlfa(new Position(i-1, 9),-1));
            list.add(new EnemyBeta(new Position(i-1, 9),-1));
        }
        return list;
    }

    private List<Wall> createWalls(){
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
            for (Enemy enemy : enemies) {
                if ((enemy.getX() > (1)) && (enemy.getX() < (Globals.width - 3))) {
                    enemy.setX(enemy.getX() + enemy.getVector());
                } else {
                    enemy.setY(enemy.getY() + 3);
                    enemy.setVector(enemy.getVector() * (-1));
                    enemy.setX(enemy.getX() + enemy.getVector());
                }
                enemy.shoot();
            }
            moveEnemyTimer = System.currentTimeMillis();
        }
    }

    @Override
    public void checkCollisions(){
        List<Enemy> deadEnemies = new ArrayList<>();
        List<Bullet> goodShots = new ArrayList<>();
        List<Wall> brokenWalls = new ArrayList<>();
        for (Bullet shot : hero.getShots()){
            for (Enemy enemy: enemies){
                if (enemy.collide(shot.getPosition())){
                    deadEnemies.add(enemy);
                    goodShots.add(shot);
                    score += 10;
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
    }
    @Override
    public boolean enemiesReachedFinish(){
        for (Enemy enemy : enemies) {
            if (enemy.getY() == Globals.height-8){
                return true;
            }
        }
        return false;
    }
    @Override
    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString(Globals.bgColor));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(Globals.width, Globals.height), ' ');
        graphics.enableModifiers(SGR.BOLD);

        graphics.setForegroundColor(TextColor.Factory.fromString(Globals.textColor));
        graphics.putString(new TerminalPosition(Globals.width/2-4, 3), "WORM UP!");
        hero.draw(graphics);
        for (Enemy enemy : enemies){
            enemy.draw(graphics);
        }
        for (Wall wall : walls){
            wall.draw(graphics);
        }


    }
}
