package elements;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WallTest {
    @Test
    public void collide(){
        Wall wall = new Wall(new Position(5,5));
        Position b1 = new Position(15,15);
        Position b2 = new Position(4,4);
        Position b3 = new Position(6,5);
        Position b4 = new Position(5,8);
        assertFalse(wall.collide(b1));
        assertTrue(wall.collide(b2));
        assertTrue(wall.collide(b3));
        assertFalse(wall.collide(b4));
    }
}
