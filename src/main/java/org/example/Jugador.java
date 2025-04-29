package org.example;

import jakarta.persistence.*;

/**
 * Classe que permet crear un jugador.
 * <p>
 * Un jugador té que tenir un nom i un nombre de punts.
 * </p>
 * <p>
 * Incorpora un constructor per indicar el nom i nombre de punts que té el
 * jugador <b>{@link #Jugador(String, int)}</b>.
 * </p>
 */
public class Jugador {

    /**
     * Nom del jugador
     */
    private String nom;
    /**
     * Punts del jugador
     */
    private int punts;

    /**
     * Retorna el nom del jugador
     * 
     * @return Nom del jugador
     */
    public String getNom() {
        return nom;
    }

    /**
     * Retorna els punts que té el jugador
     * 
     * @return Punts que ha aconseguit el jugador
     */
    public int getPunts() {
        return punts;
    }

    public void setPunts(int punts) {
        this.punts = punts;
    }

    /**
     * Constructor Jugador, estableix el nom i els punts que té el jugador.
     * 
     * @param nom Nom del jugador
     * @param punts Punts del jugador
     */
    public Jugador(String nom, int punts) {
        this.nom = nom;
        this.punts = punts;
    }
}
