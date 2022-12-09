package L7.Gr06.elements.Enemies;

import L7.Gr06.common.GUI;
import L7.Gr06.common.Globals;
import L7.Gr06.elements.Enemies.EnemyAlfa;
import L7.Gr06.elements.Position;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

public class EnemyAlfaTest {
    @Test
    public void collide(){
        EnemyAlfa enemy = new EnemyAlfa(new Position(4,4),1);
        Position b1 = new Position(15,15);
        Position b2 = new Position(4,4);
        Position b3 = new Position(5,4);
        Position b4 = new Position(4,5);
        assertFalse(enemy.collide(b1));
        assertTrue(enemy.collide(b2));
        assertTrue(enemy.collide(b3));
        assertTrue(enemy.collide(b4));
    }

    @Test
    public void draw() {
        // use mocks
    }

}
