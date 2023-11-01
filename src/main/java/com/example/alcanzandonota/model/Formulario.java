package com.example.alcanzandonota.model;

import java.util.ArrayList;

public class Formulario {

    private String codigo;
    private String tematica;
    private int numeroPreguntas;
    private Profesor profesor;
    private ArrayList<Estudiante> listaEstudiante = new ArrayList<>();
    private ArrayList<Pregunta> listaPreguntas = new ArrayList<>();

    public Formulario(String codigo, String tematica, int numeroPreguntas, Profesor profesor) {
        this.codigo = codigo;
        this.tematica = tematica;
        this.numeroPreguntas = numeroPreguntas;
        this.profesor = profesor;
        listaPreguntas= new ArrayList<>();
        listaEstudiante = new ArrayList<>();
    }

    public Formulario() {
        listaPreguntas= new ArrayList<>();
        listaEstudiante = new ArrayList<>();
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTematica() {
        return tematica;
    }

    public void setTematica(String tematica) {
        this.tematica = tematica;
    }

    public int getNumeroPreguntas() {
        return numeroPreguntas;
    }

    public void setNumeroPreguntas(int numeroPreguntas) {
        this.numeroPreguntas = numeroPreguntas;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public ArrayList<Estudiante> getListaEstudiante() {
        return listaEstudiante;
    }

    public void setListaEstudiante(ArrayList<Estudiante> listaEstudiante) {
        this.listaEstudiante = listaEstudiante;
    }

    public ArrayList<Pregunta> getListaPreguntas() {
        return listaPreguntas;
    }

    public void setListaPreguntas(ArrayList<Pregunta> listaPreguntas) {
        this.listaPreguntas = listaPreguntas;
    }
}
