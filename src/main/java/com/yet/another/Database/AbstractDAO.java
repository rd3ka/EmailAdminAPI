package com.yet.another.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.yet.another.Database.DatabaseUtils.SQLExecutor;
import com.yet.another.Database.DatabaseUtils.SQLStatementExecutor;
import com.yet.another.Database.DatabaseUtils.Utility.Query;

/*
 *
 * The backend database structure will be in this schema type, where the
 *
 * @param employee_id will be the primary key of the table
 * the @param: created_at and updated_at are meta data params that will help to keep track of the
 * changes in table. The email is set to be unique, which means no too emails are supposed to be the same
 *
 * +--------------+----------------------+------+-----+-------------------+-----------------------------+
 * | Field        | Type         | Null  | Key     | Default | Extra      |
 * +--------------+----------------------+------+-----+-------------------+-----------------------------+
 * | employee_id  | int(11)      | NO    |     PRI | NULL    |            |
 * | first_name   | varchar(50)  | NO    |         | NULL    |            |
 * | last_name    | varchar(50)  | NO    |         | NULL    |            |
 * | dob          | date         | NO    |         | NULL    |            |
 * | role         | varchar(50)  | NO    |         | NULL    |            |
 * | department   | varchar(100) | NO    |         | NULL    |            |
 * | organization | varchar(50)  | NO    |         | NOT NULL|            |
 * | created_at   | timestamp    | NO    |         | CURRENT_TIMESTAMP    |
 * | updated_at   | timestamp    | NO    |         | CURRENT_TIMESTAMP    | on update CURRENT_TIMESTAMP |
 * +--------------+----------------------+------+-----+-------------------+-----------------------------+
 *                                              ^ Employee Table
 *
 * In this table, the employee_id acts as the foreign key that references to the employee table,
 * real magic happens the @param encrypted_password which stores the password that is either set or
 * generated but before it gets, stored it is encrypted using [To-do] Encryption Algorithm
 *
 * +-----------------------+--------------+------+-----+-------------------+-----------------------------+
 * | Field              | Type           | Null | Key | Default | Extra    |
 * +-------------------+--------------+------+-----+-------------------+---------------------------------+
 * | employee_id        | int(11)        | NO   | PRI | NULL    |          |
 * | encrypted_password | varchar(255)   | NO   |     | NULL    |          |
 * | created_at         | timestamp      | NO   |     | CURRENT_TIMESTAMP  |                             |
 * | updated_at         | timestamp      | NO   |     | CURRENT_TIMESTAMP  | on update CURRENT_TIMESTAMP |
 * +-------------------+--------------+------+-----+-------------------+---------------------------------+
 *                                              ^ Password Table
 *
 */

public abstract class AbstractDAO<T> {
    protected String username = "";
    protected String password = "";

    public void executeUpdate(final String Query, final SQLExecutor<Void> executor) {
        try (Connection connection = get_Connection()) {
            if (Query.contains("?")) {
                /* Use preparedStatement if placeholders are found */
                try (PreparedStatement preparedStatement = connection.prepareStatement(Query)) {
                    executor.execute(preparedStatement);
                    preparedStatement.executeUpdate();
                    connection.close();
                }
            } else {
                /* Use statement if no placeholders are found */
                try (Statement statement = connection.createStatement()) {
                    statement.executeUpdate(Query);
                }
                connection.close();
            }
        } catch (final Exception exception) {
            System.out.println(exception.getMessage());
            System.exit(1);
        }
    }

    /* The first thing we should do is to set the username and password */
    protected void setUsername(final String username) {
        this.username = username;
    }

    protected void setPassword(final String password) {
        this.password = password;
    }

    protected Connection get_Connection() {
        String URL = Query.DEFAULT_URL;
        if (username.isEmpty() || password.isEmpty()) {
            if (username.isEmpty()) {
                System.out.println("Please provide a user with the necessary rights to connect to the database");
                System.exit(1);
            }
            if (password.isEmpty()) {
                System.out.println("Please provide a password for the following user");
                System.exit(1);
            }
        }
        final Connection connection = this.init(URL, username, password);
        if (databaseCheck(connection) == false)
            this.createDatabase(connection);

        URL = String.format("%s/%s", URL, Query.DEFAULT_DATABASE);
        try {
            return this.init(URL, username, password);
        } catch (final Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    protected void closeResources(final AutoCloseable... resources) {
        for (final AutoCloseable resource : resources) {
            if (resource != null) {
                try {
                    resource.close();
                } catch (final Exception exception) {
                    exception.printStackTrace();
                }
            }
        }
    }

    /*
     * Method to handle 'preparedStatement' for queries that give a result with
     * placeholders
     */
    protected <R> R executeQuery(final String Query, final SQLExecutor<R> executor, final String indt) {
        R result = null;
        try (Connection connection = get_Connection();
                    PreparedStatement preparedStatement = connection.prepareStatement(Query)) {
            result = executor.execute(preparedStatement);
            connection.close();
        } catch (final SQLException exception) {
            exception.printStackTrace();
            System.exit(1);
        }
        return result;
    }

    /*
     * Method to handle 'statement' for queries that give a result without a
     * placeholder
     */
    protected <R> R executeQuery(final String Query, final SQLStatementExecutor<R> executor, final boolean indt) {
        R result = null;
        try (Connection connection = get_Connection();
                    Statement statement = connection.createStatement()) {
            result = executor.execute(statement);
            connection.close();
        } catch (final SQLException exception) {
            exception.printStackTrace();
            System.exit(1);
        }
        return result;
    }

    /**
     * @param URL
     * @param username
     * @param password
     * @return SQL Connection object,
     * the primary responsibility of this function is to establish a connection between the database program
     * and the java source code, via the connector/client
     */
    private final Connection init(final String URL, final String username, final String password) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, username, password);
        } catch (final Exception exception) {
            exception.printStackTrace();
            System.exit(1);
        }
        return connection;
    }

    private final void createDatabase(final Connection connection) {
        try {
            final Statement statement = connection.createStatement();
            statement.execute(Query.CREATE_DATABASE);
            System.out.println("Database Successfully Created!");
        } catch (final Exception exception) {
            exception.printStackTrace();
        }
    }

    private final boolean databaseCheck(final Connection connection) {
        try {
            final ResultSet resultSet = connection.getMetaData().getCatalogs();
            while (resultSet.next()) {
                if (resultSet.getString(1).equals(Query.DEFAULT_DATABASE))
                    return true;
            }
        } catch (final Exception exception) {
            exception.printStackTrace();
        }
        return false;
    }

}
