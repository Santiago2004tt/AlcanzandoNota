package com.example.alcanzandonota.controller;

import com.example.alcanzandonota.application.AppInstitucion;
import com.example.alcanzandonota.exceptions.PreguntaException;
import com.example.alcanzandonota.exceptions.ValorRequeridoException;
import com.example.alcanzandonota.model.Abierta;
import com.example.alcanzandonota.model.Pregunta;
import com.example.alcanzandonota.model.Profesor;
import com.example.alcanzandonota.util.MensajeUtil;
import com.example.alcanzandonota.util.TextFormatterUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CrearPreguntaAbiertaController {

    @FXML
    private Button btnAceptar;

    @FXML
    private Button btnRegresar;

    @FXML
    private TextArea taEnunciado;

    @FXML
    private TextArea taToken;

    @FXML
    private TextField tfValorPregunta;

    @FXML
    void aceptarAction(ActionEvent event) {
        try {
            aceptar();
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

    private void aceptar() throws ValorRequeridoException, PreguntaException {
        String enunciado = taEnunciado.getText().trim();
        String tokens = taToken.getText().trim();

        if (enunciado.isEmpty()) throw new ValorRequeridoException("El valor enunciado es requerido");
        if (tokens.isEmpty()) throw new ValorRequeridoException("El valor tokens es requerido");

        Abierta preguntaAbierta = new Abierta(enunciado, new ArrayList<>(Arrays.asList(tokens.split(" "))));

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
            preguntaAbierta.setValorPregunta(valor);
        }

        guardarPregunta(preguntaAbierta);
        btnRegresar.fire();
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
