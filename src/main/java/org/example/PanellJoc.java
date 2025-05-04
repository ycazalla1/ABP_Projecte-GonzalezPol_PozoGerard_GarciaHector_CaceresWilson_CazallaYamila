package org.example;

import org.example.connector.Acces;
import org.example.menu.MenuPausa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;

import static org.example.Variables.*;
import static org.example.menu.MenuPausa.mostrarMenuPausa;

/**
 * Classe que representa el panell de joc
 *
 * Conté la bola, les racquets, obstacles i el temporitzador
 */
@SuppressWarnings("serial")
public class PanellJoc extends JPanel {

    private final int MIDA_FONT = 20;
    private static final int LIMIT_OBSTACLES = 10;
    private int tempsNivell = Variables.TEMPS_NIVELL;
    Image fons;
    Temporitzador t = new Temporitzador();
    Bola b;
    Racquet r1 = new Racquet(MARGES + MARGE_RACQUET_RECTANGLE, MARGES + MARGE_RACQUET_RECTANGLE, this);
    Racquet r2 = new Racquet(ampladaFinestra - (MARGES + MIDA_AMPLE_RACQUET + MARGE_RACQUET_RECTANGLE),
            MARGES + MARGE_RACQUET_RECTANGLE, this);
    Jugador j1;
    Jugador j2;
    ArrayList<Obstacles> obstacles = new ArrayList<>();
    int posRCentral = MARGES, ampleRCentral = ampladaFinestra - (MARGES * 2),
            altRCentral = alturaFinestra - (MARGES * 2);
    public static boolean pausa = false;
    public int nivell;
    Acces ac = new Acces();

    public PanellJoc(int amplada, int altura, String[] nomsJugadors, String nivell) throws IOException {
        ImageIcon img = new ImageIcon(rutaFons);
        fons = img.getImage();
        setPanelSize(amplada, altura);

        b = new Bola(this, nivell);
        this.j1 = new Jugador(nomsJugadors[0], 0);
        this.j2 = new Jugador(nomsJugadors[1], 0);
        ac.afegirInformacio(j1, j2);

        this.nivell = Integer.parseInt(nivell);

        if (this.nivell > 2) {
            obstacles = Obstacles.generarObstacles(ampladaFinestra, alturaFinestra, this.nivell);
        }

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                r1.keyPressed(e);
                r2.keyPressed(e);

                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    // Toggle pausa
                    pausa = !pausa;

                    // Obtener el JFrame que contiene este panel
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(PanellJoc.this);

                    // Mostrar menú de pausa en el JFrame correcto
                    mostrarMenuPausa(frame, PanellJoc.this);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                r1.keyReleased(e);
                r2.keyReleased(e);
            }
        });

        setFocusable(true);
        Temporitzador.iniciarTemporitzador();
    }

    public void setPanelSize(int amplada, int altura) {
        Dimension dimension = new Dimension(amplada, altura);
        setPreferredSize(dimension);
    }

    public void move() {
        b.bolaMoviment();
        r1.raqcquetLimitBores();
        r2.raqcquetLimitBores();
    }

    public void gameOver(Jugador j) {
        JOptionPane.showMessageDialog(this, "El guanyador és:" + j.getNom() + "\n"
                + "Punts: " + j.getPunts(), "Game Over", JOptionPane.YES_NO_OPTION);
        System.exit(ABORT);
    }

    public void setPausa(boolean pausa) {
        this.pausa = pausa;
    }

    public boolean getPausa() {
        return pausa;
    }

    public void augmentarNivell() {
        if (Temporitzador.getMilisegons() > tempsNivell) {
            nivell++;
            if (obstacles.size() < LIMIT_OBSTACLES) {
                obstacles = Obstacles.generarObstacles(ampladaFinestra, alturaFinestra, nivell);
            }
            b.incrementarVelocitatBola();
            tempsNivell += Variables.TEMPS_NIVELL;
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(fons, 0, 0, ampladaFinestra, alturaFinestra, null);

        Graphics2D bola = (Graphics2D) g;
        Graphics2D barra1 = (Graphics2D) g;
        Graphics2D barra2 = (Graphics2D) g;
        Graphics2D rCentral = (Graphics2D) g;
        Graphics2D punts = (Graphics2D) g;
        Graphics2D J1 = (Graphics2D) g;
        Graphics2D J2 = (Graphics2D) g;
        Graphics2D temporitzador = (Graphics2D) g;
        Graphics2D nivellJoc = (Graphics2D) g;
        Graphics2D obstaclesGraphics = (Graphics2D) g;

        bola.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        rCentral.setColor(Color.BLACK);
        rCentral.drawRect(posRCentral, posRCentral, ampleRCentral, altRCentral);

        bola.setColor(Color.BLACK);
        b.paintComponent(bola);

        barra1.setColor(Color.BLACK);
        barra2.setColor(Color.BLACK);

        r1.paint(barra1);
        r2.paint(barra2);

        for (int i = 0; i < obstacles.size(); i++) {
            obstacles.get(i).paint(obstaclesGraphics);
            if (nivell > 2) {
                obstacles.get(i).movimientObstacles(this.getHeight());
            }
        }

        J1.setColor(Color.BLACK);
        J1.setFont(new Font("Verdana", Font.BOLD, MIDA_FONT));
        J1.drawString(j1.getNom(), 0, 20);

        J2.setColor(Color.BLACK);
        J2.setFont(new Font("Verdana", Font.BOLD, MIDA_FONT));
        J2.drawString(j2.getNom(), Variables.ampladaFinestra - 30, 20);

        punts.setFont(new Font("Verdana", Font.BOLD, MIDA_FONT));
        punts.setColor(Color.BLACK);
        punts.drawString(j1.getPunts() + " | " + j2.getPunts(), ampladaFinestra / 2, 20);

        String nivellString = "Nivell: " + nivell;
        nivellJoc.setFont(new Font("Verdana", Font.BOLD, MIDA_FONT));
        nivellJoc.drawString(nivellString, ampladaFinestra / 2, alturaFinestra - 50);

        String temps = "Temps: " + t.getMilisegons() + "ms";
        temporitzador.setFont(new Font("Verdana", Font.BOLD, MIDA_FONT));
        temporitzador.drawString(temps, ampladaFinestra / 2, alturaFinestra - 30);
    }
}
