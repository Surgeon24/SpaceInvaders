package L7.Gr06.Elements;

import L7.Gr06.Common.Globals;
import L7.Gr06.Common.MenuBar;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class MenuBarTest {

    @Test
    public void drawTest() {
        TextGraphics tg = Mockito.mock(TextGraphics.class);
        int lives = 3;
        int level = 1;
        MenuBar mm = new MenuBar();
        mm.draw(tg, lives, level);
        Mockito.verify(tg, Mockito.times(1)).setBackgroundColor(TextColor.Factory.fromString(Globals.bgColor));
        Mockito.verify(tg, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString(Globals.textColor));
        Mockito.verify(tg, Mockito.times(1)).enableModifiers(SGR.BOLD);
        Mockito.verify(tg, Mockito.times(1)).putString(new TerminalPosition(2, 0), "LIVES: " + lives);
        Mockito.verify(tg, Mockito.times(1)).putString(new TerminalPosition(Globals.width / 2 - 3, 0), "LEVEL " + (level + 1));
        Mockito.verify(tg, Mockito.times(1)).putString(new TerminalPosition(Globals.width - 13, 0), "SCORE: " + Globals.score);
    }
}
