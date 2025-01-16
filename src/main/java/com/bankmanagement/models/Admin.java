package com.bankmanagement.models;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class Admin extends User {
    private ArrayList<String> permissions;

    public Admin(int userId, String name, String email, String username, String password) {
        super(userId, name, email, username, password);
        this.permissions = new ArrayList<>();
    }

    @Override
    public JsonObject toJson() {
        JsonObject json = super.toJson();
        json.add("permissions", new Gson().toJsonTree(this.permissions));
        return json;
    }
}
