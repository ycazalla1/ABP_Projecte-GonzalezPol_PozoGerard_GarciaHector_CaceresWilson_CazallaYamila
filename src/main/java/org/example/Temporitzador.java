package org.example;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Classe Temporitzador
 *
 * Controla el temps del joc
 *
 * @author Grup-1
 */
public class Temporitzador {

    /**
     * El temps que pasa en el joc, es compte en milisegons
     */
    private static int milisegons = 0;

    public static Timer timer = new Timer();

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
        if (timer == null) { // Solo crea un nuevo Timer si no existe
            timer = new Timer();
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
    }

    public static void aturarTemporitzador() {
        if (timer != null) {
            timer.cancel();
        }
    }

    /**
     * Torna els milisegons que han passat dintre del joc
     *
     * @return Milisegons passats
     */
    public static int getMilisegons() {
        return milisegons;
    }
}
