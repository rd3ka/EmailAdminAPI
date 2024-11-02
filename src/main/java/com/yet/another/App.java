package com.yet.another;

public class App {
    public static void main(String[] args) {
        Admin admin = new Admin("rdeka", "rd141999");
        admin.create();
        String[][] dummyData = {
                { "John", "Doe", "1990-01-15", "Software Engineer", "IT", "techcorp" },
                { "Jane", "Smith", "1985-03-22", "Product Manager", "Product", "innovate" },
                { "Emily", "Johnson", "1992-11-07", "HR Specialist", "HR", "hrsolutions" },
                { "Michael", "Brown", "1988-05-30", "Marketing Manager", "Marketing", "adventure" },
                { "Chris", "Williams", "1995-09-18", "Data Analyst", "Data", "datascience" },
                { "David", "Jones", "1993-12-01", "Finance Analyst", "Finance", "moneywise" },
                { "Sarah", "Davis", "1987-06-25", "Operations Manager", "Operations", "businesslogix" },
                { "James", "Miller", "1991-04-14", "Sales Executive", "Sales", "sellforce" },
                { "Laura", "Wilson", "1996-02-09", "UX Designer", "Design", "creativespace" },
                { "Robert", "Anderson", "1989-07-13", "DevOps Engineer", "DevOps", "cloudmasters" }
        };

        for (String dd[] : dummyData) {
            admin.insert(dd[0], dd[1], dd[2], dd[3], dd[4], dd[5]);
        }

        admin.read("John", "Doe", "1990-01-15");
    }
}
