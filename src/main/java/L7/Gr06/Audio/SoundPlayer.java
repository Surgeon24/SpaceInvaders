package L7.Gr06.Audio;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;
import java.util.Objects;

public class SoundPlayer {
    private Clip sound;

    private Clip loadSound(String fileName, float vol) throws NullPointerException{
        try {
            File musicFile = new File(Objects.requireNonNull(MusicPlayer.class.getResource("/Sounds/" + fileName)).getFile());
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicFile);
            Clip musicClip = AudioSystem.getClip();
            musicClip.open(audioInput);
            FloatControl gainControl = (FloatControl) musicClip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(vol);
            return musicClip;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setSound(String fileName, float vol) {
        this.sound = loadSound(fileName, vol);
    }

    public void playUpgrade() {
        setSound("upgrade.wav", -10);
        sound.setMicrosecondPosition(0);
        sound.start();
    }

    public void playDecline() {
        setSound("decline.wav", -10);
        sound.setMicrosecondPosition(0);
        sound.start();
    }

    public void playMenu() {
        setSound("gta-menu.wav", -5);
        sound.stop();
        sound.setMicrosecondPosition(0);
        sound.start();
    }

    public void playShootSound() {
        setSound("laserShoot.wav", -30);
        sound.setMicrosecondPosition(0);
        sound.start();
    }

    public void playGameOver() {
        setSound("game-over.wav", 0);
        sound.setMicrosecondPosition(0);
        sound.start();
    }
}
