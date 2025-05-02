package org.example;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
/**
 *
 */
public class Sound {
    public static Clip clip;
    private static FloatControl volume;

    public static void carregarSo(String ruta) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        File file = new File(ruta);
        AudioInputStream audio = AudioSystem.getAudioInputStream(file);
        clip = AudioSystem.getClip();
        clip.open(audio);

        if (clip.isControlSupported(FloatControl.Type.MASTER_GAIN)){
            volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        }
    }

    public static void reproduirSo() {
        if (clip != null){
            clip.setFramePosition(0);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        }
    }
    public static void setVolume(int valor){
        if (volume != null){
            float volumenDb = (float) (Math.log10(valor / 100.0) * 20);
            volume.setValue(volumenDb);
        }
    }
}
