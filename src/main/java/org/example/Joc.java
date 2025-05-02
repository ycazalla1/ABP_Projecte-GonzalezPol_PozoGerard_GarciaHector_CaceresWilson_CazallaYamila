package org.example;

import java.io.IOException;

/**
 * Classe Joc
 *
 * Controla el joc
 *
 * @author Grup-1
 */
public class Joc {
    /**
     * Atribut per tenir la finestra del joc
     */
    public FinestraJoc fjoc;
    /**
     * Atribut per tenir el panell del joc
     */
    public PanellJoc pJoc;

    /**
     * Constructor de la classe Joc
     *
     * Demana un array amb els noms dels jugadors i el nivell de dificultat del joc
     *
     * Si el joc es pausa, es mostra una finestra de pausa, el temps deixa de comptar i la bola deixa de moure's
     * Si el joc no es pausa, tant la bola, com les raquetes com el temps es mouen i fem que el thread del joc descanis 10 milisegons
     *
     * @param nomsJugadors Array amb els noms dels jugadors
     * @param nivell       Nivell de dificultat del joc
     */
    public Joc(String[] nomsJugadors, String nivell) throws IOException, InterruptedException {

        /**
         * Se pone este encima de la Ventana porque si no inicializamos primero
         * el Panel da error, ya que no tendría Ventana ningún parámetro
         * asignado
         */
        pJoc = new PanellJoc(Variables.ampladaFinestra, Variables.alturaFinestra, nomsJugadors, nivell);
        fjoc = new FinestraJoc(pJoc);

        new Thread(() -> {
            try {
                while (true) {
                    if (pJoc.getPausa()) {
                /*
                    Si el juego está en pausa, se llama al metodo menuGame() de la clase PanellJoc
                    que es el que se encarga de mostrar el menú del juego
                 */


                    } else {
                /*
                El metodo move() de la clase PanellJoc es el que se encarga de
                mover la bola y las palas
             */
                        pJoc.move();
            /*
                El metodo repaint() llama al metodo paintComponent(Graphics g) de la clase PanellJoc
                que es el que se encarga de dibujar el panel del juego
             */
                        pJoc.repaint();


                        pJoc.augmentarNivell();
            /*
                Le dice al procesador que el thread que se está ejecutando descanse por 10 milisegundos
                lo que permite que el procesador ejecute otros threads y en particular el thread AWT-EventQueue
                que llama al metodo paint
             */
                        Thread.sleep(10);
                    }


                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }
}
