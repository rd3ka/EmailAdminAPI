package Employee;

import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;
import java.sql.Statement;
import java.sql.PreparedStatement;
// import java.sql.ResultSet;
import Database.*;

/* TO-DO, this class needs well implementation currently missing */

public class EmployeeDAO {
  private Database employeeDatabase;
  private Statement statement;
  private PreparedStatement preparedStatement;

  public EmployeeDAO(Database database) {
    this.employeeDatabase = database;
  }

  private final void setStatement() throws Exception {
    this.statement = this.employeeDatabase.get_db().createStatement();
  }

  private final void setPreparedStatement(String Query) throws Exception {
    this.preparedStatement = this.employeeDatabase.get_db().prepareStatement(Query);
  }

  /* this function is used to create the employee table */
  final public void createEmployeeTable() {
    try {
      this.setStatement();
      statement.execute(Query.CREATE_YetAnotherDatabase);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
