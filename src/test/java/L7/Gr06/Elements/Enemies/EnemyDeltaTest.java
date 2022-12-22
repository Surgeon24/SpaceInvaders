package L7.Gr06.Elements.Enemies;

import L7.Gr06.Common.Globals;
import L7.Gr06.Elements.Bullet;
import L7.Gr06.Elements.Position;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EnemyDeltaTest {
    @Test
    public void enemyDeltaShoot(){
        Position pos = new Position(Globals.width/2, -3);
        EnemyDelta enemyDelta = new EnemyDelta(pos, 1);

        Position pos2 = new Position(30, -2);
        Bullet expectedShot = new Bullet(pos2, 1); // stub

        int shoot = 100;    // set probability to shoot
        enemyDelta.shoot(shoot);

        List<Bullet> lst = enemyDelta.getShots();
        assertEquals(lst.get(0).getX(), expectedShot.getX());
        assertEquals(lst.get(0).getY(), expectedShot.getY());
    }
}
