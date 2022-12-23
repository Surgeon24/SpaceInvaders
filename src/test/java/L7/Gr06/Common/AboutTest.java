package L7.Gr06.Common;

import L7.Gr06.Arena.Arena;
import L7.Gr06.Arena.Level1;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.ArgumentMatchers.any;
import java.io.IOException;

public class AboutTest {
    @Test
    void draw() {
        Screen screen = Mockito.mock(Screen.class);
        TextGraphics tg = Mockito.mock(TextGraphics.class);
        Mockito.when(screen.newTextGraphics()).thenReturn(tg);

        About about = new About();
        about.draw(tg);
        Mockito.verify(tg, Mockito.times(1)).setBackgroundColor(TextColor.Factory.fromString(Globals.bgColor));
        Mockito.verify(tg, Mockito.times(1)).putString(new TerminalPosition(Globals.width-30, Globals.height-2), "PRESS ANY KEY TO RETURN...");
    }
}
