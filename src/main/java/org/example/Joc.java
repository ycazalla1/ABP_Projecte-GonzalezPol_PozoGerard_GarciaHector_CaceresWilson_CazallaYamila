package org.example;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class Joc {
    public FinestraJoc fjoc;
    public PanellJoc pJoc;

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
