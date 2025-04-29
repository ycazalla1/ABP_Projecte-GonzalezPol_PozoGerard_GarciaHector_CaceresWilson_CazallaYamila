package org.example;

import java.awt.*;
import static org.example.PanellJoc.*;

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
     * Els pixels que ocupa la bola rectangularment per la hitbox
     */
    private final int MIDA_BOLA = 30;
    /**
     * El panell del joc
     */
    private PanellJoc panellJoc;
    /**
     * Velocitat de la bola
     */
    private static int velocitatBola = 1;
    /**
     * Coordenades ón apareix la bola
     */
    int x = ampladaFinestra /2, y = alturaFinestra /2;
    /**
     * Direcció que segueix la bola
     */
    int xa = 2, ya = 2;
    /**
     * Constructor de la bola
     *
     * @param panellJoc El panell del joc per dibuixar la bola
     */
    public Bola(PanellJoc panellJoc) {
        this.panellJoc = panellJoc;
    }

    /**
     * Fa moure la bola
     *
     * Quan la bola fa contacte amb una raqueta o una paret canvia la direcció de la bola mantenint la velocitat que portaba abans d'impactar
     * Quan la bola pasa de llarg a una raqueta, el jugador contrari suma un punt i la posició de la bola es reinicia
     */
    public void bolaMoviment() {
        if (x + xa < Finals.MARGES)
            xa = velocitatBola;
        if (x + xa > panellJoc.getWidth() - Finals.MARGES - Finals.TAMANY_AMPLE_RACQUET)
            xa = -velocitatBola;
        if (y + ya < Finals.MARGES)
            ya = velocitatBola;
        if (y + ya > panellJoc.getHeight() - Finals.MARGES - Finals.TAMANY_AMPLE_RACQUET)
            ya = -velocitatBola;

        x = x + xa;
        y = y + ya;

        if (collision(panellJoc.r1)) {
            xa = velocitatBola;
            x = panellJoc.r1.getTotalX() + MIDA_BOLA;
        }
        if (collision(panellJoc.r2)) {
            xa = -velocitatBola;
            x = panellJoc.r2.getTotalX() - MIDA_BOLA;
        }

        // Collisions amb obstacles
        for (int i = 0; i < panellJoc.obstacles.size(); i++) {
            if (collision(panellJoc.obstacles.get(i))) {
                xa = (xa == velocitatBola) ? -velocitatBola : velocitatBola;
                ya = (ya == velocitatBola) ? -velocitatBola : velocitatBola;
                panellJoc.obstacles.remove(i);
            }
        }

        if (x <= Finals.MARGE_RACQUET_RECTANGLE) {
            panellJoc.j2.setPunts(panellJoc.j2.getPunts()+1);
            reiniciarPosicio();
        }

        if (x >= panellJoc.getWidth() - Finals.MARGE_RACQUET_RECTANGLE - Finals.TAMANY_AMPLE_RACQUET) {
            panellJoc.j1.setPunts(panellJoc.j1.getPunts()+1);
            reiniciarPosicio();
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
        return new Rectangle(x, y, MIDA_BOLA, MIDA_BOLA);
    }

    /**
     * Dibuixa la bola en el panell
     *
     * @param g Pasem l'objecte per pintar la bola
     */
    public void paintComponent(Graphics2D g) {
        // (posició X, posició Y, amplada, altura)
        g.fillOval(x, y, MIDA_BOLA, MIDA_BOLA);
        bolaMoviment();
    }

    /**
     * Reinicia la posició de la bola
     */
    private void reiniciarPosicio() {
        x = ampladaFinestra / 2;
        y = alturaFinestra / 2;
    }

    public void incrementarVelocitatBola() {
        velocitatBola++;
    }

}
