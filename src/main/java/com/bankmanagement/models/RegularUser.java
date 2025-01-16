package com.bankmanagement.models;

import java.util.ArrayList;
import java.util.List;

public class RegularUser extends User {
    private String phone;
    private List<Account> accounts;
    private List<Loan> loans;
    private List<Transaction> transactions;

    public RegularUser(int userId, String name, String email, String username, String password, String phone) {
        super(userId, name, email, username, password);
        this.phone = phone;
        this.accounts = new ArrayList<>();
        this.loans = new ArrayList<>();
        this.transactions = new ArrayList<>();
    }
}
