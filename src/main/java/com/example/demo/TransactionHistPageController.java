package com.example.demo;

import com.bankmanagement.models.RegularUser;
import com.bankmanagement.models.Transaction;
import com.bankmanagement.models.TransactionType;
import com.bankmanagement.models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class TransactionHistPageController implements Initializable {

    RegularUser user;
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

    @FXML
    private TableView<Transaction> transactionTable;

    @FXML
    private TableColumn<Transaction, Integer> accIdCol;

    @FXML
    private TableColumn<Transaction, Double> amtCol;

    @FXML
    private TableColumn<Transaction, Long> dateCol;

    @FXML
    private TableColumn<Transaction, TransactionType> typeCol;

    @FXML
    public void backToDashboard() throws IOException {
        Stage stage = (Stage) transactionTable.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboard.fxml"));
        stage.setTitle("Dashboard");
        stage.setScene(new Scene(loader.load()));
        System.out.println("Dashboard");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("TransactionHistPage");
        user = (RegularUser) User.currentUser;
        // Create columns
        accIdCol = new TableColumn<>("Account ID");
        accIdCol.setCellValueFactory(new PropertyValueFactory<>("accountId"));
        // Optional: If you want to format how the date appears

        amtCol = new TableColumn<>("Amount");
        amtCol.setCellValueFactory(new PropertyValueFactory<>("amount"));

        typeCol = new TableColumn<>("Type");
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));

        dateCol = new TableColumn<>("Date");
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        dateCol.setCellFactory(column -> new TableCell<Transaction, Long>() {
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

        // Add columns to table
        transactionTable.getColumns().addAll(dateCol, accIdCol, amtCol, typeCol);

        // Create and add sample data (replace with your actual data)
        ObservableList<Transaction> transactions = FXCollections.observableArrayList(
                // Add your transaction objects here
        );
        transactions.setAll(user.getTransactions());

        transactionTable.setItems(transactions);
    }
}
