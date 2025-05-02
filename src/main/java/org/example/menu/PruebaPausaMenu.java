package org.example.menu;

import javax.swing.*;
import java.awt.*;

public class PruebaPausaMenu extends JFrame {
        public PruebaPausaMenu(Runnable continuarPartida, Runnable reiniciarPartida, Runnable sortirMenu) {
            setTitle("Menú de Pausa");
            setSize(300, 200);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setLocationRelativeTo(null); // Centrar la ventana

            // Configurar botones
            JButton btnContinuar = new JButton("Continuar");
            btnContinuar.addActionListener(e -> continuarPartida.run());

            JButton btnReiniciar = new JButton("Reiniciar");
            btnReiniciar.addActionListener(e -> reiniciarPartida.run());

            JButton btnSortir = new JButton("Sortir");
            btnSortir.addActionListener(e -> sortirMenu.run());

            // Añadir botones al panel
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(3, 1));
            panel.add(btnContinuar);
            panel.add(btnReiniciar);
            panel.add(btnSortir);

            add(panel);
        }

}
