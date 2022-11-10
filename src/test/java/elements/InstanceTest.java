package elements;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.Terminal;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InstanceTest {
    @Test
    public void setPosition() throws NoSuchFieldException, IllegalAccessException{
        Position pos1 = new Position(0,0);
        final Instance el = new Instance(pos1);
        Position pos2 = new Position(8,9);
        el.setPosition(pos2);
        final Field field = el.getClass().getDeclaredField("position");
        field.setAccessible(true);
        assertEquals(field.get(el), pos2);
    }

    @Test
    public void setX() {
        Position pos = new Position(0,0);
        final Instance el = new Instance(pos);
        el.setX(10);
        assertEquals(10, el.getX());
    }

    @Test
    public void setY() {
        Position pos = new Position(0,0);
        final Instance el = new Instance(pos);
        el.setY(1);
        assertEquals(1, el.getY());
    }

    @Test
    public void getPosition() throws NoSuchFieldException, IllegalAccessException{
        Position pos1 = new Position(0,0);
        final Instance el = new Instance(pos1);
        final Field field = el.getClass().getDeclaredField("position");
        field.setAccessible(true);
        Position pos2 = new Position(5,6);
        el.setPosition(pos2);
        field.set(el, pos2);
        assertEquals(el.getPosition(), pos2);
    }

    @Test
    public void getX() throws NoSuchFieldException, IllegalAccessException{
        Position pos1 = new Position(0,0);
        final Instance el = new Instance(pos1);
        final Field field = el.getClass().getDeclaredField("position");
        field.setAccessible(true);
        Position pos2 = new Position(5,6);
        el.setPosition(pos2);
        field.set(el, pos2);
        assertEquals(el.getPosition().getX(), 5);
    }

    @Test
    public void getY() throws NoSuchFieldException, IllegalAccessException{
        Position pos1 = new Position(0,0);
        final Instance el = new Instance(pos1);
        final Field field = el.getClass().getDeclaredField("position");
        field.setAccessible(true);
        Position pos2 = new Position(5,6);
        el.setPosition(pos2);
        field.set(el, pos2);
        assertEquals(el.getPosition().getY(), 6);
    }
}
