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
        // Atura el timer actual si existeix
        if (timer != null) {
            timer.cancel();
        }

        // Reinicia el comptador
        milisegons = 0;

        // Crea un nou timer i torna a iniciar
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
    public static void reiniciarTemporitzador() {
        // Atura i cancel·la el temporitzador anterior
        if (timer != null) {
            timer.cancel();
        }

        // Reinicia el temporitzador amb una nova instància
        timer = new Timer();

        TimerTask tarea = new TimerTask() {
            @Override
            public void run() {
                if (!PanellJoc.pausa) {
                    milisegons++;
                }
            }
        };

        // Programa la nova tasca per executar-se cada 1 ms
        timer.scheduleAtFixedRate(tarea, 0, 1);
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
