package org.example;

import java.awt.*;
import static org.example.PanellJoc.*;

public class Bola {

    private PanellJoc panellJoc;
    /**
     * Velocitat de la bola
     */
    private static int velocitatBola = 2;
    /**
     * Coordenades ón apareix la bola
     */
    int x = AMPLADA_FINESTRA/2, y = ALTURA_FINESTRA/2;
    int xa = 2, ya = 2;
    /**
     * Constructor de la bola
     *
     * @param panellJoc El panell del joc per dibuixar la bola
     */
    public Bola(PanellJoc panellJoc) {
        this.panellJoc = panellJoc;
    }

    public void bolaMoviment() {
        if (x + xa < 30)
            xa = velocitatBola;
        if (x + xa > panellJoc.getWidth() - 60)
            xa = -velocitatBola;
        if (y + ya < 30)
            ya = velocitatBola;
        if (y + ya > panellJoc.getHeight() - 60)
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

        if (x == 30) {
            panellJoc.j2.setPunts(panellJoc.j2.getPunts()+1);
            reiniciarPosicio();
        }

        if (x == panellJoc.getWidth() - 60) {
            panellJoc.j1.setPunts(panellJoc.j1.getPunts()+1);
            reiniciarPosicio();
        }

    }

    private boolean collision(Racquet racquet) {
        return racquet.getBounds().intersects(getBounds());
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, MIDA_BOLA, MIDA_BOLA);
    }

    public void paintComponent(Graphics2D g) {
        // (posició X, posició Y, amplada, altura)
        g.fillOval(x, y, MIDA_BOLA, MIDA_BOLA);
        bolaMoviment();
    }

    private void reiniciarPosicio() {
        x = AMPLADA_FINESTRA / 2;
        y = ALTURA_FINESTRA / 2;
    }

}
