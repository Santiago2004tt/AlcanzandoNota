package com.example.alcanzandonota.controller;

import com.example.alcanzandonota.application.AppInstitucion;
import com.example.alcanzandonota.model.Estudiante;
import com.example.alcanzandonota.model.Formulario;
import com.example.alcanzandonota.model.Profesor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.util.List;

public class HistorialPruebasController {

    @FXML
    private Button btnRegresar;

    @FXML
    private TableColumn<Formulario, String> colCodigo;

    @FXML
    private TableColumn<Formulario, String> colFormulario;

    @FXML
    private TableColumn<Estudiante, String> colEstudiantes;

    @FXML
    private TableView<Estudiante> tblEstudiantes;

    @FXML
    private TableView<Formulario> tblFormularios;

    @FXML
    void regresarAction(ActionEvent event) {
        appInstitucion.mostrarMenuProfesor(profesor);
    }

    /**
     * ATRIBUTOS
     */
    AppInstitucion appInstitucion;
    Profesor profesor;
    ObservableList<Formulario> formulariosData = FXCollections.observableArrayList();

    /**
     * GETTERS AND SETTERS
     * @param appInstitucion
     */
    public void setAppInstitucion(AppInstitucion appInstitucion) {
        this.appInstitucion = appInstitucion;
    }

    public ObservableList<Formulario> getFormulariosData() {
        formulariosData.clear();
        formulariosData.addAll(profesor.getListaFormularios());
        return formulariosData;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
        tblFormularios.setItems(getFormulariosData());
    }

    @FXML
    void initialize() {
        colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colFormulario.setCellValueFactory(new PropertyValueFactory<>("tematica"));

        tblFormularios.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue != null) {
                ObservableList<Estudiante> estudiantes = FXCollections.observableArrayList(newValue.getListaEstudiante());
                tblEstudiantes.setItems(estudiantes);
            }
        });

        colEstudiantes.setCellValueFactory(new PropertyValueFactory<>("nombreCompleto"));

        ;
    }
}
