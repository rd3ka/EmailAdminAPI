package com.yet.another.Employee;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.yet.another.Database.Database;
import com.yet.another.Database.Query;

public class EmployeeDAO {
	static private Statement statement;

	/* this function is used to create the employee table */
	final public static void createEmployeeTable(final Database database) {
		ResultSet resultSet = null;
		try {
			database.connection().setAutoCommit(false);
			/* disabling the use auto-commit for manual transaction management */
			resultSet = database.connection().getMetaData().getTables(null, null,
					Query.DEFAULT_EMPLOYEE_TABLE, new String[] { "TABLE" });
			/* first we check if the table already exists inside that database */
			final boolean tableExists = resultSet.next();
			/*
			 * storing the data from the resultSet to a boolean variable for future
			 * use/reference
			 */

			if (tableExists) /* if table already exists, we do not need to create it */
				return;
			setStatement(database);
			/* sets query which we want to execute in the database */
			statement.executeUpdate(Query.CREATE_EMP_TABLE);
			/*
			 * ^statement executes the query to create the table where employee information
			 * will be stored the final table name is set to, employee
			 */
			database.connection().commit();
			/*
			 * once the query is processed, we can commit the changes that is made by the
			 * query
			 */
		} catch (final Exception exception) {
			System.err.println("Error: " + exception.getMessage());
			/* we rollback the transaction if any exception occurs */
			try {
				if (database.connection() != null)
					database.connection().rollback();
			} catch (final SQLException rollbackException) {
				System.err.println("Error rolling back to previous transaction: " + rollbackException.getMessage());
				/* in-case in the worst-case scenario, rolling back too does not work */
			}
		} finally {
			/* we do some cleanup i.e closing out resources */
			try {
				if (resultSet != null)
					resultSet.close();
				/* close the resultSet */
				if (database.connection() != null)
					database.connection().close();
				/* close the database connection */
			} catch (final SQLException e) {
				System.out.println("Error closing resources: " + e.getMessage());
			}
		}
	}

	/* this function is used to insert an employee in the employee table */
	final public static void insertEmployee(final Employee e, final Database database) {
		PreparedStatement preparedStatement = null;
		try {
			database.connection().setAutoCommit(false);
			/* disabling the auto-commit to have manual control */
			preparedStatement = setPreparedStatement(database, Query.INSERT_EMP);
			/* sets the query for execution */
			preparedStatement.setInt(1, e.getUid());
			preparedStatement.setString(2, e.getFirstName());
			preparedStatement.setString(3, e.getLastName());
			preparedStatement.setDate(4, Date.valueOf(e.getDob()));
			preparedStatement.setString(5, e.getRole());
			preparedStatement.setString(6, e.getDepartment());
			preparedStatement.setString(7, e.getEmail());
			/*
			 * placing the value which comes from the employee class into the <?>
			 * placeholders in the query in order to have a complete valid query
			 **/
			preparedStatement.execute();
			/*
			 * ^statement executes the query to insert employee data into the employee table
			 */

			database.connection().commit();
			/* once the query has been processed we can commit these changes */

		} catch (final Exception exception) {
			/*
			 * in-case the transaction fails or faces any error, we try to
			 * rollback the changes to the previous transaction
			 */
			try {
				if (database.connection() != null)
					database.connection().rollback();
			} catch (final SQLException rollbackException) {
				System.err.println("Error rolling back to previous transaction " + rollbackException.getMessage());
				/* in-case in the worst-case scenario, rolling back too does not work */
			}

		} finally {
			/* finally, we do some cleanup work */
			try {
				if (preparedStatement != null)
					preparedStatement.close();
				/* close the resultSet */
				if (database.connection() != null)
					database.connection().close();
				/* close the database connection */
			} catch (final Exception exception) {
				exception.printStackTrace();
			}
		}
	}

	final public static void deleteEmployee(final Employee e, final Database database) {
		PreparedStatement preparedStatement = null;
		try {
			database.connection().setAutoCommit(false);
			/* disabling auto commit for more manual control */
			preparedStatement = setPreparedStatement(database, Query.DELETE_EMP);
			/* sets the query for execution */
			preparedStatement.setInt(1, e.getUid());
			/*
			 * ^statement executes the query to delete employee information from the
			 * employee table
			 */
			database.connection().commit();
			/* once the query has been processed we can commit these changes */

		} catch (final Exception exception) {
			/*
			 * in-case the transcation fails or faces any error, we try to rollback the
			 * changes to the previous transcation
			 */
			try {
				if (database.connection() != null)
					database.connection().rollback();
			} catch (final SQLException rollbackException) {
				System.err.println("Error rolling back to previous transcation " + rollbackException.getMessage());
			} /*
				 * in-case, in the worst case scenario, even rolling back does not do anything
				 * good
				 */
		} finally {
			/* finally, we do some cleaup work */
			try {
				if (preparedStatement != null)
					preparedStatement.close();
				/* close the resultSet */
				if (database.connection() != null)
					database.connection().close();
				/* close the database connection */
			} catch (final Exception exception) {
				exception.printStackTrace();
			}
		}
	}

	private static final void setStatement(final Database database) throws Exception {
		statement = database.connection().createStatement();
	}

	private static final PreparedStatement setPreparedStatement(final Database database, final String Query)
			throws Exception {
		return database.connection().prepareStatement(Query);
	}
}
