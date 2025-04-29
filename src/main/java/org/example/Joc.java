package org.example;

public class Joc {
    private FinestraJoc fjoc;
    private PanellJoc pJoc;

    public Joc() throws InterruptedException {

        /**
         * Se pone este encima de la Ventana porque si no inicializamos primero
         * el Panel da error, ya que no tendría Ventana ningún parámetro
         * asignado
         */
        pJoc = new PanellJoc(Variables.ampladaFinestra, Variables.alturaFinestra);
        fjoc = new FinestraJoc(pJoc);
    }
}
