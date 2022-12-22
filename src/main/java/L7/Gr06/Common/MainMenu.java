package L7.Gr06.Common;

import L7.Gr06.Audio.MusicPlayer;
import L7.Gr06.Audio.SoundPlayer;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;
import java.util.Random;

public class MainMenu {



    private Boolean buttonPressed = false;
    private int options = 0;
    String selectedColor = "#ede9dd";
    String idleColor     = "#968e5a";
    MusicPlayer musicPlayer = new MusicPlayer();
    SoundPlayer soundPlayer = new SoundPlayer();
    About about = new About();
    Random rand = new Random();
    String advice;
    enum STATUS {START, RESUME, GAMEOVER, WIN}

    public boolean showMenu(Screen screen, STATUS status) {
        advice = advice();
        if (status != STATUS.RESUME){
            musicPlayer.startMainMenuMusic();
        }
        try {
            while (true) {
                screen.clear();
                draw(screen.newTextGraphics(), status);
                screen.refresh();
                // while loop delete all keys, that was pressed at the current level
                while (screen.pollInput() != null)
                    screen.pollInput();
                KeyStroke key = screen.readInput();
                processKey(key);
                if (buttonPressed){
                    buttonPressed = false;
                    if (options == 0){
                        return true;
                    } else if (options == 1) {
                        about.showAbout(screen);
                    } else {
                        return false;
                    }
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        finally {
            musicPlayer.stopMusic();
        }
        return false;

    }


    public void draw(TextGraphics graphics, STATUS status) {
        graphics.setBackgroundColor(TextColor.Factory.fromString(Globals.bgColor));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(Globals.width, Globals.height), ' ');
        graphics.enableModifiers(SGR.BOLD);
        graphics.setForegroundColor(TextColor.Factory.fromString(selectedColor));
        graphics.putString(new TerminalPosition(2, 1), "                  vwx                        vwx");
        graphics.putString(new TerminalPosition(2, 2), "ab  ef  jk  nopq  yz{  [\\  ab  ef  jk  nopq  yz{  [\\  ab");
        graphics.putString(new TerminalPosition(2, 3), "cd  gh  lm  rstu  |}~  ]^  cd  gh  lm  rstu  |}~  ]^  cd");

        graphics.putString(new TerminalPosition(Globals.width/2-9, 10), "SPACE INVADERS V0.1");
        if (status == STATUS.GAMEOVER)
            graphics.putString(new TerminalPosition(Globals.width/2-5, 12), "GAME OVER!");
        else if (status == STATUS.WIN)
            graphics.putString(new TerminalPosition(Globals.width/2-5, 12), " YOU WON! ");
        if (options == 0) graphics.setForegroundColor(TextColor.Factory.fromString(selectedColor));
        else graphics.setForegroundColor(TextColor.Factory.fromString(idleColor));
        if (status == STATUS.RESUME)
            graphics.putString(new TerminalPosition(Globals.width/2-5, Globals.height/2), "RESUME GAME");
        else
            graphics.putString(new TerminalPosition(Globals.width/2-7, Globals.height/2), "START NEW GAME");

        if (options == 1) graphics.setForegroundColor(TextColor.Factory.fromString(selectedColor));
        else graphics.setForegroundColor(TextColor.Factory.fromString(idleColor));
        graphics.putString(new TerminalPosition(Globals.width/2-5, Globals.height/2+3), "ABOUT GAME");

        if (options == 2) graphics.setForegroundColor(TextColor.Factory.fromString(selectedColor));
        else graphics.setForegroundColor(TextColor.Factory.fromString(idleColor));
        graphics.putString(new TerminalPosition(Globals.width/2-2, Globals.height/2+6), "EXIT");

        graphics.setForegroundColor(TextColor.Factory.fromString(idleColor));
        graphics.putString(new TerminalPosition(1, Globals.height-1), advice);
    }

    private String advice() {
        int x = rand.nextInt(100);
        if (x >= 80)
            return "HINT: PRESS TAB DURING THE GAME TO OPEN THE SHOP";
        else if (x >= 60)
            return "HINT: TRY TO ELUDE THE LIGHTNING WHILE ITS GAINING POWER";
        else if (x >= 40)
            return "HINT: UPGRADE YOUR GUN POWER TO DEFEAT THE FINAL BOSS";
        else if (x >= 20)
            return "HINT: GUN SPEED HELPS AGAINST A LARGE NUMBER OF TARGETS";
        else
            return "HINT: IF YOU HAVE ONE LIFE LEFT, USE SHOP TO PATCH HOLES";
    }

    private void processKey(KeyStroke key) {
        switch (key.getKeyType()) {
            case EOF -> buttonPressed = true;
            case ArrowUp ->   {
                soundPlayer.playMenu();
                options = (3 + (options-1)) % 3;
            }
            case ArrowDown -> {
                soundPlayer.playMenu();
                options = (3 + (options+1)) % 3;
            }
            case Enter -> buttonPressed = true;
            case Character -> {
                switch (key.getCharacter()){
                    case 'w' -> {
                        soundPlayer.playMenu();
                        options = (3 + (options-1)) % 3;
                    }
                    case 's' -> {
                        soundPlayer.playMenu();
                        options = (3 + (options+1)) % 3;
                    }
                }
            }
        }
    }
}

