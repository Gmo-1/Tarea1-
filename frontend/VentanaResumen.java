package frontend;

import backend.GestorItems;
import backend.Item;
import backend.RespuestaUsuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.Arrays;

public class VentanaResumen extends JFrame {

    private GestorItems gestor;
    private List<RespuestaUsuario> respuestas;
    private JButton btnRevisar;
    private Runnable accionRevisar;

    public VentanaResumen(GestorItems gestor, RespuestaUsuario[] respuestasArray, Runnable accionRevisar) {
        this.gestor = gestor;
        this.respuestas = Arrays.asList(respuestasArray);
        this.accionRevisar = accionRevisar;

        setTitle("Resumen de Resultados");
        setSize(500, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JTextArea areaResumen = new JTextArea();
        areaResumen.setEditable(false);

        int total = respuestas.size();
        int correctas = (int) respuestas.stream().filter(RespuestaUsuario::esCorrecta).count();

        StringBuilder resumen = new StringBuilder();
        resumen.append("Respuestas correctas: ").append(correctas).append(" de ").append(total)
               .append(" (").append((100 * correctas / total)).append("%)\n\n");

        resumen.append("Por tipo de ítem:\n");
        for (Item.Tipo tipo : Item.Tipo.values()) {
            int totalTipo = gestor.contarPorTipo(tipo, respuestas, false);
            int correctasTipo = gestor.contarPorTipo(tipo, respuestas, true);
            int porcentaje = totalTipo > 0 ? (100 * correctasTipo / totalTipo) : 0;
            resumen.append("- ").append(tipo.name()).append(": ")
                   .append(porcentaje).append("% (").append(correctasTipo).append("/").append(totalTipo).append(")\n");
        }

        resumen.append("\nPor nivel de Bloom:\n");
        for (Item.NivelBloom nivel : Item.NivelBloom.values()) {
            int totalNivel = gestor.contarPorNivel(nivel, respuestas, false);
            int correctasNivel = gestor.contarPorNivel(nivel, respuestas, true);
            int porcentaje = totalNivel > 0 ? (100 * correctasNivel / totalNivel) : 0;
            resumen.append("- ").append(nivel.name()).append(": ")
                   .append(porcentaje).append("% (").append(correctasNivel).append("/").append(totalNivel).append(")\n");
        }

        areaResumen.setText(resumen.toString());

        btnRevisar = new JButton("Revisar respuestas");
        btnRevisar.addActionListener(e -> {
            accionRevisar.run();
            dispose();
        });

        add(new JScrollPane(areaResumen), BorderLayout.CENTER);
        add(btnRevisar, BorderLayout.SOUTH);
        setVisible(true);
    }
}
