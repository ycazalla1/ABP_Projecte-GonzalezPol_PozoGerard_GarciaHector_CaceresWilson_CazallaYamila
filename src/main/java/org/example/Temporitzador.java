package org.example;

import java.util.Timer;
import java.util.TimerTask;

public class Temporitzador {

    private static int segons = 0, minuts = 0, hores = 0;

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
                 segons++;
                 if (segons == 60) {
                     minuts++;
                     segons = 0;
                 }
                 if (minuts == 60) {
                     hores++;
                     minuts = 0;
                 }

                 String temps = String.format("%02d:%02d:%02d", hores, minuts,
                         segons);
                 System.out.println("Temps: " + temps);
             }
        };
        timer.scheduleAtFixedRate(tarea, 0, 1000);
    }

    public int getSegons() {
        return segons;
    }

    public int getMinuts() {
        return minuts;
    }

    public int getHores() {
        return hores;
    }
}
