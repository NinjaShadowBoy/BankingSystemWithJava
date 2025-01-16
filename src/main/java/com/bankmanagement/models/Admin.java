package com.bankmanagement.models;

import java.util.ArrayList;
import java.util.List;

public class Admin extends User {
    private List<String> permissions;

    public Admin(int userId, String name, String email, String username, String password) {
        super(userId, name, email, username, password);
        this.permissions = new ArrayList<>();
    }
}
