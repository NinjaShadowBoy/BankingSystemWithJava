package com.example.demo;


import com.bankmanagement.models.Bank;
import com.bankmanagement.models.RegularUser;
import com.bankmanagement.models.User;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class LoginController {

    @FXML
    private Text formError;

    @FXML
    private PasswordField pass;

    @FXML
    private TextField passtext;

    @FXML
    private JFXToggleButton showpass;

    @FXML
    private JFXButton submit;

    @FXML
    private TextField username;

    @FXML
    private JFXButton signUpButton;

    @FXML
    void togglePassword(ActionEvent event) {
        if(pass.isVisible()){
            pass.setVisible(false);
        }else {
            pass.setText(passtext.getText());
            pass.setVisible(true);
        }
        if(passtext.isVisible()){
            passtext.setVisible(false);
        }else {
            passtext.setText(pass.getText());
            passtext.setVisible(true);
        }
    }

    @FXML
    void signUp(ActionEvent event) throws IOException {
        Stage stage = (Stage) signUpButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("registration.fxml"));
        stage.setTitle("Create an account");
        stage.setScene(new Scene(loader.load()));
        System.out.println("Sign Up");
    }

    @FXML
    void loginUser(ActionEvent event) throws IOException {
        Bank bank = Bank.bank;
        String usernameText = username.getText();
        if(usernameText.isEmpty()){
            formError.setText("Please enter a username");
            formError.setVisible(true);
            return;
        }
        String passwordText;
        if (passtext.isVisible()) {
            passwordText = passtext.getText();
        } else {
            passwordText = pass.getText();
        }
        if (passwordText.isEmpty()) {
            formError.setText("Please enter a password");
            formError.setVisible(true);
            return;
        }
        User user = bank.getUser(usernameText);
        if (user == null) {
            formError.setText("Invalid username or password");
            formError.setVisible(true);
        }else {
            if(Objects.equals(user.getPassword(), passwordText)){
                User.currentUser = user;
                Stage stage = (Stage) signUpButton.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboard.fxml"));
                stage.setTitle("Dashboard");
                stage.setScene(new Scene(loader.load()));
                System.out.println("Dashboard");
            }else{
                formError.setText("Invalid username or password");
                formError.setVisible(true);
            }
        }
    }
}
