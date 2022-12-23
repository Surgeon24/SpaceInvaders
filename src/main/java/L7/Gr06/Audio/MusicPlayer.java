package L7.Gr06.Audio;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;
import java.util.Objects;

public class MusicPlayer {
    private Clip backgroundMusic;

    private Clip loadMusic(String fileName, float vol) throws NullPointerException{
        try {
            File musicFile = new File(Objects.requireNonNull(MusicPlayer.class.getResource("/Music/" + fileName)).getFile());
            if (musicFile.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicFile);
                Clip musicClip = AudioSystem.getClip();
                musicClip.open(audioInput);
                FloatControl gainControl = (FloatControl) musicClip.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(vol);
                return musicClip;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setMusic(String fileName, float vol) {
        this.backgroundMusic = loadMusic(fileName, vol);
    }

    public void startInGameMusic() {
        setMusic("space_battle.wav", 0);
        backgroundMusic.setMicrosecondPosition(0 * 1000000);
        backgroundMusic.start();
        backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void startMainMenuMusic() {
        setMusic("main_theme.wav", -5);
        backgroundMusic.setMicrosecondPosition(0);
        backgroundMusic.start();
        backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void prologMusic() {
        setMusic("starWarsTheme.wav", 0);
        backgroundMusic.setMicrosecondPosition(0);
        backgroundMusic.start();
        backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stopMusic() { backgroundMusic.stop();}
}
