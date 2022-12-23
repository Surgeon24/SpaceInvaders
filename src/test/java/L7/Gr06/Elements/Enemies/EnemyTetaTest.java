package L7.Gr06.Elements.Enemies;

import L7.Gr06.Common.Globals;
import L7.Gr06.Elements.Bullet;
import L7.Gr06.Elements.Lightning;
import L7.Gr06.Elements.Position;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EnemyTetaTest {
    private TextGraphics tg;
    private EnemyTeta enemy;

    @BeforeEach
    public void setUp() {
        enemy = new EnemyTeta(new Position(4, 4), 1);
        tg = Mockito.mock(TextGraphics.class);
    }

    @Test
    public void shootTest() {
        Lightning expectedLightning = new Lightning(new Position(4, 4), 1); // stub
        expectedLightning.setStartOfTheLighting(6);

        enemy.shoot(100); // probability to shoot

        List<Bullet> lst = enemy.getShots();
        Lightning shotLight = (Lightning) lst.get(0);

        assertEquals(expectedLightning.getStartOfTheLighting(), shotLight.getStartOfTheLighting());
        assertEquals(lst.get(0).getX(), expectedLightning.getX());
        assertEquals(lst.get(0).getY(), expectedLightning.getY());
        assertEquals(lst.get(0), expectedLightning);
    }

    @Test
    public void collide() {
        enemy = new EnemyTeta(new Position(4, 4), 1);
        Position b1 = new Position(15, 15);
        Position b2 = new Position(4, 4);
        Position b3 = new Position(5, 4);
        Position b4 = new Position(4, 5);
        assertFalse(enemy.collide(b1));
        assertTrue(enemy.collide(b2));
        assertTrue(enemy.collide(b3));
        assertTrue(enemy.collide(b4));
    }

    @Test
    public void drawTest() {
        enemy.draw(tg);
        Mockito.verify(tg, Mockito.times(1)).setBackgroundColor(TextColor.Factory.fromString(Globals.bgColor));
        enemy.setHealth(1);
        Mockito.verify(tg, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#db7e46"));
        Mockito.verify(tg, Mockito.times(1)).putString(new TerminalPosition(enemy.getX(), enemy.getY()), "[\\");
        Mockito.verify(tg, Mockito.times(1)).putString(new TerminalPosition(enemy.getX(), enemy.getY() + 1), "]^");
    }
}