package com.bankmanagement.models;

public class Transaction {
    private int transactionId;
    private int accountId;
    private TransactionType type;
    private double amount;
    private int date;
    private Integer recipientAccountId;

    public Transaction(int transactionId, int accountId, TransactionType type, double amount, int date, Integer recipientAccountId) {
        this.transactionId = transactionId;
        this.accountId = accountId;
        this.type = type;
        this.amount = amount;
        this.date = date;
        this.recipientAccountId = recipientAccountId;
    }
}
