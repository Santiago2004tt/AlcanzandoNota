package com.example.alcanzandonota.controller;

import com.example.alcanzandonota.application.AppInstitucion;
import com.example.alcanzandonota.model.Profesor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class MenuProfesorController {

    @FXML
    private Button btnCrearEstudiante;

    @FXML
    private Button btnCrearFormulario;

    @FXML
    private Button btnCrearMaestro;

    @FXML
    private Button btnHistorialFormularios;

    @FXML
    private Button btnRegresar;

    @FXML
    private Label lblNombre;

    @FXML
    void crearEstudianteAction(ActionEvent event) {
        appInstitucion.mostrarRegistroEstudiante(profesor);
    }

    @FXML
    void crearFormularioAction(ActionEvent event) {
        appInstitucion.mostrarRegistroPrueba(profesor);
    }

    @FXML
    void crearMaestroAction(ActionEvent event) {
        appInstitucion.mostrarRegistroMaestro(profesor);
    }

    @FXML
    void historialFormulariosAction(ActionEvent event) {
        appInstitucion.mostrarHistorialPruebas(profesor);
    }

    @FXML
    void regresarAction() {
        limpiarCredenciales();
        appInstitucion.mostrarLoginMaestro();
    }

    /**
     * ATRIBUTOS
     */
    AppInstitucion appInstitucion;
    Profesor profesor;
    ModelFactoryController modelFactoryController = ModelFactoryController.getInstance();

    /**
     * GETTERS AND SETTERS
     * @param appInstitucion
     */
    public void setAppInstitucion(AppInstitucion appInstitucion) {
        this.appInstitucion = appInstitucion;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
        lblNombre.setText(String.format("Bienvenido %s %s", profesor.getNombre(), profesor.getApellido()));
    }

    @FXML
    void initialize() {

    }

    private void limpiarCredenciales() {
        try {
            Preferences preferences = Preferences.userNodeForPackage(AppInstitucion.class);
            preferences.clear();
        } catch (BackingStoreException e) {
            throw new RuntimeException(e);
        }
    }

}
