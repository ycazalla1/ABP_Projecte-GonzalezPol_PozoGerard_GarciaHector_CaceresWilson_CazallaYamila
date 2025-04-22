package org.example.connectorHibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import org.example.Jugador;

/**
 * Classe que permet controlar de forma senzilla operacions que necesitin
 * connectar-se a la BBDD, utilitzant Hibernate.
 * <p>
 * Incorpora el mètode <b>{@link #afegirInformacio(Jugador)}</b>, demana un objecte de
 * la classe <b>{@link Jugador}</b> i el afegeix a la BBDD.
 * </p>
 */
public class ConnectorHibernate {

    /**
     * Afegeix les dades d'un jugador a la BBDD
     * 
     * @param jugador Objecte de la classe Jugador
     */
    public static void afegirInformacio(Jugador jugador) {
        SessionFactory sf = new Configuration()
                .addAnnotatedClass(Jugador.class)
                .configure()
                .buildSessionFactory();
        Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(jugador);
        transaction.commit();
        sf.close();
    }

}
