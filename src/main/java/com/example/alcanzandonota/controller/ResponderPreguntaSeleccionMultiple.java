package com.example.alcanzandonota.controller;

import com.example.alcanzandonota.application.AppInstitucion;
import com.example.alcanzandonota.exceptions.ValorRequeridoException;
import com.example.alcanzandonota.model.*;
import com.example.alcanzandonota.util.MensajeUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class ResponderPreguntaSeleccionMultiple {

    @FXML
    private Button btnSiguiente;

    @FXML
    private Label lblNumeroPregunta;

    @FXML
    private TextArea taEnunciado;

    @FXML
    private VBox vbOpciones;

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
        cargarOpciones();
    }

    /**
     *  CARGA LAS OPCIONES QUE TENGA LA PREGUNTA EN LA INTERFAZ
     */
    private void cargarOpciones() {
        SeleccionMultiple seleccionMultiple = (SeleccionMultiple) pregunta;
        ToggleGroup toggleGroup = new ToggleGroup();

        for (Respuesta respuesta : seleccionMultiple.getListaOpciones()) {
            RadioButton radioButton = new RadioButton(respuesta.getRespuesta());
            radioButton.setToggleGroup(toggleGroup);
            vbOpciones.getChildren().add(radioButton);
        }
    }

    private void guardarRespuesta() throws ValorRequeridoException {
        Stage stage = (Stage) btnSiguiente.getScene().getWindow();
        List<String> list = (List<String>) ((List<?>) stage.getUserData()).get(1);

        Toggle respuesta = ((RadioButton) vbOpciones.getChildren().get(0)).getToggleGroup().getSelectedToggle();
        if (respuesta == null) throw new ValorRequeridoException("Es necesario que escoja una opcion de las respuestas");
        String respuestaString = ((RadioButton) respuesta).getText();
        list.add(respuestaString);
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
