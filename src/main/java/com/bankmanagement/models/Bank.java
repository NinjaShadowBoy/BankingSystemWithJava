package com.bankmanagement.models;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Bank {
    private static class IdTracker {
        int regularUsers;
        int admins;
        int accounts;
        int transactions;
        int loans;
    }
    private IdTracker idTracker;
    // Maps to store the various entities
    private Map<Integer, RegularUser> regularUsers;
    private Map<Integer, Admin> admins;
    private Map<Integer, Account> accounts;
    private Map<Integer, Transaction> transactions;
    private Map<Integer, Loan> loans;

    // Map to store username to userId mapping
    private Map<String, Integer> usernameToUser;

    // Constructor
    private Bank() {
        regularUsers = new HashMap<>();
        admins = new HashMap<>();
        accounts = new HashMap<>();
        transactions = new HashMap<>();
        loans = new HashMap<>();
        usernameToUser = new HashMap<>();
    }

    // A global bank object
    private static Bank loadBankDB (){
        Gson gson = new Gson();
        Bank bank1 = new Bank();
        try (FileReader reader = new FileReader("test.json")) { // Parse the JSON file
            JsonObject jsonDB = JsonParser.parseReader(reader).getAsJsonObject(); // Extract attributes one by one
            JsonObject jsonIdtracker = jsonDB.get("idTracker").getAsJsonObject();
            JsonObject jsonUsernameToUser = jsonDB.get("usernameToUser").getAsJsonObject();
            JsonObject jsonRegularUsers = jsonDB.get("regularUsers").getAsJsonObject();
            JsonObject jsonAdmins = jsonDB.get("admins").getAsJsonObject();
            JsonObject jsonAccounts = jsonDB.get("accounts").getAsJsonObject();
            JsonObject jsonTransactions = jsonDB.get("transactions").getAsJsonObject();
            JsonObject jsonLoans = jsonDB.get("loans").getAsJsonObject();

            // Parse the idTracker and add it to the bank object
            bank1.idTracker = gson.fromJson(jsonIdtracker, IdTracker.class);

            // Parse the transactions and put in the bank object
            for (Map.Entry<String, JsonElement> entry : jsonTransactions.entrySet()) {
                int transactionId = Integer.parseInt(entry.getKey());
                Transaction transaction = gson.fromJson(entry.getValue(), Transaction.class);
                transaction.transactionId = transactionId;
                bank1.transactions.put(transactionId, transaction);
            }

            // Parse the loans and put in the bank object
            for (Map.Entry<String, JsonElement> entry : jsonLoans.entrySet()) {
                int loanId = Integer.parseInt(entry.getKey());
                Loan loan = gson.fromJson(entry.getValue(), Loan.class);
                loan.loanId = loanId;
                bank1.loans.put(loanId, loan);
            }

            // Parse the admins and put them in the bank object
            for (Map.Entry<String, JsonElement> entry : jsonAdmins.entrySet()) {
                int userId = Integer.parseInt(entry.getKey());
                Admin admin = gson.fromJson(entry.getValue(), Admin.class);
                admin.userId = userId;
                bank1.admins.put(admin.userId, admin);
            }

            // Parse the Accounts and put them in the bank object
            for (Map.Entry<String, JsonElement> entry : jsonAccounts.entrySet()) {
                int accountId = Integer.parseInt(entry.getKey());
                Account account = gson.fromJson(entry.getValue(), Account.class);
                account.accountId = accountId;

                // Empty the transaction list to fill with actual transaction objects we parsed above
                account.setTransactions(new ArrayList<>());
                JsonArray ids = entry.getValue().getAsJsonObject().get("transactionIds").getAsJsonArray();
                for (int i = 0; i < ids.size(); i++) {
                    account.getTransactions().add(bank1.transactions.get(ids.get(i).getAsInt()));
                }
                bank1.accounts.put(accountId, account);
            }

            // Parse the regularUsers to put in the bank object
            for (Map.Entry<String, JsonElement> entry : jsonRegularUsers.entrySet()) {
                int userId = Integer.parseInt(entry.getKey());
                RegularUser user = gson.fromJson(entry.getValue(), RegularUser.class);
                user.userId = userId;

                // Empty all the lists to fill with actual objects parsed above
                user.setLoans(new ArrayList<>());
                user.setTransactions(new ArrayList<>());
                user.setAccounts(new ArrayList<>());

                JsonArray trIds = entry.getValue().getAsJsonObject().get("transactionIds").getAsJsonArray();
                for (int i = 0; i < trIds.size(); i++) {
                    user.getTransactions().add(bank1.transactions.get(trIds.get(i).getAsInt()));
                }
                JsonArray loIds = entry.getValue().getAsJsonObject().get("loanIds").getAsJsonArray();
                for (int i = 0; i < loIds.size(); i++) {
                    user.getLoans().add(bank1.loans.get(loIds.get(i).getAsInt()));
                }
                JsonArray accIds = entry.getValue().getAsJsonObject().get("accountIds").getAsJsonArray();
                for (int i = 0; i < accIds.size(); i++) {
                    user.getAccounts().add(bank1.accounts.get(accIds.get(i).getAsInt()));
                }
                bank1.regularUsers.put(userId, user);
            }

            // Parse the usernameToUser object and put it in the bank object
            Type type = new TypeToken<Map<String, Integer>>(){}.getType();
            bank1.usernameToUser = gson.fromJson(jsonUsernameToUser, type);

//            String testDB = gson.toJson(bank);
//            System.out.println(testDB);
        } catch (IOException e) { e.printStackTrace(); }
        return bank1;
    }
    public void saveToFile(){
        Gson gson = new Gson();
        JsonObject json = new JsonObject();
        json.add("idTracker", gson.toJsonTree(this.idTracker));
        json.add("usernameToUser", gson.toJsonTree(this.usernameToUser));

        // Convert regularUsers to json
        JsonObject regularUsers = new JsonObject();
        for (Map.Entry<Integer, RegularUser> entry: this.regularUsers.entrySet()){
            regularUsers.add(entry.getKey().toString(), entry.getValue().toJson());
        }
        json.add("regularUsers", regularUsers);

        // Convert admins to json
        JsonObject admins = new JsonObject();
        for (Map.Entry<Integer, Admin> entry : this.admins.entrySet()) {
            admins.add(entry.getKey().toString(), entry.getValue().toJson());
        }
        json.add("admins", admins);

        // Convert accounts to json
        JsonObject accounts = new JsonObject();
        for (Map.Entry<Integer, Account> entry: this.accounts.entrySet()){
            accounts.add(entry.getKey().toString(), entry.getValue().toJson());
        }
        json.add("accounts", accounts);

        // Convert transactions to json
        JsonObject transactions = new JsonObject();
        for (Map.Entry<Integer, Transaction> entry: this.transactions.entrySet()){
            transactions.add(entry.getKey().toString(), entry.getValue().toJson());
        }
        json.add("transactions", transactions);

        // Convert loans to json
        JsonObject loans = new JsonObject();
        for (Map.Entry<Integer, Loan> entry: this.loans.entrySet()){
            loans.add(entry.getKey().toString(), entry.getValue().toJson());
        }
        json.add("loans", loans);

        // Clear the file contents if it exists
        try {
            Files.deleteIfExists(Paths.get("test.json"));
        } catch (IOException e) { e.printStackTrace(); }
        // Write to file
        try ( FileWriter writer = new FileWriter("test.json")) {
            writer.write(gson.toJson(json));
            System.out.println("JsonObject written to file.");
        } catch (IOException e) { e.printStackTrace(); }
    }
    // The bank database accessible everywhere
    public static Bank bank = Bank.loadBankDB();

    // Getters for the maps
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
        return usernameToUser;
    }

    // To get user from username
    public User getUser(String username) {
        if(usernameToUser.containsKey(username)) {
            int id = usernameToUser.get(username);
            if(id > 200000){
                return this.admins.get(id);
            } else if (id > 100000) {
                return this.regularUsers.get(id);
            }
        }
        return null;
    }
    // Method to add User
    public void addregularUser(String name, String email, String username, String password, String phone) {
        int newId = this.idTracker.regularUsers++;
        RegularUser newUser = new RegularUser(newId, name, email, username, password, phone);
        this.regularUsers.put(newId, newUser);
        this.usernameToUser.put(username, newId);
        newId = this.idTracker.accounts++;
        Account newAccount = new Account(newId, AccountType.CURRENT, 1000, 1, LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
        this.accounts.put(newId, newAccount);
        newUser.getAccounts().add(newAccount);
    }

    public void addTransaction(int accountId, double amount, TransactionType type, int recipientAccountId){
        int newId = this.idTracker.accounts++;
        Transaction t = new Transaction(newId, accountId, type, amount, LocalDateTime.now().toEpochSecond(ZoneOffset.UTC), recipientAccountId);
        transactions.put(newId, t);
        Account account = this.accounts.get(accountId);
        for(RegularUser user: regularUsers.values()) {
            if(user.getAccounts().contains(account)) {
                account.getTransactions().add(t);
                user.getTransactions().add(t);
            }
        }
        if(type == TransactionType.TRANSFER){
            account = this.accounts.get(recipientAccountId);
            for(RegularUser user: regularUsers.values()) {
                if(user.getAccounts().contains(account)) {
                    if (!user.getTransactions().contains(t)) {
                        user.getTransactions().add(t);
                        account.getTransactions().add(t);
                    }
                }
            }
        }
    }

    public Loan addLoan(LoanType loanType, double amount, double interestRate, long startDate, int duration, double monthlyPayment) {
        int newId = this.idTracker.accounts++;
        Loan newLoan = new Loan(newId, loanType, amount, interestRate, startDate, duration, monthlyPayment);
        this.loans.put(newId, newLoan);
        return newLoan;
    }

    // Method to add Account
    public void addAccount(Account account) {
    }

    // Method to add Loan
    public void addLoan(Loan loan) {
    }

    public Map<Integer, RegularUser> getRegularUsers() {
        return regularUsers;
    }
}
