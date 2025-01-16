package com.bankmanagement.models;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
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
    public static Bank loadBankDB (){
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
                System.out.println(entry);
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
    public void toJson(){
        JsonObject json = new JsonObject();
    }
    // The bank database accessible everywhere
    static Bank bank = Bank.loadBankDB();

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
