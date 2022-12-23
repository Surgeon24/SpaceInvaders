package L7.Gr06.Elements.Enemies;

import L7.Gr06.Common.Globals;
import L7.Gr06.Elements.Position;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EnemyGammaTest {

    private TextGraphics tg;
    private EnemyGamma enemyGamma;
    private Position pos;

    @BeforeEach
    public void setUp() {
        tg = Mockito.mock(TextGraphics.class);
        pos = new Position(Globals.width / 2, -3);
        enemyGamma = new EnemyGamma(pos, 1);
    }

    @Test
    public void collideTest() {

        Position pos2 = new Position(Globals.width, -3);
        EnemyGamma enemyGamma2 = new EnemyGamma(pos2, 1);

        assertTrue(enemyGamma.collide(pos));
        assertTrue(enemyGamma2.collide(pos2));
        assertFalse(enemyGamma.collide(pos2));
        assertFalse(enemyGamma2.collide(pos));
    }

    @Test
    public void drawTest() {
        enemyGamma.draw(tg);

        Mockito.verify(tg, Mockito.times(1)).setBackgroundColor(TextColor.Factory.fromString(Globals.bgColor));
        Mockito.verify(tg, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString(Globals.textColor));
        Mockito.verify(tg, Mockito.times(1)).putString(new TerminalPosition(enemyGamma.getX(), enemyGamma.getY()), "nopq");
        Mockito.verify(tg, Mockito.times(1)).putString(new TerminalPosition(enemyGamma.getX(), enemyGamma.getY() + 1), "rstu");
    }
}