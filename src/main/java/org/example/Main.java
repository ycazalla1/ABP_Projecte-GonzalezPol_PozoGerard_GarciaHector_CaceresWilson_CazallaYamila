package org.example;

//import org.example.connector.AccesDades1;

import org.example.menu.MenuPrincipal;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.io.IOException;

import static org.example.Variables.*;

public class Main {


        public static void main(String[] args) {




            JFrame f = new JFrame("Retro Tenis");
            MenuPrincipal menu = new MenuPrincipal();

            //Yamila!! Ejecuto el codigo haz una captura al juego para poner
            // lo en la documentacion de programacion de las clases
            // Vale
            try {
                Sound.carregarSo("src/resources/sounds/Tenis_En_La_Playa.wav");
                Sound.reproduirSo();
            } catch (Exception e){
                e.printStackTrace();
            }

            f.setSize(ampladaFinestra, alturaFinestra);
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.add(menu);
            f.setLocationRelativeTo(null);
            f.setVisible(true);

        }
    }
