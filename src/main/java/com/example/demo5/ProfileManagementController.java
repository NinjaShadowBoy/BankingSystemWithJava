package com.example.demo5;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ProfileManagementController {

    @FXML
    private Button backButton;

    @FXML
    private TextField cniField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private PasswordField newPasswordField;

    @FXML
    private PasswordField oldPasswordField;

    @FXML
    private TextField phoneField;

    @FXML
    private Button saveButton;

    @FXML
    public void initialize() {
        // Load current user details
        loadUserProfile();

        // Set up button actions
        saveButton.setOnAction(event -> saveChanges());
        backButton.setOnAction(event -> goBackToDashboard());
    }

    private void loadUserProfile() {
        // Load user data from a data source and set the fields
        firstNameField.setText("John"); // Example data
        lastNameField.setText("Doe");
        cniField.setText("CNI123456");
        emailField.setText("john.doe@example.com");
        phoneField.setText("123-456-7890");
    }

    private void saveChanges() {
        // Logic to save changes (e.g., update user details in a database)
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String cni = cniField.getText();
        String email = emailField.getText();
        String phone = phoneField.getText();

        String oldPassword = oldPasswordField.getText();
        String newPassword = newPasswordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        // Perform validation
        if (!newPassword.equals(confirmPassword)) {
            System.out.println("New passwords do not match.");
            return;
        }

        // Perform the update logic (update user information and handle password change)
        System.out.println("Changes saved: " + firstName + ", " + lastName + ", " + cni + ", " + email + ", " + phone);
        System.out.println("Password updated.");
    }

    private void goBackToDashboard() {
        // Logic to navigate back to the dashboard
        System.out.println("Navigating back to dashboard.");
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close(); // Close the profile management window
    }

}
