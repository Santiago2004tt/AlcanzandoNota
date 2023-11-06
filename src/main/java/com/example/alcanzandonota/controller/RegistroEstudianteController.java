package com.example.alcanzandonota.controller;

import com.example.alcanzandonota.application.AppInstitucion;
import com.example.alcanzandonota.exceptions.EstudianteException;
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

public class RegistroEstudianteController {

    @FXML
    private Button btnCrear;

    @FXML
    private Button btnRegresar;

    @FXML
    private PasswordField pfContrasena;

    @FXML
    private PasswordField pfVerificarContrasena;

    @FXML
    private TextField tfApellidos;

    @FXML
    private TextField tfCurso;

    @FXML
    private TextField tfEdad;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfIdentificacion;

    @FXML
    private TextField tfInstitucion;

    @FXML
    private TextField tfNombre;

    @FXML
    private TextField tfTelefono;

    @FXML
    private TextField tfTipoDocumento;

    @FXML
    void apellidosReleased(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER)
            tfTipoDocumento.requestFocus();
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
            MensajeUtil.mensajeInformacion("El estudiante fue creado exitosamente");
            appInstitucion.mostrarMenuProfesor(profesor);
        } catch (ValorRequeridoException | EstudianteException e) {
            MensajeUtil.mensajeAlerta(e.getMessage());
        }
    }

    @FXML
    void cursoReleased(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER)
            tfTelefono.requestFocus();
    }

    @FXML
    void edadReleased(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER)
            tfInstitucion.requestFocus();
    }

    @FXML
    void emailReleased(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER)
            pfContrasena.requestFocus();
    }

    @FXML
    void identificacionReleased(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER)
            tfEdad.requestFocus();
    }

    @FXML
    void institucionReleased(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER)
            tfCurso.requestFocus();
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
    void telefonoReleased(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER)
            tfEmail.requestFocus();
    }

    @FXML
    void tipoDocumentoReleased(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER)
            tfIdentificacion.requestFocus();
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

    private void crear() throws ValorRequeridoException, EstudianteException {
        String nombre = tfNombre.getText();
        String apellido = tfApellidos.getText();
        String tipoDocumento = tfTipoDocumento.getText();
        String identificacion = tfIdentificacion.getText();
        String edad = tfEdad.getText();
        String institucion = tfInstitucion.getText();
        String curso = tfCurso.getText();
        String telefono = tfTelefono.getText();
        String email = tfEmail.getText();
        String contrasena = pfContrasena.getText();
        String verificarContrasena = pfContrasena.getText();

        if (nombre.isEmpty()) throw new ValorRequeridoException("El valor nombre es requerido");
        if (apellido.isEmpty()) throw new ValorRequeridoException("El valor apellido es requerido");
        if (tipoDocumento.isEmpty()) throw new ValorRequeridoException("El valor tipo documento es requerido");
        if (identificacion.isEmpty()) throw new ValorRequeridoException("El valor identificacion es requerido");
        if (edad.isEmpty()) throw new ValorRequeridoException("El valor edad es requerido");
        if (institucion.isEmpty()) throw new ValorRequeridoException("El valor institucion es requerido");
        if (curso.isEmpty()) throw new ValorRequeridoException("El valor curso es requerido");
        if (telefono.isEmpty()) throw new ValorRequeridoException("El valor telefono es requerido");
        if (email.isEmpty()) throw new ValorRequeridoException("El valor email es requerido");
        if (contrasena.isEmpty()) throw new ValorRequeridoException("El valor contraseña es requerido");
        if (verificarContrasena.isEmpty()) throw new ValorRequeridoException("El valor verificar contraseña es requerido");

        modelFactoryController.crearEstudiante(nombre, apellido, email, telefono, identificacion, tipoDocumento, edad,
                institucion, curso, true, contrasena);
        modelFactoryController.guardarResourceSerializable();
        modelFactoryController.guardarResourceXML();
    }

    @FXML
    void initialize() {
        tfIdentificacion.setTextFormatter(new TextFormatter<>(TextFormatterUtil::integerFormat));
        tfEdad.setTextFormatter(new TextFormatter<>(TextFormatterUtil::integerFormat));
        tfCurso.setTextFormatter(new TextFormatter<>(TextFormatterUtil::integerFormat));
        tfTelefono.setTextFormatter(new TextFormatter<>(TextFormatterUtil::integerFormat));
    }
}
