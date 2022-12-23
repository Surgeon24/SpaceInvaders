package L7.Gr06.Common;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static L7.Gr06.Common.MainMenu.STATUS.*;
import static org.mockito.ArgumentMatchers.*;

public class MainMenuTest {
    @Test
    public void processKeyTest() throws IOException, URISyntaxException, FontFormatException {
//        Game game = new Game();
//        game.mainMenu.showMenu(game.gui.screen, MainMenu.STATUS.valueOf("START"));

    }







    @Test
    void draw() {
        Screen screen = Mockito.mock(Screen.class);
        TextGraphics tg = Mockito.mock(TextGraphics.class);
        Mockito.when(screen.newTextGraphics()).thenReturn(tg);


        MainMenu menu = new MainMenu();
        menu.draw(tg, START);
        Mockito.verify(tg, Mockito.times(1)).setBackgroundColor(TextColor.Factory.fromString(Globals.bgColor));
        Mockito.verify(tg, Mockito.times(3)).setForegroundColor(TextColor.Factory.fromString("#968e5a"));
        Mockito.verify(tg, Mockito.times(0)).putString(new TerminalPosition(Globals.width/2-5, 12), " YOU WON! ");

        menu.draw(tg, WIN);
        Mockito.verify(tg, Mockito.times(1)).putString(new TerminalPosition(Globals.width/2-5, 12), " YOU WON! ");
    }
}
