package com.yet.another.Password;

import java.sql.PreparedStatement;
import java.sql.Statement;

import com.yet.another.Database.*;
import com.yet.another.Employee.Employee;

public class PasswordDAO {
	private static Statement statement;

	private static final void setStatement(Database passwordDatabase) throws Exception {
		statement = passwordDatabase.get_content().createStatement();
	}

	private static final PreparedStatement setPreparedStatement(Database passwordDatabase, String Query)
			throws Exception {
		return passwordDatabase.get_content().prepareStatement(Query);
	}

	/* this function creates the password table in the YetAnotherDatabase */
	final public static void createPasswordTable(Database database) {
		try {
			setStatement(database);
			/* sets query which we want to execute in the database */
			statement.executeUpdate(Query.CREATE_PASSWORD_TABLE);
			/*
			 * ^statement executes the query to create the table where password information
			 * will be stored the final table name is set to, employee
			 */
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("Password Table Successfully created!");
			/*
			 * the sysout tells us the successful execution of the try block without any
			 * error
			 */
		}
	}

	final public static void insertPassword(Database database, Employee e, Password p) {
		try {
			PreparedStatement prep_state = setPreparedStatement(database, Query.INSERT_PASSWORD);
			/* sets the query for execution */
			prep_state.setInt(1, e.getUid());
			prep_state.setString(2, p.getEncryptedPassword());
			/*
			 * placing the value which comes from the employee class into the <?>
			 * Placeholder in the query in order to have a complete valid query
			 **/
			prep_state.execute();
			/*
			 * ^statement executes the query to insert password data into the password table
			 */
		} catch (Exception ep) {
			/*
			 * the sysout tells us the successfull execution of the try block without any
			 * exceptions
			 */
			ep.printStackTrace();
		}
	}

	final public static void updatePassword(Database database, Employee em, Password p) {
		try {
			PreparedStatement prep_state = setPreparedStatement(database, Query.UPDATE_PASSWORD);
			/* sets the query for execution */
			prep_state.setString(1, p.getEncryptedPassword());
			prep_state.setInt(2, em.getUid());
			/*
			 * placing the value which comes from the employee class into the <?>
			 * Placeholder in the query in order to have a complete valid query
			 **/
			prep_state.execute();
			/*
			 * ^statement executes the query to update the password data into the password
			 * table
			 */
		} catch (Exception e) {
			/*
			 * the sysout tells us the successfull execution of the try block without any
			 * exceptions
			 */
			e.printStackTrace();
		}
	}

	final public static void deletePassword(Database database, Employee em) {
		try {
			PreparedStatement prep_state = setPreparedStatement(database, Query.DELETE_PASSWORD);
			/* sets the query for execution */
			prep_state.setInt(1, em.getUid());
			/*
			 * placing the value which comes from the employee class into the <?>
			 * Placeholder in the query in order to have a complete valid query
			 **/
			prep_state.execute();
			/*
			 * ^statement executes the query to delete the password data associated with the
			 * employee_id from the password table
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
