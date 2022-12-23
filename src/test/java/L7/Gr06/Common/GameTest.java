package L7.Gr06.Common;

import L7.Gr06.Arena.*;
import org.junit.jupiter.api.Test;


import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class GameTest {
    @Test
    void gameConstructorTest() throws IOException, URISyntaxException, FontFormatException {
        Game game = new Game();
        List<Arena> lst = game.allLevels;

        assertTrue(lst.get(0) instanceof Level1);
        assertTrue(lst.get(1) instanceof Level2);
        assertTrue(lst.get(2) instanceof Level3);
        assertTrue(lst.get(3) instanceof Level4);
        assertTrue(lst.get(4) instanceof Level5);
        assertTrue(lst.get(5) instanceof Level6);
    }

}