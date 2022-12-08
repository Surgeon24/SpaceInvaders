package L7.Gr06.common;

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

public class Upgrades {
    private Boolean buttonPressed = false;
    private int options = 0;
    String selectedColor = "#ede9dd";
    String idleColor     = "#968e5a";
    SoundPlayer soundPlayer = new SoundPlayer();
    public void showUpgrades(Screen screen, Hero hero, Integer score){
        soundPlayer.setSound("gta-menu.wav");
        try {
            while (true) {
                screen.clear();
                draw(screen.newTextGraphics(), score);
                screen.refresh();
                // warning - key refresh should be implemented to avoid blinking of the screen
                KeyStroke key = screen.readInput();
                processKey(key);
                if (buttonPressed) {
                    buttonPressed = false;
                    if (options == 0) {}
                    else if (options == 1) {}
                    else if (options == 2) {}
                    else if (options == 3) {}
                    else { return; }
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }


    public void draw(TextGraphics graphics, Integer score) {

        graphics.setBackgroundColor(TextColor.Factory.fromString(Globals.bgColor));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(Globals.width, Globals.height), ' ');
        graphics.enableModifiers(SGR.BOLD);
        graphics.setForegroundColor(TextColor.Factory.fromString(selectedColor));

        graphics.putString(new TerminalPosition(Globals.width/2-4, 3), "UPGRADES:");
        graphics.putString(new TerminalPosition(Globals.width/2-6, 5), "POINTS: " + score);
        if (options == 0) graphics.setForegroundColor(TextColor.Factory.fromString(selectedColor));
        else graphics.setForegroundColor(TextColor.Factory.fromString(idleColor));
        graphics.putString(new TerminalPosition(1, 8), "GUN RAMMER");
        graphics.putString(new TerminalPosition(1, 10), "(GUN SPEED X1.5)");
        if (options == 1) graphics.setForegroundColor(TextColor.Factory.fromString(selectedColor));
        else graphics.setForegroundColor(TextColor.Factory.fromString(idleColor));
        graphics.putString(new TerminalPosition(1, 14), "60MM GUNS");
        graphics.putString(new TerminalPosition(1, 16), "(GUN POWER +1)");
        if (options == 2) graphics.setForegroundColor(TextColor.Factory.fromString(selectedColor));
        else graphics.setForegroundColor(TextColor.Factory.fromString(idleColor));
        graphics.putString(new TerminalPosition(1, 20), "REINFORCED ARMOR");
        graphics.putString(new TerminalPosition(1, 22), "(MAX LIVES +1)");
        if (options == 3) graphics.setForegroundColor(TextColor.Factory.fromString(selectedColor));
        else graphics.setForegroundColor(TextColor.Factory.fromString(idleColor));
        graphics.putString(new TerminalPosition(1, 26), "PATCH HOLES");
        graphics.putString(new TerminalPosition(1, 28), "CURRENT LIVES +1");

        graphics.setForegroundColor(TextColor.Factory.fromString(idleColor));
        graphics.putString(new TerminalPosition(Globals.width-30, Globals.height-1), "PRESS ESC FOR RESUME THE GAME");
    }

    private void processKey(KeyStroke key) {
        switch (key.getKeyType()) {
            case EOF -> buttonPressed = true;
            case ArrowUp ->   {
                soundPlayer.stopSound();
                soundPlayer.playSound();
                options = (4 + (options-1)) % 4;
            }
            case ArrowDown -> {
                soundPlayer.stopSound();
                soundPlayer.playSound();
                options = (4 + (options+1)) % 4;
            }
            case Enter -> buttonPressed = true;
            case Escape -> {
                options = -1;
                buttonPressed = true;
            }
            case Character -> {
                switch (key.getCharacter()){
                    case 'w' -> {
                        soundPlayer.stopSound();
                        soundPlayer.playSound();
                        options = (4 + (options-1)) % 4;
                    }
                    case 's' -> {
                        soundPlayer.stopSound();
                        soundPlayer.playSound();
                        options = (4 + (options+1)) % 4;
                    }
                }
            }
        }

    }

}
