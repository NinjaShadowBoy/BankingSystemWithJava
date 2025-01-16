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
    private int openingDate;
    private ArrayList<Transaction> transactions;

    public Account(int accountId, AccountType accountType, double balance, double interestRate, int openingDate) {
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

        // Get all transaction IDs
        JsonArray transactionsJson = new JsonArray();
        for (Transaction transaction : this.transactions) {
            transactionsJson.add(new JsonPrimitive(transaction.transactionId));
        }

        // Add the transactions Array to the object
        json.add("transactions", transactionsJson);

        return json;
    }

    ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    void setTransactions(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }
}
