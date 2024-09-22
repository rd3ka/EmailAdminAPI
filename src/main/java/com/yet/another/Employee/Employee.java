package com.yet.another.Employee;

import java.time.LocalDate;

public class Employee {

	private String firstName, lastName;
	private String role, department, domain;
	private LocalDate dob;
	private int UID;
	String emailAddress;

	public Employee() {
		/*
		 * This is an empty constructor, it will be used when we want to create a
		 * transferable object
		 * and the members of this object will be set using setters
		 */
	}

	/*
	 * Secondary constructor having:
	 * 
	 * @param: firstName - stores the firstName of the employee
	 * 
	 * @param: lastName - stores the lastName of the employee
	 * 
	 * @param: dob - stores the date of birth of the employee
	 * 
	 * @param: domain - stores the main domain i.e the organization name or company
	 * name
	 */
	public Employee(String firstName, String lastName, LocalDate dob, String domain) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.role = " ";
		this.department = " ";
		this.domain = domain;
		this.setUID();
		this.setEmailAddress();
	}

	/*
	 * Primary constructor having:
	 * 
	 * @param: firstName - stores the firstName of the employee
	 * 
	 * @param: lastName - stores the lastName of the employee
	 * 
	 * @param: dob - stores the date of birth of the employee
	 * 
	 * @param: domain - stores the main domain i.e the organization name or company
	 * name
	 * 
	 * @param: role - that stores the role of the employee
	 * 
	 * @param: department - that stores which department/BU/ServiceLine is the
	 * employee working
	 */
	public Employee(String firstName, String lastName, String role, String department, LocalDate dob, String domain) {
		this(firstName, lastName, dob, domain);
		this.role = role;
		this.department = department;
		this.setUID();
		this.setEmailAddress();
	}

	/* firstName getter */
	public String getFirstName() {
		return firstName;
	}

	/* firstName setter */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/* lastName getter */
	public String getLastName() {
		return lastName;
	}

	/* lastName setter */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/* role getter */
	public String getRole() {
		return role;
	}

	/* role setter */
	public void setRole(String role) {
		this.role = role;
	}

	/* department getter */
	public String getDepartment() {
		return department;
	}

	/* department setter */
	public void setDepartment(String department) {
		this.department = department;
	}

	/* date-of-birth getter */
	public LocalDate getDob() {
		return dob;
	}

	/* date-of-birth setter */
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	private void setUID() {
		this.UID = EmployeeUtils.generateUniqueIdentityNumber(this.firstName, this.lastName);
	}

	/* UID getter */
	public int getUID() {
		return UID;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getDomain() {
		return this.domain;
	}

	public void setEmailAddress() {
		this.emailAddress = EmployeeUtils.generateEmail(this.firstName, this.lastName, this.domain);
	}

	public String getEmailAddress() {
		return this.emailAddress;
	}

	@Override
	public String toString() {
		return String.format(
				"%s %s in the role of %s in %s was born on %s who's employee ID is %d and email address is %s",
				this.firstName,
				this.lastName,
				this.role,
				this.department,
				this.dob.toString(),
				this.getUID(),
				this.getEmailAddress());
	}
}
