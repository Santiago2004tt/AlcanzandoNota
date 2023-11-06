package com.example.alcanzandonota.controller;

import com.example.alcanzandonota.exceptions.EstudianteException;
import com.example.alcanzandonota.exceptions.FormularioException;
import com.example.alcanzandonota.exceptions.ProfesorException;
import com.example.alcanzandonota.model.*;
import com.example.alcanzandonota.persistencia.Persistencia;

import java.util.ArrayList;

public class ModelFactoryController {

    /**
     * atributos
     */
    private Institucion institucion;

    private static class SingletonHolder {
        private final static ModelFactoryController eInstance = new ModelFactoryController();
    }

    public static ModelFactoryController getInstance() {
        return SingletonHolder.eInstance;
    }

    public Institucion getInstitucion() {
        return institucion;
    }

    public ModelFactoryController() {
        institucion = Persistencia.cargarRecursoBinario();
        if(institucion == null) {
            inicializarDatos();
            Persistencia.guardarRecursoBinario(institucion);
        }
    }

    private void inicializarDatos() {
        inicializarInstitucion();
    }

    private void inicializarInstitucion() {
        institucion = new Institucion();
        try {
            institucion.crearProfesor("Jacobo", "Granada", "123", "ADMINISTRATOR", "UNI", "123");
            institucion.crearProfesor("Santiago", "Sepulveda", "456", "ADMINISTRATOR", "UNI", "456");
            institucion.crearProfesor("Sergio", "Patiño", "789", "ADMINISTRATOR", "UNI", "789");
            institucion.crearEstudiante("Santiago", "Sepulveda", "santisbb@gmail.com", "3692587414", "123", "Cedula", "19", "Uniquindio", "5", true, "123");
        } catch (ProfesorException | EstudianteException e) {
            throw new RuntimeException(e);
        }
    }

    public Formulario crearFormulario(String tematica, Profesor profesor, String curso, String gradoDificultad, ArrayList<Pregunta> preguntas) {
        return institucion.crearFormulario(tematica, profesor, curso, gradoDificultad, preguntas);
    }

    public Estudiante crearEstudiante(String nombre, String apellido, String email, String telefono, String numeroIdentificacion,
                                      String tipoDocumento, String edad, String institucion, String curso, boolean estado,
                                      String contrasena) throws EstudianteException {
        return this.institucion.crearEstudiante(nombre, apellido, email, telefono, numeroIdentificacion, tipoDocumento,
                edad, institucion, curso, estado, contrasena);
    }

    public Profesor crearProfesor(String nombre, String apellido, String cedula, String asignatura, String institucion, String codigo) throws ProfesorException {
        return this.institucion.crearProfesor(nombre, apellido, cedula, asignatura, institucion, codigo);
    }

    public void guardarResourceSerializable() {
        Persistencia.guardarRecursoBinario(institucion);
    }

    public void guardarResourceXML() {
        Persistencia.guardarRecursoXML(institucion);
    }

    public Formulario verificarCodifoFormulario(String codigo, Estudiante estudiante) throws FormularioException {
        return institucion.verificarCodifoFormulario(codigo, estudiante);
    }

    public Estudiante verificarEstudiante(String cedula, String contrasena) throws EstudianteException {
        return institucion.verificarEstudiante(cedula, contrasena);
    }

    public Profesor verificarProfesor(String cedula, String codigo) throws ProfesorException {
        return institucion.verificarProfesor(cedula, codigo);
    }
}
