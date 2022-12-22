package L7.Gr06;

import L7.Gr06.Common.Game;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Main {
    public static void main(String[] args) throws IOException, FontFormatException, URISyntaxException {

        new Game().run();
        System.out.println("application has been closed!");
    }
}
