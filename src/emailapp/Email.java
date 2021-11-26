package emailapp;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
class Email
{
	Employee emp;

	/* Use generated fields */
	private String password, emailAddress;
	private char gender;
	private LocalDate dateOfBirth;
	private int targetLength = 8;
	
	public Email(char gender, String dob, Employee emp) {
		String[] dateOfBirthAr = dob.split("\\.");
		dob = new String();
		
		/* Reversing Date Of Birth to Parse into ISO Standard LocalDate */
		for(int i = dateOfBirthAr.length - 1; i >= 0; i--)
			dob += "-" + dateOfBirthAr[i];
		
		dob = new StringBuilder(dob).deleteCharAt(0).toString();

		this.dateOfBirth = LocalDate.parse(dob);
		this.gender = gender;
		this.emp = emp;
	}

	public String getPassword(String...args) {
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
		int lowerLimit = 'a';
		int upperLimit = 'z';
		Random rand = new Random();
		this.password = rand.ints(lowerLimit, upperLimit + 1)
							.limit(this.targetLength)
							.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
							.toString();
	}

	public void changePassword() {
		System.console().printf("%s", "Enter Password : ");
		this.password = new String(System.console().readPassword());
	}

	public String getEmailAddress() {
		return this.emailAddress;
		
	}

	public void setEmailAddress(int index, String... args) {
		Function <List <String> ,String> init = word -> word.stream()
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