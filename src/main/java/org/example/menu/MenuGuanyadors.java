package org.example.menu;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MenuGuanyadors extends JPanel {
    Image background;

    public MenuGuanyadors(List<String> jugadores, Runnable onVolverAlMenu) {
        ImageIcon obj = new ImageIcon("src/resources/images/RetroTenis_background.png");
        background = obj.getImage();

        setLayout(new BorderLayout());

        Font fuenteTitulo = new Font("Verdana", Font.BOLD, 48);
        Font fuenteJugadores = new Font("Verdana", Font.BOLD, 26);
        Color colorTexto = new Color(255, 0, 0); // Rojo para dramatismo de "Game Over"

        JLabel lblGameOver = new JLabel("GAME OVER");
        lblGameOver.setFont(fuenteTitulo);
        lblGameOver.setForeground(colorTexto);
        lblGameOver.setHorizontalAlignment(SwingConstants.CENTER);
        lblGameOver.setBorder(BorderFactory.createEmptyBorder(40, 10, 20, 10));

        JPanel panelJugadores = new JPanel();
        panelJugadores.setOpaque(false);
        panelJugadores.setLayout(new BoxLayout(panelJugadores, BoxLayout.Y_AXIS));

        for (String jugador : jugadores) {
            JLabel lblJugador = new JLabel(jugador, SwingConstants.CENTER);
            lblJugador.setFont(fuenteJugadores);
            lblJugador.setForeground(Color.WHITE);
            lblJugador.setAlignmentX(Component.CENTER_ALIGNMENT);
            panelJugadores.add(lblJugador);
        }

        JButton btnVolver = new JButton("Volver al menú");
        btnVolver.setFont(new Font("Verdana", Font.BOLD, 24));
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setBackground(new Color(128, 0, 0, 200));
        btnVolver.setFocusPainted(false);
        btnVolver.setBorderPainted(false);
        btnVolver.setPreferredSize(new Dimension(260, 50));
        btnVolver.addActionListener(e -> onVolverAlMenu.run());

        JPanel panelInferior = new JPanel();
        panelInferior.setOpaque(false);
        panelInferior.add(btnVolver);

        add(lblGameOver, BorderLayout.NORTH);
        add(panelJugadores, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
    }
}
