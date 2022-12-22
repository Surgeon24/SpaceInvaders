package L7.Gr06.Elements.Enemies;

import L7.Gr06.Common.Globals;
import L7.Gr06.Elements.Bullet;
import L7.Gr06.Elements.Position;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EnemyDeltaTest {
    public static Position pos;
    public static Position pos2;
    public static EnemyDelta enemyDelta;
    public static EnemyDelta enemyDeltaWrong;

    @BeforeAll
    public static void setUp() {
        pos = new Position(Globals.width / 2, -3);
        enemyDelta = new EnemyDelta(pos, 1);
        pos2 = new Position(30, -2);
        enemyDeltaWrong = new EnemyDelta(new Position(40, 40), 1);
    }

    @Test
    public void shootTest() {

        Bullet expectedShot = new Bullet(pos2, 1); // stub

        int shoot = 100;    // set probability to shoot
        enemyDelta.shoot(shoot);

        List<Bullet> lst = enemyDelta.getShots();
        assertEquals(lst.get(0).getX(), expectedShot.getX());
        assertEquals(lst.get(0).getY(), expectedShot.getY());
    }

    @Test
    public void collideTest() {
        assert (pos.getX() <= enemyDelta.getX() && pos.getX() + 2 >= enemyDelta.getX()) &&
                (pos.getY() <= enemyDelta.getY() && pos.getY() + 2 >= enemyDelta.getY());

        assert !((pos.getX() <= enemyDeltaWrong.getX() && pos.getX() + 2 >= enemyDeltaWrong.getX()) &&
                (pos.getY() <= enemyDeltaWrong.getY() && pos.getY() + 2 >= enemyDeltaWrong.getY()));
    }
}

