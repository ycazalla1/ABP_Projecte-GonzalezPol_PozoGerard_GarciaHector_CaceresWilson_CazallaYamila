package org.example;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "jugadors")
public class Jugador {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id_jugador;
    @Column(name = "nom")
    private String nom;
    @Column(name = "punts")
    private int punts;

    public Jugador(String nom, int punts) {
        this.nom = nom;
        this.punts = punts;
    }

}
