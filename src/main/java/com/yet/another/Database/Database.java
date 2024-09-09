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

    /*
     * here this constructor is made to do manipulation on the database level i.e.
     * if there is no appropriate db
     * made, it can be used to create it and made changes to the existing databases
     */
    public Database(String userName, String password, int port_number) {
        this.userName = userName;
        this.password = password;
        this.URL = new String("jdbc:mariadb://localhost:" + port_number + "/");
        this.initialize_db();
        /* To-do implement better condition for making the if-case smaller */
        if (databaseCheck()) {
            this.URL = new String("jdbc:mariadb://localhost:" + port_number + "/" + Query.DEFAULT_DATABASE);
            initialize_db();
        } else {
            createDatabase();
            this.URL = new String("jdbc:mariadb://localhost:" + port_number + "/" + Query.DEFAULT_DATABASE);
            this.initialize_db();
        }
        /* we initialize the connect with the created database again */
    }

    public final String get_url() {
        return this.URL;
    }

    private final void initialize_db() {
        try {
            this.connect = DriverManager.getConnection(this.URL, this.userName, this.password);
            System.out.println("Connection Successful!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private final void createDatabase() {
        try {
            Statement statement = this.connect.createStatement();
            statement.execute(Query.CREATE_DATABASE);
            System.out.println("Database successfully created!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private final boolean databaseCheck() {
        try {
            ResultSet resultSet = this.connect.getMetaData().getCatalogs();
            while (resultSet.next()) {
                if (resultSet.getString(1).equals(Query.DEFAULT_DATABASE))
                    return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Connection get_content() {
        return this.connect;
    }

}
