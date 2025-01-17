package com.bankmanagement.models;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class Loan {
    public int loanId;
    private LoanType loanType;
    private double amount;
    private double interestRate;
    private long startDate;
    private int duration;  // in months
    private double monthlyPayment;
    private LoanStatus status;
    private int approvedBy;  // admin userId who approved the loan

    public Loan(int loanId, LoanType loanType, double amount, double interestRate,
                long startDate, int duration, double monthlyPayment,
                LoanStatus status, int approvedBy) {
        this.loanId = loanId;
        this.loanType = loanType;
        this.amount = amount;
        this.interestRate = interestRate;
        this.startDate = startDate;
        this.duration = duration;
        this.monthlyPayment = monthlyPayment;
        this.status = status;
        this.approvedBy = approvedBy;
    }
    public Loan(int loanId, LoanType loanType, double amount, double interestRate,
                long startDate, int duration, double monthlyPayment,
                LoanStatus status) {
        this.loanId = loanId;
        this.loanType = loanType;
        this.amount = amount;
        this.interestRate = interestRate;
        this.startDate = startDate;
        this.duration = duration;
        this.monthlyPayment = monthlyPayment;
        this.status = status;
        this.approvedBy = 0;
    }
    public Loan(int loanId, LoanType loanType, double amount, double interestRate,
                long startDate, int duration, double monthlyPayment) {
        this.loanId = loanId;
        this.loanType = loanType;
        this.amount = amount;
        this.interestRate = interestRate;
        this.startDate = startDate;
        this.duration = duration;
        this.monthlyPayment = monthlyPayment;
        this.status = LoanStatus.PENDING;
        this.approvedBy = 0;
    }

    public void setApprovedBy(int adminID) {
        this.approvedBy = adminID;
    }

    JsonObject toJson() {
        JsonObject json = new Gson().toJsonTree(this).getAsJsonObject();
        json.remove("loanId");
        return json;
    }
}