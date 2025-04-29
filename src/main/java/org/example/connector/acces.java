package org.example.connector;
import org.example.Jugador;
import java.sql.*;

public class acces {
    public static void afegirInformacio(Jugador j){
        try {
            String nom = "usuari";
            String contrasenya = "1234";
            String url = "jdbc:mysql://localhost:3306/retrotenis";
            Connection conexio = DriverManager.getConnection(url, nom, contrasenya);

            String consulta = "insert into taula (nom_usuari, puntuacio) values (?, ?)";
            PreparedStatement pr = conexio.prepareStatement(consulta);
            pr.setString(1, j.getNom());
            pr.setInt(2, j.getPunts());

            Statement st = conexio.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            st.execute(consulta);
            pr.executeUpdate();
            conexio.close();
        }
        catch (SQLException e){
            System.out.println(e.toString());
        }
    }
}
