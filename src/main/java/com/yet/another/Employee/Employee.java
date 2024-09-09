package com.yet.another.Employee;

import java.time.LocalDate;

public class Employee {
	private String firstName, lastName;
	private String role, department, domain;
	private LocalDate dob;
	private int uid;

	public Employee(String firstName, String lastName, LocalDate dob, String domain) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.role = " ";
		this.department = " ";
		this.uid = new String(this.firstName + this.lastName + this.dob).hashCode() & 0xfffffff;
		this.domain = domain;
	}

	public Employee(String firstName, String lastName, String role, String department, LocalDate dob, String domain) {
		this(firstName, lastName, dob, domain);
		this.role = role;
		this.department = department;
		this.uid = new String(this.firstName + this.lastName + this.dob).hashCode() & 0xffffff;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getEmail() {
		return this.lastName.toLowerCase() + "." + this.firstName.toLowerCase() + "@"
				+ this.domain.replaceAll(" ", "").toLowerCase() + ".com";
	}

	public int getUid() {
		return uid;
	}
}
