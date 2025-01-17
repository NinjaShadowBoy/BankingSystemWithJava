package com.example.demo;


import com.bankmanagement.models.Bank;
import com.bankmanagement.models.RegularUser;
import com.bankmanagement.models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.*;
import java.util.ResourceBundle;

public class RegistrationController implements Initializable {

    @FXML
    private TextField conpass;

    @FXML
    private TextField conpasstext;

    @FXML
    private TextField phone;

    @FXML
    private TextField email;

    @FXML
    private TextField fullName;

    @FXML
    private TextField pass;

    @FXML
    private TextField passtext;

    @FXML
    private Button submit;

    @FXML
    private TextField username;

    @FXML
    private Text formError;

    @FXML
    void checkInformationForSubmit() throws InterruptedException, IOException {
        Bank bank = Bank.bank;
        System.out.println(conpass.getText());

        String usernameText = username.getText();
        if (bank.getUsernameToUserIdMap().containsKey(usernameText)){
            formError.setText("Username already exists");
            formError.setVisible(true);
            return;
        }
        String userFullName = fullName.getText();
        if(userFullName.isEmpty()){
            formError.setText("Username is empty");
            formError.setVisible(true);
            return;
        }
        String phoneNumber = phone.getText();
        if(phoneNumber.isEmpty()){
            formError.setText("Phone number is empty");
            formError.setVisible(true);
            return;
        }
        String userEmail = email.getText();

        String password;
        String confirmPassword;
        if(conpass.isVisible()) {
            password = pass.getText();
            confirmPassword = conpass.getText();
        } else {
            password = passtext.getText();
            confirmPassword = conpasstext.getText();
        }
        if(!password.equals(confirmPassword)) {
            formError.setText("Passwords do not match");
            formError.setVisible(true);
            return;
        }
        bank.addregularUser(userFullName, userEmail, usernameText, password, phoneNumber);
        formError.setFill(Paint.valueOf("blue"));
        formError.setVisible(true);
        formError.setText("User successfully registered");
        bank.saveToFile();
        Thread.sleep(1000);
        Stage stage = (Stage) formError.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        stage.setTitle("Login");
        stage.setScene(new Scene(loader.load()));
        System.out.println("Login");
    }

    @FXML
    void togglePassword(ActionEvent event) {
        if(pass.isVisible()){
            pass.setVisible(false);
            conpass.setVisible(false);
        }else {
            pass.setText(passtext.getText());
            pass.setVisible(true);
            conpass.setText(conpasstext.getText());
            conpass.setVisible(true);
        }
        if(passtext.isVisible()){
            passtext.setVisible(false);
            conpasstext.setVisible(false);
        }else {
            passtext.setText(pass.getText());
            passtext.setVisible(true);
            conpasstext.setText(conpass.getText());
            conpasstext.setVisible(true);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Registration");
    }
}
