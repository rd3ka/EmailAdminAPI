package com.yet.another.Database.DatabaseUtils.Utility;

/* TO-DO: change from String Concatenation type to String.format() usage */

public class Query extends Defaults {

	/*
	 * Default table to store employee information will always be employee
	 */
	final static public String DEFAULT_EMPLOYEE_TABLE = "employee";
	/*
	 * Default table to store passwords will always be password
	 */
	final static public String DEFAULT_PASSWORD_TABLE = "password";

	final static public String CREATE_DATABASE = String.format("CREATE DATABASE %s ;", DEFAULT_DATABASE);

	final static public String CREATE_EMPLOYEE_TABLE = String.format(
			"CREATE TABLE %s (employee_id INT PRIMARY KEY, first_name VARCHAR(50) NOT NULL, last_name VARCHAR(50) NOT NULL, dob DATE, role VARCHAR(50), department VARCHAR(100), org VARCHAR(25) NOT NULL, created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP)",
			DEFAULT_EMPLOYEE_TABLE);

	/* Create an entry to the employee table, C - Create */
	final static public String INSERT_EMPLOYEE = String.format(
			"INSERT INTO %s (employee_id, first_name, last_name, dob, role, department, org) VALUES (?, ?, ?, ?, ?, ?, ?)",
			DEFAULT_EMPLOYEE_TABLE);

	/* Select Queries for reading key attributes, R - Read */
	final static public String READ_EMPLOYEE_ALL = String.format("SELECT * FROM %s", DEFAULT_EMPLOYEE_TABLE);

	/* Select Query for reading a specific employee of employee_id */
	final static public String READ_EMPLOYEE = String.format("SELECT * FROM %s WHERE employee_id = ?",
			DEFAULT_EMPLOYEE_TABLE);

	/* Update queries to the employee table, U - Update */
	final static public String UPDATE_EMPLOYEE_FIRST_NAME = String.format(
			"UPDATE %s SET first_name = ? WHERE employee_id = ?",
			DEFAULT_EMPLOYEE_TABLE);

	/* Update queries to the employee table, U - Update */
	final static public String UPDATE_EMPLOYEE_LAST_NAME = String.format(
			"UPDATE %s SET last_name = ? WHERE employee_id = ?",
			DEFAULT_EMPLOYEE_TABLE);

	/* Update queries to the employee table, U - Update */
	final static public String UPDATE_EMPLOYEE_DOB = String.format(
			"UPDATE %s SET dob = ? WHERE employee_id =",
			DEFAULT_EMPLOYEE_TABLE);

	/* Update queries to the employee table, U - Update */
	final static public String UPDATE_EMPLOYEE_ROLE = String.format(
			"UPDATE %s SET role = ? WHERE employee_id =",
			DEFAULT_EMPLOYEE_TABLE);

	/* Update queries to the employee table, U - Update */
	final static public String UPDATE_EMPLOYEE_DEPARTMENT = String.format(
			"UPDATE %s SET department = ? WHERE employee_id =",
			DEFAULT_EMPLOYEE_TABLE);

	/* Update queries to the employee table, U - Update */
	final static public String UPDATE_EMPLOYEE_EMAIL = String.format(
			"UPDATE %s SET org = ? WHERE employee_id =",
			DEFAULT_EMPLOYEE_TABLE);

	final static public String DELETE_EMPLOYEE = String.format(
			"DELETE FROM %s WHERE employee_id = ?",
			DEFAULT_EMPLOYEE_TABLE);

	/* we create the password table */
	final static public String CREATE_PASSWORD_TABLE = String.format(
			"CREATE TABLE %s (employee_id INT PRIMARY KEY REFERENCES employee(employee_id) ON DELETE CASCADE, encrypted_password VARCHAR(255) NOT NULL, created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP)",
			DEFAULT_PASSWORD_TABLE);
	/* creates a new entry in the password table */
	final static public String INSERT_PASSWORD = String
			.format("INSERT INTO %s (employee_id, encrypted_password) VALUES (?,?)", DEFAULT_PASSWORD_TABLE);

	/* updates the password for a particular employee_id */
	final static public String UPDATE_PASSWORD = String
			.format("UPDATE %s SET encrypted_password = ? WHERE employee_id = ?", DEFAULT_PASSWORD_TABLE);

	/* returns the encrypted_password for a particular employee id */
	final static public String READ_PASSWORD = String.format("SELECT encrypted_password FROM %s WHERE employee_id = ?",
			DEFAULT_PASSWORD_TABLE);

	/* deletes the password entry for a particular employee_id */
	final static public String DELETE_PASSWORD = String.format(
			"DELETE FROM %s WHERE employee_id = ?",
			DEFAULT_PASSWORD_TABLE);
}
