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
        String jsonString = gson.toJson(this);
        JsonObject json = JsonParser.parseString(jsonString).getAsJsonObject();
        json.remove("userId");
        return json;
    };
}
