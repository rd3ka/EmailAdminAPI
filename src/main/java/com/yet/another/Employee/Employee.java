package com.yet.another.Employee;

import java.time.LocalDate;

/**
 * 
 */
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

    /**
     * @param firstName
     * @param lastName
     * @param dob
     * @param domain
     */
    public Employee(final String firstName, final String lastName, final LocalDate dob, final String domain) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.role = " ";
        this.department = " ";
        this.domain = domain;
        this.UID = EmployeeUtils.generateUniqueIdentityNumber(this.firstName, this.lastName, this.dob);
        this.emailAddress = EmployeeUtils.generateEmail(this.firstName, this.lastName, this.domain);
    }

    /**
     * @param firstName
     * @param lastName
     * @param role
     * @param department
     * @param dob
     * @param domain
     */
    public Employee(final String firstName, final String lastName, final String role, final String department, final LocalDate dob, final String domain) {
        this(firstName, lastName, dob, domain);
        this.role = role;
        this.department = department;
        this.UID = EmployeeUtils.generateUniqueIdentityNumber(this.firstName, this.lastName, this.dob);
        this.emailAddress = EmployeeUtils.generateEmail(this.firstName, this.lastName, this.domain);
    }

    /* firstName getter */
    public String getFirstName() {
        return firstName;
    }

    /* firstName setter */
    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    /* lastName getter */
    public String getLastName() {
        return lastName;
    }

    /* lastName setter */
    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    /* role getter */
    public String getRole() {
        return role;
    }

    /* role setter */
    public void setRole(final String role) {
        this.role = role;
    }

    /* department getter */
    public String getDepartment() {
        return department;
    }

    /* department setter */
    public void setDepartment(final String department) {
        this.department = department;
    }

    /* date-of-birth getter */
    public LocalDate getDob() {
        return dob;
    }

    /* date-of-birth setter */
    public void setDob(final LocalDate dob) {
        this.dob = dob;
    }

    /* UID getter */
    public int getUID() {
        return UID;
    }

    public void setDomain(final String domain) {
        this.domain = domain;
    }
    
    public String getDomain() {
        return this.domain;
    }

    public String getEmailAddress() {
        return this.emailAddress;
    }

    @Override
    public String toString() {
        this.UID = this.UID == 0 ? EmployeeUtils.generateUniqueIdentityNumber(this.firstName, this.lastName, this.dob) : 0;
        this.emailAddress = this.emailAddress == null ? EmployeeUtils.generateEmail(this.firstName, this.lastName, this.domain) : "N/A";
        return String.format(
                "%s %s in the role of %s in %s was born on %s who's employee ID is %d and email address is %s",
                this.firstName,
                this.lastName,
                this.role,
                this.department,
                this.dob.toString(),
                this.UID,
                this.emailAddress);
    }
}
