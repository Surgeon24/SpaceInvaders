package L7.Gr06.Common;

import L7.Gr06.Arena.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class GameTest {
    private Game game;
    private Screen screen;
    private TextGraphics tg;
    private GUI gui;
    @BeforeEach
    public void setUp() throws IOException, URISyntaxException, FontFormatException {
        game = new Game();
        screen = Mockito.mock(Screen.class);
        tg = Mockito.mock(TextGraphics.class);
        gui = new GUI(Globals.width, Globals.height);
        Mockito.when(screen.newTextGraphics()).thenReturn(tg);
    }
    @Test
    void gameConstructorTest() throws IOException, URISyntaxException, FontFormatException {
        List<Arena> lst = game.allLevels;

        assertTrue(lst.get(0) instanceof Level1);
        assertTrue(lst.get(1) instanceof Level2);
        assertTrue(lst.get(2) instanceof Level3);
        assertTrue(lst.get(3) instanceof Level4);
        assertTrue(lst.get(4) instanceof Level5);
        assertTrue(lst.get(5) instanceof Level6);
    }

}