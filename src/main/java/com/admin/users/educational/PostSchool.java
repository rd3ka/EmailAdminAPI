package com.admin.users.educational;

import java.text.ParseException;
import java.util.Map;

public class PostSchool extends Educational {
    private final long EID;
    private final String Department;
    private final String Section;
    private final int RollNumber;

    public PostSchool(String FirstName,
                      String LastName,
                      String InstituteName,
                      Double Height,
                      Double Weight,
                      char Gender,
                      long UNI,
                      String DateOfBirth,
                      String Department,
                      String Field) throws ParseException {
        super(FirstName, LastName, InstituteName, Height, Weight, Gender, UNI, DateOfBirth);
        this.EID = super.GenerateUniqueID();
        this.Department = Department;
        this.Section = SetSection(Field);
        this.RollNumber = (int) (Math.random() * (80 - 1) + 1);
    }

    private synchronized String SetSection(String Field) {
        char s = (char) ((int) (Math.random() * (70 - 65) + 65));
        Map<String, Map<String, String>> mm = Map.of("Engineering", Map.of("Computer Science", "CS",
                        "Electronics & Communication", "ECE",
                        "Electrical & Electronics", "EE",
                        "Mechanical", "ME",
                        "Civil", "CI",
                        "Bio Technology", "BT",
                        "Electronics & Instrumentation", "EI",
                        "Food Technology", "FT",
                        "Aerospace Engineering", "AE"),
                "Subject Specialization", Map.of("English Hons", "EH",
                        "Geography Hons", "GH",
                        "Physics Hons", "PH",
                        "Chemistry Hons", "CH",
                        "History Hons", "HH",
                        "Mathematics Hons", "MH"));
        /*To Add More Subjects And Specializations*/

        return mm.get(Field).get(this.Department) + s;
    }

    public long getEID() {
        return EID;
    }

    public String getDepartment() {
        return Department;
    }

    public String getSection() {
        return Section;
    }

    public int getRollNumber() {
        return RollNumber;
    }
}
