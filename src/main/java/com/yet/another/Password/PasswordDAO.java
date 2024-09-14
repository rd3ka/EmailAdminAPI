package com.yet.another.Password;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.yet.another.Database.Database;
import com.yet.another.Database.Query;
import com.yet.another.Employee.Employee;

public class PasswordDAO {
	private static Statement statement;

	/* this function creates the password table in YetAnotherDatabase */
	final public static void createPasswordTable(final Database database) {
		ResultSet resultSet = null;
		try {
			database.connection().setAutoCommit(false);
			/* setting auto commit off to get more manual control */
			resultSet = database.connection()
					.getMetaData()
					.getTables(null,
							null,
							Query.DEFAULT_PASSWORD_TABLE,
							new String[] { "TABLE" });
			/* first before creating the password table, we check if it already exists */

			final boolean tableExists = resultSet.next(); /*
															 * storing the result from the the resultSet to a boolean
															 * type variable for future use/reference
															 */
			if (tableExists)
				return;
			/*
			 * checking if the table exists, if it does, then we do not need to create this
			 * table once again
			 */
			setStatement(database);
			/* sets query which we want to execute in the database */
			statement.executeUpdate(Query.CREATE_PASSWORD_TABLE);
			/*
			 * ^statement executes the query to create the table where password information
			 * will be stored the final table name is set to, employee
			 */
			database.connection().commit();
			/*
			 * once the query is processed, we can commit the changes that is made
			 * by the query
			 */
		} catch (final Exception exception) {
			System.err.println("Error: " + exception.getMessage());
			/* we rollback the transaction if any exception occurs */
			try {
				if (database.connection() != null)
					database.connection().rollback();
			} catch (final SQLException rollbackException) {
				System.err.println("Erroring rolling back to previous transaction " + rollbackException.getMessage());
				/* in-case in the worst-case scenario, rolling back too does not work */
			}
		} finally {
			/* we do some cleanup i.e closing out resources */
			try {
				if (statement != null)
					statement.close();
				/* closet the statement */
				if (database.connection() != null)
					database.connection().close();
				/* close the database connection */
			} catch (final SQLException e) {
				System.out.println("Error closing resources: " + e.getMessage());
			}
		}
	}

	final public static void insertPassword(final Database database, final Employee e, final Password p) {
		PreparedStatement preparedStatement = null;
		try {
			database.connection().setAutoCommit(false);
			/* disabling auto commit for more manual control */
			preparedStatement = setPreparedStatement(database, Query.INSERT_PASSWORD);
			/* sets the query for execution */
			preparedStatement.setInt(1, e.getUid());
			preparedStatement.setString(2, p.getEncryptedPassword());
			/*
			 * placing the value which comes from the employee class into the <?>
			 * Placeholder in the query in order to have a complete valid query
			 **/
			preparedStatement.execute();
			/*
			 * ^statement executes the query to insert password data into the password table
			 */
			database.connection().commit();
			/* once the query has been processed we can commit these changes */

		} catch (final Exception exception) {
			/*
			 * in-case the transaction fails or faces any error, we try to rollback the
			 * changes the previous
			 * transaction
			 */
			try {
				if (database.connection() != null)
					database.connection().rollback();
			} catch (final SQLException rollbackException) {
				System.err.println("Error rolling back to previous transaction " + rollbackException.getMessage());
			}
			/*
			 * in-case, in the worst case scenario, even rolling back does not do anything
			 * good
			 */
		} finally {
			/* finally, we do some clean-up */
			try {
				if (preparedStatement != null)
					preparedStatement.close();
				/* we close the preparedStatement */
				if (database.connection() != null)
					database.connection().close();
				/* we close the database connection */
			} catch (final Exception exception) {
				exception.printStackTrace();
			}
		}
	}

	final public static void updatePassword(final Database database, final Employee em, final Password p) {
		PreparedStatement preparedStatement = null;
		try {
			database.connection().setAutoCommit(false);
			/* disabling auto commit for more manual control */
			preparedStatement = setPreparedStatement(database, Query.UPDATE_PASSWORD);
			/* sets the query for execution */
			preparedStatement.setString(1, p.getEncryptedPassword());
			preparedStatement.setInt(2, em.getUid());
			/*
			 * placing the value which comes from the employee class into the <?>
			 * Placeholder in the query in order to have a complete valid query
			 **/
			preparedStatement.execute();
			/*
			 * ^statement executes the query to update the password data into the password
			 * table
			 */
			database.connection().commit();
			/* once the query has been processed we can commit these changes */
		} catch (final Exception e) {
			/*
			 * in-case the transaction fails or faces any error, we try to rollback the
			 * changes to the previous transaction
			 */
			try {
				if (database.connection() != null)
					database.connection().rollback();
			} catch (final SQLException rollbackException) {
				System.err.println("Error rolling back to previous transaction " + rollbackException.getMessage());
			}
			/* in-case, in the worst case scenario, even rolling back does not help */
		} finally {
			/* finally, we do some clean-up work */
			try {
				if (preparedStatement != null)
					preparedStatement.close();
				/* we close the preparedStatement */
				if (database.connection() != null)
					database.connection().close();
				/* we close the database connection */
			} catch (final Exception exception) {
				exception.printStackTrace();
			}
		}
	}

	final public static void deletePassword(final Database database, final Employee em) {
		PreparedStatement preparedStatement = null;
		try {
			database.connection().setAutoCommit(false);
			/* disabling the auto-commit to have manual control */
			preparedStatement = setPreparedStatement(database, Query.DELETE_PASSWORD);
			/* sets the query for execution */
			preparedStatement.setInt(1, em.getUid());
			/*
			 * placing the value which comes from the employee class into the <?>
			 * Placeholder in the query in order to have a complete valid query
			 **/
			preparedStatement.execute();
			/*
			 * ^statement executes the query to delete the password data associated with the
			 * employee_id from the password table
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
			/* finally, we do some clean-up work */
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

	private static final void setStatement(final Database passwordDatabase) throws Exception {
		statement = passwordDatabase.connection().createStatement();
	}

	private static final PreparedStatement setPreparedStatement(final Database passwordDatabase, final String Query)
			throws Exception {
		return passwordDatabase.connection().prepareStatement(Query);
	}
}
