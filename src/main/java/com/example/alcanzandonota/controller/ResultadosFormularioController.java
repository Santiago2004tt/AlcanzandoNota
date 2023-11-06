package com.example.alcanzandonota.controller;

import com.example.alcanzandonota.application.AppInstitucion;
import com.example.alcanzandonota.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.util.ArrayList;
import java.util.List;

public class ResultadosFormularioController {

    @FXML
    private Button btnAceptar;

    @FXML
    private TableColumn<Pregunta, String> colEstado;

    @FXML
    private TableColumn<Pregunta, String> colPregunta;

    @FXML
    private TableColumn<Pregunta, String> colValor;

    @FXML
    private Label lblPuntaje;

    @FXML
    private TableView<Pregunta> tblResultados;

    @FXML
    void aceptarAction(ActionEvent event) {
        appInstitucion.mostrarMenuEstudiante(estudiante);
    }

    /**
     * INSTANCIAS
     */
    AppInstitucion appInstitucion;
    Estudiante estudiante;
    Formulario formulario;
    ObservableList<Pregunta> preguntasData = FXCollections.observableArrayList();

    /**
     * GETTERS AND SETTERS
     */
    public void setAppInstitucion(AppInstitucion appInstitucion) {
        this.appInstitucion = appInstitucion;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public void setFormulario(Formulario formulario) {
        this.formulario = formulario;
        lblPuntaje.setText("Puntaje " + calcularPuntaje());
        tblResultados.setItems(getPreguntasData());
    }

    public ObservableList<Pregunta> getPreguntasData() {
        preguntasData.clear();
        preguntasData.addAll(formulario.getListaPreguntas());
        return preguntasData;
    }

    private boolean preguntaAbierta(Abierta pregunta, String respuesta) {
        List<String> tokens = pregunta.getPalabraClave();

        for (String token : tokens) {
            if (!respuesta.contains(token))
                return false;
        }
        return true;
    }

    private float calcularPuntaje() {
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
        colPregunta.setCellValueFactory(new PropertyValueFactory<>("pregunta"));
        colValor.setCellValueFactory(new PropertyValueFactory<>("valorPregunta"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("pregunta"));
        colEstado.setCellFactory(new Callback<TableColumn<Pregunta, String>, TableCell<Pregunta, String>>() {
            @Override
            public TableCell<Pregunta, String> call(TableColumn<Pregunta, String> preguntaStringTableColumn) {
                return new TableCell<>() {
                    @Override
                    protected void updateItem(String s, boolean b) {
                        super.updateItem(s, b);
                        if (!b && s != null) {
                            List<String> respuestas = formulario.getListaRespuestas().get(estudiante);
                            ArrayList<Pregunta> listaPreguntas = formulario.getListaPreguntas();
                            for (int i = 0; i < listaPreguntas.size(); i++) {
                                Pregunta pregunta = listaPreguntas.get(i);
                                if (pregunta.getPregunta().equals(s)) {
                                    String respuesta = respuestas.get(i);
                                    boolean isCorrect;
                                    if (pregunta instanceof Abierta) {
                                        isCorrect = preguntaAbierta((Abierta) pregunta, respuesta);
                                    } else {
                                        isCorrect = preguntaMultiple((SeleccionMultiple) pregunta, respuesta);
                                    }

                                    if (isCorrect) {
                                        setStyle("-fx-text-fill: green; -fx-alignment: center;");
                                        setText("Correcta");
                                    } else {
                                        setStyle("-fx-text-fill: red; -fx-alignment: center;");
                                        setText("Incorrecta");
                                    }
                                }
                            }
                        }
                    }
                };
            }
        });
    }

}
