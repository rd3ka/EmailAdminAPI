package userType;

public class Student extends User implements cmdMapToDesc {
    private String institutionName, department;
    private boolean CollgeFresher;
    private double X, XII;
    private int rollNumber;
    private char section;
    private String CourseName;

    public Student(String firstName,
            String lastName,
            String dateOfBirth,
            String institutionName, boolean CollgeFresher, int rollNumber, char section) {

        super(firstName, lastName, dateOfBirth);

        this.institutionName = institutionName;
        this.CollgeFresher = CollgeFresher;
        this.rollNumber = rollNumber;
        this.section = section;

        if (System.console().printf("Do you want to provide additional info, y/N?")
                .readLine()
                .equalsIgnoreCase("y")) {

            FunctionMapping();
            
            do {
                cmdMap.get(System.console().printf("Enter choice : ").readLine()).run();
            } while (System.console().printf("Do you want to continue? y/N")
                    .readLine()
                    .equalsIgnoreCase("y"));
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

    @Override
    public void FunctionMapping() {
        cmdMap.put(commandToDescription.apply("sD", "sD -> Set Description"), () -> setDepartment());
        cmdMap.put(commandToDescription.apply("sX", "sX -> set 10 Marks"), () -> setX());
        cmdMap.put(commandToDescription.apply("sXII", "sXII -> set 12 Marks"), () -> setXII());
        cmdMap.put(commandToDescription.apply("rN", "rN -> set RollNumber"), () -> setRollNumber());
        cmdMap.put(commandToDescription.apply("cN", "cN -> set Course Name"), () -> setCourseName());
        cmdMap.put(commandToDescription.apply("iN", "iN -> set Institution Name"), () -> setInstitutionName());
        cmdMap.put(commandToDescription.apply("sec", "sec-> set Section Name"), () -> setSection());
    }
}
