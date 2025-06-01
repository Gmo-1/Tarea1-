package frontend;

import backend.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class VentanaInicio extends JFrame {

    private JLabel lblResumen;
    private JButton btnCargar, btnComenzar;
    private GestorItems gestor;

    private Runnable accionComenzar;

    public VentanaInicio(GestorItems gestor, Runnable accionComenzar) {
        this.gestor = gestor;
        this.accionComenzar = accionComenzar;

        setTitle("Aplicación de Prueba - Taxonomía de Bloom");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        lblResumen = new JLabel("Por favor, cargue un archivo de ítems.", SwingConstants.CENTER);

        btnCargar = new JButton("Cargar archivo");
        btnComenzar = new JButton("Comenzar prueba");
        btnComenzar.setEnabled(false);

        btnCargar.addActionListener(e -> cargarArchivo());
        btnComenzar.addActionListener(e -> {
            accionComenzar.run();
            dispose(); // Cierra esta ventana
        });

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnCargar);
        panelBotones.add(btnComenzar);

        add(lblResumen, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void cargarArchivo() {
        JFileChooser selector = new JFileChooser();
        int opcion = selector.showOpenDialog(this);
        if (opcion == JFileChooser.APPROVE_OPTION) {
            File archivo = selector.getSelectedFile();
            try {
                gestor.cargarArchivo(archivo.getAbsolutePath());
                int cantidad = gestor.obtenerItems().size();
                int tiempo = gestor.obtenerTiempoTotal();
                lblResumen.setText("<html>Archivo cargado con éxito.<br>"
                        + "Total de ítems: " + cantidad + "<br>"
                        + "Tiempo estimado: " + tiempo + " segundos.</html>");
                btnComenzar.setEnabled(true);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al cargar el archivo: " + ex.getMessage());
            }
        }
    }
}
