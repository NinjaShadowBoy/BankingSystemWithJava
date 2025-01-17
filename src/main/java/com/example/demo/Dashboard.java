package com.example.demo;

import com.bankmanagement.models.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class Dashboard implements Initializable {

    int accIndex = 0;
    int numAcc = 0;
    Account account;
    RegularUser user;
    TransactionType transactionType;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        user = (RegularUser) User.currentUser;
        welcomeMessage.setText("Welcome "+ user.getUsername());
        usrId.setText("🙂 "+user.getName());
        account = user.getAccounts().get(accIndex);
        user.activeAccount = account;
        numAcc = user.getAccounts().size();
        numOfAcc.setText("You have "+numAcc+" account" + ( numAcc > 1? "s": "") );
        showAccountDetailsOnPage(account);
    }

    @FXML
    public void showAccountDetailsOnPage(Account account){
        accId.setText("   id:"+String.valueOf(account.getAccountId()));
        acctype.setText(account.getAccountType().toString() + " Account");
        accbalance.setText("Balance  💵$"+String.format("%.2f", account.getBalance()));
        rate.setText(" 💹"+String.format("%.2f", account.getInterestRate())+"%");
        openingDate.setText("Opening date: "+ LocalDateTime.ofEpochSecond(account.getOpeningDate(), 0, ZoneOffset.UTC).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }

    @FXML
    public void changeAccount(){
        accIndex = (accIndex + 1) % numAcc;
        account = user.getAccounts().get(accIndex);
        user.activeAccount = account;
        showAccountDetailsOnPage(account);
    }

    @FXML
    public void showTransactionHistory() throws IOException {
        Stage stage = (Stage) welcomeMessage.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("transactionHistPage.fxml"));
        stage.setTitle("Transaction history");
        stage.setScene(new Scene(loader.load()));
        System.out.println("Transaction history");
    }

    @FXML
    void transferFunds(){
        transactionPane.setVisible(true);
        transactionTitle.setText("Transfer Funds");
        recievelbl.setVisible(true);
        reciever.setVisible(true);
        transactionType = TransactionType.TRANSFER;
    }

    @FXML
    void withdraw(){
        transactionPane.setVisible(true);
        transactionTitle.setText("Withdraw Funds");
        recievelbl.setVisible(false);
        reciever.setVisible(false);
        transactionType = TransactionType.WITHDRAWAL;
    }

    @FXML
    void deposit(){
        transactionPane.setVisible(true);
        transactionTitle.setText("Deposit Funds");
        recievelbl.setVisible(false);
        reciever.setVisible(false);
        transactionType = TransactionType.DEPOSIT;
    }

    @FXML
    void exitTransction(){
        transactionPane.setVisible(false);
        recievelbl.setVisible(false);
        reciever.setVisible(false);
    }

    @FXML
    void confirmTransaction(ActionEvent event) throws InterruptedException {
        transactionPane.setVisible(true);
        switch (transactionType){
            case WITHDRAWAL:
                try{
                    Account acc = user.activeAccount;
                    String amtText = amount.getText();
                    double amountEntered = Double.parseDouble(amtText);
                    if(amountEntered < 0){
                        throw new IllegalArgumentException("Can't have negative amount");
                    }
                    if(amountEntered > acc.getBalance()){
                        throw new IllegalArgumentException("Balance too low");
                    }
                    acc.setBalance(acc.getBalance() - amountEntered);
                    Bank.bank.addTransaction(acc.accountId, amountEntered, transactionType, 0);
                    Bank.bank.saveToFile();
                    transactionTitle.setText("Withdrawal Successful");
                    Thread.sleep(2000);
                    exitTransction();
                } catch (NumberFormatException e) {
                    System.out.println(e.getMessage());
                    amount.setText("Invalid Amount!!");
                    Thread.sleep(1000);
                    amount.setText("");
                } catch (IllegalArgumentException e){
                    System.out.println(e.getMessage());
                    amount.setText(e.getMessage());
                    Thread.sleep(1000);
                    amount.setText("");
                }
                break;

            case DEPOSIT:
                try{
                    Account acc = user.activeAccount;
                    String amtText = amount.getText();
                    double amountEntered = Double.parseDouble(amtText);
                    if(amountEntered < 0){
                        throw new IllegalArgumentException("Can't have negative amount");
                    }
                    acc.setBalance(acc.getBalance() + amountEntered);

                    Bank.bank.addTransaction(acc.accountId, amountEntered, transactionType, 0);
                    Bank.bank.saveToFile();
                    transactionTitle.setText("Deposit Successful");
                    Thread.sleep(2000);
                    exitTransction();
                } catch (NumberFormatException e) {
                    System.out.println(e.getMessage());
                    amount.setText("Invalid Amount!!");
                    Thread.sleep(1000);
                    amount.setText("");
                } catch (IllegalArgumentException e){
                    System.out.println(e.getMessage());
                    amount.setText(e.getMessage());
                    Thread.sleep(1000);
                    amount.setText("");
                }
                break;

            case TRANSFER:
                try{
                    Account acc = user.activeAccount;
                    String amtText = amount.getText();
                    String recieverIdtxt = reciever.getText();
                    int recieverId = Integer.parseInt(recieverIdtxt);
                    double amountEntered = Double.parseDouble(amtText);
                    if(amountEntered < 0){
                        throw new IllegalArgumentException("Can't have negative amount");
                    }
                    if(amountEntered > acc.getBalance()){
                        throw new IllegalArgumentException("Balance too low");
                    }
                    if(Bank.bank.getAccounts().containsKey(recieverId)){
                        Account reciever = Bank.bank.getAccounts().get(recieverId);
                        reciever.setBalance(reciever.getBalance() + amountEntered);
                    } else {
                        throw new IllegalArgumentException("Inexisting account");
                    }
                    acc.setBalance(acc.getBalance() - amountEntered);

                    Bank.bank.addTransaction(acc.accountId, amountEntered, transactionType, recieverId);
                    Bank.bank.saveToFile();
                    transactionTitle.setText("Transfer Successful");
                    exitTransction();
                } catch (NumberFormatException e) {
                    System.out.println(e.getMessage());
                    amount.setText("Invalid Amount or Reciever account!!");
                } catch (IllegalArgumentException e){
                    System.out.println(e.getMessage());
                    amount.setText(e.getMessage());
                }
                break;
        }
    }

    @FXML
    void loanDetails() throws IOException {
        Stage stage = (Stage) transactionPane.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("loanAgreement.fxml"));
        stage.setTitle("Loans");
        stage.setScene(new Scene(loader.load()));
        System.out.println("Loan page");
    }

    @FXML
    void logout() throws IOException {
        Stage stage = (Stage) transactionPane.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        stage.setTitle("Login");
        stage.setScene(new Scene(loader.load()));
        System.out.println("Login page");
    }

    @FXML
    private AnchorPane transactionPane;

    @FXML
    private Text recievelbl;

    @FXML
    private TextField amount;

    @FXML
    private TextField reciever;

    @FXML
    private Text transactionTitle;

    @FXML
    private Text accId;

    @FXML
    private Text accbalance;

    @FXML
    private Text openingDate;

    @FXML
    private Text acctype;

    @FXML
    private Button deposit;

    @FXML
    private Button loanDetails;

    @FXML
    private Text rate;

    @FXML
    private Button transactionHistory;

    @FXML
    private Text usrId;

    @FXML
    private Text welcomeMessage;

    @FXML
    private Text numOfAcc;

}
