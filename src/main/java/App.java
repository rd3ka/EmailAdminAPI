import Database.Database;
import Employee.*;

public class App {
  public static void main(String[] args) throws Exception {
    Database database = new Database("rdeka", "rd141999", 3306);
    database.get_db().close();
  }
}
