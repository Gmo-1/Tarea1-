package frontend;

import backend.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class VentanaRevision extends JFrame {

    private JLabel lblEnunciado;
    private JLabel lblRespuesta;
    private JLabel lblEstado;
    private JButton btnAnterior, btnSiguiente, btnResumen;
    private List<RespuestaUsuario> respuestas;
    private int indiceActual = 0;
    private Runnable accionVolverResumen;

    public VentanaRevision(List<RespuestaUsuario> respuestas, Runnable accionVolverResumen) {
        this.respuestas = respuestas;
        this.accionVolverResumen = accionVolverResumen;

        setTitle("Revisión de respuestas");
        setSize(500, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        lblEnunciado = new JLabel("Enunciado", SwingConstants.CENTER);
        lblRespuesta = new JLabel("Tu respuesta: ", SwingConstants.CENTER);
        lblEstado = new JLabel("Correcta o Incorrecta", SwingConstants.CENTER);

        JPanel centro = new JPanel(new GridLayout(3, 1));
        centro.add(lblEnunciado);
        centro.add(lblRespuesta);
        centro.add(lblEstado);
        add(centro, BorderLayout.CENTER);

        btnAnterior = new JButton("Atrás");
        btnSiguiente = new JButton("Siguiente");
        btnResumen = new JButton("Volver al resumen");

        btnAnterior.addActionListener(e -> {
            indiceActual--;
            mostrarRespuesta();
        });
        btnSiguiente.addActionListener(e -> {
            indiceActual++;
            mostrarRespuesta();
        });
        btnResumen.addActionListener(e -> {
            accionVolverResumen.run();
            dispose();
        });

        JPanel botones = new JPanel();
        botones.add(btnAnterior);
        botones.add(btnSiguiente);
        botones.add(btnResumen);
        add(botones, BorderLayout.SOUTH);

        mostrarRespuesta();
        setVisible(true);
    }

    private void mostrarRespuesta() {
        RespuestaUsuario r = respuestas.get(indiceActual);
        lblEnunciado.setText((indiceActual + 1) + ". " + r.item.enunciado);
        lblRespuesta.setText("Tu respuesta: " + r.respuestaDada + " | Correcta: " + r.item.respuestaCorrecta);
        lblEstado.setText(r.esCorrecta() ? "✅ Correcta" : "❌ Incorrecta");

        btnAnterior.setEnabled(indiceActual > 0);
        btnSiguiente.setEnabled(indiceActual < respuestas.size() - 1);
    }
}
