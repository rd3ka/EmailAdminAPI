package com.yet.another.Employee;

import java.sql.Statement;
import java.sql.Date;
import java.sql.PreparedStatement;

import com.yet.another.Database.*;

/* TO-DO, this class needs well implementation currently missing */

public class EmployeeDAO {
	static private Statement statement;

	private static final void setStatement(Database employeeDatabase) throws Exception {
		statement = employeeDatabase.get_content().createStatement();
	}

	private static final PreparedStatement setPreparedStatement(Database employeeDatabase, String Query)
			throws Exception {
		return employeeDatabase.get_content().prepareStatement(Query);
	}

	/* this function is used to create the employee database */
	final public static void createEmployeeDatabase(Database database) {
		try {
			setStatement(database);
			/* sets the backend for the creation of the employee database */
			statement.execute(Query.CREATE_YetAnotherDatabase);
			/*
			 * ^statement executes the query to create the actual database the final
			 * database name is set to, YetAnotherDatabase
			 */
			// database.get_content().close();
			/*
			 * once the database creation is done, we can close the jdbc connection with
			 * database
			 */
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("YetAnotherDatabase Got Created Succesfully!");
			/*
			 * the sysout tells us the successful execution of the try blcok without any
			 * error
			 */
		}
	}

	/* this function is used to create the employee table */
	final public static void createEmployeeTable(Database employeeDatabase) {
		try {
			setStatement(employeeDatabase);
			/* sets query which we want to execute in the database */
			statement.executeUpdate(Query.CREATE_EMP_TABLE);
			/*
			 * ^statement executes the query to create the table where employee information
			 * will be stored the final table name is set to, employee
			 */
			// employeeDatabase.get_content().close();
			/*
			 * once the table creation is done, we can close the jdbc connection from the
			 * database
			 */
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("employee table just got created succesfully");
			/*
			 * the sysout tells us the successfull execution of the try block without any
			 * error
			 */
		}
	}

	/* this function is used to insert an employee in the employee table */
	final public static void insertEmployee(Employee e, Database employeeDatabase) {
		try {
			PreparedStatement prep_state = setPreparedStatement(employeeDatabase, Query.INSERT_EMP);
			/* sets the query for execution */
			prep_state.setInt(1, e.getUid());
			prep_state.setString(2, e.getFirstName());
			prep_state.setString(3, e.getLastName());
			prep_state.setDate(4, Date.valueOf(e.getDob()));
			prep_state.setString(5, e.getRole());
			prep_state.setString(6, e.getDepartment());
			prep_state.setString(7, e.getEmail());
			/*
			 * placing the value which comes from the employee class into the <?>
			 * placeholders in the query in order to have a complete valid query
			 **/
			prep_state.execute();
			/*
			 * ^statement executes the query to insert employee data into the employee table
			 */
			// employeeDatabase.get_content().close();

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			/*
			 * the sysout tells us the successfull execution of the try block without any
			 * exceptions
			 */System.out.println("Employee Information Inserted successfullly!");
		}
	}
}
