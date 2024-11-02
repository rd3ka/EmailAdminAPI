package com.yet.another;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import com.yet.another.Employee.Employee;
import com.yet.another.Employee.EmployeeDAO;
import com.yet.another.Employee.EmployeeUtils;
import com.yet.another.Password.Password;
import com.yet.another.Password.PasswordDAO;    

/**
 * Admin
 */
public class Admin {
    final private String username;
    final private String password;
    final EmployeeDAO employeeDAO;
    final PasswordDAO passwordDAO;

    Admin(final String username, final String password) {
        this.username = username;
        this.password = password;
        this.employeeDAO = new EmployeeDAO(this.username, this.password);
        this.passwordDAO = new PasswordDAO(this.username, this.password);
    }

    final public void create() {
        employeeDAO.create();
        passwordDAO.create();
    }

    final public void insert(final String firstName, final String lastName,
            final String dob,
            final String role,
            final String department,
            final String domain) {
        final Employee employee = new Employee(
                firstName,
                lastName,
                role,
                department,
                LocalDate.parse(dob, DateTimeFormatter
                        .ofPattern("yyyy-MM-dd")
                        .withLocale(Locale.US)),
                domain);
        final Password password = new Password(new SecureRandom().nextInt(8, 16));
        this.employeeDAO.insert(employee);
        this.passwordDAO.insert(employee, password);
        System.out.println("Data Inserted");
    }

    final public void read(String firstName, String lastName, String dob) {
        int employee_uid = EmployeeUtils.generateUniqueIdentityNumber(firstName,
                lastName,
                LocalDate.parse(dob, DateTimeFormatter
                        .ofPattern("yyyy-MM-dd")
                        .withLocale(Locale.US)));
        System.out.println(employee_uid);
        Employee employee = this.employeeDAO.read(employee_uid);
        Password password = this.passwordDAO.read(employee_uid);
        System.out.println(employee.toString());
        System.out.println("Employee Password: " + password.getPassword());
    }
}
