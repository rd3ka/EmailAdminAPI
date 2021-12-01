package userType;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import DateValidator.*;

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

		if (dateValidation(dateOfBirth))
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


	private boolean dateValidation(String date) {
		DateTimeFormatter dtf = DateTimeFormatter.BASIC_ISO_DATE;
		DateValidator validate = new DateValidatorUsingLocalDate(dtf);
		return validate.isValid(date);
	}

	/* To LocalDate Parser */
	private LocalDate parseToLocalDate(String date) {
		/* Spliting String with the '.' delimiter */
		String dateSplit[] = date.split("\\.");
		date = new String();

		/* converting to LocalDate format to be parsed */
		for (int i = dateSplit.length - 1; i >= 0; i--)
			date += "-".concat(dateSplit[i]);

		/* Deleteing the first extra '-' */
		date = new StringBuilder(date).deleteCharAt(0).toString();
		return LocalDate.parse(date);
	}
}
