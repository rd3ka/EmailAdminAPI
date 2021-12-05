package EmailApp;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import PasswordGenerator.*;
import UserType.Employee;

class Email {
	Employee emp;

	private String password, emailAddress;
	private char gender;
	private LocalDate dateOfBirth;

	public Email(char gender, String dob, Employee emp) {
		this.dateOfBirth = parseToLocalDate(dob);
		this.gender = gender;
		this.emp = emp;
	}

	public String getPassword(String... args) {
		if (this.password == null)
			setPassword();
		return this.password;
	}

	public char getGender() {
		return this.gender;
	}

	public LocalDate getDateOfBirth() {
		return this.dateOfBirth;
	}

	public void setPassword() {
		passGen pwdGenerate = new passGen();
		this.password = pwdGenerate.generateRandomSecurePassword();
	}

	public void changePassword() {
		System.console().printf("%s", "Enter Password : ");
		this.password = new String(System.console().readPassword());
	}

	public String getEmailAddress() {
		return this.emailAddress;

	}

	public void setEmailAddress(int index, String... args) {
		/* A Functional Interface to Get Initials of any Sentence or Word */
		Function<List<String>, String> init = word -> word.stream()
		                                      .map(e -> e.charAt(0))
		                                      .collect(StringBuilder::new,
		                                              StringBuilder::appendCodePoint,
		                                              StringBuilder::append)
		                                      .toString();
		String prefix = emp.getFirstName().toLowerCase();
		String postfix = emp.getDepartment().toLowerCase();
		this.emailAddress = prefix
		                    + '.'
		                    + init.apply(Arrays.asList(postfix.split(" ")))
		                    + "@"
		                    + init.apply(Arrays.asList(args[index].toLowerCase().split(" ")))
		                    + "."
		                    + "com";
	}

	public int getEmpHash() {
		return emp.hashCode();
	}

	public void setEmp(Employee emp) {
		this.emp = emp;
	}

	private LocalDate parseToLocalDate(String date) {
		/* Splitting String using the '.' delimiter */
		String[] dateArray = date.split("\\.");
		date = new String();

		/* Converting the date into Standard ISO LocalDate format */
		for (int i = dateArray.length - 1; i >= 0; i--)
			date += "-".concat(dateArray[i]);

		date = new StringBuilder(date).deleteCharAt(0).toString();
		/* parsing in LocalDate */
		return LocalDate.parse(date);
	}
}
