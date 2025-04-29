package org.example;

import java.util.Timer;
import java.util.TimerTask;

public class Temporitzador {

    private static int milisegons = 0;

    /**
     * Constructor del temporitzador
     */
    public Temporitzador() {
        // Constructor del temporitzador
        // Aquí pots inicialitzar els atributs o fer altres operacions necessàries
    }

    /**
     * Inicia el temporitzador
     */
    public static void iniciarTemporitzador() {
        // Inicia el temporitzador
        // Aquí pots inicialitzar els atributs o fer altres operacions necessàries
        Timer timer = new Timer();
        TimerTask tarea = new TimerTask() {
             @Override
             public void run() {
                if (!PanellJoc.pausa) {
                    milisegons++;
                }
             }
        };
        timer.scheduleAtFixedRate(tarea, 0, 1);
    }

    public static int getMilisegons() {
        return milisegons;
    }
}
