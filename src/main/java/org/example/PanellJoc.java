package org.example;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PanellJoc extends JPanel {
    public PanellJoc() {
        setPanelSize();
    }

    public void setPanelSize() {
        Dimension dimension = new Dimension(1280, 800);
        //Método para asignar la dimensión creada antes como preferida
        setPreferredSize(dimension);
    }

    public void paintComponent(Graphics g) {

        int posXJugador1 = 0, posYJugador1 = 0;
        int posXJugador2 = 1280-30, posYJugador2 = 0;
        final int wJugador = 30, hJugador = 150;
        int posXBola = 1280/2, posYBola = 0, wBola = 30, hBola = 30;



        Graphics2D g2d = (Graphics2D) g;

        /**
         * Llama a la super clase "PanelJuego", que se extiende de JPanel
         * Y que la propia JPanel se extiende en JComponent, para que haga su método
         * Esto nos sirve para evitar errores en cuanto a la generación de la imágen
         */
        super.paintComponent(g);

        g2d.setColor(Color.BLACK);

        g2d.fillRect(posXJugador1, posYJugador1, wJugador, hJugador);
        g2d.fillRect(posXJugador2, posYJugador2, wJugador, hJugador);
        g2d.fillOval(posXBola, posYBola, wBola, hBola);


    }
}
