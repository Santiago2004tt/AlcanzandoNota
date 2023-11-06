package com.example.alcanzandonota.controller;

import com.example.alcanzandonota.application.AppInstitucion;
import com.example.alcanzandonota.exceptions.ValorRequeridoException;
import com.example.alcanzandonota.model.Profesor;
import com.example.alcanzandonota.util.MensajeUtil;
import com.example.alcanzandonota.util.TextFormatterUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CrearPruebaController {

    @FXML
    private Button btnCrearPreguntas;

    @FXML
    private Button btnRegresar;

    @FXML
    private Label lblArea;

    @FXML
    private Label lblInstitucion;

    @FXML
    private Label lblProfesor;

    @FXML
    private TextField tfGradoDificultad;

    @FXML
    private TextField tfTematica;

    @FXML
    void crearPreguntasAction(ActionEvent event) {
        try {
            crearPreguntas();
        } catch (ValorRequeridoException e) {
            MensajeUtil.mensajeAlerta(e.getMessage());
        }
    }

    @FXML
    void gradoDificultadReleased(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER)
            tfTematica.requestFocus();
    }

    @FXML
    void tematicaReleased(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER)
            btnCrearPreguntas.fire();
    }

    @FXML
    void regresarAction(ActionEvent event) {
        appInstitucion.mostrarMenuProfesor(profesor);
    }

    /**
     * ATRIBUTOS
     */
    AppInstitucion appInstitucion;
    Profesor profesor;

    /**
     * GETTERS AND SETTERS
     * @param appInstitucion
     */
    public void setAppInstitucion(AppInstitucion appInstitucion) {
        this.appInstitucion = appInstitucion;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
        lblArea.setText("Area: " + profesor.getAsignatura());
        lblInstitucion.setText("Institucion: " + profesor.getInstitucion());
        lblProfesor.setText("Profesor: " + profesor.getNombre() + " " + profesor.getApellido());
    }

    private void crearPreguntas() throws ValorRequeridoException {
        String gradoDificultad = tfGradoDificultad.getText();
        String tematica = tfTematica.getText();
        String area = profesor.getAsignatura();
        String nombreProfesor = profesor.getNombre() + profesor.getApellido();
        String nombreInstitucion = profesor.getInstitucion();

        if(gradoDificultad.isEmpty()) throw new ValorRequeridoException("El valor grado dificultad es requerido");
        if(tematica.isEmpty()) throw new ValorRequeridoException("El valor tematica es requerido");

        guardarDatos(Arrays.asList(gradoDificultad, area, nombreProfesor, nombreInstitucion, tematica));
        appInstitucion.mostrarCrearPreguntas(profesor);
    }

    /**
     * PERMITE GUARDAR UNA INSTANCIA EN EL STAGE
     */
    public void guardarDatos(List<String> args) {

        Stage stage = (Stage) btnRegresar.getScene().getWindow();
        List<List<String>> list = new ArrayList<>();
        list.add(args);

        stage.setUserData(list);
    }

    /**
     * PERMITE CARGAR LA INSTANCIA GUARDADA EN LA STAGE
     */
    public void recuperarDatos() {
        Stage stage = (Stage) btnRegresar.getScene().getWindow();
        Object obj = stage.getUserData();
        if (obj instanceof List<?>) {
            List<List<Object>> list = (List<List<Object>>) obj;

            tfGradoDificultad.setText((String) list.get(0).get(0));
        }
    }

    @FXML
    void initialize() {
        tfGradoDificultad.setTextFormatter(new TextFormatter<>(TextFormatterUtil::integerFormat));
    }
}
