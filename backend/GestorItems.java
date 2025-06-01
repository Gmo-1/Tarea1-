package backend;

import java.io.*;
import java.util.*;

public class GestorItems {
    private List<Item> items = new ArrayList<>();

    public void cargarArchivo(String ruta) throws Exception {
        BufferedReader lector = new BufferedReader(new FileReader(ruta));
        String linea;
        while ((linea = lector.readLine()) != null) {
            linea = linea.trim();
            if (linea.isEmpty()) continue;
            String[] partes = linea.split("\\|");
            if (partes.length < 6) continue;

            int id = Integer.parseInt(partes[0]);
            Item.Tipo tipo = Item.Tipo.valueOf(partes[1].trim());
            Item.NivelBloom nivel = Item.NivelBloom.valueOf(partes[2].trim());
            int tiempo = Integer.parseInt(partes[3]);
            String enunciado = partes[4];
            String respuestaCorrecta = partes[5];
            String[] alternativas = tipo == Item.Tipo.OPCION_MULTIPLE ? partes[6].split(";") : new String[]{"Verdadero", "Falso"};

            Item item = new Item(id, tipo, nivel, tiempo, enunciado, respuestaCorrecta, alternativas);
            items.add(item);
        }
        lector.close();
    }

    public List<Item> obtenerItems() {
        return items;
    }

    public int obtenerTiempoTotal() {
        int total = 0;
        for (Item item : items) {
            total += item.tiempoEstimado;
        }
        return total;
    }

    public int contarPorTipo(Item.Tipo tipo, List<RespuestaUsuario> respuestas, boolean soloCorrectas) {
        int count = 0;
        for (RespuestaUsuario r : respuestas) {
            if (r.item.tipo == tipo && (!soloCorrectas || r.esCorrecta())) {
                count++;
            }
        }
        return count;
    }

    public int contarPorNivel(Item.NivelBloom nivel, List<RespuestaUsuario> respuestas, boolean soloCorrectas) {
        int count = 0;
        for (RespuestaUsuario r : respuestas) {
            if (r.item.nivel == nivel && (!soloCorrectas || r.esCorrecta())) {
                count++;
            }
        }
        return count;
    }
}
