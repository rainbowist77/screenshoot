package fan.alice.screenshoot.util;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundPlayer {

    public static void play() {
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(SoundPlayer.class.getResource("/camera.wav"));

            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();

            audioStream.close ();

        } catch (Exception e) {
            AppLogger.log("Audio playback failed: " + e.getMessage());
        }
    }
}