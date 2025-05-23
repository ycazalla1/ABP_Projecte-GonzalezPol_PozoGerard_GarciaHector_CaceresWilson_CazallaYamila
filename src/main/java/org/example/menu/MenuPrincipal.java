package org.example.menu;

import org.example.connector.Acces;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static org.example.Variables.Paraules.*;

public class MenuPrincipal extends JPanel {
    Image picture;

    public MenuPrincipal() {
        ImageIcon obj = new ImageIcon("src/resources/images/RetroTenis_background.png");
        picture = obj.getImage();

        setLayout(new BorderLayout()); // Cambiamos el layout principal

        Font fuente = new Font("Verdana", Font.BOLD, 28);

        // Crear los botones
        JButton btnJugar = crearBoton(Acces.carregarIdioma(mpJugar), fuente, e -> {
            JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
            topFrame.setContentPane(new MenuConfiguracionJugadores(
                    () -> {
                        topFrame.setContentPane(new MenuPrincipal());
                        topFrame.revalidate();
                    },
                    (jugadores, nivel) -> {
                        // Aquí pondrías la lógica para pasar al juego real
                        System.out.println("Jugador 1: " + jugadores[0]);
                        System.out.println("Jugador 2: " + jugadores[1]);
                        System.out.println("Nivel elegido: " + nivel);
                        // Ejemplo si tienes una clase PantallaJuego:
                        // topFrame.setContentPane(new PantallaJuego(jugadores[0], jugadores[1], nivel));
                        // topFrame.revalidate();
                    }
            ));
            topFrame.revalidate();
        });

        JButton btnOpciones = crearBoton(Acces.carregarIdioma(mpOpcions), fuente, e -> {
            JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
            topFrame.setContentPane(new MenuOpciones(() -> {
                topFrame.setContentPane(new MenuPrincipal());
                topFrame.revalidate();
            }));
            topFrame.revalidate();
        });

        JButton btnSalir = crearBoton(Acces.carregarIdioma(mpSortir), fuente, e -> System.exit(0));

        // Crear el panel de botones
        JPanel panelBotones = new JPanel() {
            @Override
            public Dimension getPreferredSize() {
                int width = (int) (getParent().getWidth() * 0.8); // 80% del ancho de la ventana
                int height = (int) (getParent().getHeight() * 0.2); // 20% de la altura de la ventana
                return new Dimension(width, height);
            }

            @Override
            public void doLayout() {
                int buttonWidth = (int) (getWidth() * 0.3); // 30% del ancho del panel
                int buttonHeight = (int) (getHeight() * 0.6); // 60% de la altura del panel
                for (Component comp : getComponents()) {
                    if (comp instanceof JButton) {
                        comp.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
                    }
                }
                super.doLayout();
            }
        };
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelBotones.setOpaque(false); // Para que el fondo sea transparente
        panelBotones.add(Box.createHorizontalStrut(20)); // Espaciado inicial
        panelBotones.add(btnJugar);
        panelBotones.add(Box.createHorizontalStrut(20)); // Espaciado entre botones
        panelBotones.add(btnOpciones);
        panelBotones.add(Box.createHorizontalStrut(20)); // Espaciado entre botones
        panelBotones.add(btnSalir);
        panelBotones.add(Box.createHorizontalStrut(20)); // Espaciado final

        add(panelBotones, BorderLayout.SOUTH); // Colocar abajo del todo
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(picture, 0, 0, getWidth(), getHeight(), this);
    }

    private JButton crearBoton(String texto, Font fuente, ActionListener accion) {
        JButton boton = new JButton(texto);
        boton.setFont(fuente);
        boton.setForeground(new Color(0, 255, 255)); // azul celeste
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setOpaque(true);
        boton.setBackground(new Color(0, 0, 128, 180)); // azul oscuro semitransparente
        boton.setPreferredSize(new Dimension(260, 60));
        boton.addActionListener(accion);

        // Efecto hover con repintado forzado
        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                boton.setForeground(new Color(255, 255, 0)); // amarillo
                MenuPrincipal.this.repaint();
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                boton.setForeground(new Color(0, 255, 255)); // azul celeste
                MenuPrincipal.this.repaint();
            }
        });

        return boton;
    }
}