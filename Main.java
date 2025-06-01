import backend.*;
import frontend.*;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GestorItems gestor = new GestorItems();

            new VentanaInicio(gestor, () -> {
                // Usamos un array de un elemento como contenedor mutable
                VentanaPregunta[] ventanaRef = new VentanaPregunta[1];

                ventanaRef[0] = new VentanaPregunta(
                    gestor.obtenerItems(),
                    () -> {
                        RespuestaUsuario[] respuestas = ventanaRef[0].obtenerRespuestas();
                        mostrarResumen(gestor, respuestas);
                    }
                );
            });
        });
    }

    private static void mostrarResumen(GestorItems gestor, RespuestaUsuario[] respuestas) {
        new VentanaResumen(gestor, respuestas, () -> {
            new VentanaRevision(
                java.util.Arrays.asList(respuestas),
                () -> mostrarResumen(gestor, respuestas)
            );
        });
    }
}
