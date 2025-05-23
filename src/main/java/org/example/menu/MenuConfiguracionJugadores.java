package org.example.menu;
import org.example.Joc;
import org.example.connector.Acces;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static org.example.Variables.Paraules.*;

public class MenuConfiguracionJugadores extends JPanel{

    private Image fondo;
    public static String[] jugadors;
    public static String nivell;

    Acces ac = new Acces();

    public MenuConfiguracionJugadores(Runnable volverAlMenu, java.util.function.BiConsumer<String[], String> iniciarJuego) {
        ImageIcon iconoFondo = new ImageIcon("src/resources/images/fons_1_pingpong.png");
        fondo = iconoFondo.getImage();

        setLayout(null);
        Font fuente = new Font("Verdana", Font.BOLD, 20);
        Color colorTexto = Color.CYAN;

        // Título
        JLabel lblTitulo = new JLabel(Acces.carregarIdioma(mjTitol));
        lblTitulo.setFont(new Font("Verdana", Font.BOLD, 26));
        lblTitulo.setForeground(colorTexto);
        lblTitulo.setBounds(300, 30, 500, 40);
        add(lblTitulo);

        // Nombre Jugador 1
        JLabel lblJ1 = new JLabel(Acces.carregarIdioma(mjJugador1) + ":");
        lblJ1.setFont(fuente);
        lblJ1.setForeground(colorTexto);
        lblJ1.setBounds(250, 100, 150, 40);
        add(lblJ1);

        JTextField campoJ1 = new JTextField();
        campoJ1.setFont(fuente);
        campoJ1.setBounds(400, 100, 250, 40);
        add(campoJ1);

        // Nombre Jugador 2
        JLabel lblJ2 = new JLabel(Acces.carregarIdioma(mjJugador2) + ":");
        lblJ2.setFont(fuente);
        lblJ2.setForeground(colorTexto);
        lblJ2.setBounds(250, 160, 150, 40);
        add(lblJ2);

        JTextField campoJ2 = new JTextField();
        campoJ2.setFont(fuente);
        campoJ2.setBounds(400, 160, 250, 40);
        add(campoJ2);

        // Nivel de dificultad (ahora como campo numérico)
        JLabel lblNivel = new JLabel(Acces.carregarIdioma(mjNivell) + ":");
        lblNivel.setFont(fuente);
        lblNivel.setForeground(colorTexto);
        lblNivel.setBounds(250, 220, 150, 40);
        add(lblNivel);

        JTextField campoNivel = new JTextField();
        campoNivel.setFont(fuente);
        campoNivel.setBounds(400, 220, 250, 40);
        add(campoNivel);

        // Botón iniciar juego
        JButton btnIniciar = new JButton(Acces.carregarIdioma(mjIniciarPartida));
        btnIniciar.setFont(fuente);
        btnIniciar.setBackground(new Color(0, 128, 255));
        btnIniciar.setForeground(Color.WHITE);
        btnIniciar.setBounds(350, 300, 250, 50);
        btnIniciar.addActionListener(e -> {
            jugadors = new String[]{campoJ1.getText(), campoJ2.getText()};
            nivell = campoNivel.getText();  // Ahora es un String escrito por el usuario
            iniciarJuego.accept(jugadors, nivell);
            try {
                Joc joc = new Joc(jugadors, nivell);
                joc.pJoc.setVisible(true);
                // Cierra la ventana de configuración
                Window ventana = SwingUtilities.getWindowAncestor(this);
                ventana.dispose();
            } catch (InterruptedException|IOException ex) {
                ex.printStackTrace();
            }
        });
        add(btnIniciar);

        // Botón volver
        JButton btnVolver = new JButton(BOTO_TORNAR);
        btnVolver.setFont(fuente);
        btnVolver.setBounds(20, 20, 150, 40);
        btnVolver.setForeground(colorTexto);
        btnVolver.setBackground(Color.DARK_GRAY);
        btnVolver.addActionListener(e -> volverAlMenu.run());
        add(btnVolver);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
    }
}