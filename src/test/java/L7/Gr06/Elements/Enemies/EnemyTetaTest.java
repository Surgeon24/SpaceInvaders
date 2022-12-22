package L7.Gr06.Elements.Enemies;

import L7.Gr06.Elements.Bullet;
import L7.Gr06.Elements.Lightning;
import L7.Gr06.Elements.Position;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EnemyTetaTest {

    @Test
    public void shootTest() {
        EnemyTeta enemyTeta = new EnemyTeta(new Position(0, 2), 1);
        Lightning expectedLightning = new Lightning(new Position(0, 2), 1); // stub
        expectedLightning.setStartOfTheLighting(4);

        enemyTeta.shoot(100); // probability to shoot

        List<Bullet> lst = enemyTeta.getShots();
        Lightning shotLight = (Lightning) lst.get(0);

        assertEquals(shotLight.getStartOfTheLighting(), expectedLightning.getStartOfTheLighting());
        assertEquals(lst.get(0).getX(), expectedLightning.getX());
        assertEquals(lst.get(0).getY(), expectedLightning.getY());
        assertEquals(lst.get(0), expectedLightning);
    }

    @Test
    public void collide() {
        EnemyTeta enemy = new EnemyTeta(new Position(4, 4), 1);
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