package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/*
 * 
 * The backend database structure will be in this schema type, where the
 * 
 * @param employee_id will be the primary key of the table
 * the @param: created_at and updated_at are meta data params that will help to keep track of the
 * changes in
 * table. The email is set to be unique, which means no too emails are supposed to be the same
 * 
 * +--------------+----------------------+------+-----+-------------------+-------------------------
 * ----+
 * | Field | Type | Null | Key | Default | Extra |
 * +--------------+----------------------+------+-----+-------------------+-------------------------
 * ----+
 * | employee_id | int(11) | NO | PRI | NULL | |
 * | first_name | varchar(50) | NO | | NULL | |
 * | last_name | varchar(50) | NO | | NULL | |
 * | dob | date | NO | | NULL | |
 * | role | varchar(50) | NO | | NULL | |
 * | department | varchar(100) | NO | | NULL | |
 * | email | varchar(100) | NO | UNI | NULL | |
 * | created_at | timestamp | NO | | CURRENT_TIMESTAMP | |
 * | updated_at | timestamp | NO | | CURRENT_TIMESTAMP | on update CURRENT_TIMESTAMP |
 * +--------------+----------------------+------+-----+-------------------+-------------------------
 * ----+
 * ^ Employee Table
 * 
 * In this table, the employee_id acts as the foreign key that references to the employee table,
 * real magic happens the @param encrypted_password which stores the password that is either set or
 * generated but before it gets, stored it is encrypted using [To-do] Encryption Algorithm
 * +-------------------+--------------+------+-----+-------------------+----------------------------
 * -+
 * | Field | Type | Null | Key | Default | Extra |
 * +-------------------+--------------+------+-----+-------------------+----------------------------
 * -+
 * | employee_id | int(11) | NO | PRI | NULL | |
 * | encrypted_password| varchar(255) | NO | | NULL | |
 * | created_at | timestamp | NO | | CURRENT_TIMESTAMP | |
 * | updated_at | timestamp | NO | | CURRENT_TIMESTAMP | on update CURRENT_TIMESTAMP |
 * +-------------------+--------------+------+-----+-------------------+----------------------------
 * -+
 * ^ Password Table
 *
 */

public class Database {
    private final String URL;
    private final String userName, password;
    private Connection connect;

    /*
     * here this constructor is used to pass a database string to get access to the
     * database if we want to do
     * any manipulation on the table level
     */
    public Database(String userName, String password, String database, int port_number) {
        this.URL = new String("jdbc:mysql://localhost:" + port_number + "/" + database);
        this.userName = userName;
        this.password = password;
        this.initialize_db();
    }

    /*
     * here this constructor is made to do manipulation on the database level i.e.
     * if there is no propriate db
     * made, it can be used to create it and made changes to the existing databases
     */
    public Database(String userName, String password, int port_number) {
        this.URL = new String("jdbc:mysql://localhost:" + port_number + "/");
        this.userName = userName;
        this.password = password;
        this.initialize_db();
    }

    public String get_url() {
        return this.URL;
    }

    private void initialize_db() {
        try {
            this.connect = DriverManager.getConnection(this.URL, this.userName, this.password);
            System.out.println("Connection Successful!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection get_db() {
        return this.connect;
    }

}

