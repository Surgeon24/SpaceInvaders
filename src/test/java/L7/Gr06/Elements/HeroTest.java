package L7.Gr06.Elements;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
public class HeroTest {

    @Test
    public void shootTest() {
        final Position pos = new Position(0, 0);
        final Position pos2 = new Position(0, -2);
        Hero hero = new Hero(pos);
        Bullet expectedShot = new Bullet(pos2, -1);

        hero.shoot();
        List<Bullet> lst = hero.getShots();

        assertEquals(lst.get(0), expectedShot);
    }

    @Test
    public void collideTest() {
        Hero hero = new Hero(new Position(4, 4));
        Position b1 = new Position(15, 15);
        Position b2 = new Position(4, 4);
        Position b3 = new Position(5, 4);
        Position b4 = new Position(4, 5);
        assertFalse(hero.collide(b1));
        assertTrue(hero.collide(b2));
        assertTrue(hero.collide(b3));
        assertTrue(hero.collide(b4));
    }
}