package L7.Gr06.Elements.Enemies;

import L7.Gr06.Elements.Bullet;
import L7.Gr06.Elements.Lightning;
import L7.Gr06.Elements.Position;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EnemyTetaTest {

    @Test
    public void enemyBetaShoot(){
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
}
