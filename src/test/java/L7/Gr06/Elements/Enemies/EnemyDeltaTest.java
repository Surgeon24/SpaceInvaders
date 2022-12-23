package L7.Gr06.Elements.Enemies;

import L7.Gr06.Common.Globals;
import L7.Gr06.Elements.Bullet;
import L7.Gr06.Elements.Position;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EnemyDeltaTest {
    private Position pos;
    private Position pos2;
    private EnemyDelta enemyDelta;
    private EnemyDelta enemyDeltaWrong;
    private TextGraphics tg;

    @BeforeEach
    public void setUp() {
        pos = new Position(Globals.width / 2, -3);
        enemyDelta = new EnemyDelta(pos, 1);
        pos2 = new Position(30, -2);
        enemyDeltaWrong = new EnemyDelta(new Position(40, 40), 1);
        tg = Mockito.mock(TextGraphics.class);
    }

    @Test
    public void shootTest() {

        Bullet expectedShot = new Bullet(pos2, 1);

        int shoot = 100;    // set probability to shoot
        enemyDelta.shoot(shoot);

        List<Bullet> lst = enemyDelta.getShots();
        assertEquals(lst.get(0).getX(), expectedShot.getX());
        assertEquals(lst.get(0).getY(), expectedShot.getY());
    }

    @Test
    public void collideTest() {
        assertTrue(enemyDelta.collide(pos));
        assertTrue(enemyDelta.collide(pos2));
        assertFalse(enemyDelta.collide(new Position(0,0)));
    }
    @Test
    public void drawTest(){

        enemyDelta.draw(tg);
        Mockito.verify(tg, Mockito.times(1)).setBackgroundColor(TextColor.Factory.fromString(Globals.bgColor));
        enemyDelta.setHealth(1);
        Mockito.verify(tg, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#db7e46"));
        Mockito.verify(tg, Mockito.times(1)).putString(new TerminalPosition(enemyDelta.getX(),enemyDelta.getY()), "vwx");
        Mockito.verify(tg, Mockito.times(1)).putString(new TerminalPosition(enemyDelta.getX(), enemyDelta.getY() +1), "yz{");
        Mockito.verify(tg, Mockito.times(1)).putString(new TerminalPosition(enemyDelta.getX(), enemyDelta.getY() +2), "|}~");
    }
}