package com.example.alcanzandonota.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Formulario implements Serializable {

    private String codigo;
    private String tematica;
    private int numeroPreguntas;
    private Profesor profesor;
    private String curso;
    private String institucion;
    private String gradoDificultad;
    private ArrayList<Estudiante> listaEstudiante;
    private ArrayList<Pregunta> listaPreguntas;
    private HashMap<Estudiante, List<String>> listaRespuestas;

    public Formulario(String codigo, String tematica, int numeroPreguntas, Profesor profesor, String curso, String institucion, String gradoDificultad) {
        this.codigo = codigo;
        this.tematica = tematica;
        this.numeroPreguntas = numeroPreguntas;
        this.profesor = profesor;
        this.curso = curso;
        this.institucion = institucion;
        this.gradoDificultad = gradoDificultad;
        listaPreguntas = new ArrayList<>();
        listaEstudiante = new ArrayList<>();
        listaRespuestas = new HashMap<>();
    }

    public Formulario() {
        listaPreguntas = new ArrayList<>();
        listaEstudiante = new ArrayList<>();
        listaRespuestas = new HashMap<>();
    }

    /**
     * GETTERS AND SETTERS
     *
     * @return
     */
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

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public String getGradoDificultad() {
        return gradoDificultad;
    }

    public void setGradoDificultad(String gradoDificultad) {
        this.gradoDificultad = gradoDificultad;
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

    public HashMap<Estudiante, List<String>> getListaRespuestas() {
        return listaRespuestas;
    }

    public void setListaRespuestas(HashMap<Estudiante, List<String>> listaRespuestas) {
        this.listaRespuestas = listaRespuestas;
    }
}
