package userType;

import java.time.LocalDate;

public class User {

    private String firstName,
            lastName,
            address,
            nationality;

    private LocalDate dateOfBirth;

    public User(String firstName,
            String lastName,
            String dateOfBirth) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = parseToLocalDate(dateOfBirth);

        if (System.console().printf("Would you like to provide additional info? y/N : ")
                .readLine()
                .equalsIgnoreCase("y")) {
            
            this.address = System.console().printf("Enter address : ").readLine();
            this.nationality = System.console().printf("Enter nationality : ").readLine();
        }
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDate parseToLocalDate(String str) {
        String strl[] = str.split("\\.");
        str = new String();
        for (int i = strl.length - 1; i >= 0; i--)
            str += "-".concat(strl[i]);
        str = new StringBuilder(str).deleteCharAt(0).toString();
        return LocalDate.parse(str);
    }
}
