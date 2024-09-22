package com.yet.another;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import com.yet.another.Database.Database;
import com.yet.another.Employee.Employee;
import com.yet.another.Employee.EmployeeDAO;
import com.yet.another.Password.Password;
import com.yet.another.Password.PasswordDAO;

public class Admin {
    private final String username;
    private final String password;

    Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void create() {
        Database database = new Database(this.username, this.password);
        EmployeeDAO.createEmployeeTable(database);
        PasswordDAO.createPasswordTable(database);
        database.handle(database);
    }

    public void insert(String firstName, String lastName, String dob, String role, String department, String domain) {
        Database database = new Database(this.username, this.password);
        Employee employee = new Employee(firstName,
                lastName,
                role,
                department,
                LocalDate.parse(dob, DateTimeFormatter
                        .ofPattern("yyyy-MM-dd")
                        .withLocale(Locale.US)),
                domain);
        Password password = new Password();
        EmployeeDAO.insertEmployee(employee, database);
        PasswordDAO.insertPassword(database, employee, password);
        database.handle(database);
    }
}
