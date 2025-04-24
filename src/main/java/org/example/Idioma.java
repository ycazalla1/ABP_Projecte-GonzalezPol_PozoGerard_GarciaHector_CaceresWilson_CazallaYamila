package org.example;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class Idioma {
    private String idioma;
    private Map<String, String> traduccions = new HashMap<>();

    public Idioma(String idioma) {
        this.idioma = idioma;

    }

    private void carregarIdioma(){
        try {
            String nom = "usuari";
            String contrasenya = "1234";
            String url = "jdbc:mysql://localhost:3306/retrotenis";
            Connection conexio = DriverManager.getConnection(url, nom, contrasenya);


            String query = "Select clau, text from traduccions where idioma = ?";
            PreparedStatement pr = conexio.prepareStatement(query);

        } catch (SQLException e){

        }
    }
}
