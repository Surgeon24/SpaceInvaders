package L7.Gr06.elements;

import L7.Gr06.elements.Position;
import L7.Gr06.elements.Wall;
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
        Position b5 = new Position(5,5);
        assertFalse(wall.collide(b1));
        assertFalse(wall.collide(b2));
        assertTrue(wall.collide(b3));
        assertFalse(wall.collide(b4));
        assertTrue(wall.collide(b5));
    }
}
