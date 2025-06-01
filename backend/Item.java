package backend;

public class Item {
    public enum Tipo { OPCION_MULTIPLE, VERDADERO_FALSO }
    public enum NivelBloom { RECORDAR, ENTENDER, APLICAR, ANALIZAR, EVALUAR, CREAR }

    public int id;
    public Tipo tipo;
    public NivelBloom nivel;
    public int tiempoEstimado;
    public String enunciado;
    public String respuestaCorrecta;
    public String[] alternativas;

    public Item(int id, Tipo tipo, NivelBloom nivel, int tiempo, String enunciado, String respuestaCorrecta, String[] alternativas) {
        this.id = id;
        this.tipo = tipo;
        this.nivel = nivel;
        this.tiempoEstimado = tiempo;
        this.enunciado = enunciado;
        this.respuestaCorrecta = respuestaCorrecta;
        this.alternativas = alternativas;
    }
}
