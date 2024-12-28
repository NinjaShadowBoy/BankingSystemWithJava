package com.example.demo;

public class person{
    String date;
    String time;
    double amount;
    double balance;
    String type;

    public person(String date) {
        this.date = date;
    }

    public person(String time, double amount, double balance, String type) {
        this.time = time;
        this.amount = amount;
        this.balance = balance;
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public double getAmount() {
        return amount;
    }

    public double getBalance() {
        return balance;
    }

    public String getType() {
        return type;
    }
}
