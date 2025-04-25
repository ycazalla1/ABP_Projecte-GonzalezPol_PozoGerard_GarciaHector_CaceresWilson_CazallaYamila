package org.example;

import java.awt.*;
import static org.example.PanellJoc.*;

public class Bola {

    private PanellJoc panellJoc;

    private static final int VELOCITAT_BOLA = 2;
    int x = AMPLADA_FINESTRA/2, y = ALTURA_FINESTRA/2;
    int xa = 2, ya = 2;
    int puntsJ1, puntsJ2;

    private final int MIDA_BOLA = 30;


    public Bola(PanellJoc panellJoc) {
        this.panellJoc = panellJoc;
        this.x = panellJoc.getWidth() / 2 - MIDA_BOLA / 2;
        this.y = panellJoc.getHeight() / 2 - MIDA_BOLA / 2;
    }

    public void bolaMoviment() {
        final int VELOCITAT_BOLA = 3;
        if (x + xa < 0)
            xa = VELOCITAT_BOLA;
        //System.out.println("p");
        if (x + xa > panellJoc.getWidth() - 30)
            xa = -VELOCITAT_BOLA;
        if (y + ya < 30)
            ya = VELOCITAT_BOLA;
        if (y + ya > panellJoc.getHeight() - 60)
            ya = -VELOCITAT_BOLA;
        if (collision(panellJoc.r1)) {
            xa = VELOCITAT_BOLA;
            x = panellJoc.r1.getX() - MIDA_BOLA;
        }
        if (collision(panellJoc.r2)) {
            xa = -VELOCITAT_BOLA;
            x = panellJoc.r2.getX() + MIDA_BOLA;
        }

        x = x + xa;
        y = y + ya;
    }

    private boolean collision(Racquet racquet) {
        return racquet.getBounds().intersects(getBounds());
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, MIDA_BOLA, MIDA_BOLA);
    }

    public void paintComponent(Graphics2D g) {
        //this.x = panellJoc.getWidth()/2;
        //this.y = panellJoc.getHeight()/2;
        // (posició X, posició Y, amplada, altura)
        g.fillOval(x, y, MIDA_BOLA, MIDA_BOLA);
    }

    private void reiniciarPosicio() {
        x = AMPLADA_FINESTRA / 2;
        y = ALTURA_FINESTRA / 2;
    }

}
