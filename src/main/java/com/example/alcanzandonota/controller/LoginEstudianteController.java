package com.example.alcanzandonota.controller;

import com.example.alcanzandonota.application.AppInstitucion;
import com.example.alcanzandonota.exceptions.EstudianteException;
import com.example.alcanzandonota.exceptions.ValorRequeridoException;
import com.example.alcanzandonota.model.Estudiante;
import com.example.alcanzandonota.util.MensajeUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class LoginEstudianteController {

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
    void identificacionReleased(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER)
            pfContrasena.requestFocus();
    }

    @FXML
    void inisiarSesionAction(ActionEvent event) {
        try {
            iniciarSesion();
        } catch (ValorRequeridoException | EstudianteException e) {
            MensajeUtil.mensajeAlerta(e.getMessage());
        }
    }

    @FXML
    void regresarAcrion(ActionEvent event) {
        appInstitucion.mostrarLobby();
    }

    /**
     * INSTANCIAS
     */
    AppInstitucion appInstitucion;
    ModelFactoryController modelFactoryController = ModelFactoryController.getInstance();

    /**
     * GETTERS AND SETTERS
     */
    public void setAppInstitucion(AppInstitucion appInstitucion) {
        this.appInstitucion = appInstitucion;
    }


    private void iniciarSesion() throws ValorRequeridoException, EstudianteException {
        String identificacion = tfIdentificacion.getText().trim();
        String contrasena = pfContrasena.getText().trim();

        if (identificacion.isEmpty()) throw new ValorRequeridoException("El valor identificacion es requerido");
        if (contrasena.isEmpty()) throw new ValorRequeridoException("El valor contrasela es requerido");

        Estudiante estudiante = modelFactoryController.verificarEstudiante(identificacion, contrasena);
        appInstitucion.mostrarMenuEstudiante(estudiante);
    }


}
