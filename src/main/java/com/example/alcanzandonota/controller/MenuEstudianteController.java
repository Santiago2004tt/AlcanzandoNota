package com.example.alcanzandonota.controller;

import com.example.alcanzandonota.application.AppInstitucion;
import com.example.alcanzandonota.model.Estudiante;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class MenuEstudianteController {

    @FXML
    private Button btnHistorial;

    @FXML
    private Button btnRealizarPrueba;

    @FXML
    private Button btnSalir;

    @FXML
    private Label lblApellido;

    @FXML
    private Label lblEdad;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblIdentificacion;

    @FXML
    private Label lblInstitucion;

    @FXML
    private Label lblNombre;

    @FXML
    private Label lblTelefono;

    @FXML
    private Label lblTipoIdentificacion;

    @FXML
    void historialAction(ActionEvent event) {
        appInstitucion.mostrarHistorialEstudiante(estudiante);
    }

    @FXML
    void realizarPruebaAction(ActionEvent event) {
        appInstitucion.mostrarSolucionarFormulario(estudiante);
    }

    @FXML
    void salirAction(ActionEvent event) {
        appInstitucion.mostrarLoginEstudiante();
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
        cargarDatosEstudiante();
    }

    private void cargarDatosEstudiante() {
        lblNombre.setText("Nombre: " + estudiante.getNombre());
        lblApellido.setText("Apellido: " + estudiante.getApellido());
        lblEmail.setText("Email: " + estudiante.getEmail());
        lblTelefono.setText("Telefono: " + estudiante.getTelefono());
        lblIdentificacion.setText("Identificacion: " + estudiante.getNumeroIdentificacion());
        lblTipoIdentificacion.setText("Tipo Identificacion: " + estudiante.getTipoDocumento());
        lblEdad.setText("Edad: " + estudiante.getEdad());
        lblInstitucion.setText("Institucion: " + estudiante.getInstitucion());
    }


}
