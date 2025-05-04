package org.example.menu;

import org.example.PanellJoc;
import org.example.Temporitzador;
import org.example.Variables;
import org.example.connector.Acces;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import static org.example.Variables.Paraules.*;
import static org.example.menu.MenuConfiguracionJugadores.jugadors;
import static org.example.menu.MenuConfiguracionJugadores.nivell;

public class MenuPausa extends JPanel {
    private final Image fondo;

    public MenuPausa(JFrame framePrincipal, PanellJoc panellJoc) {

        ImageIcon bg = new ImageIcon("src/resources/images/fons_1_pingpong.png");
        fondo = bg.getImage();


        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.BLACK);

        Font fuente = new Font("Verdana", Font.BOLD, 22);
        Color colorTexto = new Color(0, 255, 255);
        Color fondoBoton = new Color(0, 0, 128, 180);

        // Espaciado superior
        add(Box.createVerticalStrut(100));

        // Botón Reanudar
        JButton btnReanudar = crearBoton(Acces.carregarIdioma(mpaContinuar), fuente, () -> {
            PanellJoc.pausa = false;
            Temporitzador.reiniciarTemporitzador();
            framePrincipal.getContentPane().remove(this);
            framePrincipal.revalidate();
            framePrincipal.repaint();
            // Reiniciar el bucle del joc
            new Thread(() -> {
                try {
                    while (true) {
                        if (!PanellJoc.pausa) {
                            panellJoc.move();
                            panellJoc.repaint();
                            panellJoc.augmentarNivell();
                            Thread.sleep(10);
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        });
        add(btnReanudar);
        add(Box.createVerticalStrut(30));

        // Botón Reiniciar
        JButton btnReiniciar = crearBoton(Acces.carregarIdioma(mpaReiniciar), fuente, () -> {
            try {
                PanellJoc nouPanell = new PanellJoc(Variables.ampladaFinestra, Variables.alturaFinestra, jugadors, nivell);

                // Eliminar el panell antic
                framePrincipal.getContentPane().removeAll();

                // Afegir el nou panell
                framePrincipal.getContentPane().add(nouPanell);
                framePrincipal.revalidate();
                framePrincipal.repaint();

                nouPanell.requestFocusInWindow();

                // Reactivar el joc
                PanellJoc.pausa = false;
                Temporitzador.iniciarTemporitzador();  // Si tens un mètode per reiniciar-lo

                // Reiniciar el bucle del joc
                new Thread(() -> {
                    try {
                        while (true) {
                            if (!nouPanell.getPausa()) {
                                nouPanell.move();
                                nouPanell.repaint();
                                nouPanell.augmentarNivell();
                                Thread.sleep(10);
                            }
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        add(btnReiniciar);
        add(Box.createVerticalStrut(30));

        // Botón Salir al Menú
        JButton btnSortir = crearBoton(Acces.carregarIdioma(mpaTornarMenu), fuente, () -> {
            // Eliminar el panell antic
            framePrincipal.getContentPane().removeAll();

            // Afegir el nou panell
            framePrincipal.getContentPane().add(new MenuPrincipal());
            framePrincipal.revalidate();
            framePrincipal.repaint();

            // Reactivar el joc
            PanellJoc.pausa = false;
            Temporitzador.iniciarTemporitzador();  // Si tens un mètode per reiniciar-lo
        });
        add(btnSortir);
    }

    public JButton crearBoton(String texto, Font fuente, Runnable accion) {
        JButton boton = new JButton(texto);
        boton.setFont(fuente);
        boton.setForeground(new Color(0, 255, 255));
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setOpaque(true);
        boton.setBackground(new Color(0, 0, 128, 180));
        boton.setAlignmentX(Component.CENTER_ALIGNMENT);

        boton.addActionListener(e -> {
            if (accion != null) {
                accion.run();
            }
        });

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

        return boton;
    }

    // Método para mostrar el menú de pausa
    public static void mostrarMenuPausa(JFrame framePrincipal, PanellJoc pJoc) {
        MenuPausa menuPausa = new MenuPausa(framePrincipal, pJoc);

        // Configurar el menú de pausa
        menuPausa.setBounds(0, 0, framePrincipal.getWidth(), framePrincipal.getHeight());
        menuPausa.setOpaque(false);

        // Agregar el menú al frame principal
        framePrincipal.getContentPane().add(menuPausa, 0);
        framePrincipal.revalidate();
        framePrincipal.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
    }
}
