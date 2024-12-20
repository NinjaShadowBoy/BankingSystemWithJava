package com.example.demo;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

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

}
