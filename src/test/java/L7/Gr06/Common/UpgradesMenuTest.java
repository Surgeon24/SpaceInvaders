package L7.Gr06.Common;

import L7.Gr06.Elements.Hero;
import L7.Gr06.Elements.Position;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static L7.Gr06.Common.MainMenu.STATUS.START;
import static L7.Gr06.Common.MainMenu.STATUS.WIN;

public class UpgradesMenuTest {
    @Test
    void draw() throws IOException {
        Screen screen = Mockito.mock(Screen.class);
        TextGraphics tg = Mockito.mock(TextGraphics.class);
        Mockito.when(screen.newTextGraphics()).thenReturn(tg);
        Mockito.when(screen.readInput()).thenReturn(new KeyStroke('w',false,false), new KeyStroke(KeyType.Enter,false,false));

        UpgradesMenu menu = new UpgradesMenu();
        menu.showUpgrades(screen, new Hero(new Position(2,2)));
        Mockito.verify(tg, Mockito.times(2)).setBackgroundColor(TextColor.Factory.fromString(Globals.bgColor));
        Mockito.verify(tg, Mockito.times(2)).putString(new TerminalPosition(Globals.width-20, Globals.height-1), "CONTINUE THE GAME");
    }
}
