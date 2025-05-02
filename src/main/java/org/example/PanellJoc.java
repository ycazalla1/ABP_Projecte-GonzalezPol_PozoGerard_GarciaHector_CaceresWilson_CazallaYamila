package org.example;

import org.example.connector.Acces;
import org.example.menu.MenuPausa;
import org.example.menu.PruebaPausaMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import static org.example.Variables.*;
import static org.example.Variables.Accions.*;

/**
 * Classe que representa el panell de joc
 *
 * Conté la bola, les racquets, obstacles i el temporitzador
 *
 * @author Grup-1
 */
@SuppressWarnings("serial")
public class PanellJoc extends JPanel {

    /**
     * Mesura de la font
     */
    private final int MIDA_FONT = 20;
    /**
     * La quantiat maxima d'obstacles que es poden generar
     */
    private static final int LIMIT_OBSTACLES = 10;
    /**
     * Medeix la quantitat de temps que es porta jugant la partida
     */
    private int tempsNivell = Variables.TEMPS_NIVELL;
    /**
     * L'imatge de fons del joc
     */
    Image fons;
    /**
     * Temporitzador del joc
     */
    Temporitzador t = new Temporitzador();
    /**
     * La bola del joc
     */
    Bola b;
    /**
     * La racquet del jugador 1, la que està a la esquerra
     */
    Racquet r1 = new Racquet(MARGES + MARGE_RACQUET_RECTANGLE, MARGES + MARGE_RACQUET_RECTANGLE, this);
    /**
     * La racquet del jugador 2, la que està a la dreta
     */
    Racquet r2 = new Racquet(ampladaFinestra - (MARGES + MIDA_AMPLE_RACQUET + MARGE_RACQUET_RECTANGLE),
            MARGES + MARGE_RACQUET_RECTANGLE, this);
    /**
     * El jugador 1
     */
    Jugador j1;
    /**
     * El jugador 2
     */
    Jugador j2;
    /**
     * ArrayList amb tots els obstacles generats
     */
    ArrayList<Obstacles> obstacles = new ArrayList<>();
    /**
     * Les mesures del rectangle que bordeixen el panell i donen un marge a la partida
     */
    int posRCentral = MARGES, ampleRCentral = ampladaFinestra - (MARGES*2),
    altRCentral = alturaFinestra - (MARGES*2);
    /**
     * Emmagatzema si el joc està pausat o no
     */
    public static boolean pausa = false;
    /**
     * El nivell de dificultat del joc
     */
    public int nivell;
    /**
     * El connector amb la BBDD
     */
    Acces ac = new Acces();

    /**
     * Constructor del panell de joc
     *
     * Crea la bola, les racquets i tot el que fa falta al joc
     *
     * Si el nivell es superior a 2, es generen obstacles directament
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

        b = new Bola(this, nivell);
        this.j1 = new Jugador(nomsJugadors[0], 0);
        this.j2 = new Jugador(nomsJugadors[1], 0);
        ac.afegirInformacio(j1, j2);

        this.nivell = Integer.parseInt(nivell);

        if (this.nivell > 2) {
            obstacles = Obstacles.generarObstacles(ampladaFinestra, alturaFinestra, Integer.parseInt(nivell));
        }


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
                    MenuPausa m = new MenuPausa();
                    m.setVisible(true);
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

    /**
     * Fa la mesura del panell
     *
     * @param amplada Amplada del panell
     * @param altura Alçada del panell
     */
    public void setPanelSize(int amplada, int altura) {
        Dimension dimension = new Dimension(amplada, altura);
        //Metodo para asignar la dimensión creada antes como preferida
        setPreferredSize(dimension);
    }
    /**
     * Permet que la bola i les racquet es puguin moure
     */
    public void move() {
        b.bolaMoviment();
        r1.raqcquetLimitBores();
        r2.raqcquetLimitBores();
    }

    /**
     * Mostra un missatge de game over
     *
     * @param j Jugador
     */
    public void gameOver(Jugador j) {
        JOptionPane.showMessageDialog(this, "El guanyador és:" + j.getNom() + "\n"
                        + "Punts: " + j.getPunts(), "Game Over", JOptionPane.YES_NO_OPTION);
        //Esto es para cerrar la ventana del juego
        System.exit(ABORT);
    }

    /**
     * Posa en pausa o renuda el joc
     *
     * @param pausa true/false
     */
    public void setPausa(boolean pausa) {
        this.pausa = pausa;
    }

    /**
     * Torna si el joc està pausat o no
     *
     * @return true/false
     */
    public boolean getPausa() {
        return pausa;
    }

    /**
     * Augmenta el nivell del joc, segons el temps que ha passat
     *
     * Si el nivell es superior a un límit es generen obstacles
     */
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

    /**
     * Pinta tots els components del joc en el panell
     *
     * @param g Objecte Graphics
     */
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
