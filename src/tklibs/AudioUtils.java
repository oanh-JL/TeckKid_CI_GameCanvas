package tklibs;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class AudioUtils {
    /**
     * For playing game sound effect
     *
     * @param audioUrl
     * @return
     */
    public static Clip loadSound(String audioUrl) {
        File soundFile = new File(audioUrl);
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            return clip;
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * for playing background music, must call intialize first: mp3
     *
     * @param audioUrl
     * @return
     */
    public static MediaPlayer playMedia(String audioUrl) {
        String uriString = new File(audioUrl).toURI().toString();
        MediaPlayer mediaPlayer = new MediaPlayer(new Media(uriString));
        mediaPlayer.play();
        return mediaPlayer;
    }


}
