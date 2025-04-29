package org.example.menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuInicial extends JFrame {

    Image foto;
    JPanel panelConFondo;

    public MenuInicial() {

        //Image fondo = new ImageIcon(getClass().getResource("/RetroTenis_background.png")).getImage();
        ImageIcon fondo = new ImageIcon("src/resources/RetroTenis_background.png");
        foto = fondo.getImage();

        panelConFondo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(foto, 0, 0, null);
            }
        };


        Font fuente = new Font("Verdana", Font.BOLD, 28);

        JButton btnJugar = crearBoton("JUGAR", fuente, e -> {
            JOptionPane.showMessageDialog(null, "Iniciando juego...");
        });

        JButton btnOpciones = crearBoton("OPCIONES", fuente, e -> {
            JOptionPane.showMessageDialog(null, "Opciones aún no disponibles.");
        });

        JButton btnSalir = crearBoton("SALIR", fuente, e -> {
            System.exit(0);
        });

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.insets = new Insets(25, 0, 25, 0);
        gbc.fill = GridBagConstraints.NONE;

        panelConFondo.add(btnJugar, gbc);
        panelConFondo.add(btnOpciones, gbc);
        panelConFondo.add(btnSalir, gbc);

        add(panelConFondo);
        setVisible(true);
    }

    public void mostrarMenu() {
        JFrame f = new JFrame("Menú Principal");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(1280, 800);
        f.setLocationRelativeTo(null);
        f.setResizable(false);
        f.add(panelConFondo);
        f.setVisible(true);
    }

    private JButton crearBoton(String texto, Font fuente, ActionListener accion) {
        JButton boton = new JButton(texto);
        boton.setFont(fuente);
        boton.setFocusPainted(false);
        boton.setBackground(new Color(30, 30, 30));
        boton.setForeground(Color.WHITE);
        boton.setPreferredSize(new Dimension(320, 70));
        boton.addActionListener(accion);
        return boton;
    }
}