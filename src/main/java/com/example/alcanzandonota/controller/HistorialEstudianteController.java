package com.example.alcanzandonota.controller;

import com.example.alcanzandonota.application.AppInstitucion;
import com.example.alcanzandonota.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.util.List;

public class HistorialEstudianteController {

    @FXML
    private Button btnAceptar;

    @FXML
    private TableColumn<Formulario, String> colPrueba;

    @FXML
    private TableColumn<Formulario, String> colPuntaje;

    @FXML
    private TableView<Formulario> tblHistorial;

    @FXML
    void aceptarAction(ActionEvent event) {
        appInstitucion.mostrarMenuEstudiante(estudiante);
    }

    /**
     * INSTANCIAS
     *
     * @return
     */
    private AppInstitucion appInstitucion;
    private Estudiante estudiante;
    private ObservableList<Formulario> formulariosData = FXCollections.observableArrayList();
    private ModelFactoryController modelFactoryController = ModelFactoryController.getInstance();

    public ObservableList<Formulario> getFormulariosData() {
        formulariosData.clear();
        formulariosData.addAll(estudiante.getListaFormularios());
        return formulariosData;
    }

    /**
     * GETTERS AND SETTERS
     * @return
     */

    public void setAppInstitucion(AppInstitucion appInstitucion) {
        this.appInstitucion = appInstitucion;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
        tblHistorial.setItems(getFormulariosData());
    }

    private boolean preguntaAbierta(Abierta pregunta, String respuesta) {
        List<String> tokens = pregunta.getPalabraClave();

        for (String token : tokens) {
            if (!respuesta.contains(token))
                return false;
        }
        return true;
    }

    private float calcularPuntaje(Formulario formulario) {
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

    @FXML
    void initialize() {
        colPrueba.setCellValueFactory(new PropertyValueFactory<>("tematica"));
        colPuntaje.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colPuntaje.setCellFactory(new Callback<TableColumn<Formulario, String>, TableCell<Formulario, String>>() {
            @Override
            public TableCell<Formulario, String> call(TableColumn<Formulario, String> formularioStringTableColumn) {
                return new TableCell<>() {
                    @Override
                    protected void updateItem(String s, boolean b) {
                        super.updateItem(s, b);
                        if (!b && s != null) {
                            Formulario formulario = modelFactoryController.getInstitucion().getFormularios().get(s);
                            setText(String.valueOf(calcularPuntaje(formulario)));
                        }
                    }
                };
            }
        });
    }

}
