package org.example;

import java.awt.*;

public class Obstacles {

    private int x, y;
    private int amplada, altura;
    private PanellJoc panellJoc;

    /**
     * Constructor de la classe Obstacles
     */
    public Obstacles() {
        // Constructor de la classe Obstacles
        // Aquí pots inicialitzar els atributs o fer altres operacions necessàries
    }

    /**
     * Constructor de la classe Obstacles
     *
     * @param x        posició x
     * @param y        posició y
     * @param amplada  amplada del obstacle
     * @param altura   altura del obstacle
     * @param panellJoc panell de joc
     */
    public Obstacles(int x, int y, int amplada, int altura, PanellJoc panellJoc) {
        this.x = x;
        this.y = y;
        this.amplada = amplada;
        this.altura = altura;
        this.panellJoc = panellJoc;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, amplada, altura);
    }

    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(x, y, amplada, altura);
    }

}
