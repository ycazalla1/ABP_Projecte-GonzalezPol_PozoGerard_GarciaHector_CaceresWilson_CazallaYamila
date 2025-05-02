package org.example;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Classe Obstacles
 *
 * Classe que representa els obstacles del joc, la seva posició, velocitat, amplada i alçada
 *
 * Permet crear de forma aleatoria en posicions aleatorias una quantitat x de obstacles
 * Permet que els obstacles es moguin sense parar d'adalt a abaix
 *
 * @author Grup-1
 */
public class Obstacles {

    /**
     * Amplada per defecte del obstacle
     */
    private final static int AMPLADA_DEFECTE = 30;
    /**
     * Altura per defecte del obstacle
     */
    private final static int ALTURA_DEFECTE = 50;
    /**
     * La cuantitat d'obstacles aleatoris que hi hauran per pantalla
     */
    private final static int RANG_MOVIMENT = 300;

    /**
     * Les coordenades del objecte dins del panell
     */
    private int x, y;
    /**
     * La velocitat del obstacle
     */
    private int ya = 1;
    /**
     * La posició inicial y del obstacle
     */
    private int posicioInicial;

    /**
     * Constructor de la classe Obstacles
     *
     * @param x posició x
     * @param y posició y
     */
    public Obstacles(int x, int y) {
        this.x = x;
        this.y = y;
        this.posicioInicial = y;
    }

    /**
     * Genera obstacles de forma aleatoria, deixa un espai determinat entre les
     * racquets i la generació dels obstacles, no poden sortir obstacles que
     * estiguin collisionant entre ells
     *
     * @param amplada Amplada del panell
     * @param altura  Altura del panell
     * @return ArrayList d'obstacles, amb tots els obstacles creats
     */
    public static ArrayList<Obstacles> generarObstacles(int amplada, int altura, int quantiatObstacles) {
        Random r = new Random();
        ArrayList<Obstacles> obstacles = new ArrayList<>();

        int minimAmple = amplada / 3;
        int limitAmple = amplada - minimAmple;
        int minimAlt = 60;
        int limitAlt = altura - 60;

        final int SEPARACIO_MINIMA = AMPLADA_DEFECTE;

        int i = 0;
        while (i < quantiatObstacles) {
            int obstacleX = r.nextInt(minimAmple, limitAmple);
            int obstacleY = r.nextInt(minimAlt, limitAlt);

            boolean estaAprop = false;
            for (Obstacles o : obstacles) {
                // comprobar si están demasiado cerca en X e Y
                if (Math.abs(obstacleX - o.x) < SEPARACIO_MINIMA &&
                        Math.abs(obstacleY - o.y) < SEPARACIO_MINIMA) {
                    estaAprop = true;
                    break;
                }
            }

            if (!estaAprop) {
                obstacles.add(new Obstacles(obstacleX, obstacleY));
                i++;
            }
        }
        return obstacles;

    }

    /**
     * Mètode que fa moure el obstacle, si toca el límit de la pantalla canvia de
     * direcció
     *
     * @param altura Altura del panell
     */

    public void movimientObstacles(int altura) {
        // Mi cuerpo pide salsa
        y += ya;

        if (y <= posicioInicial - RANG_MOVIMENT || y >= posicioInicial + RANG_MOVIMENT) {
            ya = -ya;
        }

        // Si toca el borde de la pantalla
        if (y < Variables.MARGES) {
            y = Variables.MARGES;
            ya = -ya;
        } else if (y + ALTURA_DEFECTE > altura - Variables.MARGES) {
            y = altura - Variables.MARGES - ALTURA_DEFECTE;
            ya = -ya;
        }
    }

    /**
     * Retorna la posició del obstacle
     *
     * @return Posició del obstacle
     */
    public Rectangle getBounds() {
        return new Rectangle(x, y, AMPLADA_DEFECTE, ALTURA_DEFECTE);
    }

    /**
     * Mètode per pintar per el panell l'obstacle
     *
     * @param g Objecte Graphics per pintar
     */
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(x, y, AMPLADA_DEFECTE, ALTURA_DEFECTE);
    }

}
