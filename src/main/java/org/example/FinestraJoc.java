package org.example;

import javax.swing.JFrame;

public class FinestraJoc {
    private JFrame jf;

    public FinestraJoc(PanellJoc pJoc) {

        jf = new JFrame();

        //Finalizar el programa cuando se cierre la ventana
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Esto es para ensamblar la ventana del juego con el panel (imágen)
        jf.add(pJoc);
        //Hace que aparezca en el centro de la pantalla
        jf.setLocationRelativeTo(null);

        //Para que no pueda el usuario redimensionar la ventana de juego
        jf.setResizable(false);
        //Para que la medida de la venta sea la misma que la del panel
        jf.pack();

        /**
         * Se pone debajo para que no de error, porque si se pone encima
         * puede pasar que cuando se redimensione la ventana no se vea el juego.
         */
        jf.setVisible(true);

    }

}