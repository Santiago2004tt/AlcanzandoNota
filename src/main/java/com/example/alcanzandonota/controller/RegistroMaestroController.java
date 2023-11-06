package com.example.alcanzandonota.controller;

import com.example.alcanzandonota.application.AppInstitucion;
import com.example.alcanzandonota.exceptions.ProfesorException;
import com.example.alcanzandonota.exceptions.ValorRequeridoException;
import com.example.alcanzandonota.model.Profesor;
import com.example.alcanzandonota.util.MensajeUtil;
import com.example.alcanzandonota.util.TextFormatterUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class RegistroMaestroController {

    @FXML
    private Button btnCrear;

    @FXML
    private Button btnRegresar;

    @FXML
    private PasswordField pfContrasena;

    @FXML
    private PasswordField pfVerificarContrasena;

    @FXML
    private TextField tfAsignatura;

    @FXML
    private TextField tfApellidos;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfIdentificacion;

    @FXML
    private TextField tfInstitucion;

    @FXML
    private TextField tfNombre;

    @FXML
    void apellidosReleased(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER)
            tfIdentificacion.requestFocus();
    }

    @FXML
    void asignaturaReleased(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER)
            tfEmail.requestFocus();
    }

    @FXML
    void contrasenaReleased(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER)
            pfVerificarContrasena.requestFocus();
    }

    @FXML
    void crearAction(ActionEvent event) {
        try {
            crear();
            MensajeUtil.mensajeInformacion("El maestro fue creado exitosamente");
            appInstitucion.mostrarMenuProfesor(profesor);
        } catch (ValorRequeridoException | ProfesorException e) {
            MensajeUtil.mensajeAlerta(e.getMessage());
        }
    }

    @FXML
    void emailReleased(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER)
            pfContrasena.requestFocus();
    }

    @FXML
    void identificacionReleased(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER)
            tfInstitucion.requestFocus();
    }

    @FXML
    void institucionReleased(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER)
            tfAsignatura.requestFocus();
    }

    @FXML
    void nombreReleased(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER)
            tfApellidos.requestFocus();
    }

    @FXML
    void regresarAction(ActionEvent event) {
        appInstitucion.mostrarMenuProfesor(profesor);
    }

    @FXML
    void verificarContrasenaReleased(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER)
            btnCrear.fire();
    }

    /**
     * INSTANCIAS
     */
    private AppInstitucion appInstitucion;
    private Profesor profesor;
    private ModelFactoryController modelFactoryController = ModelFactoryController.getInstance();

    /**
     * GETTERS AND SETTERS
     */
    public void setAppInstitucion(AppInstitucion appInstitucion) {
        this.appInstitucion = appInstitucion;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    private void crear() throws ValorRequeridoException, ProfesorException {
        String nombre = tfNombre.getText().trim();
        String apellidos = tfApellidos.getText().trim();
        String identificacion = tfIdentificacion.getText().trim();
        String asignatura = tfAsignatura.getText().trim();
        String institucion = tfInstitucion.getText().trim();
        String email = tfEmail.getText().trim();
        String contrasena = pfContrasena.getText().trim();
        String verificarContrasena = pfVerificarContrasena.getText().trim();

        if (nombre.isEmpty()) throw new ValorRequeridoException("El valor nombre es requerido");
        if (apellidos.isEmpty()) throw new ValorRequeridoException("El valor apellidos es requerido");
        if (identificacion.isEmpty()) throw new ValorRequeridoException("El valor identificacion es requerido");
        if (asignatura.isEmpty()) throw new ValorRequeridoException("El valor asignatura es requerido");
        if (email.isEmpty()) throw new ValorRequeridoException("El valor email es requerido");
        if (contrasena.isEmpty()) throw new ValorRequeridoException("El valor contraseña es requerido");
        if (verificarContrasena.isEmpty()) throw new ValorRequeridoException("El valor verificar contraseña es requerido");
        if (!contrasena.equals(verificarContrasena)) throw new ProfesorException("Las contraseñas no coinciden");

        modelFactoryController.crearProfesor(nombre, apellidos, identificacion, asignatura, institucion, contrasena);
        modelFactoryController.guardarResourceSerializable();
        modelFactoryController.guardarResourceXML();
    }

    @FXML
    void initialize() {
        tfIdentificacion.setTextFormatter(new TextFormatter<>(TextFormatterUtil::integerFormat));
    }

}
