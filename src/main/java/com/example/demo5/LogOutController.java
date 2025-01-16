package com.example.demo5;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class LogOutController {

    @FXML
    private Button cancelButton;

    @FXML
    private Button confirmButton;

    // This method will be called when the Confirm button is clicked
    @FXML
    public void initialize() {
        confirmButton.setOnAction(event -> {
            System.out.println("User logged out.");
            Stage stage = (Stage) confirmButton.getScene().getWindow();
            stage.close();
        });

        cancelButton.setOnAction(event -> {
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.close();
        });
    }

}
