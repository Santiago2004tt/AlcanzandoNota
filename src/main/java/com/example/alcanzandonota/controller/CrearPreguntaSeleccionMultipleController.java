package com.example.alcanzandonota.controller;

import com.example.alcanzandonota.application.AppInstitucion;
import com.example.alcanzandonota.exceptions.PreguntaException;
import com.example.alcanzandonota.exceptions.ValorRequeridoException;
import com.example.alcanzandonota.model.Pregunta;
import com.example.alcanzandonota.model.Profesor;
import com.example.alcanzandonota.model.Respuesta;
import com.example.alcanzandonota.model.SeleccionMultiple;
import com.example.alcanzandonota.util.MensajeUtil;
import com.example.alcanzandonota.util.TextFormatterUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class CrearPreguntaSeleccionMultipleController {

    @FXML
    private Button btnAceptar;

    @FXML
    private Button btnAgregarOpcion;

    @FXML
    private Button btnRegresar;

    @FXML
    private TextArea taEnunciado;

    @FXML
    private TextField tfValorPregunta;

    @FXML
    private VBox vbOpciones;

    @FXML
    void aceptarAction(ActionEvent event) {
        try {
            aceptar();
        } catch (ValorRequeridoException | PreguntaException e) {
            MensajeUtil.mensajeAlerta(e.getMessage());
        }
    }

    @FXML
    void agregarOpcionAction(ActionEvent event) {
        try {
            agregarOpcion();
        } catch (ValorRequeridoException | PreguntaException e) {
            MensajeUtil.mensajeAlerta(e.getMessage());
        }
    }

    @FXML
    void regresarAction(ActionEvent event) {
        appInstitucion.mostrarCrearPreguntas(profesor);
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
    }

    /**
     * VERIFICA LA INFORMACION DE LA PREGUNTA Y LA GUARDA TEMPORALMENTE
     * @throws ValorRequeridoException
     */
    private void aceptar() throws ValorRequeridoException, PreguntaException {
        String enunciado = taEnunciado.getText();
        ArrayList<String> respuestas = new ArrayList<>();

        vbOpciones.getChildren().forEach(x -> respuestas.add(((RadioButton) x).getText()));

        if (enunciado.isEmpty()) throw new ValorRequeridoException("El valor enunciado es requerido");
        if (respuestas.size() < 2) throw new ValorRequeridoException("Es necesario que hayan 2 o mas respuestas");

        Toggle correcta = ((RadioButton) vbOpciones.getChildren().get(0)).getToggleGroup().getSelectedToggle();
        if (correcta == null) throw new ValorRequeridoException("Es necesario que escoja una respuesta correcta");

        SeleccionMultiple seleccionMultiple = new SeleccionMultiple(enunciado);

        if (!tfValorPregunta.getText().isEmpty()) {
            float valor = Float.parseFloat(tfValorPregunta.getText());
            if (valor > 5 || valor < 0) {
                throw new PreguntaException("El valor del numero debe estar entre el rango de 0 a 5");
            }
            float valorT = valor + calcularValorTotal();
            if (valorT > 5) {
                throw new PreguntaException("El valor debe ser menor por que la sumatoria = "+ valorT + " supera el el rango maximo 5");
            }
            boolean bandera = obtenerListaPreguntas().stream().anyMatch(x -> x.getValorPregunta() == 0);
            if (valorT == 5 && bandera) {
                throw new PreguntaException("No puede asiganar el valor " + valor + " porque la suma da 5 y hay preguntas sin asignar valor");
            }
            seleccionMultiple.setValorPregunta(valor);
        }


        int count = 0;
        for (String s : respuestas) {
            Respuesta respuesta = new Respuesta(s, String.valueOf((char) (65 + count++)));
            if (s.equals(((RadioButton) correcta).getText()))
                seleccionMultiple.setRespuestaCorrecta(respuesta);
            seleccionMultiple.getListaOpciones().add(respuesta);
        }

        guardarPregunta(seleccionMultiple);
        btnRegresar.fire();
    }

    private void agregarOpcion() throws ValorRequeridoException, PreguntaException {

        TextInputDialog textInputDialog = new TextInputDialog();
        textInputDialog.setHeaderText(null);
        textInputDialog.setContentText("Ingrese una respuesta");
        String info = textInputDialog.showAndWait().orElse("");

        if (info.isEmpty()) throw new ValorRequeridoException("Es necesario que agregue una opcion");

        for (Node node : vbOpciones.getChildren()) {
            if (((RadioButton) node).getText().equals(info))
                throw new PreguntaException("No se permiten opciones repetidas");
        }

        RadioButton radioButton = new RadioButton(info);
        ToggleGroup toggleGroup;
        int lastIndex = vbOpciones.getChildren().size() - 1;
        if (lastIndex >= 0) {
            toggleGroup = ((RadioButton) vbOpciones.getChildren().get(lastIndex)).getToggleGroup();
        } else {
            toggleGroup = new ToggleGroup();
        }
        radioButton.setToggleGroup(toggleGroup);
        vbOpciones.getChildren().add(radioButton);
    }

    /**
     * CALCULA LA SUMA DEL VALOR DE LAS PREGUNTAS QUE HAN SIDO ESPECIFICADAS
     * @return
     */
    private float calcularValorTotal() {
        float total = 0;
        List<Pregunta> preguntas = obtenerListaPreguntas();
        for (Pregunta pregunta : preguntas) {
            total += pregunta.getValorPregunta();
        }
        return total;
    }

    /**
     * GUARDA LA PREGUNTA EN LA STAGE PARA PODER CREAR EL FORMULARIO DESPUES
     * @param pregunta
     */
    private void guardarPregunta(Pregunta pregunta) {
        Stage stage = (Stage) btnRegresar.getScene().getWindow();
        List<List<?>> list = (List<List<?>>) stage.getUserData();
        List<Pregunta> preguntas = obtenerListaPreguntas();
        preguntas.add(pregunta);
        if (list.size() == 1) {
            list.add(preguntas);
        } else {
            list.set(1, preguntas);
        }
        stage.setUserData(list);
    }

    /**
     * DEVUELVE LA LISTA DE LAS PREGUNTAS ALMACENADAS EN EL STAGE
     * @return
     */
    private List<Pregunta> obtenerListaPreguntas() {
        Stage stage = (Stage) btnRegresar.getScene().getWindow();
        List<List<?>> list = (List<List<?>>) stage.getUserData();
        return (list.size() == 1) ? new ArrayList<>() : (List<Pregunta>) list.get(1);
    }

    @FXML
    void initialize() {
        tfValorPregunta.setTextFormatter(new TextFormatter<>(TextFormatterUtil::decimalFormat));
    }
}
