package org.example;

import javax.swing.JFrame;

/**
 * Classe FinestraJoc
 *
 * Crea la finestra del joc
 *
 * @author Grup-1
 */
public class FinestraJoc {
    /**
     * Assigna un panell al joc
     */
    private JFrame jf;

    /**
     * Construeix la finestra del joc i li assigna les propietats
     *
     * @param pJoc Panell del joc
     */
    public FinestraJoc(PanellJoc pJoc) {
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
    }

}