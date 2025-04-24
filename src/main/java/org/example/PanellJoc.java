package org.example;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

@SuppressWarnings("serial")
public class PanellJoc extends JPanel {

    private static final int AMPLADA_FINESTRA = 1280, ALTURA_FINESTRA = 800;

    // Posició inicial bola
    int x = 0, y = 0;

    Bola b = new Bola(this);
    Racquet r1 = new Racquet(0, 0, this);
    Racquet r2 = new Racquet(AMPLADA_FINESTRA-30, 0, this);

    public PanellJoc() {
        setPanelSize(AMPLADA_FINESTRA, ALTURA_FINESTRA);
        this.x = AMPLADA_FINESTRA/2;
        this.y = ALTURA_FINESTRA/2;
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                r1.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                r1.keyReleased(e);
            }
        });
        setFocusable(true);
    }

    public void setPanelSize(int amplada, int altura) {
        Dimension dimension = new Dimension(amplada, altura);
        //Metodo para asignar la dimensión creada antes como preferida
        setPreferredSize(dimension);
    }

    public void move() {
        b.bolaMoviment();
        r1.raqcquetMoviment();
        r2.raqcquetMoviment();
    }

    public void paintComponent(Graphics g) {

        /**
         * Llama a la super clase "PanelJuego", que se extiende de JPanel
         * Y que la propia JPanel se extiende en JComponent, para que haga su método
         * Esto nos sirve para evitar errores en cuanto a la generación de la imágen
         */
        super.paintComponent(g);

        Graphics2D bola = (Graphics2D) g;
        Graphics2D barra1 = (Graphics2D) g;
        Graphics2D barra2 = (Graphics2D) g;

        //Suaviza los bordes de las figuras
        bola.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        bola.setColor(Color.BLACK);
        b.paintComponent(bola);

        barra1.setColor(Color.BLACK);
        barra2.setColor(Color.BLACK);


        r1.paint(barra1);
        r2.paint(barra2);

    }
}
