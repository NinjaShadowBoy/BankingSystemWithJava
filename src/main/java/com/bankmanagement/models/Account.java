package com.bankmanagement.models;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.util.ArrayList;
import java.util.List;

public class Account {
    public int accountId;
    private AccountType accountType;
    private double balance;
    private double interestRate;
    private long openingDate;
    private ArrayList<Transaction> transactions;

    public Account(int accountId, AccountType accountType, double balance, double interestRate, long openingDate) {
        this.accountId = accountId;
        this.accountType = accountType;
        this.balance = balance;
        this.interestRate = interestRate;
        this.openingDate = openingDate;
        this.transactions = new ArrayList<>();
    }

    JsonObject toJson() {
        JsonObject json = new Gson().toJsonTree(this).getAsJsonObject();
        json.remove("accountId");
        json.remove("transactions");

        // Get all transaction IDs
        JsonArray transactionsJson = new JsonArray();
        for (Transaction transaction : this.transactions) {
            transactionsJson.add(new JsonPrimitive(transaction.transactionId));
        }

        // Add the transactions Array to the object
        json.add("transactionIds", transactionsJson);

        return json;
    }

    ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    void setTransactions(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }

    // Getters
    public int getAccountId() {
        return accountId;
    }
    public double getBalance() {
        return balance;
    }
    public double getInterestRate() {
        return interestRate;
    }
    public long getOpeningDate() {
        return openingDate;
    }
    public AccountType getAccountType() {
        return accountType;
    }

    // Setters
    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }
    public void setOpeningDate(long openingDate) {
        this.openingDate = openingDate;
    }
}
