package org.example;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "jugadors")
public class Jugador {

    @Id
    @Column(name = "id_jugador")
    private int id_jugador;
    @Column(name = "nom")
    private String nom;
    @Column(name = "punts")
    private int punts;

    public int getPunts() {
        return punts;
    }

    public String getNom() {
        return nom;
    }

    public Jugador(int id, String nom, int punts) {
        this.id_jugador = id;
        this.nom = nom;
        this.punts = punts;
    }

}
