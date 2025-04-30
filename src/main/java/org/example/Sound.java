package org.example;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
/**
 *
 */
public class Sound {
    private Clip clip;


    public void carregarSo(String ruta) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        File file = new File(ruta);
        AudioInputStream audio = AudioSystem.getAudioInputStream(file);
        clip = AudioSystem.getClip();
        clip.open(audio);
    }

    public void reproduirSo() {
        if (clip != null){
            clip.setFramePosition(0);
            clip.start();
        }
    }
}
