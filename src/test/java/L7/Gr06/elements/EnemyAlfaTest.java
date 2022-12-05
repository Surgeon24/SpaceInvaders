package L7.Gr06.elements;

import L7.Gr06.elements.Enemies.EnemyAlfa;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EnemyAlfaTest {

    @Test
    public void enemyConstructor() {
        EnemyAlfa enemy = new EnemyAlfa(new Position(1,1),1);
    }

    @Test
    public void collide(){
        EnemyAlfa enemy = new EnemyAlfa(new Position(4,4),1);
        Position b1 = new Position(15,15);
        Position b2 = new Position(4,4);
        Position b3 = new Position(6,4);
        Position b4 = new Position(4,6);
        assertFalse(enemy.collide(b1));
        assertTrue(enemy.collide(b2));
        assertTrue(enemy.collide(b3));
        assertTrue(enemy.collide(b4));


    }
}
