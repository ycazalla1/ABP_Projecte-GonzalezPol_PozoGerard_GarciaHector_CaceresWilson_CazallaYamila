package org.example.menu;

import org.example.PanellJoc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MenuPausa extends JPanel {

    private Image fondo;

    public MenuPausa(Runnable reanudarJuego, Runnable reiniciarJuego, Runnable salirAlMenu) {
        ImageIcon bg = new ImageIcon("src/resources/images/fons_1_pingpong.png");
        fondo = bg.getImage();

        setLayout(null);
        Font fuente = new Font("Verdana", Font.BOLD, 22);
        Color colorTexto = new Color(0, 255, 255);
        Color fondoBoton = new Color(0, 0, 128, 180);

        int centroX = 400;

        // Botón Reanudar
        JButton btnReanudar = crearBoton("REANUDAR", fuente, reanudarJuego);
        btnReanudar.setBounds(centroX, 150, 300, 60);
        add(btnReanudar);

        // Botón Reiniciar
        JButton btnReiniciar = crearBoton("REINICIAR", fuente, reiniciarJuego);
        btnReiniciar.setBounds(centroX, 240, 300, 60);
        add(btnReiniciar);

        // Botón Salir al Menú Principal
        JButton btnSalir = crearBoton("SALIR AL MENÚ", fuente, salirAlMenu);
        btnSalir.setBounds(centroX, 330, 300, 60);
        add(btnSalir);
    }

    private JButton crearBoton(String texto, Font fuente, Runnable accion) {
        JButton boton = new JButton(texto);
        boton.setFont(fuente);
        boton.setForeground(new Color(0, 255, 255));
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setOpaque(true);
        boton.setBackground(new Color(0, 0, 128, 180));
        boton.addActionListener(e -> accion.run());

        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                boton.setForeground(new Color(255, 255, 0));
                repaint();
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                boton.setForeground(new Color(0, 255, 255));
                repaint();
            }
        });

        return boton;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
    }
}
