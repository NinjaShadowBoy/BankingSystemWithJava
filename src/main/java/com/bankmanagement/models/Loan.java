package com.bankmanagement.models;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class Loan {
    public int loanId;
    public LoanType loanType;
    public double amount;
    public double interestRate;
    public long startDate;
    public int duration;  // in months
    public double monthlyPayment;
    public LoanStatus status;
    public int approvedBy;  // admin userId who approved the loan

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

    // Getters
    public int getLoanId() {
        return loanId;
    }
    public void setLoanId(int loanId) {
        this.loanId = loanId;
    }
    public LoanType getLoanType() {
        return loanType;
    }
    public void setLoanType(LoanType loanType) {
        this.loanType = loanType;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public double getInterestRate() {
        return interestRate;
    }
    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public long getStartDate() {
        return startDate;
    }
    public void setStartDate(long startDate) {
        this.startDate = startDate;
    }
    public int getDuration() {
        return duration;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }
    public LoanStatus getStatus() {
        return status;
    }
    public void setStatus(LoanStatus status) {
        this.status = status;
    }
    public int getApprovedBy() {
        return approvedBy;
    }
}
