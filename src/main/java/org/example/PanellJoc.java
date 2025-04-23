package org.example;

import javax.swing.JPanel;
import java.awt.*;

@SuppressWarnings("serial")
public class PanellJoc extends JPanel {

    private static final int AMPLADA_FINESTRA = 1280, ALTURA_FINESTRA = 800;

    // Posició inicial bola
    int x = 0, y = 0;
    // Increment de posició bola = velocitat
    int xa = 1, ya = 1;



    public void moureBola() {
        x = x + 1;
        y = y + 1;
    }

    public PanellJoc() {
        setPanelSize(AMPLADA_FINESTRA, ALTURA_FINESTRA);
        this.x = AMPLADA_FINESTRA/2;
        this.y = ALTURA_FINESTRA/2;
    }

    public void setPanelSize(int amplada, int altura) {
        Dimension dimension = new Dimension(amplada, altura);
        //Método para asignar la dimensión creada antes como preferida
        setPreferredSize(dimension);
    }

    public void paintComponent(Graphics g) {

        /**
         * Llama a la super clase "PanelJuego", que se extiende de JPanel
         * Y que la propia JPanel se extiende en JComponent, para que haga su método
         * Esto nos sirve para evitar errores en cuanto a la generación de la imágen
         */
        super.paintComponent(g);

        int posXJugador1 = 0, posYJugador1 = 0;
        int posXJugador2 = 1280-30, posYJugador2 = 0;
        final int wJugador = 30, hJugador = 150;
        final int MIDA_BOLA = 30;

        Graphics2D bola = (Graphics2D) g;
        Graphics2D barra1 = (Graphics2D) g;
        Graphics2D barra2 = (Graphics2D) g;

        //Suaviza los bordes de las figuras
        bola.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        bola.setColor(Color.BLACK);
        barra1.setColor(Color.BLACK);
        barra2.setColor(Color.BLACK);

        bola.fillOval(x, y, MIDA_BOLA, MIDA_BOLA);
        barra1.fillRect(posXJugador1, posYJugador1, wJugador, hJugador);
        barra2.fillRect(posXJugador2, posYJugador2, wJugador, hJugador);
    }
}
