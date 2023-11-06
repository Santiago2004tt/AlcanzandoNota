package com.example.alcanzandonota.model;

import com.example.alcanzandonota.exceptions.EstudianteException;
import com.example.alcanzandonota.exceptions.FormularioException;
import com.example.alcanzandonota.exceptions.ProfesorException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Institucion implements Serializable {

    /**
     * ATRIBUTOS
     */
    private String id;
    private String nombre;
    private String ubicacion;
    private String horarioServicio;
    private ArrayList<Estudiante> listaEstudiantes;
    private ArrayList<Profesor> listaProfesores;
    private HashMap<String, Formulario> formularios;

    /**
     * CONSTRUCTORES
     * @param id
     * @param nombre
     * @param ubicacion
     * @param horarioServicio
     */
    public Institucion(String id, String nombre, String ubicacion, String horarioServicio) {
        this.id = id;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.horarioServicio = horarioServicio;
        listaEstudiantes = new ArrayList<>();
        listaProfesores = new ArrayList<>();
        formularios = new HashMap<>();
    }

    public Institucion() {
        listaEstudiantes = new ArrayList<>();
        listaProfesores = new ArrayList<>();
        formularios = new HashMap<>();
    }

    /**
     * RETORNA AL ESTUDIANTE BUSCADO SI LO ENCUENTRA DE LO CONTRARIO DEVUELVE NULL
     * @param cedula
     * @return
     */
    public Estudiante buscarEstudiante(String cedula) {
        return listaEstudiantes.stream().filter(estudiante -> estudiante.getNumeroIdentificacion().equals(cedula))
                .findFirst().orElse(null);
    }

    /**
     * RETORNA AL PROFESOR BUSCADO SI LO ENCUENTRA DE LO CONTRARIO DEVUELVE NULL
     * @param cedula
     * @return
     */
    private Profesor buscarProfesor(String cedula) {
        return listaProfesores.stream().filter(profesor -> profesor.getCedula().equals(cedula)).findFirst().orElse(null);
    }

    /**
     * CREA UN FORMULARIO Y LO ASIGNA AL PROFESOR Y A LA LISTA DE FORMULARIOS
     * @param tematica
     * @param profesor
     * @param preguntas
     * @return
     */
    public Formulario crearFormulario(String tematica, Profesor profesor, String curso, String gradoDificultad, ArrayList<Pregunta> preguntas) {
        String codigo = formularios.size() + 1 + "";
        Formulario formulario = new Formulario(codigo, tematica, preguntas.size(), profesor, curso, profesor.getInstitucion(), gradoDificultad);
        formulario.setListaPreguntas(preguntas);
        formularios.put(codigo, formulario);
        profesor.getListaFormularios().add(formulario);
        return formulario;
    }

    /**
     * CREA UN ESTUDIANTE SI ESTE NO EXISTE
     * @param nombre
     * @param apellido
     * @param email
     * @param telefono
     * @param numeroIdentificacion
     * @param tipoDocumento
     * @param edad
     * @param institucion
     * @param curso
     * @param estado
     * @param contrasena
     * @return
     * @throws EstudianteException
     */
    public Estudiante crearEstudiante(String nombre, String apellido, String email, String telefono, String numeroIdentificacion,
                                    String tipoDocumento, String edad, String institucion, String curso, boolean estado,
                                    String contrasena) throws EstudianteException {
        if (estudaitneExiste(numeroIdentificacion))
            throw new EstudianteException("El estudiante ya existe ");

        Estudiante estudiante = new Estudiante(nombre, apellido, email, telefono, numeroIdentificacion, tipoDocumento,
                edad, institucion, curso, estado, contrasena);
        listaEstudiantes.add(estudiante);
        return estudiante;
    }

    /**
     * CREA UN PROFESOR SI ESTE NO EXISTE
     * @param nombre
     * @param apellido
     * @param cedula
     * @param asignatura
     * @param institucion
     * @param codigo
     */
    public Profesor crearProfesor(String nombre, String apellido, String cedula, String asignatura, String institucion,
                                  String codigo) throws ProfesorException {
        if (profesorExiste(cedula))
            throw new ProfesorException("El profesor ya existe ");

        Profesor profesor = new Profesor(nombre, apellido, cedula, asignatura, institucion, codigo);
        listaProfesores.add(profesor);
        return profesor;
    }

    /**
     * VERIFICA SI UN ESTUDIANTE YA EXISTE
     * @param numeroIdentificacion
     * @return
     */
    private boolean estudaitneExiste(String numeroIdentificacion) {
        return listaEstudiantes.stream().anyMatch(estudiante -> estudiante.getNumeroIdentificacion().equals(numeroIdentificacion));
    }

    /**
     * VERIFICA SI UN PROFESOR YA EXISTE
     * @param cedula
     * @return
     */
    private boolean profesorExiste(String cedula) {
        return listaProfesores.stream().anyMatch(profesor -> profesor.getCedula().equals(cedula));
    }

    /**
     * VERIFICA SI EXISTE UN FORMULARIO ASOCIADO AL CODIGO PASDO Y SI EXISTE LO RETORNA
     * @param codigo
     * @return
     * @throws FormularioException
     */
    public Formulario verificarCodifoFormulario(String codigo, Estudiante estudiante) throws FormularioException {
        Formulario formulario = formularios.get(codigo);
        if (formulario == null)
            throw new FormularioException("El formulario no existe");
        if (!formulario.getGradoDificultad().equals(estudiante.getCurso()))
            throw new FormularioException("El estudiante no cumple con el grado del formulario");
        if (formulario.getListaRespuestas().containsKey(estudiante))
            throw new FormularioException("El estudiatne ya resolvio el formulario");
        return formulario;
    }

    /**
     * VERIFICA SI LOS DATOS DE INICIO DEL ESTUDIANTE ESTAN CORRECTOS
     * @param cedula
     * @param contrasena
     * @return
     * @throws ProfesorException
     */
    public Estudiante verificarEstudiante(String cedula, String contrasena) throws EstudianteException {
        Estudiante estudiante = buscarEstudiante(cedula);
        if (estudiante == null)
            throw new EstudianteException("El estudiante no existe");
        if (!estudiante.getContrasena().equals(contrasena))
            throw new EstudianteException("Contraseña incorrecta");
        return estudiante;
    }

    /**
     * VERIFICA SI LOS DATOS DE INICIO DEL PROFESOR ESTAN CORRECTOS
     * @param cedula
     * @param codigo
     * @return
     * @throws ProfesorException
     */
    public Profesor verificarProfesor(String cedula, String codigo) throws ProfesorException {
        Profesor profesor = buscarProfesor(cedula);
        if (profesor == null)
            throw new ProfesorException("El profesor no existe ");
        if (!profesor.getCodigo().equals(codigo))
            throw new ProfesorException("Contraseña incorrecta");
        return profesor;
    }

    /**
     * GETTERS AND SETTERS
     * @return
     */
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

    public HashMap<String, Formulario> getFormularios() {
        return formularios;
    }

    public void setFormularios(HashMap<String, Formulario> formularios) {
        this.formularios = formularios;
    }

}
