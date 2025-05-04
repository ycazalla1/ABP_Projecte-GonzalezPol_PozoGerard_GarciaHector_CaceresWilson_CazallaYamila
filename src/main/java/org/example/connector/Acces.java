package org.example.connector;
import org.example.Jugador;
import org.example.Variables;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.example.Variables.idiomaSeleccionado;

/**
 * Classe Acces
 *
 * Funciona de connector entre el programa i la BBDD
 *
 * Afegeix jugadors a la BBDD només si no existeixen en la BBDD
 * Modifica la puntuació del jugador si és superior a la que ja hi ha
 * Carrega les traduccions de les paraules segons l'idioma seleccionat
 *
 * @author Grup-1
 */
public class Acces {
    /**
     * Nom d'usuari de la base de dades
     */
    private static final String NOM = "root";
    /**
     * Contrasenya de la base de dades
     */
    private static final String CONTRASENYA = "1234";
    /**
     * URL de la base de dades
     */
    private static final String URL = "jdbc:mysql://localhost:3306/retro_tenis";
    private static final int RANQUING_JUGADORS_MAXIMS = 10;

    /**
     * Afegeix els jugadors a la BBDD
     *
     * Si un jugador ja existeix en la BBDD no s'afageix
     *
     * @param j1 Jugador 1
     * @param j2 Jugador 2
     */
    public static void afegirInformacio(Jugador j1, Jugador j2) {
        try {
            Connection conexio = DriverManager.getConnection(URL, NOM, CONTRASENYA);

            Statement consulta = conexio.createStatement();

            String jugador1 = j1.getNom();
            int puntsJ1 = j1.getPunts();

            String jugador2 = j2.getNom();
            int puntsJ2 = j2.getPunts();

            if (!usuariRepetit(j1)) {
                consulta.executeUpdate("insert into usuaris (nom_usuari, puntuacio, idioma)" +
                        "values ('" + jugador1 + "', " + puntsJ1 + ", '" + idiomaSeleccionado + "')");
            }

            if (!usuariRepetit(j2)) {
                consulta.executeUpdate("insert into usuaris (nom_usuari, puntuacio, idioma)" +
                        "values ('" + jugador2 + "', " + puntsJ2 + ", '" + idiomaSeleccionado + "')");
            }
            conexio.close();
        }
        catch (SQLException e){
            System.out.println(e.toString());
        }
    }

    /**
     * Torna si un usuari ja existeix a la BBDD
     *
     * @param j Jugador
     * @return true/false Si ja existeix o no
     */
    public static boolean usuariRepetit(Jugador j) {

        try {
            Connection conexio = DriverManager.getConnection(URL, NOM, CONTRASENYA);

            Statement st = conexio.createStatement();
            String consulta = "SELECT nom_usuari FROM usuaris WHERE nom_usuari = '" + j.getNom() + "'";
            ResultSet rs = st.executeQuery(consulta);
            if (rs.next()) {
                System.out.println("El nom d'usuari ja existeix");
                return true;
            } else {
                System.out.println("El nom d'usuari no existeix");
                return false;
            }

        } catch(SQLException e) {
            System.out.println(e.toString());
        }
        return false;
    }

    /**
     * Modifica la puntuació del jugador en la BBDD si es major a la que ja hi ha
     *
     * @param j Jugador
     */
    public static void modificarPuntuacio(Jugador j) {
        try {
            Connection conexio = DriverManager.getConnection(URL, NOM, CONTRASENYA);

            Statement st = conexio.createStatement();

            ResultSet rs = st.executeQuery("SELECT puntuacio FROM usuaris WHERE nom_usuari = '" + j.getNom() + "'");
            if (rs.next()) {
                if (j.getPunts() < rs.getInt("puntuacio")) {
                    System.out.println("La puntuació no es pot modificar, ja que és inferior a la que hi ha a la base de dades");

                } else {
                    st.executeUpdate("UPDATE usuaris SET puntuacio = " + j.getPunts() + " WHERE nom_usuari = '" + j.getNom() + "'");
                }
            }
        } catch(SQLException e) {
            System.out.println(e.toString());
        }
    }

    /**
     * Rep una paraula i torna una traducció segons el que hi ha en la BBDD
     *
     * @param paraula Paraula a cercar en la BBDD
     * @return Paraula traduïda de la BBDD
     */
    public static String carregarIdioma(String paraula){
        String traduccio = "";
        try {
            Connection conexio = DriverManager.getConnection(URL, NOM, CONTRASENYA);

            Statement st = conexio.createStatement();
            String consulta = "SELECT " + Variables.idiomaSeleccionado + " FROM traduccions WHERE clau = '" + paraula + "'";
            ResultSet rs = st.executeQuery(consulta);
            if (rs.next()) {
                traduccio = rs.getString(Variables.idiomaSeleccionado);
            }
        } catch(SQLException e) {
            System.out.println(e.toString());
        }
        return traduccio;

    }

    public static ArrayList<Jugador> mostrarRanking() {

        ArrayList<Jugador> mejoresJugadores = new ArrayList<>();

        try {
            Connection conexio = DriverManager.getConnection(URL, NOM, CONTRASENYA);

            Statement st = conexio.createStatement();


            String consulta = "SELECT nom_usuari, puntuacio FROM usuaris ORDER BY puntuacio DESC";
            ResultSet rs = st.executeQuery(consulta);
            int i = 0;
            while (rs.next() && i < RANQUING_JUGADORS_MAXIMS) {
                String nombre = rs.getString("nom_usuari");
                int puntuacion = rs.getInt("puntuacio");

                // Crear un objeto Jugador y agregarlo a la lista
                Jugador jugador = new Jugador(nombre, puntuacion);
                mejoresJugadores.add(jugador);
                i++;
            }
        } catch(SQLException e) {
            System.out.println(e.toString());
        }

        return mejoresJugadores;

    }

}
