package L7.Gr06.Elements;

import L7.Gr06.Common.Globals;
import L7.Gr06.Elements.Shots.Lightning;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class LightningTest {

    @Test
    public void drawTest() {
        TextGraphics tg = Mockito.mock(TextGraphics.class);
        Lightning light = new Lightning(new Position(0, 0), 1);
        light.draw(tg);
        Mockito.verify(tg, Mockito.times(1)).setBackgroundColor(TextColor.Factory.fromString(Globals.bgColor));
        Mockito.verify(tg, Mockito.times(1)).enableModifiers(SGR.BOLD);
        Mockito.verify(tg, Mockito.times(1)).putString(new TerminalPosition(light.getX(), 0), "_");
    }
}