package com.example.alcanzandonota.model;

public class Pregunta {

    private String id;
    private String tema;
    private String valorPregunta;
    private String pregunta;

    public Pregunta(String id, String tema, String valorPregunta, String pregunta) {
        this.id = id;
        this.tema = tema;
        this.valorPregunta = valorPregunta;
        this.pregunta = pregunta;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String getValorPregunta() {
        return valorPregunta;
    }

    public void setValorPregunta(String valorPregunta) {
        this.valorPregunta = valorPregunta;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }
}
