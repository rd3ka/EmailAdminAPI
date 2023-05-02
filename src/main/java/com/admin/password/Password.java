package com.admin.password;

import java.security.SecureRandom;
public class Password {
    private String password;
    private final int MIN_LENGTH;
    private final int MAX_LENGTH;

    public Password() {
        this.MIN_LENGTH = 8;
        this.MAX_LENGTH = 24;
        this.password = GeneratePassword();
    }
    public synchronized String getPassword() {
        return this.password;
    }
    public synchronized void setPassword(String password) {
        if (CheckStrength(password).equals("medium") || CheckStrength(password).equals("strong"))
            this.password = password;
        else
            System.out.println("Password too weak!");
    }
    private synchronized String GeneratePassword() {
        SecureRandom sr = new SecureRandom();
        char[] pass = new char[this.MIN_LENGTH];
        for(int i = 0; i < this.MIN_LENGTH; i++) {
            int ch = sr.nextInt(1,5);
            switch (ch) {
                case 1 -> pass[i] = (char)(sr.nextInt(65,91));
                case 2 -> pass[i] = (char)(sr.nextInt(97, 123));
                case 3 -> pass[i] = (char)(sr.nextInt(48, 58));
                case 4 -> {
                    char[] specialCharactersList = {'!','#','&','*','@','+','-'};
                    pass[i] = specialCharactersList[sr.nextInt(0,6)];
                }
            }
        }
        return new String(pass);
    }

    private synchronized String CheckStrength(String password) {
        long NumberCount  = password.chars()
                .filter(c ->  c >= 48 && c <= 57)
                .count();

        long SmallCount   = password.chars()
                .filter(c ->  c >= 65 && c <= 90)
                .count();

        long CapitalCount = password.chars()
                .filter(c ->  c >= 97 && c <= 122)
                .count();

        if (NumberCount <= 2 && CapitalCount <= 1 && SmallCount <= 4)
            return "weak";
        else if (NumberCount <= 3 && CapitalCount <= 2 && SmallCount < 6)
            return "medium";
        return "strong";
    }
}
