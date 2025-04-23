package org.example;

import org.example.connector.AccesDades1;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Joc joc = new Joc();
        AccesDades1.afegirInformacio(new Jugador(19, "Pol", 1000));

    }
}