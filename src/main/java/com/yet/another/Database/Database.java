package com.yet.another.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

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
 * | email        | varchar(100) | NO    |    UNI  | NULL    |            |
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

public class Database {

    private String URL;
    private final String userName, password;
    private Connection connect;
    public int DEFAULT_PORT = 3306; /* The default port is always set to 3306 */

    /*
     * here this constructor is made to do manipulation on the database level i.e.
     * if there is no appropriate db
     * made, it can be used to create it and made changes to the existing databases
     */
    public Database(final String userName, final String password) {
        this.userName = userName;
        this.password = password;
        this.URL = String.format("jdbc:mariadb://localhost:%d/", this.DEFAULT_PORT);
        this.init();
        /*
         * if default database does not exists, then we first make the database before
         * initializing the default database
         */
        if (!databaseCheck())
            createDatabase();
        this.URL = String.format("jdbc:mariadb://localhost:%d/%s", this.DEFAULT_PORT, Query.DEFAULT_DATABASE);
        this.init();
    }

    /* returns the final URL; can be used of debug */
    public final String get_url() {
        return this.URL;
    }

    /*
     * no params, init() func is used to connect the DriverManager with the Database
     * using the URL that we made
     */
    public final void init() {
        try {
            this.connect = DriverManager.getConnection(this.URL, this.userName, this.password);
            System.out.println("Connection Successful!");
        } catch (final Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /* this is a joke; but probably necessary */
    public Connection connection() {
        return this.connect;
    }

    /* setter func for port number */
    final public void setDefaultPortNumber(final int portNumber) {
        this.DEFAULT_PORT = portNumber;
    }

    /* getter userName (kept for debugging) */
    final public String getDatabaseUsername() {
        return this.userName;
    }

    /* getter password (kept for debugging) */
    final public String getDatabasePassword() {
        return this.password;
    }

    /*
     * no params func that returns void -> creates a default database if there is no
     * database present to work on
     */
    private final void createDatabase() {
        try {
            final Statement statement = this.connect.createStatement();
            statement.execute(Query.CREATE_DATABASE);
            System.out.println("Database successfully created!");
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * no params func that returns void -> checks if at all we need to make a
     * database to begin with
     */
    private final boolean databaseCheck() {
        try {
            final ResultSet resultSet = this.connect.getMetaData().getCatalogs();
            while (resultSet.next()) {
                if (resultSet.getString(1).equals(Query.DEFAULT_DATABASE))
                    return true;
            }
        } catch (final Exception exception) {
            exception.printStackTrace();
        }
        return false;
    }

    /*
     * This method is specifically made to handle and close the database connection
     * once the transaction is complete
     */
    public final boolean handle(Database database) {
        try {
            if (database.connection() != null) {
                database.connection().close();
            }
            return database.connection().isClosed();
        } catch (final Exception exception) {
            exception.printStackTrace();
        }
        return false;
    }
}
