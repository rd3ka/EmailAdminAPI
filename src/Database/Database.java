package Database;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
    private final String URL;
    private final String userName, password;
    private Connection connect;
    public Database(String userName, String password, String database, int port_number) {
        this.URL = new String("jdbc:mysql://localhost:" + port_number + "/" + database);
        this.userName = userName;
        this.password = password;
    }

    public String get_url() { return this.URL; }

    public Connection initialize() {
        try {
            this.connect = DriverManager.getConnection(this.URL, this.userName, this.password);
            System.out.println("Connection Successful!");
        } catch(Exception e) { e.printStackTrace(); }

        return this.connect;
    }
    
}