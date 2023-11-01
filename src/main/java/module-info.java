module com.example.alcanzandonota {
    requires javafx.controls;
    requires javafx.fxml;


    exports com.example.alcanzandonota.application;
    opens com.example.alcanzandonota.application to javafx.fxml;
    opens com.example.alcanzandonota.controller to javafx.fxml;
}