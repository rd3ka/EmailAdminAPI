package com.yet.another.Employee;

import java.sql.Date;
import java.sql.ResultSet;

import com.yet.another.Database.AbstractDAO;
import com.yet.another.Database.DatabaseUtils.Utility.*;

public class EmployeeDAO extends AbstractDAO<Employee> {

    public EmployeeDAO(String username, String password) {
        setUsername(username);
        setPassword(password);
    }

    final public void create() {
        try {
            ResultSet resultSet = get_Connection()
                    .getMetaData()
                    .getTables(null, null, Query.DEFAULT_EMPLOYEE_TABLE,
                            new String[] { "TABLE" });
            /*
             * retrieving information from the metadata of the database about an 'TABLE'
             * that matches the DEFAULT_PASSWORD_TABLE name
             */
            final boolean tableExists = resultSet.next();
            if (tableExists)
                return;
            /* if it does exists, we simply return from the function */
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        executeUpdate(Query.CREATE_EMPLOYEE_TABLE, statement -> null);
        /* ^ default thing to do is to create the table */
    }

    final public void insert(final Employee employee) {
        executeUpdate(Query.INSERT_EMPLOYEE, statement -> {
            statement.setInt(1, employee.getUID());
            statement.setString(2, employee.getFirstName());
            statement.setString(3, employee.getLastName());
            statement.setDate(4, Date.valueOf(employee.getDob()));
            statement.setString(5, employee.getRole());
            statement.setString(6, employee.getDepartment());
            statement.setString(7, employee.getDomain());
            return null;
        });
    }

    final public Employee read(final int employeeUID) {
        return executeQuery(Query.READ_EMPLOYEE, statement -> {
            Employee employee = new Employee();
            statement.setInt(1, employeeUID);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                employee.setFirstName(result.getString(2));
                employee.setLastName(result.getString(3));
                employee.setDob(result.getDate(4).toLocalDate());
                employee.setRole(result.getString(5));
                employee.setDepartment(result.getString(6));
                employee.setDomain(result.getString(7));
            } else
                System.out.println("No Employee Found with UID " + employeeUID);
            return employee;
        }, "");
    }

    final public void delete(final Employee employee) {
        executeUpdate(Query.DELETE_EMPLOYEE, statement -> {
            statement.setInt(1, employee.getUID());
            return null;
        });
    }

}
