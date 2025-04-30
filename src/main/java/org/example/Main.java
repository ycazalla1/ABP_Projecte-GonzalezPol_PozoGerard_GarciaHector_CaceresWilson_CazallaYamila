package org.example;

//import org.example.connector.AccesDades1;

import org.example.menu.MenuPrincipal;
import javax.swing.*;

    public class Main {
        public static void main(String[] args){

            JFrame f = new JFrame("Retro Tenis");
            MenuPrincipal menu = new MenuPrincipal();

            f.setSize(1280, 800);
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.add(menu);
            f.setLocationRelativeTo(null);
            f.setVisible(true);

        }
    }
