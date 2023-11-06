package com.example.alcanzandonota.util;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class MensajeUtil {

    /**
     * METODOS QUE PROPORCIONAN AYUDA A LA HORA DE MOSTRAR ALERTAS ENTRE OTROS
     * @param mensaje .
     */
    public static void mensajeInformacion(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Éxito");
        alert.setHeaderText("Éxito");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public static void mensajeAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Error");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public static boolean confirmacion(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(mensaje);
        alert.setHeaderText(null);
        ButtonType resultado = alert.showAndWait().orElse(ButtonType.CANCEL);
        return resultado == ButtonType.OK;
    }
}
