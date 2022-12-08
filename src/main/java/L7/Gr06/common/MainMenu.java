package L7.Gr06.common;

import L7.Gr06.Audio.MusicPlayer;
import L7.Gr06.Audio.SoundPlayer;
import L7.Gr06.elements.Hero;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class MainMenu {



    private Boolean buttonPressed = false;
    private int options = 0;
    String selectedColor = "#ede9dd";
    String idleColor     = "#968e5a";
    MusicPlayer musicPlayer = new MusicPlayer("main_theme.wav");
    SoundPlayer soundPlayer = new SoundPlayer();
    enum STATUS {START, RESUME, GAMEOVER, WIN}

    public boolean showMenu(Screen screen, STATUS status) {
        soundPlayer.setSound("gta-menu.wav");
        About about = new About();
        Upgrades upgrades = new Upgrades();
        if (status == STATUS.START){
            musicPlayer.startMusic();
        }
        try {
            while (true) {
                screen.clear();
                draw(screen.newTextGraphics(), status);
                screen.refresh();
                    // warning - key refresh should be implemented to avoid blinking of the screen
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
        graphics.putString(new TerminalPosition(2, 2), "ab  ef  jk  nopq");
        graphics.putString(new TerminalPosition(2, 3), "cd  gh  lm  rstu");

        graphics.putString(new TerminalPosition(Globals.width/2-9, Globals.height/2-6), "SPACE INVADERS V0.1");
        if (status == STATUS.GAMEOVER)
            graphics.putString(new TerminalPosition(Globals.width/2-5, Globals.height/2-4), "GAME OVER!");
        else if (status == STATUS.WIN)
            graphics.putString(new TerminalPosition(Globals.width/2-5, Globals.height/2-4), " YOU WON! ");
        if (options == 0) graphics.setForegroundColor(TextColor.Factory.fromString(selectedColor));
        else graphics.setForegroundColor(TextColor.Factory.fromString(idleColor));
        if (status == STATUS.RESUME)
            graphics.putString(new TerminalPosition(Globals.width/2-5, Globals.height/2), "RESUME GAME");
        else
            graphics.putString(new TerminalPosition(Globals.width/2-7, Globals.height/2), "START NEW GAME");

        if (options == 1) graphics.setForegroundColor(TextColor.Factory.fromString(selectedColor));
        else graphics.setForegroundColor(TextColor.Factory.fromString(idleColor));
        graphics.putString(new TerminalPosition(Globals.width/2-4, Globals.height/2+3), "ABOUT GAME");

        if (options == 2) graphics.setForegroundColor(TextColor.Factory.fromString(selectedColor));
        else graphics.setForegroundColor(TextColor.Factory.fromString(idleColor));
        graphics.putString(new TerminalPosition(Globals.width/2-5, Globals.height/2+6), "EXIT");
    }

    private void processKey(KeyStroke key) {
        switch (key.getKeyType()) {
            case EOF -> buttonPressed = true;
            case ArrowUp ->   {
                soundPlayer.playSound();
                options = (3 + (options-1)) % 3;
            }
            case ArrowDown -> {
                soundPlayer.playSound();
                options = (3 + (options+1)) % 3;
            }
            case Enter -> buttonPressed = true;
            case Character -> {
                switch (key.getCharacter()){
                    case VK_ ->
                }
            }
        }

    }
}

