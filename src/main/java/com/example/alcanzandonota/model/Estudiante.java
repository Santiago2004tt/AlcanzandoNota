package com.example.alcanzandonota.model;

import java.util.ArrayList;

public class Estudiante {

    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private String numeroIdentificacion;
    private String tipoDocumento;
    private String edad;
    private String institucion;
    private ArrayList<Formulario> listaFormularios ;

    public Estudiante(String nombre, String apellido, String email, String telefono, String numeroIdentificacion, String tipoDocumento, String edad, String institucion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.numeroIdentificacion = numeroIdentificacion;
        this.tipoDocumento = tipoDocumento;
        this.edad = edad;
        this.institucion = institucion;
        listaFormularios = new ArrayList<>();
    }

    public Estudiante() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public ArrayList<Formulario> getListaFormularios() {
        return listaFormularios;
    }

    public void setListaFormularios(ArrayList<Formulario> listaFormularios) {
        this.listaFormularios = listaFormularios;
    }
}
