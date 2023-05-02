package com.admin;

import com.admin.email.Email;
import com.admin.email.EnterpriseEmail;

import java.text.ParseException;

public class App {
    public static void main( String[] args ) throws ParseException {
        Email e = new EnterpriseEmail("Raktim", "Deka",
                "14/09/1999",
                "Information Technology",
                "Junior Developer",
                "Wipro Limited",
                123456);
        e.GenerateEmail();
        e.GenerateDefaultPassword();
        e.ViewAccountDetails();
    }
}
