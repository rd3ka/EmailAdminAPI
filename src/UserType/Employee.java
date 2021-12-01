package UserType;

import java.security.SecureRandom;
import java.util.AbstractMap;

public class Employee extends User implements cmdMapToDesc {

	private String department, position;
	private int tenure;
	private Double salary;
	private long identificationNum,
	        employeeUpperLimit,
	        employeeLowerLimit;

	private AbstractMap.SimpleEntry<String, Double> baseToPosition;

	public Employee(String firstName,
	                String lastName,
	                String dateOfBirth) {

		super(firstName, lastName, dateOfBirth);
		this.employeeUpperLimit = 130000;
		this.employeeLowerLimit = 120000;

		if (System.console().printf("Do you want to provide base salary of your position? y/N : ")
		        .readLine()
		        .equalsIgnoreCase("y")) {

			/* Department is a dependency! */
			setDepartment();

			this.baseToPosition = new AbstractMap.SimpleEntry<>(
			    System.console().printf("Enter position : ").readLine(),
			    Double.parseDouble(System.console().printf("Enter salary : ").readLine()));

		}

		if (System.console().printf("Do you want to change/add/provide additional details? y/N : ")
		        .readLine()
		        .equalsIgnoreCase("y")) {

			FunctionMapping();

			do {
				cmdMap.get(System.console().printf("Enter Choice : ").readLine()).run();
			} while (System.console().printf("Do you wish to continue? y/N : ")
			         .readLine()
			         .equalsIgnoreCase("y"));
		}
	}

	public String getPosition() {
		return position;
	}

	public void setPosition() {
		this.position = this.baseToPosition.getKey();
	}

	public long getEmployeeUpperLimit() {
		return employeeUpperLimit;
	}

	public void setEmployeeUpperLimit(long... employeeUpperLimit) {
		if (employeeUpperLimit.length == 0)
			this.employeeUpperLimit = Long.parseLong(System.console()
			                          .printf("Enter unique ID upper limit : ").readLine());
		else
			this.employeeUpperLimit = employeeUpperLimit[0];
	}

	public long getEmployeeLowerLimit() {
		return employeeLowerLimit;
	}

	public void setEmployeeLowerLimit(long... employeeLowerLimit) {
		if (employeeLowerLimit.length == 0)
			this.employeeLowerLimit = Long.parseLong(System.console()
			                          .printf("Enter uniquee ID lower limit : ").readLine());
		else
			this.employeeLowerLimit = employeeLowerLimit[0];
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String... department) {
		if (department.length == 0)
			this.department = System.console().printf("What is your department of work? : ")
			                  .readLine();
		else
			this.department = department[0];
	}

	public int getTenure() {
		return tenure;
	}

	public void settenure(int... tenure) {
		if (tenure.length == 0)
			this.tenure = Integer.parseInt(System.console()
			                               .printf("Enter the number of years in the organization : ")
			                               .readLine());
		else
			this.tenure = tenure[0];
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double... salary) {
		if (salary.length == 0)
			this.salary = this.baseToPosition.getValue();
		else
			this.salary = salary[0];
	}

	public long getIdentificationNum() {
		return identificationNum;
	}

	public void setIdentificationNum() {
		SecureRandom random = new SecureRandom();
		this.identificationNum = random.nextLong(this.employeeUpperLimit - this.employeeLowerLimit)
		                         + this.employeeLowerLimit;
		System.console().printf("New Identification Set!\n");
	}

	@Override
	public String toString() {
		return String.format("%s %s %d %d %d", this.getFirstName(),
		                     this.getLastName(),
		                     this.salary,
		                     this.identificationNum,
		                     this.tenure);
	}

	@Override
	public void FunctionMapping() {
		cmdMap.put(commandToDescription.apply("sD", "\tsD -> \tSet Department\n"), () -> setDepartment());
		cmdMap.put(commandToDescription.apply("sEUL", "\tsEUL -> Change Unique ID Upper Limit\n"),
		           () -> setEmployeeUpperLimit());
		cmdMap.put(commandToDescription.apply("sELL", "\tsELL -> Change Unique ID Lower Limit\n"),
		           () -> setEmployeeLowerLimit());
		cmdMap.put(commandToDescription.apply("sP", "\tsP -> \tChange Position\n"), () -> setPosition());
		cmdMap.put(commandToDescription.apply("sT", "\tsT -> \tSet Tenure\n"), () -> settenure());
		cmdMap.put(commandToDescription.apply("sID", "\tsID -> \tChange/Set UniqueID\n"), () -> setIdentificationNum());
	}
}
