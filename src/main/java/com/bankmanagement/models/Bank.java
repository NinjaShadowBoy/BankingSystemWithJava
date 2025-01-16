package com.bankmanagement.models;

import java.util.HashMap;
import java.util.Map;

public class Bank {
    // Maps to store the various entities
    private Map<Integer, User> users;
    private Map<Integer, Account> accounts;
    private Map<Integer, Transaction> transactions;
    private Map<Integer, Loan> loans;

    // Map to store username to userId mapping
    private Map<String, Integer> usernameToIdMap;

    // Constructor
    public Bank() {
        users = new HashMap<>();
        accounts = new HashMap<>();
        transactions = new HashMap<>();
        loans = new HashMap<>();
        usernameToIdMap = new HashMap<>();
    }

    // Getters for the maps
    public Map<Integer, User> getUsers() {
        return users;
    }

    public Map<Integer, Account> getAccounts() {
        return accounts;
    }

    public Map<Integer, Transaction> getTransactions() {
        return transactions;
    }

    public Map<Integer, Loan> getLoans() {
        return loans;
    }

    public Map<String, Integer> getUsernameToUserIdMap() {
        return usernameToIdMap;
    }

    // Method to add User
    public void addUser(User user) {
    }

    // Method to add Account
    public void addAccount(Account account) {
    }

    // Method to add Transaction
    public void addTransaction(Transaction transaction) {
    }

    // Method to add Loan
    public void addLoan(Loan loan) {
    }
}
