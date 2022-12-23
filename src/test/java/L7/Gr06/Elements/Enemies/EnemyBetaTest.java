package L7.Gr06.Elements.Enemies;

import L7.Gr06.Common.Globals;
import L7.Gr06.Elements.Shots.Bullet;
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

public class EnemyBetaTest {

    private TextGraphics tg;
    private EnemyBeta enemy;

    @BeforeEach
    public void setUp(){
        tg = Mockito.mock(TextGraphics.class);
        enemy = new EnemyBeta(new Position(4, 4), 1);
    }
    @Test
    public void shootTest() {
        Position pos = new Position(4, 5);
        Bullet expectedShot = new Bullet(pos, 1);

        int shoot = 100;    // probability to shoot
        enemy.shoot(shoot);

        List<Bullet> lst = enemy.getShots();
        assertEquals(lst.get(0).getX(), expectedShot.getX());
        assertEquals(lst.get(0).getY(), expectedShot.getY());
        assertEquals(lst.get(0), expectedShot);
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
    public void draw(){
        enemy.draw(tg);
        enemy.setHealth(2);
        Mockito.verify(tg, Mockito.times(1)).setBackgroundColor(TextColor.Factory.fromString(Globals.bgColor));
        Mockito.verify(tg, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString(Globals.textColor));
        Mockito.verify(tg, Mockito.times(1)).putString(new TerminalPosition(enemy.getX(), enemy.getY()), "jk");
        Mockito.verify(tg, Mockito.times(1)).putString(new TerminalPosition(enemy.getX(), enemy.getY()+1), "lm");
    }
}