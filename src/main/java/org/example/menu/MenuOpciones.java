package org.example.menu;

import org.example.Sound;
import org.example.connector.Acces;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import static org.example.Variables.*;
import static org.example.Variables.Paraules.*;

public class MenuOpciones extends JPanel {
    private JComboBox<String> comboIdiomas;
    private JComboBox<String> comboResolucion;
    private JSlider sliderVolumen;
    private Image fondo;
    private JButton btnGuardarTodo;

    public MenuOpciones(Runnable volverAlMenu) {
        ImageIcon bg = new ImageIcon("src/resources/images/fons_1_pingpong.png");
        fondo = bg.getImage();

        setLayout(null); // Usar null layout para posicionar manualmente
        Font fuente = new Font("Verdana", Font.BOLD, 22);
        Color colorTexto = new Color(0, 255, 255);
        Color fondoBoton = new Color(0, 0, 128, 180);

        // Botón "<"
        JButton btnAtras = new JButton("<");
        btnAtras.setFont(new Font("Verdana", Font.BOLD, 24));
        btnAtras.setForeground(Color.CYAN);
        btnAtras.setBackground(fondoBoton);
        btnAtras.setFocusPainted(false);
        btnAtras.addActionListener(e -> volverAlMenu.run());
        add(btnAtras);

        // Idioma
        JLabel lblIdioma = crearLabel(Acces.carregarIdioma(moIdioma), fuente);
        add(lblIdioma);

        comboIdiomas = new JComboBox<>(new String[]{"Català", "Castellano", "English"});
        comboIdiomas.setFont(fuente);
        comboIdiomas.setBackground(fondoBoton);
        comboIdiomas.setForeground(colorTexto);
        add(comboIdiomas);

        // Resolución
        JLabel lblResolucion = crearLabel(Acces.carregarIdioma(moResolucio), fuente);
        add(lblResolucion);

        comboResolucion = new JComboBox<>(new String[]{"800x600", "1280x720", "1920x1080"});
        comboResolucion.setFont(fuente);
        comboResolucion.setBackground(fondoBoton);
        comboResolucion.setForeground(colorTexto);
        add(comboResolucion);

        // Panel para Volumen
        JPanel panelVolumen = new JPanel(null);
        panelVolumen.setBackground(fondoBoton);

        JLabel lblVolumen = new JLabel(Acces.carregarIdioma(moVolum));
        lblVolumen.setFont(fuente);
        lblVolumen.setHorizontalAlignment(SwingConstants.CENTER);
        lblVolumen.setForeground(colorTexto);
        panelVolumen.add(lblVolumen);

        sliderVolumen = new JSlider(0, 100, 50);
        sliderVolumen.setOpaque(false);
        sliderVolumen.setMajorTickSpacing(25);
        sliderVolumen.setPaintTicks(true);
        sliderVolumen.setPaintLabels(true);
        sliderVolumen.setForeground(colorTexto);
        sliderVolumen.addChangeListener(e -> Sound.setVolume(sliderVolumen.getValue()));
        panelVolumen.add(sliderVolumen);
        add(panelVolumen);

        // Botón GUARDAR
        btnGuardarTodo = crearBoton(Acces.carregarIdioma(moGuardar), fuente, () -> {
            // Guardar idioma seleccionado
            idiomaSeleccionado = (String) comboIdiomas.getSelectedItem();
            Acces.carregarIdioma(idiomaSeleccionado); // Método para actualizar el idioma en tu sistema

            // Guardar resolución seleccionada
            resolucionSeleccionada = (String) comboResolucion.getSelectedItem();
            ampladaFinestra = Integer.parseInt(resolucionSeleccionada.split("x")[0]);
            alturaFinestra = Integer.parseInt(resolucionSeleccionada.split("x")[1]);

            JFrame framePrincipal = (JFrame) SwingUtilities.getWindowAncestor(this);
            if (framePrincipal != null) {
                framePrincipal.setSize(ampladaFinestra, alturaFinestra);
                framePrincipal.revalidate();
                framePrincipal.repaint();
            }

            // Actualizar textos de los componentes
            lblIdioma.setText(Acces.carregarIdioma(moIdioma));
            lblResolucion.setText(Acces.carregarIdioma(moResolucio));
            lblVolumen.setText(Acces.carregarIdioma(moVolum));
            btnGuardarTodo.setText(Acces.carregarIdioma(moGuardar));

            JOptionPane.showMessageDialog(this,
                    Acces.carregarIdioma("opciones_guardadas") + ":\n" +
                            Acces.carregarIdioma("idioma") + ": " + idiomaSeleccionado + "\n" +
                            Acces.carregarIdioma("resolucion") + ": " + resolucionSeleccionada + "\n" +
                            Acces.carregarIdioma("volumen") + ": " + sliderVolumen.getValue());
        });
        add(btnGuardarTodo);

        // Listener para ajustar posiciones y tamaños dinámicamente
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int ancho = getWidth();
                int alto = getHeight();

                btnAtras.setBounds(20, 20, ancho / 15, alto / 15);

                int centroX = ancho / 2 - 150;
                lblIdioma.setBounds(centroX, alto / 10, 300, 40);
                comboIdiomas.setBounds(centroX, alto / 10 + 50, 300, 50);

                lblResolucion.setBounds(centroX, alto / 4 + 30, 300, 40); // Incrementa la posición vertical
                comboResolucion.setBounds(centroX, alto / 4 + 80, 300, 50); // Ajusta el espacio entre el JLabel y el JComboBox

                panelVolumen.setBounds(centroX, alto / 2, 300, 120);
                lblVolumen.setBounds(0, 0, 300, 40);
                sliderVolumen.setBounds(0, 50, 300, 50);

                btnGuardarTodo.setBounds(ancho - 320, alto - 100, 280, 50);
            }
        });
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