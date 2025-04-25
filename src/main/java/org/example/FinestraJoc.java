package org.example;

import javax.swing.JFrame;

public class FinestraJoc {
    private JFrame jf;
    private final int PUNTUACIO_WINNER = 5;

    public FinestraJoc(PanellJoc pJoc) throws InterruptedException {

        jf = new JFrame();

        //Finalizar el programa cuando se cierre la ventana
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Esto es para ensamblar la ventana del juego con el panel (imágen)
        jf.add(pJoc);


        //Para que no pueda el usuario redimensionar la ventana de juego
        jf.setResizable(false);
        //Para que la medida de la venta sea la misma que la del panel
        jf.pack();

        /*  Hace que aparezca en el centro de la pantalla
         *  Se debe situar esta línea debajo de las medidas de la ventana
         *  porque si no aparecerá la ventana en el medio de la pantalla
         */
        jf.setLocationRelativeTo(null);

        /*
         * Se pone debajo para que no de error, porque si se pone encima
         * puede pasar que cuando se redimensione la ventana no se vea el juego.
         */
        jf.setVisible(true);

        while (true) {
            pJoc.move();
            /*
                El metodo repaint() llama al metodo paintComponent(Graphics g) de la clase PanellJoc
                que es el que se encarga de dibujar el panel del juego
             */
            pJoc.repaint();
            if (pJoc.j1.getPunts() >= PUNTUACIO_WINNER || pJoc.j2.getPunts() >= PUNTUACIO_WINNER) {
                /*
                    Si el jugador 1 o el jugador 2 llega a la puntuación ganadora
                    se llama al metodo gameOver() de la clase PanellJoc
                 */

                if (pJoc.j1.getPunts() >= pJoc.j2.getPunts() + 2) {
                    pJoc.gameOver(pJoc.j1);
                } else if (pJoc.j2.getPunts() >= pJoc.j1.getPunts() + 2) {
                    pJoc.gameOver(pJoc.j2);
                }
            }
            /*
                Le dice al procesador que el thread que se está ejecutando descanse por 10 milisegundos
                lo que permite que el procesador ejecute otros threads y en particular el thread AWT-EventQueue
                que llama al metodo paint
             */
            Thread.sleep(10);
        }
    }
}