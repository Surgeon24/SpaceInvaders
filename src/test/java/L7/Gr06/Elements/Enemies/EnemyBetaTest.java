package L7.Gr06.Elements.Enemies;

import L7.Gr06.Elements.Bullet;
import L7.Gr06.Elements.Position;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class EnemyBetaTest {
    
    @Test
    public void enemyBetaShoot(){
        Position pos = new Position(0, 0);
        Position pos2 = new Position(0, 1);
        EnemyBeta enemyBeta = new EnemyBeta(pos, 1);
        Bullet expectedShot = new Bullet(pos2, 1); // stub

        int shoot = 100;    // probability to shoot
        enemyBeta.shoot(shoot);

        List<Bullet> lst = enemyBeta.getShots();
        assertEquals(lst.get(0), expectedShot);
    }
}
