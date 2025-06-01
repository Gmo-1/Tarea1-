package frontend;

import backend.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class VentanaPregunta extends JFrame {

    private JLabel lblEnunciado;
    private JRadioButton[] opciones;
    private ButtonGroup grupoOpciones;
    private JButton btnAnterior, btnSiguiente;
    private List<Item> items;
    private RespuestaUsuario[] respuestas;
    private int indiceActual = 0;
    private Runnable accionFinalizar;

    public VentanaPregunta(List<Item> items, Runnable accionFinalizar) {
        this.items = items;
        this.accionFinalizar = accionFinalizar;
        this.respuestas = new RespuestaUsuario[items.size()];

        setTitle("Aplicación de Preguntas");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        lblEnunciado = new JLabel("Pregunta", SwingConstants.CENTER);
        add(lblEnunciado, BorderLayout.NORTH);

        JPanel panelOpciones = new JPanel(new GridLayout(5, 1));
        grupoOpciones = new ButtonGroup();
        opciones = new JRadioButton[5];
        for (int i = 0; i < 5; i++) {
            opciones[i] = new JRadioButton();
            grupoOpciones.add(opciones[i]);
            panelOpciones.add(opciones[i]);
        }
        add(panelOpciones, BorderLayout.CENTER);

        btnAnterior = new JButton("Atrás");
        btnSiguiente = new JButton("Siguiente");
        btnAnterior.addActionListener(e -> {
            guardarRespuesta();
            indiceActual--;
            mostrarPregunta();
        });
        btnSiguiente.addActionListener(e -> {
            guardarRespuesta();
            if (indiceActual == items.size() - 1) {
                accionFinalizar.run();
                dispose();
            } else {
                indiceActual++;
                mostrarPregunta();
            }
        });

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnAnterior);
        panelBotones.add(btnSiguiente);
        add(panelBotones, BorderLayout.SOUTH);

        mostrarPregunta();
        setVisible(true);
    }

    private void mostrarPregunta() {
        grupoOpciones.clearSelection();
        Item item = items.get(indiceActual);
        lblEnunciado.setText((indiceActual + 1) + ". " + item.enunciado);

        for (int i = 0; i < opciones.length; i++) {
            if (i < item.alternativas.length) {
                opciones[i].setText(item.alternativas[i]);
                opciones[i].setVisible(true);
                if (respuestas[indiceActual] != null &&
                    respuestas[indiceActual].respuestaDada.equals(item.alternativas[i])) {
                    opciones[i].setSelected(true);
                }
            } else {
                opciones[i].setVisible(false);
            }
        }

        btnAnterior.setEnabled(indiceActual > 0);
        btnSiguiente.setText(indiceActual == items.size() - 1 ? "Enviar respuestas" : "Siguiente");
    }

    private void guardarRespuesta() {
        for (JRadioButton opcion : opciones) {
            if (opcion.isVisible() && opcion.isSelected()) {
                respuestas[indiceActual] = new RespuestaUsuario(items.get(indiceActual), opcion.getText());
                break;
            }
        }
    }

    public RespuestaUsuario[] obtenerRespuestas() {
        return respuestas;
    }
}
