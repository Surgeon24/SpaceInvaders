package L7.Gr06;

import L7.Gr06.common.Game;

public class Main {

    /*
        main application, that construct the game and closes all processes after exiting from the game.
     */
    public static void main(String[] args) {

        Game game = new Game();
        game.run();
        System.out.println("application has been closed!");
    }

}
