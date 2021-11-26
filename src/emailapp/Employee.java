package emailapp;

import java.util.Random;

public class Employee {

    private String firstName,lastName,department;
    private int tenure,salary;
    private long identificationNum;

    public Employee(String firstName, String lastName, String department, int tenure) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.tenure = tenure;
        setIdentificationNum();
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
        Random rand = new Random();
        this.identificationNum = 1200000 + rand.nextInt(9000);
    }

    @Override
    public String toString() {
        return String.format("%s %s %d %d %d",this.firstName,
                                              this.lastName,
                                              this.salary,
                                              this.identificationNum,
                                              this.tenure); 
    }    
}
