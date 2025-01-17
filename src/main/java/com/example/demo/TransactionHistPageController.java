package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class TransactionHistPageController implements Initializable {

    @FXML
    private TextField myAmount;

    @FXML
    private TextField myBalance;

    @FXML
    private TextField myDate;

    @FXML
    private TextField myTime;

    @FXML
    private ComboBox<?> myType;

    @FXML
    private AnchorPane trasacPage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("TransactionHistPage");
    }
}
