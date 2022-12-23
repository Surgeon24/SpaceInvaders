package L7.Gr06.Arena;

import L7.Gr06.Elements.Bullet;
import L7.Gr06.Elements.Enemies.Enemy;
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
import java.util.Random;

import static java.util.Collections.emptyList;

public abstract class Arena {
    public Random rand = new Random();
    public Hero hero = new Hero(new Position(Globals.width / 2, Globals.height - 2));
    public List<Enemy> enemies = new ArrayList<>();
    public List<Wall> walls = new ArrayList<>();

    public Arena() {
    }

    public void changePositions() {
        for (Enemy enemy : enemies) {
            int randomNum = rand.nextInt(100);
            if ((enemy.getVector() == 1 && enemy.getX() >= Globals.width - 4)
                    || (enemy.getVector() == -1 && enemy.getX() <= 2)) {
                enemy.setY(enemy.getY() + 3);
                enemy.setVector(enemy.getVector() * (-1));
            } else {
                enemy.setX(enemy.getX() + enemy.getVector());
            }
            enemy.shoot(randomNum);
        }
    }

    public void checkCollisions() {
        List<Enemy> deadEnemies = new ArrayList<>();
        List<Bullet> goodPlayerShots = new ArrayList<>();
        List<Bullet> goodEnemyShots = new ArrayList<>();
        List<Wall> brokenWalls = new ArrayList<>();
        heroShotsCollision(deadEnemies, goodPlayerShots, brokenWalls);
        enemyShotsCollision(goodEnemyShots, brokenWalls);
        for (Enemy enemy : deadEnemies)
            enemies.remove(enemy);
        for (Bullet shot : goodPlayerShots)
            hero.getShots().remove(shot);
        for (Wall wall : brokenWalls)
            walls.remove(wall);
    }

    private void heroShotsCollision(List<Enemy> deadEnemies, List<Bullet> goodPlayerShots, List<Wall> brokenWalls) {
        for (Bullet shot : hero.getShots()) {
            for (Enemy enemy : enemies) {
                if (enemy.collide(shot.getPosition())) {
                    if (enemy.getHealth() <= hero.getGunPower()) {
                        deadEnemies.add(enemy);
                        Globals.score += enemy.getValue();
                    } else enemy.setHealth(enemy.getHealth() - hero.getGunPower());
                    goodPlayerShots.add(shot);
                }
            }
            for (Wall wall : walls) {
                if (wall.collide(shot.getPosition())) {
                    if (wall.getStrength() == 0)
                        brokenWalls.add(wall);
                    goodPlayerShots.add(shot);
                }
            }
            if (shot.getY() < -10)
                goodPlayerShots.add(shot);
        }
    }

    private void enemyShotsCollision(List<Bullet> goodEnemyShots, List<Wall> brokenWalls) {
        for (Enemy enemy : enemies) {
            for (Bullet shot : enemy.getShots()) {
                if (hero.collide(shot.getPosition())) {
                    hero.changeLives(-1);
                    goodEnemyShots.add(shot);
                }
                for (Wall wall : walls) {
                    if (wall.collide(shot.getPosition())) {
                        goodEnemyShots.add(shot);
                        if (wall.getStrength() == 0)
                            brokenWalls.add(wall);
                    }
                }
                if (shot.getY() > Globals.height + 10)
                    goodEnemyShots.add(shot);
            }
            for (Bullet shot : goodEnemyShots) {
                enemy.getShots().remove(shot);
            }
            goodEnemyShots.clear();
        }
    }

    public boolean enemiesReachedFinish() {
        for (Enemy enemy : enemies) {
            if (enemy.getY() > Globals.height - 8) {
                return true;
            }
        }
        return false;
    }

    public boolean nextLevel() {
        return enemies.equals(emptyList());
    }

    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString(Globals.bgColor));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(Globals.width, Globals.height), ' ');
        graphics.enableModifiers(SGR.BOLD);
        graphics.setForegroundColor(TextColor.Factory.fromString(Globals.textColor));
    }
}