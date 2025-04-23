package org.example;

import java.awt.*;

public class Barra {

    final int MIDA_AMPLADA = 30, MIDA_ALTURA = 150;

    int x = 0, y = 0;

    private PanellJoc pJoc;

    public Barra(PanellJoc panellJoc) {
        this.pJoc = panellJoc;
    }

    public void paintComponent(Graphics2D g) {
        // (posició X, posició Y, amplada, altura)
        g.fillOval(x,y, MIDA_AMPLADA, MIDA_ALTURA);
    }

}
