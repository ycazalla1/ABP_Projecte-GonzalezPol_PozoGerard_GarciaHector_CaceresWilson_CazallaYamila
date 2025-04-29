package org.example;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class Idioma {
    private String codiIdioma;
    private Map<String, String> traduccions;

    public Idioma(String codiIdioma){
        this.codiIdioma = "ca";
        this.traduccions = new HashMap<>();
    }
    public void carregar(){
        String url = "jdbc:mysql://localhost:3306/retrotenis";
        String nom = "usuari";
        String contrasenya = "1234";

        try (Connection conexio = DriverManager.getConnection(url, nom, contrasenya)) {
            String consulta = "SELECT * FROM idioma WHERE codi_idioma = ?";
            PreparedStatement pr = conexio.prepareStatement(consulta);
            pr.setString(1, codiIdioma);

            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                String clau = rs.getString("clau");
                String text = rs.getString("text");
                traduccions.put(clau, text);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String traduir(String clau){
        return traduccions.getOrDefault(clau, clau);
    }
    public String getCodiIdioma(){
        return codiIdioma;
    }
    public void setCodiIdioma(String codiIdioma){
        this.codiIdioma = codiIdioma;
    }
}