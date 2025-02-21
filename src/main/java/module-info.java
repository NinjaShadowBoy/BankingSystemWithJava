module com.example.demo {
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.jfoenix;
    requires com.google.gson; // Ensure Gson is required
    opens com.bankmanagement.models to com.google.gson, javafx.base, javafx.controls, javafx.fxml, javafx.web;

    opens com.example.demo to javafx.fxml,  com.google.gson, javafx.base, javafx.controls, javafx.web;
    exports com.example.demo;
}