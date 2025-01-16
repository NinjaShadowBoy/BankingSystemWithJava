package com.example.ooppage10;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) {
        // Load the main application stage (optional)
//        stage.setTitle("Logout Confirmation");
//        stage.show();

        // Show the logout confirmation dialog
        showLogoutConfirmationDialog(stage);
    }

    private void showLogoutConfirmationDialog(Stage owner) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Logout-Page.fxml"));
            Stage dialogStage = new Stage();
            dialogStage.initOwner(owner);
            dialogStage.initModality(Modality.APPLICATION_MODAL);

            Scene scene = new Scene(fxmlLoader.load());
            dialogStage.setScene(scene);
            dialogStage.setTitle("Logout Confirmation");
            dialogStage.show();

        } catch (IOException e) {
            e.printStackTrace(); // Log the error and handle it appropriately
        }
    }

    public static void main(String[] args) {
        launch();
    }
}