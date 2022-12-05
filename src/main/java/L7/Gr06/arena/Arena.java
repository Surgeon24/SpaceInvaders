package L7.Gr06.arena;

import L7.Gr06.elements.Bullet;
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
    public Integer score = 0;
    public Hero hero = new Hero(new Position(Globals.width/2, Globals.height-2));
    public List<Enemy> enemies = new ArrayList<>();
    public List<Wall> walls = new ArrayList<>();

    public Arena(){}
    public Integer getScore(){ return score; }
    public void changePositions(){
        for (Enemy enemy : enemies) {
            if ((enemy.getVector() == 1 && enemy.getX() >= Globals.width-3)
                || (enemy.getVector() == -1 && enemy.getX() <= 1)) {
                enemy.setY(enemy.getY() + 3);
                enemy.setVector(enemy.getVector() * (-1));
            } else {
                enemy.setX(enemy.getX() + enemy.getVector());
            }
            enemy.shoot();
        }
    }
    public void checkCollisions(){
        List<Enemy> deadEnemies = new ArrayList<>();
        List<Bullet> goodPlayerShots = new ArrayList<>();
        List<Bullet> goodEnemyShots = new ArrayList<>();
        List<Wall> brokenWalls = new ArrayList<>();
        //check collisions hero's bullets
        for (Bullet shot : hero.getShots()){
            for (Enemy enemy: enemies){
                if (enemy.collide(shot.getPosition())){
                    deadEnemies.add(enemy);
                    goodPlayerShots.add(shot);
                    score += enemy.getValue();
                }
            }
            for (Wall wall : walls){
                if (wall.collide(shot.getPosition())){
                    if (wall.getStrength() == 0)
                        brokenWalls.add(wall);
                    goodPlayerShots.add(shot);
                }
            }
        }
        for (Enemy enemy : deadEnemies){
            enemies.remove(enemy);
        }
        for (Bullet shot : goodPlayerShots){
            hero.getShots().remove(shot);
        }
        //check collisions enemy's bullets
        for(Enemy enemy : enemies){
            for (Bullet shot : enemy.getShots()){
                if (hero.collide(shot.getPosition())){
                    hero.subtractLive();
                    goodEnemyShots.add(shot);
                }
                for (Wall wall : walls){
                    if (wall.collide(shot.getPosition())){
                        goodEnemyShots.add(shot);
                        if (wall.getStrength() == 0)
                            brokenWalls.add(wall);
                    }
                }
            }
            for (Bullet shot : goodEnemyShots){
                enemy.getShots().remove(shot);
            }
            goodEnemyShots.clear();
        }
        for (Wall wall : brokenWalls){
            walls.remove(wall);
        }
    }
    public boolean enemiesReachedFinish(){
        for (Enemy enemy : enemies) {
            if (enemy.getY() > Globals.height-8){
                return true;
            }
        }
        return false;
    }
    public boolean nextLevel(){
        return enemies.equals(emptyList());
    }
    public void draw(TextGraphics graphics){
        graphics.setBackgroundColor(TextColor.Factory.fromString(Globals.bgColor));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(Globals.width, Globals.height), ' ');
        graphics.enableModifiers(SGR.BOLD);
        graphics.setForegroundColor(TextColor.Factory.fromString(Globals.textColor));
    }
}
