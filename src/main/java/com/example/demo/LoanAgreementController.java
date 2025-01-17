
package com.example.demo;

import com.bankmanagement.models.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

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

    LoanType type;

    @FXML
    TableColumn<Loan, Integer> idCol;

    @FXML
    TableColumn<Loan, LoanType> typeCol;

    @FXML
    TableColumn<Loan, Double> amtCol;

    @FXML
    TableColumn<Loan, Double> rateCol;

    @FXML
    TableColumn<Loan, Long> dateCol;

    @FXML
    TableColumn<Loan, Integer> durationCol;

    @FXML
    TableColumn<Loan, LoanStatus> statusCol;

    @FXML
    TableColumn<Loan, Double> emiCol;

    @FXML
    TableView<Loan> loanTable;

    @FXML
    public void initialize() {
        // Check if applyLoanButton is null
        RegularUser user = (RegularUser) User.currentUser;
        idCol = new TableColumn<>("Loan ID");
        typeCol = new TableColumn<>("Loan Type");
        amtCol = new TableColumn<>("Loan Amount ($)");
        rateCol = new TableColumn<>("Loan rete (%)");
        dateCol = new TableColumn<>("Start Date");
        durationCol = new TableColumn<>("Duration (months)");
        statusCol = new TableColumn<>("Loan Status");

        idCol.setCellValueFactory(new PropertyValueFactory<>("loanId"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("loanType"));
        amtCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
        rateCol.setCellValueFactory(new PropertyValueFactory<>("interestRate"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        dateCol.setCellFactory(column -> new TableCell<Loan, Long>() {
            @Override
            protected void updateItem(Long date, boolean empty) {
                super.updateItem(date, empty);
                if (empty || date == null) {
                    setText(null);
                } else {
                    // Format the date as you want it to appear
                    setText(LocalDateTime.ofEpochSecond(date, 0, ZoneOffset.UTC).format(DateTimeFormatter.ISO_ORDINAL_DATE));
                }
            }
        });
        durationCol.setCellValueFactory(new PropertyValueFactory<>("duration"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

        loanTable.getColumns().addAll(idCol, typeCol, amtCol, rateCol, dateCol, durationCol, statusCol);

        ObservableList<Loan> loans = FXCollections.observableArrayList();
        loans.setAll(user.getLoans());
        loanTable.setItems(loans);
    }

    @FXML
    Text loanTypeLabel;

    @FXML
    VBox loanDetailsForm;

    @FXML
    private TextField loanAmountInput;

    @FXML
    private TextField durationInput;

    @FXML
    private Text emiInput;

    @FXML
    private Text totalInput;

    Double rate;


    @FXML
    void applyCarLoan(ActionEvent event) {
        loanDetailsForm.setVisible(true);
        loanTypeLabel.setText("Car loan");
        type = LoanType.CAR;
        rate = 0.05;
    }

    @FXML
    void applyHomeLoan(ActionEvent event) {
        loanDetailsForm.setVisible(true);
        loanTypeLabel.setText("Home loan");
        type = LoanType.HOME;
        rate = 0.08;
    }

    @FXML
    void applyPersoLoan(ActionEvent event) {
        loanDetailsForm.setVisible(true);
        loanTypeLabel.setText("Personal loan");
        type = LoanType.PERSONAL;
        rate = 0.1;
    }

    @FXML
    void backToDashboard(ActionEvent event) throws IOException {
        Stage stage = (Stage) loanTable.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboard.fxml"));
        stage.setTitle("Dashboard");
        stage.setScene(new Scene(loader.load()));
        System.out.println("Dashboard");
    }

    @FXML
    void closeForm(ActionEvent event) {
        loanDetailsForm.setVisible(false);
    }
    Double P;
    Double r;
    Integer n;
    Double emi;
    RegularUser user = (RegularUser) User.currentUser;

    @FXML
    void calculateResult() {
        try {
            String loanAmtTxt = loanAmountInput.getText();
            String durationTxt = durationInput.getText();

             P = Double.parseDouble(loanAmtTxt);
             r = rate;
             n = Integer.parseInt(durationTxt);
             emi = ((P*r) * Math.pow(1+r, n)) / ( Math.pow(1+r, n) - 1);
            emiInput.setText("$"+ String.format("%.5f", emi));
            totalInput.setText("$"+String.format("%.5f", emi * n));
        }catch (Exception e) {
            throw e;
        }
    }

    @FXML
    void confirmLoan(ActionEvent event) {
        try{
            calculateResult();
            user.getLoans().add(Bank.bank.addLoan(type, P, r*100, LocalDateTime.now().toEpochSecond(ZoneOffset.UTC), n, emi));
            Bank.bank.saveToFile();
        }
        catch (Exception e) {
            loanTypeLabel.setText(e.getMessage());
        }
    }
}
