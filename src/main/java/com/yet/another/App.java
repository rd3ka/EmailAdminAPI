package com.yet.another;

/**
 * Hello world!
 *
 */
import java.time.LocalDate;

import com.yet.another.Database.Database;
import com.yet.another.Employee.Employee;
import com.yet.another.Employee.EmployeeDAO;

public class App {
  public static void main(String[] args) throws Exception {
    Database database = new Database("rdeka", "rd141999", 3306);
    EmployeeDAO.createEmployeeDatabase(database);
    Database employeeDatabase = new Database("rdeka", "rd141999", "YetAnotherDatabase", 3306);
    EmployeeDAO.createEmployeeTable(employeeDatabase);
    EmployeeDAO.insertEmployee(new Employee("Raktim", "Deka", LocalDate.of(1999, 9, 14), "wipro"), employeeDatabase);
  }
}
