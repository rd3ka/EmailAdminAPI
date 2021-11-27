package userType;

import java.security.SecureRandom;

public class Employee {

	private String firstName, lastName, department;
	private int tenure, salary;
	private long identificationNum;
	private long employeeUpperLimit = 130000;
	private long employeeLowerLimit = 120000;

	public Employee(String firstName, String lastName, String department, int tenure) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.department = department;
		this.tenure = tenure;
		setIdentificationNum();
	}

	public long getEmployeeUpperLimit() {
		return employeeUpperLimit;
	}

	public void setEmployeeUpperLimit(long employeeUpperLimit) {
		this.employeeUpperLimit = employeeUpperLimit;
	}

	public long getEmployeeLowerLimit() {
		return employeeLowerLimit;
	}

	public void setEmployeeLowerLimit(long employeeLowerLimit) {
		this.employeeLowerLimit = employeeLowerLimit;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public int getTenure() {
		return tenure;
	}

	public void settenure(int tenure) {
		this.tenure = tenure;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public long getIdentificationNum() {
		return identificationNum;
	}

	public void setIdentificationNum() {
		SecureRandom random = new SecureRandom();
		this.identificationNum = random.nextLong(this.employeeUpperLimit - this.employeeLowerLimit)
				+ this.employeeLowerLimit;
	}

	@Override
	public String toString() {
		return String.format("%s %s %d %d %d", this.firstName,
				this.lastName,
				this.salary,
				this.identificationNum,
				this.tenure);
	}
}
