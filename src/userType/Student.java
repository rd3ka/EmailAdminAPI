package userType;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class Student extends User {
    private String institutionName, department;
    private boolean CollgeFresher;
    private double X, XII;
    private int rollNumber;
    private char section;
    private String CourseName;

    public Student(String firstName,
            String lastName,
            String address,
            String nationality,
            LocalDate dateOfBirth,
            String institutionName, boolean CollgeFresher, int rollNumber, char section) {

        super(firstName, lastName, address, nationality, dateOfBirth);

        this.institutionName = institutionName;
        this.CollgeFresher = CollgeFresher;
        this.rollNumber = rollNumber;
        this.section = section;

        /* ToDo! - Selection Menu Looping */
        String ret = System.console()
                .printf("Do you want to provide additional Information? y/n \n")
                .readLine();

        if (ret.equals("y")) {

            /* BiFunction to map command with Description for better input */
            BiFunction<String, String, String> commandToDescription = (cmd, des) -> cmd;
            /* Mapping Strings with Runnable calls */
            Map<String, Runnable> parentMap = new HashMap<>();

            parentMap.put(commandToDescription.apply("sD", "sD -> Set Description"), () -> setDepartment());
            parentMap.put(commandToDescription.apply("sX", "sX -> set 10 Marks"), () -> setX());
            parentMap.put(commandToDescription.apply("sXII", "sXII -> set 12 Marks"), () -> setXII());
            parentMap.put(commandToDescription.apply("rN", "rN -> set RollNumber"), () -> setRollNumber());
            parentMap.put(commandToDescription.apply("cN", "cN -> set Course Name"), () -> setCourseName());
            parentMap.put(commandToDescription.apply("iN", "iN -> set Institution Name"), () -> setInstitutionName());
            parentMap.put(commandToDescription.apply("sec", "sec-> set Section Name"), () -> setSection());

            /* Calling the specific function to set/modify changes */
            parentMap.get(System.console().printf("Type your choice : ").readLine()).run();

        }

    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment() {
        if (this.CollgeFresher)
            this.department = System.console().readLine();
    }

    public double getX() {
        return X;
    }

    public void setX() {
        if (this.CollgeFresher)
            this.X = Double.parseDouble(System.console().readLine());
    }

    public double getXII() {
        return this.XII;
    }

    public void setXII() {
        this.XII = Double.parseDouble(System.console().readLine());
    }

    public String getCourseName() {
        return CourseName;
    }

    public void setCourseName() {
        CourseName = System.console().readLine();
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName() {
        this.institutionName = System.console().readLine();
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber() {
        this.rollNumber = Integer.parseInt(System.console().readLine());
    }

    public char getSection() {
        return section;
    }

    public void setSection() {
        this.section = System.console().readLine().charAt(0);
    }
}
