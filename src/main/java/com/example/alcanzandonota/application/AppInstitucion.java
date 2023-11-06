package com.example.alcanzandonota.application;

import com.example.alcanzandonota.controller.*;

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import com.example.alcanzandonota.exceptions.ProfesorException;
import com.example.alcanzandonota.model.Estudiante;
import com.example.alcanzandonota.model.Formulario;
import com.example.alcanzandonota.model.Pregunta;
import com.example.alcanzandonota.model.Profesor;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AppInstitucion extends Application {

    Stage stage;
    @Override
    public void start(Stage stage) throws BackingStoreException {
        this.stage = stage;
        Preferences preferences = Preferences.userNodeForPackage(AppInstitucion.class);
        String[] llaves = preferences.keys();
        if (llaves.length != 0) {
            String value = preferences.get(llaves[0], null);
            try {
                Profesor profesor = ModelFactoryController.getInstance().verificarProfesor(llaves[0], value);
                mostrarMenuProfesor(profesor);
            } catch (ProfesorException e) {
                mostrarLobby();
            }
        } else {
            mostrarLobby();
        }
    }

    public static void main(String[] args) {
        launch();
    }

    public void mostrarCrearPreguntaAbierta(Profesor profesor){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(AppInstitucion.class.getResource("/views/crear-pregunta-abierta.fxml"));
            AnchorPane rootLayout = loader.load();
            CrearPreguntaAbiertaController controller = loader.getController();//Obtenemos el controlador
            controller.setAppInstitucion(this);
            controller.setProfesor(profesor);
            Scene scene = new Scene(rootLayout);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void mostrarCrearPreguntaSeleccionMultiple(Profesor profesor){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(AppInstitucion.class.getResource("/views/crear-pregunta-seleccion-multiple.fxml"));
            AnchorPane rootLayout = loader.load();
            CrearPreguntaSeleccionMultipleController controller = loader.getController();//Obtenemos el controlador
            controller.setAppInstitucion(this);
            controller.setProfesor(profesor);
            Scene scene = new Scene(rootLayout);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void mostrarCrearPreguntas(Profesor profesor){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(AppInstitucion.class.getResource("/views/crear-preguntas.fxml"));
            AnchorPane rootLayout = loader.load();
            CrearPreguntasController controller = loader.getController();//Obtenemos el controlador
            controller.setAppInstitucion(this);
            controller.setProfesor(profesor);
            Scene scene = new Scene(rootLayout);
            stage.setScene(scene);
            controller.getPreguntasData();
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void mostrarHistorialEstudiante(Estudiante estudiante){
        try {
            stage.close();
            stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(AppInstitucion.class.getResource("/views/historial-estudiante.fxml"));
            AnchorPane rootLayout = loader.load();
            HistorialEstudianteController controller = loader.getController();//Obtenemos el controlador
            controller.setAppInstitucion(this);
            controller.setEstudiante(estudiante);
            Scene scene = new Scene(rootLayout);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void mostrarHistorialPruebas(Profesor profesor){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(AppInstitucion.class.getResource("/views/historial-pruebas.fxml"));
            AnchorPane rootLayout = loader.load();
            HistorialPruebasController controller = loader.getController();//Obtenemos el controlador
            controller.setAppInstitucion(this);
            controller.setProfesor(profesor);
            Scene scene = new Scene(rootLayout);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void mostrarLobby(){
        try {
            stage.close();
            stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(AppInstitucion.class.getResource("/views/lobby.fxml"));
            AnchorPane rootLayout = loader.load();
            LobbyController controller = loader.getController();//Obtenemos el controlador
            controller.setAppInstitucion(this);
            Scene scene = new Scene(rootLayout);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void mostrarLoginEstudiante(){
        try {
            stage.close();
            stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(AppInstitucion.class.getResource("/views/login-estudiante.fxml"));
            AnchorPane rootLayout = loader.load();
            LoginEstudianteController controller = loader.getController();//Obtenemos el controlador
            controller.setAppInstitucion(this);
            Scene scene = new Scene(rootLayout);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void mostrarLoginMaestro(){
        try {
            stage.close();
            stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(AppInstitucion.class.getResource("/views/login-maestro.fxml"));
            AnchorPane rootLayout = loader.load();
            LoginMaestroController controller = loader.getController();//Obtenemos el controlador
            controller.setAppInstitucion(this);
            Scene scene = new Scene(rootLayout);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void mostrarMenuEstudiante(Estudiante estudiante){
        try {
            stage.close();
            stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(AppInstitucion.class.getResource("/views/menu-estudiante.fxml"));
            AnchorPane rootLayout = loader.load();
            MenuEstudianteController controller = loader.getController();//Obtenemos el controlador
            controller.setAppInstitucion(this);
            controller.setEstudiante(estudiante);
            Scene scene = new Scene(rootLayout);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void mostrarMenuProfesor(Profesor profesor){
        try {
            stage.close();
            stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(AppInstitucion.class.getResource("/views/menu-profesor.fxml"));
            AnchorPane rootLayout = loader.load();
            MenuProfesorController controller = loader.getController();//Obtenemos el controlador
            controller.setAppInstitucion(this);
            controller.setProfesor(profesor);
            Scene scene = new Scene(rootLayout);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void mostrarRegistroEstudiante(Profesor profesor){
        try {
            stage.close();
            stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(AppInstitucion.class.getResource("/views/registro-estudiante.fxml"));
            AnchorPane rootLayout = loader.load();
            RegistroEstudianteController controller = loader.getController();//Obtenemos el controlador
            controller.setAppInstitucion(this);
            controller.setProfesor(profesor);
            Scene scene = new Scene(rootLayout);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void mostrarRegistroMaestro(Profesor profesor){
        try {
            stage.close();
            stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(AppInstitucion.class.getResource("/views/registro-maestro.fxml"));
            AnchorPane rootLayout = loader.load();
            RegistroMaestroController controller = loader.getController();//Obtenemos el controlador
            controller.setAppInstitucion(this);
            controller.setProfesor(profesor);
            Scene scene = new Scene(rootLayout);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void mostrarRegistroPrueba(Profesor profesor){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(AppInstitucion.class.getResource("/views/crear-prueba.fxml"));
            AnchorPane rootLayout = loader.load();
            CrearPruebaController controller = loader.getController();//Obtenemos el controlador
            controller.setAppInstitucion(this);
            controller.setProfesor(profesor);
            Scene scene = new Scene(rootLayout);
            stage.setScene(scene);
            controller.recuperarDatos();
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void mostrarResponderPreguntaAbierta(Estudiante estudiante, Formulario formulario, Pregunta pregunta, int numeroPregunta){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(AppInstitucion.class.getResource("/views/responder-pregunta-abierta.fxml"));
            AnchorPane rootLayout = loader.load();
            ResponderPreguntaAbiertaController controller = loader.getController();//Obtenemos el controlador
            controller.setAppInstitucion(this);
            controller.setEstudiante(estudiante);
            controller.setFormulario(formulario);
            controller.setPregunta(pregunta, numeroPregunta);
            Scene scene = new Scene(rootLayout);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void mostrarResultadosFormulario(Estudiante estudiante, Formulario formulario){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(AppInstitucion.class.getResource("/views/resultados-formulario.fxml"));
            AnchorPane rootLayout = loader.load();
            ResultadosFormularioController controller = loader.getController();//Obtenemos el controlador
            controller.setAppInstitucion(this);
            controller.setEstudiante(estudiante);
            controller.setFormulario(formulario);
            Scene scene = new Scene(rootLayout);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void mostrarResponderPreguntaSeleccionMultiple(Estudiante estudiante, Formulario formulario, Pregunta pregunta, int numeroPregunta){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(AppInstitucion.class.getResource("/views/responder-pregunta-seleccion-multiple.fxml"));
            AnchorPane rootLayout = loader.load();
            ResponderPreguntaSeleccionMultiple controller = loader.getController();//Obtenemos el controlador
            controller.setAppInstitucion(this);
            controller.setEstudiante(estudiante);
            controller.setFormulario(formulario);
            controller.setPregunta(pregunta, numeroPregunta);
            Scene scene = new Scene(rootLayout);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void mostrarSolucionarFormulario(Estudiante estudiante){
        try {
            stage.close();
            stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(AppInstitucion.class.getResource("/views/solucionar-formulario.fxml"));
            AnchorPane rootLayout = loader.load();
            SolucionarFormularioController controller = loader.getController();//Obtenemos el controlador
            controller.setAppInstitucion(this);
            controller.setEstudiante(estudiante);
            Scene scene = new Scene(rootLayout);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void stop() throws BackingStoreException {
        // Guarda la información de sesión al cerrar la aplicación
//        Preferences preferences = Preferences.userNodeForPackage(AppInstitucion.class);
//        preferences.put("usuario", "nombre_de_usuario");
    }
}