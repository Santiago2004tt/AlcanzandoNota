package com.example.alcanzandonota.model;

public class Abierta extends Pregunta{

    private String respuesta;
    private String palabraClave;

    public Abierta(String id, String tema, String valorPregunta, String pregunta, String respuesta, String palabraClave) {
        super(id, tema, valorPregunta, pregunta);
        this.respuesta = respuesta;
        this.palabraClave = palabraClave;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getPalabraClave() {
        return palabraClave;
    }

    public void setPalabraClave(String palabraClave) {
        this.palabraClave = palabraClave;
    }
}
