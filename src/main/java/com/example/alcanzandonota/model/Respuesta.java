package com.example.alcanzandonota.model;

import java.io.Serializable;

public class Respuesta implements Serializable {

    private String respuesta;
    private String id;

    public Respuesta(String respuesta, String id) {
        this.respuesta = respuesta;
        this.id = id;
    }

    public Respuesta() {
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
