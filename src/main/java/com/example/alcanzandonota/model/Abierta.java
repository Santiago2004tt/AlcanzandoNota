package com.example.alcanzandonota.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Abierta extends Pregunta implements Serializable {

    private String respuesta;
    private ArrayList<String> palabraClave;

    public Abierta(String id, String tema, float valorPregunta, String pregunta, String respuesta, ArrayList<String> palabraClave) {
        super(id, tema, valorPregunta, pregunta);
        this.respuesta = respuesta;
        this.palabraClave = palabraClave;
    }

    public Abierta(String pregunta, ArrayList<String> palabraClave) {
        super(pregunta);
        this.palabraClave = palabraClave;
    }

    public Abierta() {
        this.palabraClave = new ArrayList<>();
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public ArrayList<String> getPalabraClave() {
        return palabraClave;
    }

    public void setPalabraClave(ArrayList<String> palabraClave) {
        this.palabraClave = palabraClave;
    }
}
