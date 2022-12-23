package L7.Gr06.Elements.Enemies;

import L7.Gr06.Common.Globals;
import L7.Gr06.Elements.Position;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class EnemyAlfaTest {
    private TextGraphics tg;
    private EnemyAlfa enemy;

    @BeforeEach
    public void setUp() {
        tg = Mockito.mock(TextGraphics.class);
        enemy = new EnemyAlfa(new Position(4, 4), 1);
    }

    @Test
    public void collide() {
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
    public void draw() {
        enemy.draw(tg);
        Mockito.verify(tg, Mockito.times(1)).setBackgroundColor(TextColor.Factory.fromString(Globals.bgColor));
        Mockito.verify(tg, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString(Globals.textColor));
        Mockito.verify(tg, Mockito.times(1)).putString(new TerminalPosition(4, 4), "ab");
        Mockito.verify(tg, Mockito.times(1)).putString(new TerminalPosition(4, 5), "cd");
    }
}