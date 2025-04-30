package org.example;

import org.example.menu.MenuPausa;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;

import static org.example.Variables.*;


@SuppressWarnings("serial")
public class PanellJoc extends JPanel {

    private final int MIDA_FONT = 20;
    private static final int LIMIT_OBSTACLES = 10;
    private static final int PUNTUACIO_REQUERIDA_INICIAL = 5000;
    private int puntuacioRequerida = PUNTUACIO_REQUERIDA_INICIAL;
    //private Sound so;

    Image fons;

    /**
     * Temporitzador del joc
     */
    Temporitzador t = new Temporitzador();

    Bola b = new Bola(this);
    Racquet r1 = new Racquet(MARGES + MARGE_RACQUET_RECTANGLE, MARGES + MARGE_RACQUET_RECTANGLE, this);
    Racquet r2 = new Racquet(ampladaFinestra - (MARGES + MIDA_AMPLE_RACQUET + MARGE_RACQUET_RECTANGLE),
            MARGES + MARGE_RACQUET_RECTANGLE, this);

    Jugador j1 = new Jugador("Yamila", 0);
    Jugador j2 = new Jugador("Javi", 0);

    ArrayList<Obstacles> obstacles = new ArrayList<>();

    int posRCentral = MARGES, ampleRCentral = ampladaFinestra - (MARGES*2),
    altRCentral = alturaFinestra - (MARGES*2);

    public static boolean pausa = false;

    private int nivell = 0;

    /**
     * Constructor del panell de joc
     *
     * @param amplada Amplada del panell
     * @param altura  Altura del panell
     */

    public PanellJoc(int amplada, int altura, String[] nomsJugadors, String nivell) throws IOException {
        //so = new Sound();
        //so.carregarSo("src/resources/sounds/Tenis_En_La_Playa.mp3");
        ImageIcon img = new ImageIcon(rutaFons);
        fons = img.getImage();
        setPanelSize(amplada, altura);
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

                r1.keyPressed(e);
                r2.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    //Si la tecla ESC es premuda, el joc es pausa
                    if (!pausa) {
                        pausa = true;
                    } else {
                        pausa = false;
                    }
                    MenuPausa menuPausa = new MenuPausa(() -> {
                        pausa = false;
                        //so.reproduirSo();
                    }, () -> {
                        //so.reproduirSo();
                        //reiniciarJoc();
                    }, () -> {
                        //so.reproduirSo();
                        //t.pararTemporitzador();
                        //menuGame();
                    });
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
        //Metodo para asignar la dimensión creada antes como preferida
        setPreferredSize(dimension);
    }

    public void move() {
        //El método move() de la clase Bola es el que se encarga de mover la bola
        b.bolaMoviment();
        //Llamamos al método de la clase Racquet para que se mueva
        r1.raqcquetLimitBores();
        r2.raqcquetLimitBores();
    }


    /**
     * Método que se encarga de mostrar el menú del juego
     */
    /*
    public void menuGame() {
        JDialog dialog = new JDialog((Frame) null, "Menu", true);
        MenuPausa mp = new MenuPausa(dialog);

        dialog.add(mp);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        // Agregar KeyListener para cerrar con ESC
        dialog.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    dialog.dispose();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {}
        });

        dialog.setFocusable(true);
        dialog.setVisible(true);
        pausa = false;
    }

     */

    public void gameOver(Jugador j) {
        JOptionPane.showMessageDialog(this, "El guanyador és:" + j.getNom() + "\n"
                        + "Punts: " + j.getPunts(), "Game Over", JOptionPane.YES_NO_OPTION);
        //Esto es para cerrar la ventana del juego
        System.exit(ABORT);
    }

    public void setPausa(boolean pausa) {
        this.pausa = pausa;
    }

    public boolean getPausa() {
        return pausa;
    }

    public void augmentarNivell() {
        if (Temporitzador.getMilisegons() > puntuacioRequerida) {
            nivell++;
            if (obstacles.size() < LIMIT_OBSTACLES) {
                obstacles = Obstacles.generarObstacles(ampladaFinestra, alturaFinestra, nivell);
            }
            b.incrementarVelocitatBola();
            //if (obstacles.size() < LIMIT_OBSTACLES)
            //obstacles = Obstacles.generarObstacles(AMPLADA_FINESTRA, ALTURA_FINESTRA, nivell * 3);
            puntuacioRequerida += PUNTUACIO_REQUERIDA_INICIAL;
        }
    }

    public void paintComponent(Graphics g) {

        /**
         * Llama a la super clase "PanelJuego", que se extiende de JPanel
         * Y que la propia JPanel se extiende en JComponent, para que haga su método
         * Esto nos sirve para evitar errores en cuanto a la generación de la imágen
         */
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

        //Dibuja el fondo del panel


        //Suaviza los bordes de las figuras
        bola.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);


        rCentral.setColor(Color.BLACK);
        rCentral.drawRect(posRCentral, posRCentral,
                ampleRCentral, altRCentral);

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
        J2.drawString(j2.getNom(), Variables.ampladaFinestra -30, 20);

        punts.setFont(new Font("Verdana", Font.BOLD, MIDA_FONT));
        punts.setColor(Color.BLACK);
        punts.drawString(j1.getPunts() + " | " + j2.getPunts(), ampladaFinestra /2, 20);

        String nivellString = "Nivell: " + nivell;
        nivellJoc.setFont(new Font("Verdana", Font.BOLD, MIDA_FONT));
        nivellJoc.drawString(nivellString, ampladaFinestra /2, alturaFinestra -50);


        /*Dibuja el tiempo
        */

        String temps = "Temps: " + Integer.toString(t.getMilisegons()) + "ms";
        temporitzador.setFont(new Font("Verdana", Font.BOLD, MIDA_FONT));
        temporitzador.drawString(temps, ampladaFinestra /2, alturaFinestra -30);


    }
}
