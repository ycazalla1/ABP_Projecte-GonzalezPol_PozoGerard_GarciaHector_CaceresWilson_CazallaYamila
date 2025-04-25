package org.example;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Racquet {
    private final static int MIDA_AMPLADA = 30, MIDA_ALTURA = 150;
    private int x, y, ya = 0;
    private PanellJoc panellJoc;


    public Racquet(int x, int y, PanellJoc panellJoc){
        this.x = x;
        this.y = y;
        this.panellJoc = panellJoc;
    }

    public void raqcquetLimitBores(){
        if (y + ya > 0 && y + ya < panellJoc.getHeight()-MIDA_ALTURA){
            y += ya;
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, MIDA_AMPLADA, MIDA_ALTURA);
    }

    public int getTotalX() {
        if (x == 0)
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
        final int VELOCITAT_PALA = 4;
        if (e.getKeyCode() == KeyEvent.VK_W && x == 0)
            ya = -VELOCITAT_PALA;
        if (e.getKeyCode() == KeyEvent.VK_S && x == 0)
            ya = VELOCITAT_PALA;
        if (e.getKeyCode() == KeyEvent.VK_O && x != 0)
            ya = -VELOCITAT_PALA;
        if (e.getKeyCode() == KeyEvent.VK_L && x != 0)
            ya = VELOCITAT_PALA;
    }
}
