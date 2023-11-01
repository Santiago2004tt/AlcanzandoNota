package com.example.alcanzandonota.model;

import java.util.ArrayList;

public class Profesor {

    private String nombre;
    private String apellido;
    private String cedula;
    private String asignatura;
    private String institucion;
    private String codigo;
    private ArrayList<Formulario> listaFormularios;

    public Profesor(String nombre, String apellido, String cedula, String asignatura, String institucion, String codigo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.asignatura = asignatura;
        this.institucion = institucion;
        this.codigo = codigo;
        listaFormularios = new ArrayList<>();
    }

    public Profesor() {
        listaFormularios = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(String asignatura) {
        this.asignatura = asignatura;
    }

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public ArrayList<Formulario> getListaFormularios() {
        return listaFormularios;
    }

    public void setListaFormularios(ArrayList<Formulario> listaFormularios) {
        this.listaFormularios = listaFormularios;
    }
}
