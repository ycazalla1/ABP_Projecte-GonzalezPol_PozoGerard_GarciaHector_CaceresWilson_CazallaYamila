package org.example;

import java.awt.*;

public class Bola {

    // Posició inicial bola
    int x = 0, y = 0;
    // Increment de posició bola = velocitat
    int xa = 1, ya = 1;

    private final int MIDA_BOLA = 30;

    private PanellJoc panellJoc;

    public Bola(PanellJoc panellJoc) {
        this.panellJoc = panellJoc;
    }

    public void moviment() {
        if (x + xa < 0)
            xa = 1;
        if (x + xa > panellJoc.getWidth() - 30)
            xa = -1;
        if (y + ya < 0)
            ya = 1;
        if (y + ya > panellJoc.getHeight() - 30)
            ya = -1;

        x = x + xa;
        y = y + ya;
    }

    public void paintComponent(Graphics2D g) {
        // (posició X, posició Y, amplada, altura)
        g.fillOval(x,y, MIDA_BOLA, MIDA_BOLA);
    }

}
