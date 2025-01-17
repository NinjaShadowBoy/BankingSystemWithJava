package com.bankmanagement.models;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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
    JsonObject toJson(){
        Gson gson = new Gson();
        JsonObject json = new JsonObject();
        json.addProperty("name", name);
        json.addProperty("email", email);
        json.addProperty("username", username);
        json.addProperty("password", password);
        return json;
    };

    public static User currentUser;

    // Getters
    String getName(){
        return name;
    }
    String getEmail(){
        return email;
    }
    String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }

    // Setters
    void setName(String name){
        this.name = name;
    }
    void setEmail(String email){
        this.email = email;
    }
    void setUsername(String username){
        this.username = username;
    }
    void setPassword(String password){
        this.password = password;
    }
}
