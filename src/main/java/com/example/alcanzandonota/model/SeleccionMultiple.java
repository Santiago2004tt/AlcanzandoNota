package com.example.alcanzandonota.model;

import java.io.Serializable;
import java.util.ArrayList;

public class SeleccionMultiple extends Pregunta implements Serializable {
    private int cantidadOpciones;
    private Respuesta respuestaCorrecta;
    private ArrayList<Respuesta> listaOpciones;


    public SeleccionMultiple(String id, String tema, float valorPregunta, String pregunta, int cantidadOpciones, Respuesta respuestaCorrecta) {
        super(id, tema, valorPregunta, pregunta);
        this.cantidadOpciones = cantidadOpciones;
        this.respuestaCorrecta = respuestaCorrecta;
        listaOpciones = new ArrayList<>();
    }

    public SeleccionMultiple(String id, String tema, float valorPregunta, String pregunta) {
        super(id, tema, valorPregunta, pregunta);
        listaOpciones = new ArrayList<>();
    }

    public SeleccionMultiple(float valorPregunta, String pregunta) {
        super(valorPregunta, pregunta);
        listaOpciones = new ArrayList<>();
    }

    public SeleccionMultiple(String pregunta) {
        super(pregunta);
        listaOpciones = new ArrayList<>();
    }

    public SeleccionMultiple() {
        listaOpciones = new ArrayList<>();
    }

    public int getCantidadOpciones() {
        return cantidadOpciones;
    }

    public void setCantidadOpciones(int cantidadOpciones) {
        this.cantidadOpciones = cantidadOpciones;
    }

    public Respuesta getRespuestaCorrecta() {
        return respuestaCorrecta;
    }

    public void setRespuestaCorrecta(Respuesta respuestaCorrecta) {
        this.respuestaCorrecta = respuestaCorrecta;
    }

    public ArrayList<Respuesta> getListaOpciones() {
        return listaOpciones;
    }

    public void setListaOpciones(ArrayList<Respuesta> listaOpciones) {
        this.listaOpciones = listaOpciones;
    }
}
