package org.example;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Racquet {
    private final static int MIDA_AMPLADA = 30, MIDA_ALTURA = 150;
    private int x, y, ya = 0;
    private PanellJoc panellJoc;

    private int palaJ1, palaJ2;

    public Racquet() {

    }

    /**
     * Constructor de la pala
     * @param x posicio x
     * @param y posicio y
     * @param panellJoc panell de joc
     */
    public Racquet(int x, int y, PanellJoc panellJoc){
        this.x = x;
        this.y = y;
        this.panellJoc = panellJoc;
    }

    public void raqcquetLimitBores(){
        if (y + ya > Variables.MARGES && y + ya < panellJoc.getHeight()-MIDA_ALTURA - Variables.MARGES){
            y += ya;
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, MIDA_AMPLADA, MIDA_ALTURA);
    }

    public int getTotalX() {
        // EL 40 ES EL MARGEN MÁS EL MARGEN DE LA RACQUET CON EL RECTANGULO
        if (x == 40)
            return x + MIDA_AMPLADA;
        else
            return x - MIDA_AMPLADA;
    }

    public void paint(Graphics g){
        g.fillRect(x, y, MIDA_AMPLADA, MIDA_ALTURA);
    }

    public void keyReleased(KeyEvent e) {
        ya = 0;
    }


    public void keyPressed(KeyEvent e) {
        final int VELOCITAT_PALA = 5;
        // EL 40 ES EL MARGEN MÁS EL MARGEN DE LA RACQUET CON EL RECTANGULO
        if (e.getKeyCode() == KeyEvent.VK_W && x == 40)
            ya = -VELOCITAT_PALA;
        if (e.getKeyCode() == KeyEvent.VK_S && x == 40)
            ya = VELOCITAT_PALA;
        if (e.getKeyCode() == KeyEvent.VK_O && x != 40)
            ya = -VELOCITAT_PALA;
        if (e.getKeyCode() == KeyEvent.VK_L && x != 40)
            ya = VELOCITAT_PALA;
    }

    public void setPala(int punts) {
        final int BARRA_ESQUERRA = panellJoc.posRCentral,
        BARRA_DRETA = panellJoc.ampleRCentral;

        if (punts < 3) {
            palaJ1 = BARRA_ESQUERRA;
            palaJ2 = BARRA_DRETA;
        } else {
            //panellJoc.b.setComptadorPunts(0);
            if (palaJ1 == BARRA_ESQUERRA) {
                palaJ1 = BARRA_DRETA;
                palaJ2 = BARRA_ESQUERRA;
            } else {
                palaJ1 = BARRA_ESQUERRA;
                palaJ2 = BARRA_DRETA;

            }
        }

    }

}
