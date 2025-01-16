package com.bankmanagement.models;

public abstract class User {
    protected int userId;
    protected String name;
    protected String email;
    protected String username;
    protected String password;

    public User(int userId, String name, String email, String username, String password) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
    }
}
