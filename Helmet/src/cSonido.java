import javax.sound.sampled.*;
import java.io.IOException;

public class cSonido {

    Clip nextRound;
    Clip background;



    public void sonidoUnico(String name) {
        try {

            AudioInputStream audioIn = AudioSystem.getAudioInputStream(Main.class.getResource("Sound/" + name));
            nextRound = AudioSystem.getClip();
            nextRound.open(audioIn);
            nextRound.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void loop(String name) {
        try {

            AudioInputStream audioIn = AudioSystem.getAudioInputStream(Main.class.getResource("Sound/" + name));
            background = AudioSystem.getClip();
            background.open(audioIn);
            background.loop(Clip.LOOP_CONTINUOUSLY);

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        background.close();
    }
}
