package L7.Gr06.Elements;

import L7.Gr06.Common.Globals;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.mockito.Mockito;
public class WallTest {

    @Test
    public void collide() {
        Wall wall = new Wall(new Position(5, 5));
        Position b1 = new Position(15, 15);
        Position b2 = new Position(4, 4);
        Position b3 = new Position(6, 5);
        Position b4 = new Position(5, 8);
        Position b5 = new Position(5, 5);
        assertFalse(wall.collide(b1));
        assertFalse(wall.collide(b2));
        assertTrue(wall.collide(b3));
        assertFalse(wall.collide(b4));
        assertTrue(wall.collide(b5));
    }
    @Test
    public void drawWallTest(){
        TextGraphics tg = Mockito.mock(TextGraphics.class);
        Wall wall = new Wall(new Position(5, Globals.height - 8));
        wall.draw(tg);
        Mockito.verify(tg, Mockito.times(1)).setBackgroundColor(TextColor.Factory.fromString("#8c92ac"));
        Mockito.verify(tg, Mockito.times(1)).enableModifiers(SGR.BOLD);
        Mockito.verify(tg, Mockito.times(1)).putString(new TerminalPosition(5, 32), "     ");
    }
}
