package com.yet.another;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import com.yet.another.Database.Database;
import com.yet.another.Employee.Employee;
import com.yet.another.Employee.EmployeeDAO;
import com.yet.another.Employee.EmployeeUtils;
import com.yet.another.Password.Password;
import com.yet.another.Password.PasswordDAO;
import com.yet.another.Password.PasswordUtils;

public class Admin {
    private final String username;
    private final String password;

    Admin(final String username, final String password) {
        this.username = username;
        this.password = password;
    }

    public void create() {
        final Database database = new Database(this.username, this.password);
        EmployeeDAO.createEmployeeTable(database);
        PasswordDAO.createPasswordTable(database);
        database.handle(database);
    }

    public void insert(final String firstName, final String lastName, final String dob, final String role,
            final String department, final String domain) {
        final Database database = new Database(this.username, this.password);
        final Employee employee = new Employee(firstName,
                lastName,
                role,
                department,
                LocalDate.parse(dob, DateTimeFormatter
                        .ofPattern("yyyy-MM-dd")
                        .withLocale(Locale.US)),
                domain);
        final Password password = new Password();
        EmployeeDAO.insertEmployee(employee, database);
        PasswordDAO.insertPassword(database, employee, password);
        database.handle(database);
    }

    public void read(final String firstName, final String lastName, String dob) {
        final Database database = new Database(this.username, this.password);
        /* this implementation is probably not the best, but for now let this just be */
        int employee_id = EmployeeUtils.generateUniqueIdentityNumber(firstName, lastName,
                LocalDate.parse(dob, DateTimeFormatter
                        .ofPattern("yyyy-MM-dd")
                        .withLocale(Locale.US)));
        Employee employee = EmployeeDAO.readEmployee(database, employee_id);
        Password password = PasswordDAO.readPassword(database, employee_id);
        System.out.println(employee.toString());
        System.out.println("Employee Password: " + PasswordUtils.getDecryptedPassword(password.getPassword()));
    }
}
