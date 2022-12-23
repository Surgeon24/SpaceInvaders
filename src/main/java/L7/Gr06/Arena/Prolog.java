package L7.Gr06.Arena;

import L7.Gr06.Audio.MusicPlayer;
import L7.Gr06.Audio.SoundPlayer;
import L7.Gr06.Common.Globals;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class Prolog {
    Integer firstRow = Globals.height;
    MusicPlayer musicPlayer = new MusicPlayer();
    SoundPlayer soundPlayer = new SoundPlayer();

    public void showProlog(Screen screen){
        try {
            musicPlayer.prologMusic();
            for (; firstRow > 19; firstRow--) {
                screen.clear();
                drawBefore(screen.newTextGraphics());
                screen.refresh();
                Thread.sleep(1000);
                if (screen.pollInput() != null) {
                    firstRow = 19;
                    System.out.println("!");
                }
            }
            screen.clear();
            screen.newTextGraphics().setBackgroundColor(TextColor.Factory.fromString(Globals.bgColor));
            screen.newTextGraphics().fillRectangle(new TerminalPosition(0, 0), new TerminalSize(Globals.width, Globals.height), ' ');
            screen.refresh();
            musicPlayer.stopMusic();
            soundPlayer.playStop();
            Thread.sleep(2000);
            screen.clear();
            drawAfter(screen.newTextGraphics());
            screen.refresh();
            Thread.sleep(3000);
        } catch(InterruptedException | IOException e){
            e.printStackTrace();
        }
    }

    public void drawBefore(TextGraphics graphics){
        graphics.setBackgroundColor(TextColor.Factory.fromString(Globals.bgColor));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(Globals.width, Globals.height), ' ');
        graphics.enableModifiers(SGR.BOLD);
        graphics.setForegroundColor(TextColor.Factory.fromString(Globals.textColor));
        graphics.putString(new TerminalPosition(Globals.width/2-20, firstRow)       ,"A LONG TIME AGO IN A GALAXY FAR, FAR AWAY");
        graphics.putString(new TerminalPosition(Globals.width/2-20, firstRow+4) ,"FLYING MONSTERS FOUGHT WITH SPACESHIPS...");
        graphics.putString(new TerminalPosition(Globals.width/2-15, firstRow+8) , "...FOR  A  PLACE  UNDER  THE  SUN");
        graphics.putString(new TerminalPosition(Globals.width/2-22, firstRow+12) , "LEADER OF THE ALIENS WAS CUNNING AND POWERFUL");
        graphics.putString(new TerminalPosition(Globals.width/2-20, firstRow+16), "LEAVING  NO CHANCE  FOR  THE  ORDINARY...");
    }

    public void drawAfter(TextGraphics graphics){
        graphics.setBackgroundColor(TextColor.Factory.fromString(Globals.bgColor));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(Globals.width, Globals.height), ' ');
        graphics.enableModifiers(SGR.BOLD);
        graphics.setForegroundColor(TextColor.Factory.fromString(Globals.textColor));
        graphics.putString(new TerminalPosition(Globals.width/2-12, Globals.height/2), "   WHO AM I KIDDING...");
        graphics.putString(new TerminalPosition(Globals.width/2-12, Globals.height/2+2), "LETS JUST START THE GAME!");
    }
}
