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

public class UpgradesMenu {
    private Boolean buttonPressed = false;
    private int options = 0;
    private int gunSpeedUpdate = 0;
    private int gunPowerUpdate = 0;
    private int armorUpdate = 0;
    String selectedColor = "#ede9dd";
    String idleColor     = "#968e5a";
    Integer numberOfOptions = 5;
    SoundPlayer soundPlayer = new SoundPlayer();
    Upgrades upgrades = new Upgrades();
    public void showUpgrades(Screen screen, Hero hero){
        try {
            while (true) {
                screen.clear();
                draw(screen.newTextGraphics());
                screen.refresh();
                // while loop delete all keys, that was pressed at the current level
                while (screen.pollInput() != null)
                    screen.pollInput();
                KeyStroke key = screen.readInput();
                processKey(key);
                if (buttonPressed) {
                    buttonPressed = false;
                    if (options == 0) {
                        if (gunSpeedUpdate < 4) {
                            if (Globals.score >= (int) upgrades.gunSpeed.get(gunSpeedUpdate + 1).getValue0()) {
                                Globals.score -= (int) upgrades.gunSpeed.get(gunSpeedUpdate + 1).getValue0();
                                hero.changeGunSpeed((int) upgrades.gunSpeed.get(gunSpeedUpdate + 1).getValue2());
                                gunSpeedUpdate++;
                                soundPlayer.playUpgrade();
                            } else {
                                soundPlayer.playDecline();
                            }
                        } else {
                            soundPlayer.playDecline();
                        }
                    }
                    else if (options == 1) {
                        if (gunPowerUpdate < 5) {
                            if (Globals.score >= (int) upgrades.gunPower.get(gunPowerUpdate + 1).getValue0()) {
                                Globals.score -= (int) upgrades.gunPower.get(gunPowerUpdate + 1).getValue0();
                                hero.changeGunPower((int) upgrades.gunPower.get(gunPowerUpdate + 1).getValue2());
                                gunPowerUpdate++;
                                soundPlayer.playUpgrade();
                            } else {
                                soundPlayer.playDecline();
                            }
                        } else {
                            soundPlayer.playDecline();
                        }
                    }
                    else if (options == 2) {
                        if (armorUpdate < 4) {
                            if (Globals.score >= (int) upgrades.armor.get(armorUpdate + 1).getValue0()) {
                                Globals.score -= (int) upgrades.armor.get(armorUpdate + 1).getValue0();
                                Globals.maxLives += ((int) upgrades.armor.get(armorUpdate + 1).getValue2());
                                armorUpdate++;
                                soundPlayer.playUpgrade();
                            } else {
                                soundPlayer.playDecline();
                            }
                        } else {
                            soundPlayer.playDecline();
                        }
                    }
                    else if (options == 3) {
                        if (Globals.score >= 250 && Globals.maxLives > hero.getLives()) {
                            Globals.score -= 250;
                            hero.changeLives(1);
                            soundPlayer.playUpgrade();
                        } else {
                            soundPlayer.playDecline();
                        }
                    }
                    else if (options == 4){ return;}
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    private void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString(Globals.bgColor));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(Globals.width, Globals.height), ' ');
        graphics.enableModifiers(SGR.BOLD);
        graphics.setForegroundColor(TextColor.Factory.fromString(selectedColor));

        graphics.putString(new TerminalPosition(Globals.width / 2 - 4, 3), "UPGRADES:");
        graphics.putString(new TerminalPosition(Globals.width / 2 - 4, 5), "  POINTS: " + Globals.score);

        if (options == 0) graphics.setForegroundColor(TextColor.Factory.fromString(selectedColor));
        else graphics.setForegroundColor(TextColor.Factory.fromString(idleColor));
        graphics.putString(new TerminalPosition(1, 8), " GUN RAMMER");
        if (gunSpeedUpdate == upgrades.gunSpeed.size()-2)
            graphics.putString(new TerminalPosition(1, 10), "(YOU ALREADY HAVE THE BEST)");
        else {
            graphics.putString(new TerminalPosition(1, 10), "(INCREASE GUN SPEED)");
            graphics.putString(new TerminalPosition(1, 11), " PRICE: " + upgrades.gunSpeed.get(gunSpeedUpdate + 1).getValue0());
        }

        if (options == 1) graphics.setForegroundColor(TextColor.Factory.fromString(selectedColor));
        else graphics.setForegroundColor(TextColor.Factory.fromString(idleColor));
        graphics.putString(new TerminalPosition(1, 14), upgrades.gunPower.get(gunPowerUpdate + 1).getValue1() + " GUNS");
        if (gunPowerUpdate == upgrades.gunPower.size()-2)
            graphics.putString(new TerminalPosition(1, 16), "(YOU ALREADY HAVE THE BEST)");
        else {
            graphics.putString(new TerminalPosition(1, 16), "(INCREASE GUN POWER)");
            graphics.putString(new TerminalPosition(1, 17), " PRICE: " + upgrades.gunPower.get(gunPowerUpdate + 1).getValue0());
        }

        if (options == 2) graphics.setForegroundColor(TextColor.Factory.fromString(selectedColor));
        else graphics.setForegroundColor(TextColor.Factory.fromString(idleColor));
        graphics.putString(new TerminalPosition(1, 20), " REINFORCED ARMOR");
        if (armorUpdate == upgrades.armor.size()-2)
            graphics.putString(new TerminalPosition(1, 22), "(YOU ALREADY HAVE THE BEST)");
        else {
            graphics.putString(new TerminalPosition(1, 22), "(MAX LIVES +1)");
            graphics.putString(new TerminalPosition(1, 23), " PRICE: " + upgrades.armor.get(armorUpdate + 1).getValue0());
        }

        if (options == 3) graphics.setForegroundColor(TextColor.Factory.fromString(selectedColor));
        else graphics.setForegroundColor(TextColor.Factory.fromString(idleColor));
        graphics.putString(new TerminalPosition(1, 26), " PATCH HOLES");
        graphics.putString(new TerminalPosition(1, 28), "CURRENT LIVES +1");
        graphics.putString(new TerminalPosition(1, 29), " PRICE: 250");

        if (options == 4) graphics.setForegroundColor(TextColor.Factory.fromString(selectedColor));
        else graphics.setForegroundColor(TextColor.Factory.fromString(idleColor));
        graphics.putString(new TerminalPosition(Globals.width-20, Globals.height-1), "CONTINUE THE GAME");
        drawStats(graphics);
    }

    private void drawStats(TextGraphics graphics){
        graphics.setForegroundColor(TextColor.Factory.fromString(idleColor));
        graphics.putString(new TerminalPosition(Globals.width/2+12, 10), "ef");
        graphics.putString(new TerminalPosition(Globals.width/2+12, 11), "gh");
        graphics.putString(new TerminalPosition(Globals.width/2+2, 14), "RATE OF FIRE: ");
        graphics.putString(new TerminalPosition(Globals.width/2+17, 14), (String) upgrades.gunSpeed.get(gunSpeedUpdate).getValue1());
        graphics.putString(new TerminalPosition(Globals.width/2+2, 16), "CALIBER: ");
        graphics.putString(new TerminalPosition(Globals.width/2+12, 16), (String) upgrades.gunPower.get(gunPowerUpdate).getValue1());
        graphics.putString(new TerminalPosition(Globals.width/2+2, 18), "ARMOR: ");
        graphics.putString(new TerminalPosition(Globals.width/2+10, 18), (String) upgrades.armor.get(armorUpdate).getValue1());
    }

    private void processKey(KeyStroke key) {
        switch (key.getKeyType()) {
            case EOF -> buttonPressed = true;
            case ArrowUp ->   {
                soundPlayer.playMenu();
                options = (numberOfOptions + (options-1)) % numberOfOptions;
            }
            case ArrowDown -> {
                soundPlayer.playMenu();
                options = (numberOfOptions + (options+1)) % numberOfOptions;
            }
            case Enter -> buttonPressed = true;
            case Character -> {
                switch (key.getCharacter()){
                    case 'w' -> {
                        soundPlayer.playMenu();
                        options = (numberOfOptions + (options-1)) % numberOfOptions;
                    }
                    case 's' -> {
                        soundPlayer.playMenu();
                        options = (numberOfOptions + (options+1)) % numberOfOptions;
                    }
                }
            }
        }

    }

    public void resetAll() {
        gunSpeedUpdate = 0;
        gunPowerUpdate = 0;
        armorUpdate = 0;
    }

}
