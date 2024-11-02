package com.yet.another.Password;

import java.sql.ResultSet;

import com.yet.another.Database.AbstractDAO;
import com.yet.another.Database.DatabaseUtils.Utility.Query;
import com.yet.another.Employee.Employee;

/**
 * PasswordDAOneo
 */
public class PasswordDAO extends AbstractDAO<Password> {

    public PasswordDAO(String username, String password) {
        setUsername(username);
        setPassword(password);
    }

    final public void create() {
        try {
            ResultSet resultSet = get_Connection()
                    .getMetaData()
                    .getTables(null, null, Query.DEFAULT_PASSWORD_TABLE,
                            new String[] { "TABLE" });
            /*
             * retrieving information from the metadata of the database about any 'TABLE'
             * that matches the DEFAULT_PASSWORD_TABLE name
             */
            final boolean tableExists = resultSet.next();
            if (tableExists)
                return;
            /* if it does exists, we simply return from the function */
        } catch (final Exception exception) {
            exception.printStackTrace();
            /*
             * in the code flow, if there is any exception that java.sql class create, this
             * will handle it
             */
        }
        executeUpdate(Query.CREATE_PASSWORD_TABLE, statement -> null);
        /* ^ default thing to do is to create the table */
    }

    final public void insert(final Employee employee, final Password password) {
        executeUpdate(Query.INSERT_PASSWORD, statement -> {
            statement.setInt(1, employee.getUID());
            statement.setString(2, new EncryptionUtils()
                    .getEncryptedPassword(password.getPassword()));
            return null;
        });
    }

    final public void update(final Employee employee, final Password password) {
        executeUpdate(Query.UPDATE_PASSWORD, statement -> {
            statement.setString(1, password.getPassword());
            statement.setInt(2, employee.getUID());
            return null;
        });
    }

    final public Password read(int employeeUID) {
        return executeQuery(Query.READ_PASSWORD, statement -> {
            Password password = null;
            statement.setInt(1, employeeUID);
            ResultSet resultSet = statement.executeQuery();
            password = new Password();
            if (resultSet.next()) {
                password.setPassword(new EncryptionUtils()
                        .getDecryptedPassword(resultSet.getString(1)));
                /*
                 * potentially, one of the biggest jokes of the centaury, but help and I am
                 * keeping this
                 */
                return password;
            } else
                return null;
        }, "");
    }

    final public void delete(final Employee employee) {
        executeQuery(Query.DELETE_PASSWORD, statement -> {
            statement.setInt(1, employee.getUID());
            return null;
        }, null);
    }
}
