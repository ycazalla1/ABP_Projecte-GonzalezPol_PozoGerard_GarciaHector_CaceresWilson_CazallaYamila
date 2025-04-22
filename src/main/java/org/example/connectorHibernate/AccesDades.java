package org.example.connectorHibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import org.example.Jugador;

// Import del jugador provisional

public class AccesDades {

    public static void afegirInformacio(Jugador j) {
        SessionFactory sf = new Configuration()
             .addAnnotatedClass(Jugador.class)
             .configure()
             .buildSessionFactory();
        Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(j);
        transaction.commit();
        sf.close();
    }

}
