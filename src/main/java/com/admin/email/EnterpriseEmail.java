package com.admin.email;

import com.admin.password.Password;
import com.admin.users.Enterprise;

import java.text.ParseException;

public class EnterpriseEmail implements Email {

    private final String EnterpriseDomainName;
    private String EmailAddress;
    private String EmailDefaultPassword;
    private final Enterprise E;
    public EnterpriseEmail(String FirstName,
                           String LastName,
                           String DateOfBirth,
                           String Department,
                           String Position,
                           String EnterpriseName,
                           long UNI) throws ParseException {
        this.E = new Enterprise(FirstName,LastName, DateOfBirth, UNI, Department, Position);
        E.setEnterpriseName(EnterpriseName);
        this.EnterpriseDomainName = GenerateDomain();
    }

    private synchronized String GenerateDomain() {
        return this.E.getEnterpriseName()
                .toLowerCase()
                .split(" ")
                [0];
    }

    @Override
    public void GenerateEmail() {
        this.EmailAddress = E.getFirstName().toLowerCase()
                .concat(".")
                .concat(E.getLastName().toLowerCase())
                .concat(".")
                .concat(E.getDepartment().toLowerCase())
                .concat("@")
                .concat(this.EnterpriseDomainName)
                .concat(".com");
    }

    @Override
    public synchronized String GetEmail() { return this.EmailAddress; }

    @Override
    public void GenerateDefaultPassword() {
        this.EmailDefaultPassword = new Password().getPassword();
    }

    @Override
    public String GetDefaultPassword() { return this.EmailDefaultPassword; }

    @Override
    public void ViewAccountDetails() {
        System.out.printf("%s is an Employee of %s\n",E.getFirstName(), E.getEnterpriseName());
        System.out.printf("Generated Email Address: %s\n", this.EmailAddress);
        System.out.printf("Default Password: %s\n", this.EmailDefaultPassword);
    }
}
