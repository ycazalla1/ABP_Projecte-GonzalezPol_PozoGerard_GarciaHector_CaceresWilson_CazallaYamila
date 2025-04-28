package org.example;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class Idioma {
    private String codiIdioma;
    private Map<String, String> traduccions;

    public Idioma(String codiIdioma){
        this.codiIdioma = "ca";
        this.traduccions = new HashMap<>();
    }
}
