package com.example.alcanzandonota.controller;

import com.example.alcanzandonota.application.AppInstitucion;
import com.example.alcanzandonota.exceptions.FormularioException;
import com.example.alcanzandonota.exceptions.ValorRequeridoException;
import com.example.alcanzandonota.model.Abierta;
import com.example.alcanzandonota.model.Estudiante;
import com.example.alcanzandonota.model.Formulario;
import com.example.alcanzandonota.model.Pregunta;
import com.example.alcanzandonota.util.MensajeUtil;
import com.example.alcanzandonota.util.TextFormatterUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class SolucionarFormularioController {

    @FXML
    private Button btnAceptar;

    @FXML
    private Button btnRegresar;

    @FXML
    private TextField tfCodigoPrueba;

    @FXML
    void aceptarAction(ActionEvent event) {
        try {
            aceptar();
        } catch (ValorRequeridoException | FormularioException e) {
            MensajeUtil.mensajeAlerta(e.getMessage());
        }
    }

    @FXML
    void codigoPruebaReleased(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER)
            btnAceptar.fire();
    }

    @FXML
    void regresarAction(ActionEvent event) {
        appInstitucion.mostrarMenuEstudiante(estudiante);
    }

    /**
     * INSTANCIAS
     */
    AppInstitucion appInstitucion;
    Estudiante estudiante;
    ModelFactoryController modelFactoryController = ModelFactoryController.getInstance();

    /**
     * GETTERS AND SETTERS
     */
    public void setAppInstitucion(AppInstitucion appInstitucion) {
        this.appInstitucion = appInstitucion;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    private void aceptar() throws ValorRequeridoException, FormularioException {
        String codigo = tfCodigoPrueba.getText();

        if (codigo.isEmpty()) throw new ValorRequeridoException("El valor codigo es requerido");

        Formulario formulario = modelFactoryController.verificarCodifoFormulario(codigo, estudiante);

        if (MensajeUtil.confirmacion("Desea realizar ya el formulario")) {
            Stage sata = (Stage) btnRegresar.getScene().getWindow();
            sata.setUserData(new ArrayList<>(List.of(0, new ArrayList<>())));
            verificarPregunta(formulario, 0);
        }
    }

    private void verificarPregunta(Formulario formulario, int numeroPregunta) {
        Stage stage = (Stage) btnRegresar.getScene().getWindow();
        ((List<Integer>) stage.getUserData()).set(0, numeroPregunta + 1);

        List<Pregunta> preguntas = formulario.getListaPreguntas();
        if (numeroPregunta >= preguntas.size()) {
            return;
        }
        Pregunta pregunta = preguntas.get(numeroPregunta);
        if (pregunta instanceof Abierta) {
            appInstitucion.mostrarResponderPreguntaAbierta(estudiante, formulario, pregunta, numeroPregunta + 1);
        } else {
            appInstitucion.mostrarResponderPreguntaSeleccionMultiple(estudiante, formulario, pregunta, numeroPregunta + 1);
        }
    }

    @FXML
    void initialize() {
        tfCodigoPrueba.setTextFormatter(new TextFormatter<>(TextFormatterUtil::integerFormat));
    }
}
