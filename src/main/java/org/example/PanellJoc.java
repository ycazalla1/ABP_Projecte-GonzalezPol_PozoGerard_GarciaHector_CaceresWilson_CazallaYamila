package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

@SuppressWarnings("serial")
public class PanellJoc extends JPanel {

    static final int AMPLADA_FINESTRA = 1280, ALTURA_FINESTRA = 800;
    private final int MIDA_FONT = 20;

    Bola b = new Bola(this);
    Racquet r1 = new Racquet(30, 30, this);
    Racquet r2 = new Racquet(AMPLADA_FINESTRA-60, 30, this);

    Jugador j1 = new Jugador("Yamila", 0);
    Jugador j2 = new Jugador("Javi", 0);

    /**
     * Constructor del panell de joc
     */

    public PanellJoc() {
        setPanelSize(AMPLADA_FINESTRA, ALTURA_FINESTRA);
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                r1.keyPressed(e);
                r2.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    menuGame();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                r1.keyReleased(e);
                r2.keyReleased(e);
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
        r1.raqcquetLimitBores();
        r2.raqcquetLimitBores();
    }

    public void menuGame() {
        JPanel menu = new JPanel();
        Dimension d = new Dimension(50, 100);
        menu.setPreferredSize(d);
        JButton b1 = new JButton("Continuar");
        JButton b2 = new JButton("Reiniciar");
        JButton b3 = new JButton("Sortir");
        b1.setBounds(new Rectangle(0, 0, 200, 50));
        menu.add(b1);
        menu.add(b2);
        menu.add(b3);
        JOptionPane.showMessageDialog(null, menu,
                "Menu", JOptionPane.YES_NO_OPTION);
    }

    public void gameOver() {
        JOptionPane.showMessageDialog(this, "Game Over",
                "Game Over", JOptionPane.YES_NO_OPTION);
        /*  //Esto es para cerrar la ventana del juego
            System.exit(ABORT);
         */
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
        Graphics2D requadreCentral = (Graphics2D) g;
        Graphics2D punts = (Graphics2D) g;
        Graphics2D J1 = (Graphics2D) g;
        Graphics2D J2 = (Graphics2D) g;

        //Suaviza los bordes de las figuras
        bola.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);


        requadreCentral.setColor(Color.BLACK);
        requadreCentral.drawRect(30, 30,
                AMPLADA_FINESTRA-(30*2), ALTURA_FINESTRA-(30*2));

        bola.setColor(Color.BLACK);
        b.paintComponent(bola);

        barra1.setColor(Color.BLACK);
        barra2.setColor(Color.BLACK);


        r1.paint(barra1);
        r2.paint(barra2);

        J1.setColor(Color.BLACK);
        J1.setFont(new Font("Verdana", Font.BOLD, MIDA_FONT));
        J1.drawString(j1.getNom(), 0, 20);

        J2.setColor(Color.BLACK);
        J2.setFont(new Font("Verdana", Font.BOLD, MIDA_FONT));
        J2.drawString(j2.getNom(), AMPLADA_FINESTRA-30, 20);

        punts.setFont(new Font("Verdana", Font.BOLD, MIDA_FONT));
        punts.setColor(Color.BLACK);
        punts.drawString(j1.getPunts() + " | " + j2.getPunts(), AMPLADA_FINESTRA/2, 20);


    }
}
