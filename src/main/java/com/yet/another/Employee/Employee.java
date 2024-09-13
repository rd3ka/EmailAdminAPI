package com.yet.another.Employee;

import java.time.LocalDate;

public class Employee {
	private String firstName, lastName;
	private String role, department, domain;
	private LocalDate dob;
	private int uid;

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
		this.uid = new String(this.firstName + this.lastName + this.dob).hashCode() & 0xfffffff;
		this.domain = domain;
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
		this.uid = new String(this.firstName + this.lastName + this.dob).hashCode() & 0xffffff;
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

	/* uid getter */
	public int getUid() {
		return uid;
	}

	/* email getter/generator */
	public String getEmail() {
		return String.format("%s.%s@%s.com", this.lastName.toLowerCase(),
				this.firstName.toLowerCase(),
				this.domain.replaceAll(" ", "").toLowerCase());
	}
}
