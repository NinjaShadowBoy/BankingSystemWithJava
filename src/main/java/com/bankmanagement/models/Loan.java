package com.bankmanagement.models;

public class Loan {
    private int loanId;
    private LoanType loanType;
    private double amount;
    private double interestRate;
    private String startDate;
    private int duration;  // in months
    private double monthlyPayment;
    private LoanStatus status;
    private int approvedBy;  // admin userId who approved the loan

    public Loan(int loanId, LoanType loanType, double amount, double interestRate,
                String startDate, int duration, double monthlyPayment,
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
                String startDate, int duration, double monthlyPayment,
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
                String startDate, int duration, double monthlyPayment) {
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
}