package com.example.alcanzandonota.controller;

import com.example.alcanzandonota.application.AppInstitucion;
import com.example.alcanzandonota.exceptions.ValorRequeridoException;
import com.example.alcanzandonota.model.Abierta;
import com.example.alcanzandonota.model.Estudiante;
import com.example.alcanzandonota.model.Formulario;
import com.example.alcanzandonota.model.Pregunta;
import com.example.alcanzandonota.util.MensajeUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.util.List;

public class ResponderPreguntaAbiertaController {

    @FXML
    private Button btnSiguiente;

    @FXML
    private Label lblNumeroPregunta;

    @FXML
    private TextArea taEnunciado;

    @FXML
    private TextArea taRespuesta;

    @FXML
    void siguienteAction(ActionEvent event) {
        try {
            siguiente();
        } catch (ValorRequeridoException e) {
            MensajeUtil.mensajeAlerta(e.getMessage());
        }
    }

    /**
     * INSTANCIAS
     */
    AppInstitucion appInstitucion;
    Estudiante estudiante;
    Formulario formulario;
    Pregunta pregunta;
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

    public void setFormulario(Formulario formulario) {
        this.formulario = formulario;
    }

    public void setPregunta(Pregunta pregunta, int numeroPregunta) {
        this.pregunta = pregunta;
        lblNumeroPregunta.setText("Pregunta " + numeroPregunta);
        taEnunciado.setText(pregunta.getPregunta());
    }

    private void guardarRespuesta() throws ValorRequeridoException {
        Stage stage = (Stage) btnSiguiente.getScene().getWindow();
        List<String> list = (List<String>) ((List<?>) stage.getUserData()).get(1);

        String respuesta = taRespuesta.getText();
        if (respuesta.isEmpty()) throw new ValorRequeridoException("Es necesario que escriba una respuesta");
        list.add(respuesta);
    }

    private void siguiente() throws ValorRequeridoException {
        guardarRespuesta();
        verificarPregunta();
    }

    private void verificarPregunta() {
        Stage stage = (Stage) btnSiguiente.getScene().getWindow();
        int numeroPregunta = (int) ((List<?>) stage.getUserData()).get(0);
        ((List<Integer>) stage.getUserData()).set(0, numeroPregunta + 1);

        List<Pregunta> preguntas = formulario.getListaPreguntas();
        if (numeroPregunta >= preguntas.size()) {
            guardarRespuestasPrueba();
            appInstitucion.mostrarResultadosFormulario(estudiante, formulario);
            return;
        }
        Pregunta pregunta = preguntas.get(numeroPregunta);
        if (pregunta instanceof Abierta) {
            appInstitucion.mostrarResponderPreguntaAbierta(estudiante, formulario, pregunta, numeroPregunta + 1);
        } else {
            appInstitucion.mostrarResponderPreguntaSeleccionMultiple(estudiante, formulario, pregunta, numeroPregunta + 1);
        }
    }

    private void guardarRespuestasPrueba() {
        Stage stage = (Stage) btnSiguiente.getScene().getWindow();
        List<String> list = (List<String>) ((List<?>) stage.getUserData()).get(1);

        formulario.getListaRespuestas().put(estudiante, list);
        estudiante.getListaFormularios().add(formulario);
        formulario.getListaEstudiante().add(estudiante);
        modelFactoryController.guardarResourceSerializable();
        modelFactoryController.guardarResourceXML();
    }

}
