package org.example;

import org.example.connector.Acces;
import org.example.menu.MenuGuanyadors;

import javax.swing.*;
import java.awt.*;

import static org.example.Variables.INCREMENT_VELOCITAT_BOLA;
import static org.example.Variables.*;

/**
 * Classe que representa la bola del joc
 * 
 * Permet fer un constructor per la bola
 * 
 * Incorpora methodes per controlar el moviment de la bola, les colisions, pintar la bola en el panell i reiniciar la posició quan es marca un punt
 *
 * @author Grup-1
 */
public class Bola {

    /**
     * El panell del joc
     */
    private PanellJoc panellJoc;
    /**
     * Velocitat de la bola
     */
    private static float velocitatBola;
    /**
     * Coordenades ón apareix la bola
     */
    float x = ampladaFinestra /2, y = alturaFinestra /2;
    /**
     * Direcció que segueix la bola
     */
    float xa, ya;
    /**
     * Constructor de la bola
     *
     * @param panellJoc El panell del joc per dibuixar la bola
     */
    public Bola(PanellJoc panellJoc, String nivell) {
        this.panellJoc = panellJoc;
        velocitatBola = (float) (Variables.VELOCITAT_BOLA_INICIAL * Math.pow(INCREMENT_VELOCITAT_BOLA, Double.parseDouble(nivell)));
        System.out.println("Nivell: " + Integer.parseInt(nivell));
        System.out.println("velocitat bola: " + velocitatBola);
        this.xa = velocitatBola;
        this.ya = velocitatBola;
    }

    /**
     * Fa moure la bola.
     *
     * Quan la bola fa contacte amb una paret canvia la direcció de la bola mantenint la velocitat que portaba
     * abans d'impactar
     */
    public void bolaMoviment() {

        System.out.println(velocitatBola);


        // Si la bola surt per l'esquerra
        if (x <= Variables.MARGES) {
            PanellJoc.pausa = true;
            panellJoc.j2.setPunts(Temporitzador.getMilisegons());
            Acces.modificarPuntuacio(panellJoc.j2);
            MenuGuanyadors menuGuanyadors = new MenuGuanyadors();
            menuGuanyadors.mostrar();
            SwingUtilities.getWindowAncestor(panellJoc).dispose(); // Cierra la ventana
        }

        // Si la bola surt per la dreta
        if (x >= Variables.ampladaFinestra - (Variables.MARGES*2)) {
            PanellJoc.pausa = true;
            panellJoc.j1.setPunts(Temporitzador.getMilisegons());
            Acces.modificarPuntuacio(panellJoc.j1);
            MenuGuanyadors menuGuanyadors = new MenuGuanyadors();
            menuGuanyadors.mostrar();
            SwingUtilities.getWindowAncestor(panellJoc).dispose(); // Cierra la ventana
        }

        // Rebote en los bordes superior e inferior
        if (y + ya < Variables.MARGES || y + ya > panellJoc.getHeight() - Variables.MARGES - Variables.MIDA_AMPLE_RACQUET) {
            ya = -ya;
        }

        x = x + xa;
        y = y + ya;

        // Colisions amb les pales
        if (collision(panellJoc.r1)) {
            xa = velocitatBola;
            x = panellJoc.r1.getTotalX() + MIDA_BOLA;
        }
        if (collision(panellJoc.r2)) {
            xa = -velocitatBola;
            x = panellJoc.r2.getTotalX() - MIDA_BOLA;
        }

        // Col·lisions amb obstacles
        // Cuando toca los obstáculos, la bola se ralla y ya no se reincia cuando toca las paredes
        for (int i = 0; i < panellJoc.obstacles.size(); i++) {
            if (collision(panellJoc.obstacles.get(i))) {
                System.out.println(xa);
                xa = (xa == velocitatBola) ? -velocitatBola : velocitatBola;
                ya = (ya == velocitatBola) ? -velocitatBola : velocitatBola;
                panellJoc.obstacles.remove(i);
                break;
            }
        }



    }

    /**
     * Comprova si la bola ha colisionat amb la racquet
     *
     * @param racquet La racqueta per mirar si ha colisionat
     * @return true/false, si colisiona o no
     */
    private boolean collision(Racquet racquet) {
        return racquet.getBounds().intersects(getBounds());
    }
    private boolean collision(Obstacles obstacle) {
        return obstacle.getBounds().intersects(getBounds());
    }

    /**
     * Obté la posició i la mesura de la bola
     *
     * @return Rectangle amb l'informació de la bola
     */
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, MIDA_BOLA, MIDA_BOLA);
    }

    /**
     * Dibuixa la bola en el panell
     *
     * @param g Pasem l'objecte per pintar la bola
     */
    public void paintComponent(Graphics2D g) {
        // (posició X, posició Y, amplada, altura)
        g.fillOval((int)x, (int)y, MIDA_BOLA, MIDA_BOLA);
        bolaMoviment();
    }

    /**
     * Reinicia la posició de la bola
     */
    private void reiniciarPosicio() {
        x = Variables.ampladaFinestra / 2;
        y = Variables.alturaFinestra / 2;
    }

    /**
     * Incrementa la velocitat de la bola
     */
    public void incrementarVelocitatBola() {
        velocitatBola *= INCREMENT_VELOCITAT_BOLA;
        xa = (xa > 0) ? velocitatBola : -velocitatBola;
        ya = (ya > 0) ? velocitatBola : -velocitatBola;
    }

}
