package com.example.alcanzandonota.controller;

import com.example.alcanzandonota.application.AppInstitucion;
import com.example.alcanzandonota.exceptions.PreguntaException;
import com.example.alcanzandonota.exceptions.ValorRequeridoException;
import com.example.alcanzandonota.model.Formulario;
import com.example.alcanzandonota.model.Pregunta;
import com.example.alcanzandonota.model.Profesor;
import com.example.alcanzandonota.util.MensajeUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;


public class CrearPreguntasController {

    @FXML
    private Button btnAbierta;

    @FXML
    private Button btnCrearFormulario;

    @FXML
    private Button btnRegresar;

    @FXML
    private Button btnSeleccionMultiple;

    @FXML
    private TableColumn<String, String> colPreguntas;

    @FXML
    private Label lblCantidadPreguntas;

    @FXML
    private TableView<Pregunta> tblPreguntas;

    @FXML
    void abiertaAction(ActionEvent event) {
        appInstitucion.mostrarCrearPreguntaAbierta(profesor);
    }

    @FXML
    void crearFormularioAction(ActionEvent event) {
        try {
            crearFormulario();
        } catch (ValorRequeridoException | PreguntaException e) {
            MensajeUtil.mensajeAlerta(e.getMessage());
        }
    }

    @FXML
    void regresarAction(ActionEvent event) {
        if (MensajeUtil.confirmacion("Desea regresar, si lo hace perdera el progreso no guardado")) {
            appInstitucion.mostrarRegistroPrueba(profesor);
        }
    }

    @FXML
    void seleccionMultipleAction(ActionEvent event) {
        appInstitucion.mostrarCrearPreguntaSeleccionMultiple(profesor);
    }

    /**
     * ATRIBUTOS
     */
    AppInstitucion appInstitucion;
    Profesor profesor;
    ModelFactoryController modelFactoryController = ModelFactoryController.getInstance();
    ObservableList<Pregunta> preguntasData = FXCollections.observableArrayList();

    /**
     * GETTERS AND SETTERS
     * @param appInstitucion
     */
    public void setAppInstitucion(AppInstitucion appInstitucion) {
        this.appInstitucion = appInstitucion;
    }

    public ObservableList<Pregunta> getPreguntasData() {
        preguntasData.clear();
        if (btnRegresar.getScene() != null) {
            Stage stage = (Stage) btnRegresar.getScene().getWindow();
            List<List<Pregunta>> listObjects = (List<List<Pregunta>>) stage.getUserData();
            if (listObjects.size() != 1) {
                List<Pregunta> preguntas = (List<Pregunta>) listObjects.get(1);
                preguntasData.addAll(preguntas);
                lblCantidadPreguntas.setText("Cantidad preguntas: " + preguntas.size());
            }
        }
        tblPreguntas.setItems(preguntasData);
        return preguntasData;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
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

    private void crearFormulario() throws ValorRequeridoException, PreguntaException {
        ArrayList<Pregunta> preguntas = (ArrayList<Pregunta>) obtenerListaPreguntas();
        float valorTotal = calcularValorTotal();

        if (preguntas.isEmpty()) throw new ValorRequeridoException("Es necesario que cree al menos una pregunta");
        boolean bandera = obtenerListaPreguntas().stream().anyMatch(x -> x.getValorPregunta() == 0);
        if (valorTotal < 5 && !bandera)
            throw new PreguntaException("La la sumatoria de valores no es equivalente a 5, porfavor agregar una " +
                    "pregunta extra sin valor o con el valor faltante = " + (valorTotal - 5));

        if (bandera) {
            List<Pregunta> preguntasSinValor = preguntas.stream().filter(x -> x.getValorPregunta() == 0).toList();
            float valorIndividual = (5 - valorTotal) / preguntasSinValor.size();
            for (Pregunta pregunta : preguntasSinValor) {
                pregunta.setValorPregunta(valorIndividual);
            }
        }

        Stage stage = (Stage) btnRegresar.getScene().getWindow();
        List<String> datos = (List<String>) ((List<List<?>>) stage.getUserData()).get(0);
        Formulario formulario = modelFactoryController.crearFormulario(datos.get(4), profesor, datos.get(0), datos.get(0), preguntas);

        MensajeUtil.mensajeInformacion("El codigo del formulario es: " + formulario.getCodigo());
        modelFactoryController.guardarResourceSerializable();
        modelFactoryController.guardarResourceXML();

        appInstitucion.mostrarMenuProfesor(profesor);
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
        colPreguntas.setCellValueFactory(new PropertyValueFactory<>("pregunta"));
    }
}