package L7.Gr06.Elements.Enemies;

import L7.Gr06.Elements.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EnemyAlfaTest {
    @Test
    public void collide(){
        EnemyAlfa enemy = new EnemyAlfa(new Position(4,4),1);
        Position b1 = new Position(15,15);
        Position b2 = new Position(4,4);
        Position b3 = new Position(5,4);
        Position b4 = new Position(4,5);
        assertFalse(enemy.collide(b1));
        assertTrue(enemy.collide(b2));
        assertTrue(enemy.collide(b3));
        assertTrue(enemy.collide(b4));
    }

    @Test
    public void draw() {
        // use mocks
    }

}
