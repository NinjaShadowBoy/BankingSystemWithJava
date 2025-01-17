package com.bankmanagement.models;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.util.ArrayList;
import java.util.List;

public class RegularUser extends User {
    private String phone;
    private ArrayList<Account> accounts;
    private ArrayList<Loan> loans;
    private ArrayList<Transaction> transactions;
    public Account activeAccount;

    public RegularUser(int userId, String name, String email, String username, String password, String phone) {
        super(userId, name, email, username, password);
        this.phone = phone;
        this.accounts = new ArrayList<>();
        this.loans = new ArrayList<>();
        this.transactions = new ArrayList<>();
    }

    @Override
    JsonObject toJson() {
        JsonObject json = super.toJson();
        // Add phone number
        json.addProperty("phone", this.phone);

        // Get all account IDs
        JsonArray accountsJson = new JsonArray();
        for (Account account : this.accounts) {
            accountsJson.add(new JsonPrimitive(account.accountId));
        }
        json.add("accountIds", accountsJson);

        // Get all loan IDs
        JsonArray loansJson = new JsonArray();
        for (Loan loan : this.loans) {
            loansJson.add(new JsonPrimitive(loan.loanId));
        }
        json.add("loanIds", loansJson);

        // Get all transaction IDs
        JsonArray transactionsJson = new JsonArray();
        for (Transaction transaction : this.transactions) {
            transactionsJson.add(new JsonPrimitive(transaction.transactionId));
        }
        json.add("transactionIds", transactionsJson);

        return json;
    }

    // Getters
    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }
    public ArrayList<Account> getAccounts() {
        return accounts;
    }
    public ArrayList<Loan> getLoans() {
        return loans;
    }

    // Setters
    void setAccounts(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }
    void setLoans(ArrayList<Loan> loans) {
        this.loans = loans;
    }
    void setTransactions(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }
}
