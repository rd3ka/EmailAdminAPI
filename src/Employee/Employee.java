package Employee;

import java.time.LocalDate;

public class Employee {
    private String firstName, lastName;
    private String role, department;
    private LocalDate dob;
    private final int uid;

    public Employee(String firstName, String lastName, LocalDate dob) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.role = " ";
        this.department = " ";
        this.uid = new String(this.firstName + this.lastName + this.dob).hashCode() & 0xfffffff;
    }

    public Employee(String firstName, String lastName, String role, String department, LocalDate dob, int uid) {
        this(firstName, lastName, dob);
        this.role = role;
        this.department = department;
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

    public int getUid() {
        return uid;
    }
}


