package org.example.menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MenuPausa extends JPanel {

    private Image fondo;

    public MenuPausa() {
        ImageIcon bg = new ImageIcon("src/resources/images/fons_1_pingpong.png");
        fondo = bg.getImage();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));  // Usamos BoxLayout para alinear los botones verticalmente
        setBackground(Color.BLACK);

        Font fuente = new Font("Verdana", Font.BOLD, 22);
        Color colorTexto = new Color(0, 255, 255);
        Color fondoBoton = new Color(0, 0, 128, 180);

        // Botón Reanudar
        JButton btnReanudar = crearBoton("REANUDAR", fuente, this::reanudarJuego);
        add(Box.createVerticalStrut(50)); // Espacio superior entre botones
        add(btnReanudar);

        // Botón Reiniciar
        JButton btnReiniciar = crearBoton("REINICIAR", fuente, this::reiniciarJuego);
        add(Box.createVerticalStrut(20)); // Espacio entre botones
        add(btnReiniciar);

        // Botón Salir al Menú Principal
        JButton btnSalir = crearBoton("SALIR AL MENÚ", fuente, this::salirAlMenu);
        add(Box.createVerticalStrut(20)); // Espacio entre botones
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

        boton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                boton.setForeground(new Color(255, 255, 0));
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent evt) {
                boton.setForeground(new Color(0, 255, 255));
                repaint();
            }
        });

        boton.setAlignmentX(Component.CENTER_ALIGNMENT);  // Centrar el botón en el JPanel
        return boton;
    }

    // Acción de reanudar el juego
    private void reanudarJuego() {
        System.out.println("Juego reanudado.");
        // Aquí va la lógica para reanudar el juego
    }

    // Acción de reiniciar el juego
    private void reiniciarJuego() {
        System.out.println("Juego reiniciado.");
        // Aquí va la lógica para reiniciar el juego
    }

    // Acción de salir al menú
    private void salirAlMenu() {
        System.out.println("Saliendo al menú.");
        // Aquí va la lógica para salir al menú principal
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
    }
}
