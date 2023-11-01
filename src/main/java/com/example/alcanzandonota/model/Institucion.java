package com.example.alcanzandonota.model;

import java.util.ArrayList;

public class Institucion {
    private String id;
    private String nombre;
    private String ubicacion;
    private String horarioServicio;
    private ArrayList<Estudiante> listaEstudiantes;
    private ArrayList<Profesor> listaProfesores;

    public Institucion(String id, String nombre, String ubicacion, String horarioServicio) {
        this.id = id;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.horarioServicio = horarioServicio;
        listaEstudiantes = new ArrayList<>();
        listaProfesores = new ArrayList<>();
    }

    public Institucion() {
        listaEstudiantes = new ArrayList<>();
        listaProfesores = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getHorarioServicio() {
        return horarioServicio;
    }

    public void setHorarioServicio(String horarioServicio) {
        this.horarioServicio = horarioServicio;
    }

    public ArrayList<Estudiante> getListaEstudiantes() {
        return listaEstudiantes;
    }

    public void setListaEstudiantes(ArrayList<Estudiante> listaEstudiantes) {
        this.listaEstudiantes = listaEstudiantes;
    }

    public ArrayList<Profesor> getListaProfesores() {
        return listaProfesores;
    }

    public void setListaProfesores(ArrayList<Profesor> listaProfesores) {
        this.listaProfesores = listaProfesores;
    }
}
