package com.admin.users;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Users {

    protected final String FirstName,
            LastName;
    protected final long UniqueNationalIdentification;

    protected String Nationality;
    protected String Address;
    protected Date DateOfBirth;

    public Users(String FirstName,
                 String LastName,
                 String Nationality,
                 String Address,
                 String DateOfBirth,
                 long UNI) throws ParseException {
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.Nationality = Nationality;
        this.Address = Address;
        this.DateOfBirth = new SimpleDateFormat("dd/MM/yyyy").parse(DateOfBirth);
        this.UniqueNationalIdentification = UNI;
    }

    public Users(String FirstName,
                 String LastName,
                 String DateOfBirth,
                 long UNI) throws ParseException {
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.DateOfBirth = new SimpleDateFormat("dd/MM/yyyy").parse(DateOfBirth);
        this.UniqueNationalIdentification = UNI;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getNationality() {
        return Nationality;
    }

    public String getAddress() {
        return Address;
    }

    public Date getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) throws ParseException {
        this.DateOfBirth = new SimpleDateFormat("dd/MM/yyyy").parse(dateOfBirth);
    }

    public long getUniqueNationalIdentification() {
        return UniqueNationalIdentification;
    }
}
