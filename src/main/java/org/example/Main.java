package org.example;

//import org.example.connector.AccesDades1;

import org.example.menu.MenuPrincipal;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.io.IOException;

import static org.example.Variables.*;

public class Main {
        public static void main(String[] args) throws UnsupportedAudioFileException, LineUnavailableException, IOException {

            try {
                Sound.carregarSo("src/resources/sounds/Playa_De_Retro_Tenis.mp3");
                Sound.reproduirSo();
            } catch (Exception e){
                e.printStackTrace();
            }


            JFrame f = new JFrame("Retro Tenis");
            MenuPrincipal menu = new MenuPrincipal();


            f.setSize(ampladaFinestra, alturaFinestra);
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.add(menu);
            f.setLocationRelativeTo(null);
            f.setVisible(true);

        }
    }
