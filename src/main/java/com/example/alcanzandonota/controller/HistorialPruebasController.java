package com.example.alcanzandonota.controller;

import com.example.alcanzandonota.application.AppInstitucion;
import com.example.alcanzandonota.model.*;
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
    private TableColumn<Estudiante, String> colEstudiantes;

    @FXML
    private TableColumn<Formulario, String> colFormulario;

    @FXML
    private TableColumn<Estudiante, String> colNota;

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
    Formulario formulario;
    ModelFactoryController modelFactoryController = ModelFactoryController.getInstance();
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
                formulario = newValue;
                ObservableList<Estudiante> estudiantes = FXCollections.observableArrayList(newValue.getListaEstudiante());
                tblEstudiantes.setItems(estudiantes);
            }
        });

        colEstudiantes.setCellValueFactory(new PropertyValueFactory<>("nombreCompleto"));
        colNota.setCellValueFactory(new PropertyValueFactory<>("numeroIdentificacion"));
        colNota.setCellFactory(new Callback<TableColumn<Estudiante, String>, TableCell<Estudiante, String>>() {
            @Override
            public TableCell<Estudiante, String> call(TableColumn<Estudiante, String> estudianteStringTableColumn) {
                return new TableCell<>() {
                    @Override
                    protected void updateItem(String s, boolean b) {
                        super.updateItem(s, b);
                        if (s != null) {
                            Estudiante estudiante = modelFactoryController.getInstitucion().buscarEstudiante(s);
                            setText(String.valueOf(calcularPuntaje(formulario, estudiante)));
                        }
                    }
                };
            }
        });
        ;
    }

    private boolean preguntaAbierta(Abierta pregunta, String respuesta) {
        List<String> tokens = pregunta.getPalabraClave();

        for (String token : tokens) {
            if (!respuesta.contains(token))
                return false;
        }
        return true;
    }

    private float calcularPuntaje(Formulario formulario, Estudiante estudiante) {
        float puntaje = 0;
        List<String> respuestasAlumno = formulario.getListaRespuestas().get(estudiante);
        List<Pregunta> preguntas = formulario.getListaPreguntas();
        boolean isCorrecta = false;

        for (int i = 0; i < respuestasAlumno.size(); i++) {
            Pregunta pregunta = preguntas.get(i);
            String respuesta = respuestasAlumno.get(i);
            if (pregunta instanceof Abierta)
                isCorrecta = preguntaAbierta((Abierta) pregunta, respuesta);
            else
                isCorrecta = preguntaMultiple((SeleccionMultiple) pregunta, respuesta);
            if (isCorrecta)
                puntaje += pregunta.getValorPregunta();
        }
        return puntaje;
    }

    private boolean preguntaMultiple(SeleccionMultiple pregunta, String respuesta) {
        return pregunta.getRespuestaCorrecta().getRespuesta().equals(respuesta);
    }
}
