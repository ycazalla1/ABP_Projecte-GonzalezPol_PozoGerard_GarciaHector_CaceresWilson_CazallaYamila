package org.example;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Racquet {
    /**
     *Mida de la pala
     */
    private final static int MIDA_AMPLADA = 30, MIDA_ALTURA = 150;

    /**
     * Posició i velocitat vertical
     */
    private int x, y, ya = 0;

    /**
     *Panell del joc
     */
    private PanellJoc panellJoc;

    /**
     * Costats de les pales
     */
    private int palaJ1, palaJ2;

    /**
     *Constructor buit
     */
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

    /**
     * Limita el moviment de la pala
     */
    public void raqcquetLimitBores(){
        if (y + ya > Variables.MARGES && y + ya < panellJoc.getHeight()-MIDA_ALTURA - Variables.MARGES){
            y += ya;
        }
    }

    /**
     *Retorna l'àrea de la pala
     */

    public Rectangle getBounds() {
        return new Rectangle(x, y, Variables.MIDA_AMPLE_RACQUET, Variables.MIDA_ALT_RACQUET);
    }

    /**
     *Coordenada X per col·lisions
     */
    public int getTotalX() {
        // EL 40 ES EL MARGEN MÁS EL MARGEN DE LA RACQUET CON EL RECTANGULO
        if (x == 40)
            return x + Variables.MIDA_AMPLE_RACQUET;
        else
            return x - Variables.MIDA_AMPLE_RACQUET;
    }


    /**
     * Dibuixa la pala
     */
    public void paint(Graphics g){
        g.fillRect(x, y, Variables.MIDA_AMPLE_RACQUET, Variables.MIDA_ALT_RACQUET);

    }

    /**
     * Atura el moviment en deixar anar la tecla
     */
    public void keyReleased(KeyEvent e) {
        ya = 0;
    }


    /**
     * Mou la pala segons la tecla
     */
    public void keyPressed(KeyEvent e) {
        final int VELOCITAT_PALA = 6;
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


    /**
     * Assigna costat de les pales segons punts
     * @param punts
     */
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
