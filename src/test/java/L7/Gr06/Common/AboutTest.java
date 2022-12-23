package L7.Gr06.Common;

import L7.Gr06.Arena.Arena;
import L7.Gr06.Arena.Level1;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import org.mockito.Mockito;
import static org.mockito.ArgumentMatchers.any;
import java.io.IOException;

public class AboutTest {
    void showAbout() throws IOException {
        Screen screen = Mockito.mock(Screen.class);
        TextGraphics tg = Mockito.mock(TextGraphics.class);

        Mockito.when(screen.newTextGraphics()).thenReturn(tg);
        Mockito.when(screen.readInput()).thenReturn(any());
        About about = new About();
        about.showAbout(screen);
        Mockito.verify(about, Mockito.times(1)).draw(tg);

    }
}
