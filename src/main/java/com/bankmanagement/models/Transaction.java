package com.bankmanagement.models;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class Transaction {
    public int transactionId;
    private int accountId;
    private TransactionType type;
    private double amount;
    private long date;
    private Integer recipientAccountId;

    public Transaction(int transactionId, int accountId, TransactionType type, double amount, int date, Integer recipientAccountId) {
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
}
