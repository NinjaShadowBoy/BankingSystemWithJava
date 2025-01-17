package com.bankmanagement.models;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class Transaction {
    public int transactionId;
    public int accountId;
    public TransactionType type;
    public double amount;
    public long date;
    public int recipientAccountId;

    public Transaction(int transactionId, int accountId, TransactionType type, double amount, long date, Integer recipientAccountId) {
        this.transactionId = transactionId;
        this.accountId = accountId;
        this.type = type;
        this.amount = amount;
        this.date = date;
        this.recipientAccountId = recipientAccountId;
    }

    JsonObject toJson() {
        JsonObject json = new Gson().toJsonTree(this).getAsJsonObject();
        json.remove("transactionId");
        return json;
    }

    // Add getters for all properties that you want to display in TableView
    public int getTransactionId() {
        return transactionId;
    }

    public int getAccountId() {
        return accountId;
    }

    public TransactionType getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public long getDate() {
        return date;
    }

    public Integer getRecipientAccountId() {
        return recipientAccountId;
    }

    // Setters
    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }
    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
    public void setType(TransactionType type) {
        this.type = type;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public void setDate(long date) {
        this.date = date;
    }
    public void setRecipientAccountId(Integer recipientAccountId) {
        this.recipientAccountId = recipientAccountId;
    }
}
