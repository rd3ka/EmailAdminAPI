package Database;

public class Query {
    /*
     * Here we write all the queries related to the Employee
     * and the Password Table
     */

    final private String CREATE_EMP_TABLE = "CREATE TABLE employee ( "
            + "employee_id INT PRIMARY KEY,"
            + "first_name VARCHAR(50) NOT NULL, "
            + "last_name VARCHAR(50) NOT NULL, "
            + "dob DATE, "
            + "role VARCHAR(50), "
            + "department VARCHAR(100), "
            + "email VARCHAR(100) UNIQUE NOT NULL, "
            + "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, "
            + "updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP "
            + ")";

    /* Create an entry to the employee table, C - Create */
    final private String INSERT_EMP = "INSERT INTO employee ( "
            + "(first_name, last_name, dob, role, department, email) "
            + "(?, ?, ?, ?, ?, ? )";

    /* Select Queries for reading key attributes, R - Read */
    final private String READ_EMP_ALL = "SELECT * FROM employee";
    /* Update queries to the employee table, U - Update */
    final private String UPDATE_EMP_first_name = "UPDATE employee"
            + "SET first_name = ?"
            + "WHERE employee_id = ?";
    /* Update queries to the employee table, U - Update */
    final private String UPDATE_EMP_last_name = "UPDATE employee"
            + "SET last_name = ?"
            + "WHERE employee_id = ?";
    /* Update queries to the employee table, U - Update */
    final private String UPDATE_EMP_dob = "UPDATE employee "
            + "SET dob = ?"
            + "WHERE employee_id = ?";
    /* Update queries to the employee table, U - Update */
    final private String UPDATE_EMP_role = "UPDATE employee "
            + "SET role = ?"
            + "WHERE employee_id = ?";
    /* Update queries to the employee table, U - Update */
    final private String UPDATE_EMP_department = "UPDATE employee"
            + "SET department = ?"
            + "WHERE employee_id = ?";
    /* Update queries to the employee table, U - Update */
    final private String UPDATE_EMP_email = "UPDATE employee"
            + "SET email = ?"
            + "WHERE employeeid = ?";

    final private String CREATE_PASS_TABLE = "CREATE TABLE passowrd ( "
            + "employee_id INT PRIMARY KEY REFERENCES employee(employee_id) ON DELETE CASCADE, "
            + "encrypted_password VARCHAR(255) NOT NULL, "
            + "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, "
            + "updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP "
            + ")";
}
