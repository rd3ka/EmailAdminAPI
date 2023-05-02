package com.admin.users;

import java.math.BigInteger;
import java.text.ParseException;
import java.util.Map;

public class Enterprise extends Users {
    String EnterpriseName;
    final String Department;
    final String Position;
    final long UEI;

    public Enterprise(String FirstName,
                      String LastName,
                      String DateOfBirth,
                      long UNI,
                      String Department,
                      String Position) throws ParseException {
        super(FirstName, LastName, DateOfBirth, UNI);
        this.Department = mapDepartment(Department);
        this.Position = Position;
        this.UEI = GenerateUEI();
        this.EnterpriseName = "";
    }

    public synchronized void setEnterpriseName(String EnterpriseName) {
        this.EnterpriseName = EnterpriseName;
    }

    public synchronized String getEnterpriseName() {
        return this.EnterpriseName;
    }

    private synchronized long GenerateUEI() {
        String firstPart = String.valueOf(this.getFirstName().hashCode());
        String ThirdPart = String.valueOf(this.getUniqueNationalIdentification() % (int) (Math.pow(10, 3)));
        return Long.parseLong(firstPart + ThirdPart);
    }

    public String getDepartment() {
        return Department;
    }
    private String mapDepartment(String dept) {
        Map <String, String> DeptAbbr = Map.of("Information Technology", "IT",
                "Human Resources", "HR",
                "Business Operations", "Man",
                "Sales And Marketing", "S&M");
        return DeptAbbr.get(dept);
    }

    public String getPosition() {
        return Position;
    }

    public long getUEI() {
        return UEI;
    }
}
