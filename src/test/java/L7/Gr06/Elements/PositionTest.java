package L7.Gr06.Elements;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PositionTest {
    @Test
    public void setX() throws NoSuchFieldException, IllegalAccessException {
        final Position pos = new Position(0, 0);
        final Field field = pos.getClass().getDeclaredField("x");
        field.setAccessible(true);
        field.set(pos, 5);
        assertEquals(pos.getX(), 5);
    }

    @Test
    public void setY() throws NoSuchFieldException, IllegalAccessException {
        final Position pos = new Position(0, 0);
        final Field field = pos.getClass().getDeclaredField("y");
        field.setAccessible(true);
        field.set(pos, 5);
        assertEquals(5, pos.getY());
    }

    @Test
    public void getX() {
        final Position pos = new Position(5, 0);
        assertEquals(5, pos.getX());
    }

    @Test
    public void getY() {
        final Position pos = new Position(0, 2);
        assertEquals(2, pos.getY());
    }

    @Test
    public void equals() {
        Position pos1 = new Position(3, 5);
        Position pos2 = new Position(5, 3);
        Position pos3 = new Position(3, 3);
        Position pos4 = new Position(3, 5);
        Assertions.assertNotEquals(pos1, pos2);
        Assertions.assertNotEquals(pos1, pos3);
        Assertions.assertEquals(pos1, pos4);
    }
}
