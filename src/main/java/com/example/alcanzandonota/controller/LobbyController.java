package com.example.alcanzandonota.controller;

import com.example.alcanzandonota.application.AppInstitucion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class LobbyController {

    @FXML
    private Button btnEstudiatne;

    @FXML
    private Button btnMaestro;

    @FXML
    void estudianteAction(ActionEvent event) {
        appInstitucion.mostrarLoginEstudiante();
    }

    @FXML
    void maestroAction(ActionEvent event) {
        appInstitucion.mostrarLoginMaestro();
    }

    /**
     * INSTANCIAS
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
}
