package backend;

public class RespuestaUsuario {
    public Item item;
    public String respuestaDada;

    public RespuestaUsuario(Item item, String respuestaDada) {
        this.item = item;
        this.respuestaDada = respuestaDada;
    }

    public boolean esCorrecta() {
        return item.respuestaCorrecta.equals(respuestaDada);
    }
}
