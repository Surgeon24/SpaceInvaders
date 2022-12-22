package L7.Gr06.Elements.Enemies;

import L7.Gr06.Elements.Bullet;
import L7.Gr06.Elements.Position;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EnemyBetaTest {

    @Test
    public void shootTest() {
        Position pos = new Position(0, 0);
        Position pos2 = new Position(0, 1);
        EnemyBeta enemyBeta = new EnemyBeta(pos, 1);
        Bullet expectedShot = new Bullet(pos2, 1); // stub

        int shoot = 100;    // probability to shoot
        enemyBeta.shoot(shoot);

        List<Bullet> lst = enemyBeta.getShots();
        assertEquals(lst.get(0), expectedShot);
    }

    @Test
    public void collide() {
        EnemyBeta enemy = new EnemyBeta(new Position(4, 4), 1);
        Position b1 = new Position(15, 15);
        Position b2 = new Position(4, 4);
        Position b3 = new Position(5, 4);
        Position b4 = new Position(4, 5);
        assertFalse(enemy.collide(b1));
        assertTrue(enemy.collide(b2));
        assertTrue(enemy.collide(b3));
        assertTrue(enemy.collide(b4));
    }
}
