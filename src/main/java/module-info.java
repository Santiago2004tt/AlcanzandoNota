module com.example.alcanzandonota {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.logging;
    requires java.prefs;


    exports com.example.alcanzandonota.application;
    opens com.example.alcanzandonota.application to javafx.fxml;
    opens com.example.alcanzandonota.controller to javafx.fxml;
    exports com.example.alcanzandonota.exceptions;
    exports com.example.alcanzandonota.model;
}