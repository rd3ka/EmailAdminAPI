package com.admin.email;

import com.admin.password.Password;
import com.admin.users.educational.Educational;
import com.admin.users.educational.PostSchool;
import com.admin.users.educational.School;

import java.text.ParseException;

public class EducationalEmail implements Email{
    private final String EducationalDomainName;
    private String EmailAddress;
    private String DefaultEmailPassword;

    private Educational E;

    public EducationalEmail(String FirstName,
                            String LastName,
                            String InstituteName,
                            Double Height,
                            Double Weight,
                            char Gender,
                            long UNI,
                            String DateOfBirth,
                            String Department,
                            String Field) throws ParseException {
        this.E = new Educational(FirstName, LastName, InstituteName, Height, Weight, Gender, UNI, DateOfBirth);
        if (this.E.getAge() > 19)
            this.E = new PostSchool(FirstName, LastName,
                    InstituteName,
                    Height, Weight,
                    Gender,
                    UNI,
                    DateOfBirth,
                    Department,
                    Field);
        else
            this.E = new School(FirstName, LastName,
                    InstituteName,
                    Height, Weight,
                    Gender,
                    UNI,
                    DateOfBirth);

        this.EducationalDomainName = GenerateDomain();
    }

    private synchronized String GenerateDomain() {
        return this.E.getInstituteName()
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
                .concat(String.valueOf(E.getAge()))
                .concat("@")
                .concat(this.EducationalDomainName)
                .concat(".com");
    }

    @Override
    public String GetEmail() {
        return this.EmailAddress;
    }

    @Override
    public void GenerateDefaultPassword() {
        this.DefaultEmailPassword = new Password().getPassword();
    }

    @Override
    public String GetDefaultPassword() {
        return this.DefaultEmailPassword;
    }

    @Override
    public void ViewAccountDetails() {
        System.out.printf("%s is a student of %s\n", this.E.getFirstName(), this.E.getInstituteName());
        System.out.printf("Generated Email ID: %s\n", this.EmailAddress);
        System.out.printf("Default Password: %s\n", this.DefaultEmailPassword);
    }
}
