package com.example.alcanzandonota.model;

import java.io.Serializable;

public class Pregunta implements Serializable {

    private String id;
    private String tema;
    private float valorPregunta;
    private String pregunta;

    public Pregunta(String id, String tema, float valorPregunta, String pregunta) {
        this.id = id;
        this.tema = tema;
        this.valorPregunta = valorPregunta;
        this.pregunta = pregunta;
    }

    public Pregunta(float valorPregunta, String pregunta) {
        this.valorPregunta = valorPregunta;
        this.pregunta = pregunta;
    }

    public Pregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public Pregunta() {
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

    public float getValorPregunta() {
        return valorPregunta;
    }

    public void setValorPregunta(float valorPregunta) {
        this.valorPregunta = valorPregunta;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }
}
