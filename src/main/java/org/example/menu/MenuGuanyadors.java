package org.example.menu;

import org.example.Jugador;
import org.example.PanellJoc;
import org.example.Temporitzador;
import org.example.Variables;
import org.example.connector.Acces;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

public class MenuGuanyadors extends JPanel {

    private Image fondo;

    public MenuGuanyadors() {
        // Cargar fondo
        ImageIcon iconoFondo = new ImageIcon("src/resources/images/fons_1_pingpong.png");
        fondo = iconoFondo.getImage();

        setLayout(null);
        Font fuente = new Font("Verdana", Font.BOLD, 20);
        Color colorTexto = Color.CYAN;

        // Título
        JLabel lblTitulo = new JLabel(Acces.carregarIdioma("rankingTitol"));  // Asume que tienes una clave "rankingTitol"
        lblTitulo.setFont(new Font("Verdana", Font.BOLD, 26));
        lblTitulo.setForeground(colorTexto);
        lblTitulo.setBounds(300, 30, 500, 40);
        add(lblTitulo);

        ArrayList<Jugador> jugadors = Acces.mostrarRanking();

        // Tabla con los jugadores
        String[] columnes = {"Nom Usuari", "Puntuació"};
        DefaultTableModel model = new DefaultTableModel(columnes, 0);
        for (Jugador jugador : jugadors) {
            Object[] fila = {jugador.getNom(), jugador.getPunts()};
            model.addRow(fila);
        }
        JTable tabla = new JTable(model);
        tabla.setFont(fuente);
        tabla.setRowHeight(30);
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(200, 100, 500, 200);
        add(scroll);

        // Botón volver
        JButton btnVolver = new JButton(Acces.carregarIdioma("tornarMenu"));
        btnVolver.setFont(new Font("Verdana", Font.BOLD, 22));
        btnVolver.setForeground(new Color(0, 255, 255));
        btnVolver.setFocusPainted(false);
        btnVolver.setBorderPainted(false);
        btnVolver.setOpaque(true);
        btnVolver.setBackground(new Color(0, 0, 128, 180));
        //btnVolver.getBounds(amplada);

// Acción del botón
        btnVolver.addActionListener(e -> {
            JFrame frameActual = (JFrame) SwingUtilities.getWindowAncestor(this);
            frameActual.setContentPane(new MenuPrincipal());
            frameActual.revalidate();
        });

        // Cargar datos de la base de datos

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
    }

    public void mostrar() {
        JFrame frame = new JFrame(Acces.carregarIdioma("rankingTitol"));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(Variables.ampladaFinestra, Variables.alturaFinestra);
        frame.setLocationRelativeTo(null);
        frame.setContentPane(this);
        frame.setVisible(true);
    }
}
