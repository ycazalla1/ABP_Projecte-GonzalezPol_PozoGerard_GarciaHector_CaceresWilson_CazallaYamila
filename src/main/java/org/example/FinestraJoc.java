package org.example;

import javax.swing.JFrame;

public class FinestraJoc {
    private JFrame jf;

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
            pJoc.repaint();
            /*
                Le dice al procesador que el thread que se está ejecutando descanse por 10 milisegundos
                lo que permite que el procesador ejecute otros threads y en particular el thread AWT-EventQueue
                que llama al metodo paint
             */
            Thread.sleep(10);
        }
    }
}