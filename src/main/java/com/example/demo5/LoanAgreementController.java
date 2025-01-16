
package com.example.demo5;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class LoanAgreementController {

    @FXML
    private Label EMILabel;

    @FXML
    private Label amountLabel;

    @FXML
    private Label amountLeftLabel;

    @FXML
    private Label amountPaidLabel;


    @FXML
    private Button applyLoanCarButton;

    @FXML
    private Button applyLoanHomeButton;

    @FXML
    private Button applyLoanPersonButton;

    @FXML
    private Button cancelBtn;

    @FXML
    private Button confirmBtn;

    @FXML
    private Button applyLoanButton;

    @FXML
    private Button backButton;

    @FXML
    private Label interestRateLabel;

    @FXML
    private Label loanAmountLabel;

    @FXML
    private Label totalLabel;

    @FXML
    private VBox loanBoxFill;


    @FXML
    private AnchorPane rootpane;

    @FXML
    private Label tenureLabel;


    @FXML
    public void initialize() {
        // Check if applyLoanButton is null
        if (applyLoanButton == null) {
            System.err.println("applyLoanButton is null. Check your FXML file for fx:id.");
        } else {
            // Set up the button click event
            applyLoanButton.setOnMouseClicked(_ -> {
                try {
                    VBox box = FXMLLoader.load(getClass().getResource("helloview2.fxml"));
//                    rootpane.getChildren().setAll(box);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        // Hide loanBoxFill at startup
        if (loanBoxFill != null) {
            loanBoxFill.setVisible(true);
        } else {
            System.err.println("loanBoxFill is null. Check your FXML file for fx:id.");
        }
    }

    private void showLoanBox() {
        // Make the loanBoxFill visible on button click
        if (loanBoxFill != null) {
            loanBoxFill.setVisible(true);
        } else {
            System.err.println("loanBoxFill is null. Cannot make it visible.");
        }
    }


}
