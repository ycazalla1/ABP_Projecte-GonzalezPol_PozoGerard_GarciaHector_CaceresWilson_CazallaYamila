package org.example;

import org.example.menu.MenuPrincipal;
import org.example.menu.PantallaConfiguracionJugadores;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static org.example.Temporitzador.aturarTemporitzador;
import static org.example.menu.PantallaConfiguracionJugadores.jugadors;
import static org.example.menu.PantallaConfiguracionJugadores.nivell;

/**
 * Classe Variables
 *
 * Compté moltes variables que es fan servir en diferents classes del projecte
 */
public class Variables {

    /**
     * Indica cada quants milisegons es puja el nivell
     */
    public static final int TEMPS_NIVELL = 20000;
    /**
     * Indica els marges del requadre
     */
    public static final int MARGES = 30;
    /**
     * Indica la mida de les racquets
     */
    public static final int MIDA_ALT_RACQUET = 150, MIDA_AMPLE_RACQUET = 30;
    /**
     * Indica la mida de la bola
     */
    public static final int MIDA_BOLA = 30;
    /**
     * Indica l'espai que hi ha entre una racquet i el marge del rectangle
     */
    public static final int MARGE_RACQUET_RECTANGLE = 10;
    /**
     * Indica la velocitat de la bola
     */
    public static final float INCREMENT_VELOCITAT_BOLA = 1.1f;
    /**
     * Indica la velocitat inicial de la bola
     */
    public static final float VELOCITAT_BOLA_INICIAL = 0.4f;
    /**
     * Indica la velocitat de la racquet
     */
    public static final int VELOCITAT_RACQUET = 5;
    /**
     * Indica el idioma seleccionat
     */
    public static String idiomaSeleccionado = "Català";
    /**
     * Indica la resolució seleccionada
     */
    public static String resolucionSeleccionada = "1280x720";
    /**
     * Indica l'amplada i l'alçada de la finestra
     */
    public static int ampladaFinestra = Integer.parseInt(resolucionSeleccionada.split("x")[0])
            , alturaFinestra = Integer.parseInt(resolucionSeleccionada.split("x")[1]);
    /**
     * Indica la ruta del fons de pantalla
     */
    public static String rutaFons = "src/resources/images/fons_1_pingpong.png";

    /**
     * Compté les paraules del menú del joc
     */
    public static class Paraules {

        // Menú principal
        /**
         * El botó de jugar
         */
        public static String mpJugar = "mpJugar";
        /**
         * El botó d'opcions
         */
        public static String mpOpcions = "mpOpcions";
        /**
         * El botó de sortir
         */
        public static String mpSortir = "mpSortir";

        // Pantalla de configuració
        /**
         * El text de titol
         */
        public static String mjTitol = "mjTitol";
        /**
         * El text del jugador 1
         */
        public static String mjJugador1 = "mjJugador1";
        /**
         * El text del jugador 2
         */
        public static String mjJugador2 = "mjJugador2";
        /**
         * El text del nivell
         */
        public static String mjNivell = "mjNivell";
        /**
         * El botó per iniciar la partida
         */
        public static String mjIniciarPartida = "mjIniciarPartida";
        /**
         * El botó per tornar enrere
         */
        public static final String BOTO_TORNAR = "<";

        // Menú Opcions
        /**
         * El text de la resolució
         */
        public static String moResolucio = "moResolucio";
        /**
         * El text del idioma
         */
        public static String moIdioma = "moIdioma";
        /**
         * El botó per guardar
         */
        public static String moGuardar = "moGuardar";
        /**
         * El text del volum
         */
        public static String moVolum = "moVolum";

    }

    public static class Accions {
        public static Runnable continuarPartida = new Runnable() {
            @Override
            public void run() {
                PanellJoc.pausa = false; // Quitar la pausa
                Temporitzador.iniciarTemporitzador();
                // Reanudar el temporizador
                JFrame frameActual = (JFrame) SwingUtilities.getWindowAncestor(null);
                if (frameActual != null) {
                    frameActual.dispose(); // Cerrar el menú de pausa
                }
            }
        };
        public static Runnable reiniciarPartida = new Runnable() {
            @Override
            public void run() {
                try {
                    Joc joc = new Joc(jugadors, nivell); // Crear una nueva instancia del juego
                    joc.pJoc.setVisible(true);          // Mostrar la ventana del juego
                } catch (InterruptedException | IOException ex) {
                    ex.printStackTrace();               // Manejar posibles excepciones
                }
            }
        };
        public static Runnable sortirMenu = new Runnable() {
            @Override
            public void run() {
                MenuPrincipal menu = new MenuPrincipal(); // Crear una nueva instancia del menú principal
                menu.setVisible(true);                    // Mostrar el menú principal
                JFrame frameActual = (JFrame) SwingUtilities.getWindowAncestor(null);
                if (frameActual != null) {
                    frameActual.dispose();               // Cerrar la ventana actual
                }
            }
        };
    }

}
