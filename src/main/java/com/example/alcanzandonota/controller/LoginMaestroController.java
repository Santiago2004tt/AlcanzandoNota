package com.example.alcanzandonota.controller;

import com.example.alcanzandonota.application.AppInstitucion;
import com.example.alcanzandonota.exceptions.ProfesorException;
import com.example.alcanzandonota.exceptions.ValorRequeridoException;
import com.example.alcanzandonota.model.Profesor;
import com.example.alcanzandonota.util.MensajeUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class LoginMaestroController {

    @FXML
    private Button btnIniciarSesion;

    @FXML
    private Button btnRegresar;

    @FXML
    private PasswordField pfContrasena;

    @FXML
    private TextField tfIdentificacion;

    @FXML
    void contrasenaReleased(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER)
            btnIniciarSesion.fire();
    }

    @FXML
    void identificacionRelease(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER)
            pfContrasena.requestFocus();
    }

    @FXML
    void iniciarSesionAction(ActionEvent event) {
        try {
            iniciarSesion();
        } catch (ValorRequeridoException | ProfesorException e) {
            MensajeUtil.mensajeAlerta(e.getMessage());
        }
    }

    @FXML
    void regresarAction(ActionEvent event) {
        appInstitucion.mostrarLobby();
    }

    /**
     * ATRIBUTOS
     */
    AppInstitucion appInstitucion;
    ModelFactoryController modelFactoryController = ModelFactoryController.getInstance();

    /**
     * GETTERS AND SETTERS
     * @param appInstitucion
     */
    public void setAppInstitucion(AppInstitucion appInstitucion) {
        this.appInstitucion = appInstitucion;
    }

    private void iniciarSesion() throws ValorRequeridoException, ProfesorException {
        String identificacion = tfIdentificacion.getText();
        String contrasena = pfContrasena.getText();

        if (identificacion.isEmpty()) throw new ValorRequeridoException("El campo identificacion es requerido");
        if (contrasena.isEmpty()) throw new ValorRequeridoException("El campo contraseña es requerido");

        Profesor profesor = modelFactoryController.verificarProfesor(identificacion, contrasena);
        mantenerSesion(profesor);
        appInstitucion.mostrarMenuProfesor(profesor);
    }

    private void mantenerSesion(Profesor profesor) {
        Preferences preferences = Preferences.userNodeForPackage(AppInstitucion.class);
        limpiarCredenciales();
        preferences.put(profesor.getCedula(), profesor.getCodigo());
    }

    private void limpiarCredenciales() {
        try {
            Preferences preferences = Preferences.userNodeForPackage(AppInstitucion.class);
            preferences.clear();
        } catch (BackingStoreException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void initialize() {
    }
}
