package L7.Gr06.Audio;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;
import java.util.Objects;

public class MusicPlayer {
    private Clip backgroundMusic;
    private String file;

    public MusicPlayer(String fileName) {
        this.file = fileName;
        this.backgroundMusic = loadMusic(fileName);
        System.out.println("fileName - " + fileName + "\n");
        System.out.println("loadMusic(fileName) - " + loadMusic(fileName) + "\n");
    }

    private Clip loadMusic(String fileName) throws NullPointerException{
        try {
            File musicFile = new File(Objects.requireNonNull(MusicPlayer.class.getResource("/Music/" + fileName)).getFile());
            if (musicFile.exists()) {
                System.out.println("file exists: " + musicFile);
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicFile);
                Clip musicClip = AudioSystem.getClip();
                musicClip.open(audioInput);
                FloatControl gainControl = (FloatControl) musicClip.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-10.0f);
                return musicClip;
            } else {
                System.out.println("file not exists: " + musicFile);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void startMusic() {
        backgroundMusic.setMicrosecondPosition(0);
        backgroundMusic.start();
        backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stopMusic() { backgroundMusic.stop();}
}
