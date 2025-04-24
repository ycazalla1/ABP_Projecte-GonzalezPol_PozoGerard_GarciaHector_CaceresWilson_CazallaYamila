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
 * <p>
 * Incorpora etiquetes d'Hibernate per poder utilitzar-lo directament.
 * <br>
 * El id del jugador és autoincremental, la classe la incorpora però no es pot
 * modificar o canviar, quan es declara un jugador comença en 1 i es va
 * incrementant.
 * </p>
 */
@Entity(name = "jugadors")
public class Jugador {

    /**
     * El id del jugador, es autoincrementa
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_jugador")
    private int id_jugador;
    /**
     * Nom del jugador
     */
    @Column(name = "nom")
    private String nom;
    /**
     * Punts del jugador
     */
    @Column(name = "punts")
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
