package org.example;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Classe KeyInputs
 *
 * Gestiona les entrades per teclat
 *
 * @author Grup-1
 */
public class KeyInputs implements KeyListener {

    /**
     * Atribut KeyListener per poder interactuar amb el teclat
     */
    KeyListener keyListener = new KeyListener() {
        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {

        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    };

    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * Mètode per gestionar quan una de tecla es premuda
     *
     * @param e Objecte KeyEvent
     */
    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("keyPressed="+KeyEvent.getKeyText(e.getKeyCode()));
    }

    /**
     * Mètode per gestionar quan una de tecla es premuda
     *
     * @param e Objecte KeyEvent
     */
    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("keyReleased="+KeyEvent.getKeyText(e.getKeyCode()));
    }
}