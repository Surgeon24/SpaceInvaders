package L7.Gr06.Elements.Enemies;

import L7.Gr06.Common.Globals;
import L7.Gr06.Elements.Position;
import org.junit.jupiter.api.Test;

public class EnemyGammaTest {
    @Test
    public void collideTest() {
        Position pos = new Position(Globals.width / 2, -3);
        EnemyDelta enemyDelta = new EnemyDelta(pos, 1);

        Position pos2 = new Position(Globals.width, -3);
        EnemyDelta enemyDelta2 = new EnemyDelta(pos2, 1);

        assert (pos.getX() <= enemyDelta.getX() && pos.getX() + 3 >= enemyDelta.getX()) &&
                (pos.getY() <= enemyDelta.getY() && pos.getY() + 1 >= enemyDelta.getY());

        assert !((enemyDelta2.getX() <= enemyDelta.getX() && enemyDelta2.getX() + 3 >= enemyDelta.getX()) &&
                (enemyDelta2.getY() <= enemyDelta.getY() && enemyDelta2.getY() + 1 >= enemyDelta.getY()));
    }
}
