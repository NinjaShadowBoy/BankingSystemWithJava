package com.bankmanagement.models;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private int accountId;
    private AccountType accountType;
    private double balance;
    private double interestRate;
    private int openingDate;
    private List<Transaction> transactions;

    public Account(int accountId, AccountType accountType, double balance, double interestRate, int openingDate) {
        this.accountId = accountId;
        this.accountType = accountType;
        this.balance = balance;
        this.interestRate = interestRate;
        this.openingDate = openingDate;
        this.transactions = new ArrayList<>();
    }
}
