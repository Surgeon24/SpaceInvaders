package L7.Gr06.Elements;

import L7.Gr06.Common.Globals;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

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

    @Test
    public void drawTest(){
        TextGraphics tg = Mockito.mock(TextGraphics.class);
        Hero hero = new Hero(new Position(Globals.width/2, Globals.height-2));
        hero.draw(tg);
        Mockito.verify(tg, Mockito.times(1)).setBackgroundColor(TextColor.Factory.fromString(Globals.bgColor));
        Mockito.verify(tg, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString(Globals.textColor));
        Mockito.verify(tg, Mockito.times(1)).enableModifiers(SGR.BOLD);

        Mockito.verify(tg, Mockito.times(1)).putString(new TerminalPosition(30, 38), "ef");
        Mockito.verify(tg, Mockito.times(1)).putString(new TerminalPosition(30, 39), "gh");
    }
}