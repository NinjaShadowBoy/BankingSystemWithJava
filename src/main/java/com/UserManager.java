//package com.bankmanagement.models;

import java.util.HashMap;

public class UserManager {
    // HashMap to store username to userID mapping
    private static HashMap<String, Integer> usernameToUserId = new HashMap<>();

    // Method to add a new user
    public static boolean addUser(String username, int userId) {
        if (!usernameToUserId.containsKey(username)) {
            usernameToUserId.put(username, userId);
            return true; // Successfully added
        } else {
            System.out.println("Username already taken!");
            return false; // Username already exists
        }
    }

    // Method to get the user ID based on username
    public static Integer getUserIdByUsername(String username) {
        return usernameToUserId.get(username); // Returns null if not found
    }

    // Method to change a user's username
    public static boolean changeUsername(String oldUsername, String newUsername) {
        // Check if new username is already taken
        if (usernameToUserId.containsKey(newUsername)) {
            System.out.println("New username is already taken!");
            return false; // Can't change to the new username
        }

        // Check if the old username exists
        if (usernameToUserId.containsKey(oldUsername)) {
            int userId = usernameToUserId.get(oldUsername);
            // Remove the old username and add the new one
            usernameToUserId.remove(oldUsername);
            usernameToUserId.put(newUsername, userId);
            return true; // Username successfully changed
        } else {
            System.out.println("Old username not found!");
            return false; // Old username doesn't exist
        }
    }

    public static void main(String[] args) {
        // Example of adding and retrieving users
        addUser("jsmith", 100001);
        addUser("jdoe", 100002);

        System.out.println(getUserIdByUsername("jsmith")); // Output: 100001
        System.out.println(getUserIdByUsername("jdoe"));   // Output: 100002

        // Trying to change username
        boolean result = changeUsername("jsmith", "johnsmith");
        System.out.println("Change successful: " + result); // Output: Change successful: true
        System.out.println(getUserIdByUsername("johnsmith")); // Output: 100001
        System.out.println(getUserIdByUsername("jsmith"));   // Output: null
    }
}
