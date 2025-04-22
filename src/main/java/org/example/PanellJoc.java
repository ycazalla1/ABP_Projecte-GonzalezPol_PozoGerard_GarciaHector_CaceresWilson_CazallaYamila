package org.example;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PanellJoc extends JPanel {
    public PanellJoc() {
        setPanelSize();
    }

    public void setPanelSize() {
        Dimension dimension = new Dimension(1280, 800);
        //Método para asignar la dimensión creada antes como preferida
        setPreferredSize(dimension);
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.RED);
        g2d.fillOval(0, 0, 30, 30);
        g2d.drawOval(0, 50, 30, 30);
        g2d.fillRect(50, 0, 30, 30);
        g2d.drawRect(50, 50, 30, 30);

        g2d.draw(new Ellipse2D.Double(0, 100, 30, 30));


    }
}
