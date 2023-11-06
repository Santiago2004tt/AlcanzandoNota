package com.example.alcanzandonota.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Estudiante implements Serializable {

    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private String numeroIdentificacion;
    private String tipoDocumento;
    private String edad;
    private String institucion;
    private String curso;
    private boolean estado;
    private String contrasena;
    private ArrayList<Formulario> listaFormularios ;

    public Estudiante(String nombre, String apellido, String email, String telefono, String numeroIdentificacion,
                      String tipoDocumento, String edad, String institucion, String curso, boolean estado, String contrasena) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.numeroIdentificacion = numeroIdentificacion;
        this.tipoDocumento = tipoDocumento;
        this.edad = edad;
        this.institucion = institucion;
        this.curso = curso;
        this.estado = estado;
        this.contrasena = contrasena;
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

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public ArrayList<Formulario> getListaFormularios() {
        return listaFormularios;
    }

    public void setListaFormularios(ArrayList<Formulario> listaFormularios) {
        this.listaFormularios = listaFormularios;
    }

    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }
}
