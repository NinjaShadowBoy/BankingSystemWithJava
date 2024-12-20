package com.example.demo;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class RegistrationController {

    @FXML
    private TextField conpass;

    @FXML
    private DatePicker dob;

    @FXML
    private TextField email;

    @FXML
    private TextField fullName;

    @FXML
    private TextField pass;

    @FXML
    private TextField phone;

    @FXML
    private Button submit;

    @FXML
    private TextField username;

    @FXML
    void checkInformationForSubmit() {
        System.out.println(conpass.getText());
    }

}
