package com.admin.users.educational;

import java.text.ParseException;

public class School extends Educational {

    private final long UID;
    private final char Section;
    private final String Standard;
    private final int RollNumber;

    public School(String FirstName,
                  String LastName,
                  String InstituteName,
                  Double Height,
                  Double Weight,
                  char Gender,
                  long UNI,
                  String DateOfBirth) throws ParseException {
        super(FirstName, LastName, InstituteName, Height, Weight, Gender, UNI, DateOfBirth);
        this.UID = super.GenerateUniqueID();
        this.Section = (char) ((int) (Math.random() * (70 - 65)) + 65);
        this.Standard = SetStandard();
        this.RollNumber = (int)(Math.random() * (80 - 1) + 1);
    }

    public long getUID() {
        return UID;
    }

    public char getSection() {
        return Section;
    }

    public String getStandard() {
        return Standard;
    }

    public int getRollNumber() {
        return RollNumber;
    }

    private synchronized String SetStandard() {
        int age = super.getAge();
        if (age >= 18 && age <= 19)
            return "XII";
        else if (age >= 17 && age <= 18)
            return "XI";
        else if (age >= 16 && age <= 17)
            return "X";
        else if (age >= 15 && age <= 16)
            return "IX";
        else if (age >= 14 && age <= 15)
            return "VIII";
        else if (age >= 13 && age <= 14)
            return "VII";
        else if (age >= 12 && age <= 13)
            return "VI";
        else if (age >= 11 && age <= 12)
            return "V";
        else if (age >= 10 && age <= 11)
            return "IV";
        else if (age >= 9 && age <= 10)
            return "III";
        else if (age >= 8 && age <= 9)
            return "II";
        else
            return "I";
    }


}
