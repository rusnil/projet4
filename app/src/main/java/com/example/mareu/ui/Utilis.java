package com.example.mareu.ui;


public class Utilis {

    public static boolean isEmailValid(String email) {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (email.isEmpty()) {
            return false;
        }
        return email.matches(emailPattern);
    }
}
