package org.example.menu;

import org.example.Sound;
import org.example.connector.Acces;

import javax.swing.*;
import java.awt.*;

import static org.example.Variables.*;
import static org.example.Variables.Paraules.*;

public class MenuOpciones extends JPanel {
    private JComboBox<String> comboIdiomas;
    private JComboBox<String> comboResolucion;
    private JSlider sliderVolumen;
    private Image fondo;

    public MenuOpciones(Runnable volverAlMenu) {
        ImageIcon bg = new ImageIcon("src/resources/images/fons_1_pingpong.png");
        fondo = bg.getImage();

        setLayout(null);
        Font fuente = new Font("Verdana", Font.BOLD, 22);
        Color colorTexto = new Color(0, 255, 255);
        Color fondoBoton = new Color(0, 0, 128, 180);

        // Botón "<"
        JButton btnAtras = new JButton("<");
        btnAtras.setFont(new Font("Verdana", Font.BOLD, 24));
        btnAtras.setBounds(20, 20, 60, 40);
        btnAtras.setForeground(Color.CYAN);
        btnAtras.setBackground(fondoBoton);
        btnAtras.setFocusPainted(false);
        btnAtras.addActionListener(e -> volverAlMenu.run());
        add(btnAtras);

        int centroX = 400;

        // Idioma
        JLabel lblIdioma = crearLabel(Acces.carregarIdioma(moIdioma), fuente);
        lblIdioma.setBounds(centroX, 100, 300, 40);
        add(lblIdioma);

        comboIdiomas = new JComboBox<>(new String[]{"Català", "Castellano", "English"});
        comboIdiomas.setFont(fuente);
        comboIdiomas.setBounds(centroX, 150, 300, 50);
        comboIdiomas.setBackground(fondoBoton);
        comboIdiomas.setForeground(colorTexto);
        add(comboIdiomas);

        // Resolución
        JLabel lblResolucion = crearLabel(Acces.carregarIdioma(moResolucio), fuente);
        lblResolucion.setBounds(centroX, 230, 350, 40);
        add(lblResolucion);

        comboResolucion = new JComboBox<>(new String[]{"800x600", "1280x720", "1920x1080"});
        comboResolucion.setFont(fuente);
        comboResolucion.setBounds(centroX, 280, 300, 50);
        comboResolucion.setBackground(fondoBoton);
        comboResolucion.setForeground(colorTexto);
        add(comboResolucion);

        // Panel para Volumen
        JPanel panelVolumen = new JPanel(null);
        panelVolumen.setBounds(centroX, 370, 300, 120);
        panelVolumen.setBackground(fondoBoton);

        JLabel lblVolumen = new JLabel(Acces.carregarIdioma(moVolum));
        lblVolumen.setFont(fuente);
        lblVolumen.setBounds(0, 0, 300, 40);
        lblVolumen.setHorizontalAlignment(SwingConstants.CENTER);
        lblVolumen.setForeground(colorTexto);
        panelVolumen.add(lblVolumen);

        sliderVolumen = new JSlider(0, 100, 50);
        sliderVolumen.setBounds(0, 50, 300, 50);
        sliderVolumen.setOpaque(false);
        sliderVolumen.setMajorTickSpacing(25);
        sliderVolumen.setPaintTicks(true);
        sliderVolumen.setPaintLabels(true);
        sliderVolumen.setForeground(colorTexto);

        // ✅ Volumen funcional
        sliderVolumen.addChangeListener(e -> {
            int valor = sliderVolumen.getValue();
            Sound.setVolume(valor);
        });

        panelVolumen.add(sliderVolumen);
        add(panelVolumen);

        // Botón GUARDAR (abajo derecha)
        JButton btnGuardarTodo = crearBoton(Acces.carregarIdioma(moGuardar), fuente, () -> {
            idiomaSeleccionado = (String) comboIdiomas.getSelectedItem();
            resolucionSeleccionada = (String) comboResolucion.getSelectedItem();
            int volumen = sliderVolumen.getValue();

            JOptionPane.showMessageDialog(this,
                    "Opciones guardadas:\nIdioma: " + idiomaSeleccionado +
                            "\nResolución: " + resolucionSeleccionada +
                            "\nVolumen: " + volumen);
        });
        btnGuardarTodo.setBounds(950, 650, 280, 50);
        add(btnGuardarTodo);
    }

    private JLabel crearLabel(String texto, Font fuente) {
        JLabel label = new JLabel(texto);
        label.setFont(fuente);
        label.setForeground(Color.WHITE);
        return label;
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