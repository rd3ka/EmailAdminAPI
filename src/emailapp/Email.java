package emailapp;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import passwordGenerator.passGen;
import userType.Employee;

class Email {
	Employee emp;

	/* Use generated fields */
	private String password, emailAddress;
	private char gender;
	private LocalDate dateOfBirth;
	//private int targetLength = 8;

	public Email(char gender, String dob, Employee emp) {
		String[] dateOfBirthArray = dob.split("\\.");
		dob = new String();

		/* Converting date standard ISO LocalDate format to-be parsed */
		for (int i = dateOfBirthArray.length - 1; i >= 0; i--)
			dob += "-".concat(dateOfBirthArray[i]);

		dob = new StringBuilder(dob).deleteCharAt(0).toString();

		this.dateOfBirth = LocalDate.parse(dob);
		this.gender = gender;
		this.emp = emp;
	}

	/* to-do */
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
}
