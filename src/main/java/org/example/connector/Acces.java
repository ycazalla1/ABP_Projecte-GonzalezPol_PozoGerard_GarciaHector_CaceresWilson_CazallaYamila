package org.example.connector;
import org.example.Jugador;
import java.sql.*;

public class Acces {
    private static final String NOM = "root";
    private static final String CONTRASENYA = "1234";
    private static final String URL = "jdbc:mysql://localhost:3306/retro_tenis";

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
                        "values ('" + jugador1 + "', " + puntsJ1 + ", 'Català')");
            }

            if (!usuariRepetit(j2)) {
                consulta.executeUpdate("insert into usuaris (nom_usuari, puntuacio, idioma)" +
                        "values ('" + jugador2 + "', " + puntsJ2 + ", 'Català')");
            }
            conexio.close();
        }
        catch (SQLException e){
            System.out.println(e.toString());
        }
    }

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

}
